# Multi-stage Dockerfile for Java Selenium Test Framework
# Equivalent to Python project's Docker setup

# ═══════════════════════════════════════════════════════════════════
# Stage 1: Build
# ═══════════════════════════════════════════════════════════════════
FROM maven:3.9-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy pom.xml first for dependency caching
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the project (skip tests during build)
RUN mvn clean package -DskipTests -q

# ═══════════════════════════════════════════════════════════════════
# Stage 2: Test Runner
# ═══════════════════════════════════════════════════════════════════
FROM maven:3.9-eclipse-temurin-17

# Install Chrome for Selenium tests
RUN apt-get update && apt-get install -y \
    wget \
    gnupg \
    unzip \
    && wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
    && echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list \
    && apt-get update \
    && apt-get install -y google-chrome-stable \
    && rm -rf /var/lib/apt/lists/*

# Install Playwright dependencies
RUN npx playwright install-deps chromium firefox webkit || true

WORKDIR /app

# Copy from builder
COPY --from=builder /app/pom.xml .
COPY --from=builder /app/target ./target
COPY --from=builder /root/.m2 /root/.m2
COPY src ./src
COPY scripts ./scripts

# Create directories for test artifacts
RUN mkdir -p screenshots test_results reports

# Environment variables
ENV HEADLESS=true
ENV BROWSER=chrome
ENV MAVEN_OPTS="-Xmx1024m"

# Default command: run all tests
CMD ["mvn", "test", "-Dheadless=true"]


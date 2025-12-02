#!/usr/bin/env bash
# setup_env.sh
# Setup Java environment and download all Maven dependencies
set -e

REQUIRED_JAVA_VERSION="17"

echo "╔══════════════════════════════════════════════════════════════╗"
echo "║         Java Selenium Project - Environment Setup            ║"
echo "╚══════════════════════════════════════════════════════════════╝"
echo ""

# ═══════════════════════════════════════════════════════════════════
# 1. Check Java Installation
# ═══════════════════════════════════════════════════════════════════
echo "[SETUP] Checking Java installation..."

if ! command -v java &> /dev/null; then
    echo "[ERROR] Java is not installed!"
    echo "        Please install Java $REQUIRED_JAVA_VERSION or higher."
    echo "        Download from: https://adoptium.net/"
    exit 1
fi

# Get Java version
JAVA_VERSION=$(java -version 2>&1 | head -n 1 | cut -d'"' -f2 | cut -d'.' -f1)

if [ "$JAVA_VERSION" -lt "$REQUIRED_JAVA_VERSION" ]; then
    echo "[ERROR] Java $REQUIRED_JAVA_VERSION or higher is required."
    echo "        Current version: $JAVA_VERSION"
    echo ""
    echo "        On macOS with multiple Java versions:"
    echo "        export JAVA_HOME=\$(/usr/libexec/java_home -v $REQUIRED_JAVA_VERSION)"
    exit 1
fi

echo "[OK] Java $JAVA_VERSION detected."

# ═══════════════════════════════════════════════════════════════════
# 2. Check Maven Installation
# ═══════════════════════════════════════════════════════════════════
echo ""
echo "[SETUP] Checking Maven installation..."

if ! command -v mvn &> /dev/null; then
    echo "[ERROR] Maven is not installed!"
    echo "        Please install Maven 3.8+."
    echo "        macOS: brew install maven"
    echo "        Linux: sudo apt install maven"
    exit 1
fi

MVN_VERSION=$(mvn -version 2>&1 | head -n 1 | grep -oE '[0-9]+\.[0-9]+\.[0-9]+')
echo "[OK] Maven $MVN_VERSION detected."

# ═══════════════════════════════════════════════════════════════════
# 3. Download Dependencies
# ═══════════════════════════════════════════════════════════════════
echo ""
echo "[SETUP] Downloading Maven dependencies..."
echo "        (This may take a few minutes on first run)"
echo ""

mvn dependency:resolve -q
mvn dependency:resolve-plugins -q

echo "[OK] All dependencies downloaded."

# ═══════════════════════════════════════════════════════════════════
# 4. Compile Project
# ═══════════════════════════════════════════════════════════════════
echo ""
echo "[SETUP] Compiling project..."

mvn compile test-compile -q

echo "[OK] Project compiled successfully."

# ═══════════════════════════════════════════════════════════════════
# 5. Verify Setup with Quick Test
# ═══════════════════════════════════════════════════════════════════
echo ""
echo "[SETUP] Running quick verification test..."

mvn test -Dtest="ConstantsTest" -q 2>/dev/null

echo "[OK] Verification test passed."

# ═══════════════════════════════════════════════════════════════════
# 6. Create directories if needed
# ═══════════════════════════════════════════════════════════════════
mkdir -p logs
mkdir -p target/screenshots
mkdir -p allure-results

# ═══════════════════════════════════════════════════════════════════
# Summary
# ═══════════════════════════════════════════════════════════════════
echo ""
echo "╔══════════════════════════════════════════════════════════════╗"
echo "║                    Setup Complete! ✓                         ║"
echo "╚══════════════════════════════════════════════════════════════╝"
echo ""
echo "Quick Start Commands:"
echo "  mvn test -Dtest=\"**/unit/*Test\"        # Run unit tests"
echo "  mvn test -Dtest=\"**/api/*Test\"         # Run API tests"
echo "  mvn test -Dtest=\"**/web/*Test\"         # Run Selenium UI tests"
echo "  mvn test -Dtest=\"PlaywrightSauceDemoTest\"  # Run Playwright tests"
echo ""
echo "For headless browser tests:"
echo "  mvn test -Dtest=\"**/web/*Test\" -DHEADLESS=true"
echo ""


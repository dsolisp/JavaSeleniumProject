@echo off
REM setup_env.bat
REM Setup Java environment and download all Maven dependencies (Windows)
setlocal enabledelayedexpansion

echo ================================================================
echo          Java Selenium Project - Environment Setup
echo ================================================================
echo.

REM ═══════════════════════════════════════════════════════════════════
REM 1. Check Java Installation
REM ═══════════════════════════════════════════════════════════════════
echo [SETUP] Checking Java installation...

where java >nul 2>nul
if %ERRORLEVEL% neq 0 (
    echo [ERROR] Java is not installed!
    echo         Please install Java 21 or higher.
    echo         Download from: https://adoptium.net/
    exit /b 1
)

for /f "tokens=3" %%g in ('java -version 2^>^&1 ^| findstr /i "version"') do (
    set JAVA_VERSION=%%g
)
set JAVA_VERSION=%JAVA_VERSION:"=%
for /f "delims=. tokens=1" %%a in ("%JAVA_VERSION%") do set JAVA_MAJOR=%%a

if %JAVA_MAJOR% LSS 21 (
    echo [ERROR] Java 21 or higher is required.
    echo         Current version: %JAVA_VERSION%
    exit /b 1
)

echo [OK] Java %JAVA_VERSION% detected.

REM ═══════════════════════════════════════════════════════════════════
REM 2. Check Maven Installation
REM ═══════════════════════════════════════════════════════════════════
echo.
echo [SETUP] Checking Maven installation...

where mvn >nul 2>nul
if %ERRORLEVEL% neq 0 (
    echo [ERROR] Maven is not installed!
    echo         Please install Maven 3.8+.
    echo         Download from: https://maven.apache.org/download.cgi
    exit /b 1
)

for /f "tokens=3" %%g in ('mvn -version 2^>^&1 ^| findstr /i "Apache Maven"') do (
    set MVN_VERSION=%%g
)
echo [OK] Maven %MVN_VERSION% detected.

REM ═══════════════════════════════════════════════════════════════════
REM 3. Download Dependencies
REM ═══════════════════════════════════════════════════════════════════
echo.
echo [SETUP] Downloading Maven dependencies...
echo         (This may take a few minutes on first run)
echo.

call mvn dependency:resolve -q
if %ERRORLEVEL% neq 0 (
    echo [ERROR] Failed to download dependencies.
    exit /b 1
)
call mvn dependency:resolve-plugins -q

echo [OK] All dependencies downloaded.

REM ═══════════════════════════════════════════════════════════════════
REM 4. Compile Project
REM ═══════════════════════════════════════════════════════════════════
echo.
echo [SETUP] Compiling project...

call mvn compile test-compile -q
if %ERRORLEVEL% neq 0 (
    echo [ERROR] Compilation failed.
    exit /b 1
)

echo [OK] Project compiled successfully.

REM ═══════════════════════════════════════════════════════════════════
REM 5. Verify Setup with Quick Test
REM ═══════════════════════════════════════════════════════════════════
echo.
echo [SETUP] Running quick verification test...

call mvn test -Dtest="ConstantsTest" -q 2>nul
if %ERRORLEVEL% neq 0 (
    echo [WARN] Verification test had issues, but setup may still be OK.
) else (
    echo [OK] Verification test passed.
)

REM ═══════════════════════════════════════════════════════════════════
REM 6. Create directories if needed
REM ═══════════════════════════════════════════════════════════════════
if not exist "logs" mkdir logs
if not exist "target\screenshots" mkdir target\screenshots
if not exist "allure-results" mkdir allure-results

REM ═══════════════════════════════════════════════════════════════════
REM Summary
REM ═══════════════════════════════════════════════════════════════════
echo.
echo ================================================================
echo                     Setup Complete!
echo ================================================================
echo.
echo Quick Start Commands:
echo   mvn test -Dtest="**/unit/*Test"            # Run unit tests
echo   mvn test -Dtest="**/api/*Test"             # Run API tests
echo   mvn test -Dtest="**/web/*Test"             # Run Selenium UI tests
echo   mvn test -Dtest="PlaywrightSauceDemoTest"  # Run Playwright tests
echo.
echo For headless browser tests:
echo   mvn test -Dtest="**/web/*Test" -DHEADLESS=true
echo.

endlocal


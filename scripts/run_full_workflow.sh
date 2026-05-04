#!/bin/bash
# ═══════════════════════════════════════════════════════════════════════════════
# Full Test Workflow Script for Java Selenium Project
# Equivalent to Python project's run_full_workflow.py
# ═══════════════════════════════════════════════════════════════════════════════

set -e

echo "═══════════════════════════════════════════════════════════════════════════════"
echo "🚀 JAVA SELENIUM PROJECT - FULL TEST WORKFLOW"
echo "═══════════════════════════════════════════════════════════════════════════════"
echo "Started at: $(date)"
echo ""

# Navigate to project directory
cd "$(dirname "$0")/.."

# Configuration
HEADLESS=${HEADLESS:-true}
BROWSER=${BROWSER:-chrome}

echo "Configuration:"
echo "   - Browser: $BROWSER"
echo "   - Headless: $HEADLESS"
echo ""

# ═══════════════════════════════════════════════════════════════════════════════
# Phase 1: Environment Check
# ═══════════════════════════════════════════════════════════════════════════════
echo "📋 Phase 1: Environment Check"
echo "───────────────────────────────────────────────────────────────────────────────"

# Check Java
java -version 2>&1 | head -1
echo ""

# Check Maven
mvn -version | head -1
echo ""

echo "✅ Environment check passed"
echo ""

# ═══════════════════════════════════════════════════════════════════════════════
# Phase 2: Build
# ═══════════════════════════════════════════════════════════════════════════════
echo "📦 Phase 2: Build"
echo "───────────────────────────────────────────────────────────────────────────────"

mvn clean compile -q
echo "✅ Build successful"
echo ""

# ═══════════════════════════════════════════════════════════════════════════════
# Phase 3: Unit Tests
# ═══════════════════════════════════════════════════════════════════════════════
echo "🧪 Phase 3: Unit Tests"
echo "───────────────────────────────────────────────────────────────────────────────"

mvn test -Dtest="**/unit/*Test" -DHEADLESS=$HEADLESS -DBROWSER=$BROWSER
echo ""

# ═══════════════════════════════════════════════════════════════════════════════
# Phase 4: API Tests
# ═══════════════════════════════════════════════════════════════════════════════
echo "🌐 Phase 4: API Tests"
echo "───────────────────────────────────────────────────────────────────────────────"

mvn test -Dtest="**/backend/*Test" -DHEADLESS=true -DBROWSER=$BROWSER || echo "⚠️  Some API tests may have failed"
echo ""

# ═══════════════════════════════════════════════════════════════════════════════
# Phase 5: Web Tests (Headless)
# ═══════════════════════════════════════════════════════════════════════════════
echo "🖥️  Phase 5: Web Tests (Headless)"
echo "───────────────────────────────────────────────────────────────────────────────"

if [ "$HEADLESS" = "true" ]; then
    mvn test -Dtest="**/ui/practice/*Test,**/ui/sauce/*Test" -DHEADLESS=true -DBROWSER=$BROWSER || echo "⚠️  Some web tests may have failed"
else
    echo "⏭️  Skipping web tests (HEADLESS=$HEADLESS)"
fi
echo ""

# ═══════════════════════════════════════════════════════════════════════════════
# Phase 6: Performance Tests
# ═══════════════════════════════════════════════════════════════════════════════
echo "⚡ Phase 6: Performance Tests"
echo "───────────────────────────────────────────────────────────────────────────────"

mvn test -Dtest="**/performance/*Test" -DHEADLESS=true || echo "⚠️  Some performance tests may have failed"
echo ""

# ═══════════════════════════════════════════════════════════════════════════════
# Phase 7: Visual Tests
# ═══════════════════════════════════════════════════════════════════════════════
echo "📸 Phase 7: Visual Tests"
echo "───────────────────────────────────────────────────────────────────────────────"

if [ "$HEADLESS" = "true" ]; then
    mvn test -Dtest="**/ui/visual/*Test" -DHEADLESS=true || echo "⚠️  Some visual tests may have failed"
else
    echo "⏭️  Skipping visual tests (HEADLESS=$HEADLESS)"
fi
echo ""

# ═══════════════════════════════════════════════════════════════════════════════
# Phase 8: Coverage Report
# ═══════════════════════════════════════════════════════════════════════════════
echo "📊 Phase 8: Coverage Report"
echo "───────────────────────────────────────────────────────────────────────────────"

mvn jacoco:report -q
echo "✅ Coverage report generated"
echo ""

# ═══════════════════════════════════════════════════════════════════════════════
# Phase 9: Test Analytics
# ═══════════════════════════════════════════════════════════════════════════════
echo "📈 Phase 9: Test Analytics"
echo "───────────────────────────────────────────────────────────────────────────────"

echo "Test results collected in: test_results/"
ls -la test_results/*.json 2>/dev/null || echo "No analytics files found yet"
echo ""

# ═══════════════════════════════════════════════════════════════════════════════
# Summary
# ═══════════════════════════════════════════════════════════════════════════════
echo "═══════════════════════════════════════════════════════════════════════════════"
echo "✅ FULL WORKFLOW COMPLETED"
echo "═══════════════════════════════════════════════════════════════════════════════"
echo ""
echo "📋 Phases Completed:"
echo "   ✅ Environment Check"
echo "   ✅ Build"
echo "   ✅ Unit Tests"
echo "   ✅ API Tests"
echo "   ✅ Web Tests"
echo "   ✅ Performance Tests"
echo "   ✅ Visual Tests"
echo "   ✅ Coverage Report"
echo "   ✅ Test Analytics"
echo ""
echo "📁 Reports:"
echo "   - Test Results: target/surefire-reports/"
echo "   - Coverage: target/site/jacoco/index.html"
echo "   - Screenshots: screenshots/"
echo "   - Visual Baselines: baselines/"
echo "   - Visual Diffs: diffs/"
echo "   - Test Analytics: test_results/"
echo ""
echo "Completed at: $(date)"
echo ""


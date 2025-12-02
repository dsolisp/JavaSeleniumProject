package com.automation.unit;

import com.automation.utils.PerformanceMonitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for PerformanceMonitor.
 */
@DisplayName("PerformanceMonitor Tests")
class PerformanceMonitorTest {

    private PerformanceMonitor monitor;

    @BeforeEach
    void setUp() {
        monitor = new PerformanceMonitor("TestMonitor");
    }

    @Test
    @DisplayName("Should time operation and record duration")
    void shouldTimeOperationAndRecordDuration() {
        var result = monitor.timeOperation("test_operation", () -> {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "result";
        });
        
        assertThat(result.result()).isEqualTo("result");
        assertThat(result.durationMs()).isGreaterThanOrEqualTo(50);
    }

    @Test
    @DisplayName("Should time void operation")
    void shouldTimeVoidOperation() {
        long duration = monitor.timeOperation("void_operation", () -> {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        assertThat(duration).isGreaterThanOrEqualTo(50);
    }

    @Test
    @DisplayName("Should record and retrieve metric statistics")
    void shouldRecordAndRetrieveMetricStatistics() {
        monitor.recordMetric("test_metric", 100);
        monitor.recordMetric("test_metric", 200);
        monitor.recordMetric("test_metric", 150);
        
        PerformanceMonitor.MetricStats stats = monitor.getStats("test_metric");
        
        assertThat(stats.count()).isEqualTo(3);
        assertThat(stats.mean()).isEqualTo(150.0);
        assertThat(stats.median()).isEqualTo(150);
        assertThat(stats.min()).isEqualTo(100);
        assertThat(stats.max()).isEqualTo(200);
    }

    @Test
    @DisplayName("Should return empty stats for unknown metric")
    void shouldReturnEmptyStatsForUnknownMetric() {
        PerformanceMonitor.MetricStats stats = monitor.getStats("unknown_metric");
        
        assertThat(stats.count()).isEqualTo(0);
        assertThat(stats.mean()).isEqualTo(0);
    }

    @Test
    @DisplayName("Should get memory info")
    void shouldGetMemoryInfo() {
        PerformanceMonitor.MemoryInfo memoryInfo = monitor.getMemoryInfo();
        
        assertThat(memoryInfo.totalMb()).isGreaterThan(0);
        assertThat(memoryInfo.availableMb()).isGreaterThan(0);
        assertThat(memoryInfo.usedMb()).isGreaterThan(0);
        assertThat(memoryInfo.usedPercent()).isBetween(0.0, 100.0);
    }

    @Test
    @DisplayName("Should get system metrics")
    void shouldGetSystemMetrics() {
        PerformanceMonitor.SystemMetrics metrics = monitor.getSystemMetrics();
        
        assertThat(metrics.cpuCount()).isGreaterThan(0);
        assertThat(metrics.totalMemoryMb()).isGreaterThan(0);
        assertThat(metrics.memoryUsedPercent()).isBetween(0.0, 100.0);
    }

    @Test
    @DisplayName("Should generate performance report")
    void shouldGeneratePerformanceReport() {
        monitor.recordMetric("metric1", 100);
        monitor.recordMetric("metric2", 200);
        
        var report = monitor.generateReport();
        
        assertThat(report).containsKeys("metric1", "metric2");
    }

    @Test
    @DisplayName("Should clear metrics")
    void shouldClearMetrics() {
        monitor.recordMetric("test_metric", 100);
        monitor.clearMetrics();
        
        var report = monitor.generateReport();
        
        assertThat(report).isEmpty();
    }

    @Test
    @DisplayName("Should have default thresholds defined")
    void shouldHaveDefaultThresholdsDefined() {
        assertThat(PerformanceMonitor.DEFAULT_PAGE_LOAD_THRESHOLD_MS).isEqualTo(3000);
        assertThat(PerformanceMonitor.DEFAULT_API_RESPONSE_THRESHOLD_MS).isEqualTo(2000);
        assertThat(PerformanceMonitor.DEFAULT_ELEMENT_FIND_THRESHOLD_MS).isEqualTo(500);
    }
}


package com.automation.utils;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * Performance monitoring and benchmarking utility.
 * Equivalent to Python's utils/performance_monitor.py
 * 
 * Uses OSHI for system metrics (equivalent to psutil).
 */
public class PerformanceMonitor {

    private static final Logger logger = LoggerFactory.getLogger(PerformanceMonitor.class);

    // Thresholds (milliseconds)
    public static final long DEFAULT_PAGE_LOAD_THRESHOLD_MS = 3000;
    public static final long DEFAULT_API_RESPONSE_THRESHOLD_MS = 2000;
    public static final long DEFAULT_ELEMENT_FIND_THRESHOLD_MS = 500;

    private final String name;
    private final SystemInfo systemInfo;
    private final Map<String, List<Long>> metrics;

    public PerformanceMonitor(String name) {
        this.name = name;
        this.systemInfo = new SystemInfo();
        this.metrics = new ConcurrentHashMap<>();
    }

    /**
     * Time an operation and record the duration.
     */
    public <T> TimedResult<T> timeOperation(String operationName, Supplier<T> operation) {
        Instant start = Instant.now();
        T result = operation.get();
        long durationMs = Duration.between(start, Instant.now()).toMillis();
        
        recordMetric(operationName, durationMs);
        logger.debug("{}: {} completed in {}ms", name, operationName, durationMs);
        
        return new TimedResult<>(result, durationMs);
    }

    /**
     * Time a void operation.
     */
    public long timeOperation(String operationName, Runnable operation) {
        Instant start = Instant.now();
        operation.run();
        long durationMs = Duration.between(start, Instant.now()).toMillis();
        
        recordMetric(operationName, durationMs);
        logger.debug("{}: {} completed in {}ms", name, operationName, durationMs);
        
        return durationMs;
    }

    /**
     * Record a metric value.
     */
    public void recordMetric(String metricName, long value) {
        metrics.computeIfAbsent(metricName, k -> Collections.synchronizedList(new ArrayList<>()))
               .add(value);
    }

    /**
     * Get statistics for a metric.
     */
    public MetricStats getStats(String metricName) {
        List<Long> values = metrics.get(metricName);
        if (values == null || values.isEmpty()) {
            return new MetricStats(0, 0, 0, 0, 0, 0);
        }

        synchronized (values) {
            long count = values.size();
            double mean = values.stream().mapToLong(Long::longValue).average().orElse(0);
            long min = values.stream().mapToLong(Long::longValue).min().orElse(0);
            long max = values.stream().mapToLong(Long::longValue).max().orElse(0);
            
            List<Long> sorted = new ArrayList<>(values);
            Collections.sort(sorted);
            long median = sorted.get(sorted.size() / 2);
            
            double variance = values.stream()
                    .mapToDouble(v -> Math.pow(v - mean, 2))
                    .average().orElse(0);
            double stdDev = Math.sqrt(variance);

            return new MetricStats(count, mean, median, min, max, stdDev);
        }
    }

    /**
     * Get current system memory usage.
     */
    public MemoryInfo getMemoryInfo() {
        GlobalMemory memory = systemInfo.getHardware().getMemory();
        long total = memory.getTotal();
        long available = memory.getAvailable();
        long used = total - available;
        double usedPercent = (double) used / total * 100;

        return new MemoryInfo(
                bytesToMB(total),
                bytesToMB(available),
                bytesToMB(used),
                usedPercent
        );
    }

    /**
     * Get current CPU usage.
     */
    public double getCpuUsage() {
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100;
    }

    /**
     * Get system information.
     */
    public SystemMetrics getSystemMetrics() {
        MemoryInfo memory = getMemoryInfo();
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        
        return new SystemMetrics(
                processor.getLogicalProcessorCount(),
                memory.totalMb(),
                memory.usedPercent()
        );
    }

    /**
     * Generate performance report.
     */
    public Map<String, MetricStats> generateReport() {
        Map<String, MetricStats> report = new LinkedHashMap<>();
        metrics.keySet().forEach(key -> report.put(key, getStats(key)));
        return report;
    }

    /**
     * Clear all recorded metrics.
     */
    public void clearMetrics() {
        metrics.clear();
    }

    private double bytesToMB(long bytes) {
        return bytes / (1024.0 * 1024.0);
    }

    // Record classes for results
    public record TimedResult<T>(T result, long durationMs) {}

    public record MetricStats(long count, double mean, long median, long min, long max, double stdDev) {}

    public record MemoryInfo(double totalMb, double availableMb, double usedMb, double usedPercent) {}

    public record SystemMetrics(int cpuCount, double totalMemoryMb, double memoryUsedPercent) {}
}


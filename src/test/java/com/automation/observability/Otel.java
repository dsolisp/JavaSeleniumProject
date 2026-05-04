package com.automation.observability;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.BatchSpanProcessor;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.NonNull;

public final class Otel {
    private static volatile boolean configured = false;

    private Otel() {}

    @SuppressWarnings("null")
    public static synchronized void configure(String serviceName) {
        if (configured) return;

        String endpoint = System.getenv("OTEL_EXPORTER_OTLP_ENDPOINT"); // e.g. http://localhost:4317
        if (endpoint == null || endpoint.isBlank()) {
            configured = true; // no-op when endpoint missing
            return;
        }
        @NonNull String otlpEndpoint = endpoint;
        String rawHeaders = System.getenv("OTEL_EXPORTER_OTLP_HEADERS"); // "k1=v1,k2=v2"

        @NonNull Resource resource = Resource.getDefault().toBuilder()
                .put("service.name", serviceName)
                .build();

        @NonNull OtlpGrpcSpanExporter exporter = OtlpGrpcSpanExporter.builder()
                .setEndpoint(otlpEndpoint)
                .setTimeout(Duration.ofSeconds(5))
                .setHeaders(() -> parseHeaders(rawHeaders))
                .build();

        @NonNull BatchSpanProcessor processor = BatchSpanProcessor.builder(exporter).build();

        @NonNull SdkTracerProvider provider = SdkTracerProvider.builder()
                .setResource(resource)
                .addSpanProcessor(processor)
                .build();

        @NonNull OpenTelemetrySdk sdk = OpenTelemetrySdk.builder()
                .setTracerProvider(provider)
                .build();

        GlobalOpenTelemetry.set(sdk);
        configured = true;
    }

    public static Tracer tracer() {
        OpenTelemetry otel = GlobalOpenTelemetry.get();
        return otel.getTracer("java-tests");
    }

    private static Map<String, String> parseHeaders(String raw) {
        Map<String, String> out = new HashMap<>();
        if (raw == null || raw.isBlank()) return out;
        for (String part : raw.split(",")) {
            if (part == null) continue;
            String p = part.trim();
            if (p.isEmpty()) continue;
            int idx = p.indexOf('=');
            if (idx < 0) continue;
            String k = p.substring(0, idx).trim();
            String v = p.substring(idx + 1).trim();
            if (!k.isEmpty()) out.put(k, v);
        }
        return out;
    }
}


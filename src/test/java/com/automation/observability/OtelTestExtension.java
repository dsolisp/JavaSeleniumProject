package com.automation.observability;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class OtelTestExtension implements BeforeEachCallback, AfterEachCallback {
    private static final ExtensionContext.Namespace NS = ExtensionContext.Namespace.create(OtelTestExtension.class);

    @Override
    public void beforeEach(ExtensionContext context) {
        Otel.configure("JavaSeleniumProject");
        Tracer tracer = Otel.tracer();

        Span span = tracer.spanBuilder("test")
                .setAttribute("test.class", java.util.Objects.requireNonNull(context.getRequiredTestClass().getSimpleName()))
                .setAttribute("test.name", java.util.Objects.requireNonNull(context.getDisplayName()))
                .startSpan();

        Scope scope = span.makeCurrent();
        context.getStore(NS).put("span", span);
        context.getStore(NS).put("scope", scope);
    }

    @Override
    public void afterEach(ExtensionContext context) {
        Scope scope = context.getStore(NS).remove("scope", Scope.class);
        if (scope != null) scope.close();

        Span span = context.getStore(NS).remove("span", Span.class);
        if (span != null) span.end();
    }
}


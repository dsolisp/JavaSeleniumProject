package com.automation.observability;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public final class RestAssuredOtel {
    private RestAssuredOtel() {}

    public static Filter filter() {
        return new Filter() {
            @Override
            public Response filter(
                    FilterableRequestSpecification requestSpec,
                    FilterableResponseSpecification responseSpec,
                    FilterContext ctx
            ) {
                Otel.configure("JavaSeleniumProject");
                Tracer tracer = Otel.tracer();

                String method = java.util.Objects.requireNonNull(requestSpec.getMethod());
                String uri = java.util.Objects.requireNonNull(requestSpec.getURI());

                Span span = tracer.spanBuilder(method + " " + uri)
                        .setSpanKind(SpanKind.CLIENT)
                        .setAttribute("http.method", method)
                        .setAttribute("http.url", uri)
                        .startSpan();

                try (Scope scope = span.makeCurrent()) {
                    Response response = ctx.next(requestSpec, responseSpec);
                    int status = response.statusCode();
                    span.setAttribute("http.status_code", status);
                    if (status >= 400) span.setStatus(StatusCode.ERROR);
                    return response;
                } catch (Exception e) {
                    span.recordException(e);
                    span.setStatus(StatusCode.ERROR);
                    throw e;
                } finally {
                    span.end();
                }
            }
        };
    }
}


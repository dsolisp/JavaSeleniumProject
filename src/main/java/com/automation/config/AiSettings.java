package com.automation.config;

/**
 * AI-related settings (ADR-019). Loaded from environment; healing off by default in CI.
 */
public final class AiSettings {

    private AiSettings() {}

    public static boolean isHealingEnabled() {
        return "true".equalsIgnoreCase(System.getenv().getOrDefault("AI_HEALING_ENABLED", "false"));
    }

    public static int maxHealsPerRun() {
        return Integer.parseInt(System.getenv().getOrDefault("AI_MAX_HEALS_PER_RUN", "10"));
    }

    public static String cacheDir() {
        return System.getenv().getOrDefault("AI_HEAL_CACHE_DIR", ".ai-heal-cache");
    }

    public static String provider() {
        return System.getenv().getOrDefault("AI_PROVIDER", "ollama");
    }

    public static boolean isTriageEnabled() {
        return "true".equalsIgnoreCase(System.getenv().getOrDefault("AI_TRIAGE_ENABLED", "false"));
    }
}

# AI tooling (JavaSeleniumProject)

**ADR-019** · [Portfolio AI_ROADMAP](../../../shared-docs/docs/AI_ROADMAP.md)

## Healing

- `HealingLocator` in `com.automation.utils` — heuristic fallbacks at page/locator layer.
- Optional: `mvn test -Pai-heal` enables `com.headout:autoheal-locator` dependency.
- `AI_HEALING_ENABLED=true` only on nightly / local (never default PR CI).

## Env

Copy `.env.ai.example` to `.env.ai` for local runs.

## Flaky / triage

Use Python portfolio `pytest-flakefighters` pattern or export Allure + Ollama script (shared `scripts/ai_triage` pattern in Playwright repo).

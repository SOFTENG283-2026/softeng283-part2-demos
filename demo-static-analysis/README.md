# SOFTENG 283 — Static analysis demo

`BookingService` works and its tests pass, but PMD finds a lot of code quality issue.

We should make every PMD warning disappear by refactoring, while keeping all the tests green. One warning is a false positive (false alarms)

```
   ./mvnw test
   ./mvnw pmd:check
   ./mvnw pmd:cpd-check
```

Notes:
- Several violations can share one smell.
- Refactor, one smell at a time. After every step: run the tests, run PMD
- find the false positive.** One violation reports code that is fine as it is. Use `@SuppressWarnings("PMD.<RuleName>")`
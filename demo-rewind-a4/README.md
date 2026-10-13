# A4 demo: reserving surplus food

## Commands

```sh
./mvnw clean test
./mvnw test jacoco:report
./mvnw test pitest:mutationCoverage@run-pitest
./mvnw pmd:pmd
for i in {1..10}; do ./mvnw test || break; done
```

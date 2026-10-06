# Test adequacy demo

```sh
./mvnw clean test
./mvnw clean test jacoco:report
./mvnw pitest:mutationCoverage
./mvnw clean verify
```

Open `target/site/jacoco/index.html` for the JaCoCo report and
`target/pit-reports/index.html` for the PIT report.

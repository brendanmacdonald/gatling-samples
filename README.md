Install Gatling - https://gatling.io/docs/current/quickstart/

Execution
```
mvn test -Pperformance
```

There are currently 3 Simulation files. They can be run in one of two ways (check pom.xml for extra details):
- they each have their own entry in the <execution> tag of the pom.xml. This allows them to be run individually.
- they also each have an additional entry in the <execution> tag of the pom.xml that allows them all to be run as a group.

Reports
```
/target/gatling/...
```

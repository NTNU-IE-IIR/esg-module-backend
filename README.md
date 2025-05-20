# ESG Module backend

Backend project for the ESG module in
[FisheryInsight](https://ncmc.no/digitalisere-hel-fiskerin-ringen/). The project is done as part of
the bachelor thesis by group 14.

### How to run

To run the project, you need maven and Java 21 installed. Then you can run the following command:

```shell
mvn spring-boot:run
```

To build the project, you can run the following command:

```shell
mvn -B package --file pom.xml
```

#### Data simulator

To run the application using the data simulator, change the `ACTIVE` constant in the
`DataSimulator.java` file  to `true`. See **How to run** to run the application again.

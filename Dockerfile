# ---- build ----
FROM maven:3.9-eclipse-temurin-25 AS build
WORKDIR /src
# Resolve dependencies first so they cache between code changes.
COPY pom.xml .
COPY subly-domain/pom.xml subly-domain/
COPY subly-application/pom.xml subly-application/
COPY subly-infrastructure/pom.xml subly-infrastructure/
COPY subly-api/pom.xml subly-api/
RUN mvn -q -B dependency:go-offline -DskipTests || true
COPY . .
RUN mvn -q -B package -DskipTests \
 && java -Djarmode=tools -jar subly-api/target/subly-api.jar extract --layers --launcher --destination /extracted

# ---- runtime ----
FROM eclipse-temurin:25-jre-alpine
RUN addgroup -S subly && adduser -S subly -G subly
WORKDIR /app
COPY --from=build /extracted/dependencies/ ./
COPY --from=build /extracted/spring-boot-loader/ ./
COPY --from=build /extracted/snapshot-dependencies/ ./
COPY --from=build /extracted/application/ ./
USER subly
EXPOSE 8080
ENV JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=75"
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]

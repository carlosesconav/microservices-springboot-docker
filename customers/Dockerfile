# ======================
# Build
# ======================
FROM public.ecr.aws/amazoncorretto/amazoncorretto:17 AS build

WORKDIR /app

COPY build.gradle settings.gradle gradlew ./

COPY gradle ./gradle

RUN ./gradlew dependencies --no-daemon

# copy the project code
COPY src ./src

# Compile and create JAR
RUN ./gradlew clean build -x test --no-daemon

# ======================
# Runtime
# ======================
FROM public.ecr.aws/amazoncorretto/amazoncorretto:17

WORKDIR /app

COPY --from=build /app/build/libs/*.jar /app/customers.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/customers.jar"]
# ==============================
# Etapa 1 - Build da aplicação
# ==============================
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copia os arquivos do projeto
COPY pom.xml .
COPY src ./src

# Gera o JAR
RUN mvn clean package -DskipTests

# ==============================
# Etapa 2 - Runtime
# ==============================
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copia o JAR gerado na etapa de build
COPY --from=build /app/target/carolinaVca-0.0.1-SNAPSHOT.jar app.jar

# Porta padrão (Render usa PORT dinamicamente)
EXPOSE 8080

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]

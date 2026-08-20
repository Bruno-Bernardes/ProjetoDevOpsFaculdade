# =============================================================
# Estágio 1: Build da Aplicação Java
# =============================================================
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder

WORKDIR /app

# Copia o arquivo de dependências primeiro para reaproveitar o cache
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código-fonte e compila gerando o artefato .jar
COPY src ./src
RUN mvn clean package -DskipTests

# =============================================================
# Estágio 2: Execução em Ambiente de Produção (Imagem Leve)
# =============================================================
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copia apenas o .jar gerado no estágio de build
COPY --from=builder /app/target/*.jar app.jar

# Mapeamento da porta da aplicação
EXPOSE 8080

# Comando para inicialização da aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
# Usa la imagen base de Amazon Corretto 21 (Java 21)
FROM public.ecr.aws/amazoncorretto/amazoncorretto:21

# Define el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el jar construido dentro del contenedor
COPY build/libs/demo-api-rest-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto (ajusta si usas otro)
EXPOSE 8080

# Define el comando para correr la app con parámetros de memoria
ENTRYPOINT ["java", "-Xms96m", "-Xmx128m", "-XX:+HeapDumpOnOutOfMemoryError", "-XX:HeapDumpPath=/app/logs/dump.hprof", "-jar", "app.jar"]

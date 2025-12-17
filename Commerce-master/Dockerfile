# Usar Tomcat 9 con JDK 11 (compatible con la aplicación)
FROM tomcat:9-jdk11

# Instalar herramientas necesarias
RUN apt-get update && apt-get install -y \
    maven \
    && rm -rf /var/lib/apt/lists/*

# Crear directorio de trabajo
WORKDIR /app

# Copiar archivos del proyecto
COPY . /app/

# Crear estructura de directorios para compilación manual
RUN mkdir -p build/classes

# Compilar las clases Java manualmente (ya que no hay configuración Maven completa)
RUN find src/java -name "*.java" -print0 | xargs -0 javac -cp "lib/*:lib/metro/*" -d build/classes -encoding UTF-8 || true

# Copiar las librerías necesarias
RUN mkdir -p /usr/local/tomcat/lib && \
    cp lib/mssql-jdbc-9.4.1.jre11.jar /usr/local/tomcat/lib/

# Limpiar aplicaciones por defecto y crear directorio ROOT
RUN rm -rf /usr/local/tomcat/webapps/* && \
    mkdir -p /usr/local/tomcat/webapps/ROOT

# Copiar la aplicación web
RUN cp -r web/* /usr/local/tomcat/webapps/ROOT/

# Copiar las clases compiladas
RUN mkdir -p /usr/local/tomcat/webapps/ROOT/WEB-INF/classes && \
    cp -r target/classes/* /usr/local/tomcat/webapps/ROOT/WEB-INF/classes/ 2>/dev/null || \
    cp -r build/classes/* /usr/local/tomcat/webapps/ROOT/WEB-INF/classes/ 2>/dev/null || true

# Copiar todas las librerías al directorio WEB-INF/lib
RUN mkdir -p /usr/local/tomcat/webapps/ROOT/WEB-INF/lib && \
    cp lib/*.jar /usr/local/tomcat/webapps/ROOT/WEB-INF/lib/ && \
    cp lib/CopyLibs*/*.jar /usr/local/tomcat/webapps/ROOT/WEB-INF/lib/ 2>/dev/null || true && \
    cp lib/metro*/*.jar /usr/local/tomcat/webapps/ROOT/WEB-INF/lib/ 2>/dev/null || true

# Configurar permisos
RUN chmod -R 755 /usr/local/tomcat/webapps/ROOT

# Puerto de Tomcat (mapeado a 9000 en el host)
EXPOSE 8080

# Iniciar Tomcat
CMD ["catalina.sh", "run"]
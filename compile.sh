#!/bin/bash

echo "=== Script de compilación para Commerce ==="

# Crear directorio de salida
mkdir -p build/classes

# Crear lista de todos los JARs
CLASSPATH=""
for jar in lib/*.jar lib/metro/*.jar lib/metro-2/*.jar lib/CopyLibs*/*.jar; do
    if [ -f "$jar" ]; then
        CLASSPATH="$CLASSPATH:$jar"
    fi
done

# Agregar servlet-api.jar si existe
if [ -f "lib/javax.servlet-api-4.0.1.jar" ]; then
    CLASSPATH="$CLASSPATH:lib/javax.servlet-api-4.0.1.jar"
fi

echo "Classpath configurado: $CLASSPATH"

# Encontrar todos los archivos Java
echo "Buscando archivos Java..."
find src/java -name "*.java" > java_files.txt
TOTAL_FILES=$(wc -l < java_files.txt)
echo "Encontrados $TOTAL_FILES archivos Java"

# Compilar todos los archivos Java
echo "Compilando archivos Java..."
javac -cp ".$CLASSPATH" -d build/classes -encoding UTF-8 @java_files.txt 2> compile_errors.log

if [ $? -eq 0 ]; then
    echo "✅ Compilación exitosa!"
else
    echo "❌ Errores de compilación encontrados. Ver compile_errors.log"
    echo "Primeros 20 errores:"
    head -20 compile_errors.log
fi

# Contar clases compiladas
if [ -d "build/classes" ]; then
    COMPILED=$(find build/classes -name "*.class" | wc -l)
    echo "Clases compiladas: $COMPILED"
fi

# Limpiar
rm -f java_files.txt

echo "=== Fin de compilación ==="
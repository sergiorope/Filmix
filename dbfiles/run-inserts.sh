#!/bin/bash
set -e

echo "⏳ Esperando a que MySQL esté listo..."
until mysql -hmysql_db -uroot -prootpassword -e "SELECT 1;" &> /dev/null
do
  sleep 2
done

echo "✅ MySQL está listo. Esperando al backend para que cree las tablas..."
# espera adicional para que Hibernate cree las tablas
sleep 10

echo "📥 Ejecutando inserts..."
mysql -hmysql_db -uroot -prootpassword filmix < /docker-entrypoint-initdb.d/data.sql

echo "✅ Inserts ejecutados correctamente."

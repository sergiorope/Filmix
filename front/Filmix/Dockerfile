FROM nginx:alpine

# Borra archivos por defecto de Nginx
RUN rm -rf /usr/share/nginx/html/*

# Copia tu front al contenedor
COPY . /usr/share/nginx/html

# Expone el puerto 80
EXPOSE 80

# Arranca Nginx
CMD ["nginx", "-g", "daemon off;"]

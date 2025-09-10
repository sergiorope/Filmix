FROM nginx:alpine


WORKDIR /usr/share/nginx/html

RUN rm -rf ./*

COPY . .

# Ejecutar Nginx
CMD ["nginx", "-g", "daemon off;"]

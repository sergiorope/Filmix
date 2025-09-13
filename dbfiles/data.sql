-- ===========================================
-- USUARIOS
-- ===========================================
INSERT INTO usuario (id, nombre, correo, password) VALUES 
(1, 'Sergio', 'sergio@mail.com',  '$2a$12$PcOqrSIcs8x.w6IcyzLG8umdIsR6rF7Yt8uAFk3rNar9wBztvIDA2'),
(2, 'Laura',  'laura@mail.com',   '$2a$12$8NX4SdKZX0t3m9Qi.PMaLucW5uhbujE4Baa08gjJr9bo3WzBKOW86'),
(3, 'Carlos', 'carlos@mail.com',  '$2a$12$eX4r7hT7b3xv4u9kC1T3YOpw1j/8Y89zN/zt7pR6cuzj7S1nJvLhC'),
(4, 'Ana',    'ana@mail.com',     '$2a$12$dY8j9T7v3o1b5wL7kT6pZunY8o/2P4VhZk7rR0Q5b9p2Lk8uYvF9O'),
(5, 'Lucía',  'lucia@mail.com',   '$2a$12$e8J9o7R3xU6bF0Q9lK5pGuq4P7hX3V8mN6tA5yR2lJ9qQ1hT6bS5y'),
(6, 'Miguel', 'miguel@mail.com',  '$2a$12$kQ3lM7pN5vA8zL2oP6dQxU1nC8yJ9tR3wL5bH0pS8fD2rG7tZ6cE');

-- ===========================================
-- LISTAS
-- ===========================================
INSERT INTO lista (id, usuario_id) VALUES 
(1, 1),(2, 2),(3, 3),(4, 4),(5, 5),(6, 6);

-- ===========================================
-- PELÍCULAS
-- ===========================================
INSERT INTO pelicula (id, nombre, sinopsis, imagen) VALUES
(1, 'Inception',        'Un ladrón que roba secretos corporativos a través de sueños.', 'https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_UX182_CR0,0,182,268_AL_.jpg'),
(2, 'The Matrix',       'Un hacker descubre la verdad sobre la realidad.', 'https://m.media-amazon.com/images/M/MV5BNzQzOTk3NzItMDY0My00ZjRhLTk2ZjUtNWZkZTAyMjBiZDQ2XkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_UX182_CR0,0,182,268_AL_.jpg'),
(3, 'Interstellar',     'Un grupo viaja a través de un agujero de gusano para salvar la humanidad.', 'https://m.media-amazon.com/images/M/MV5BZjdkOTU3NzYtYjYtNWZjOS1hMmIzLTAwN2YxYzQ3YjU5XkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UX182_CR0,0,182,268_AL_.jpg'),
(4, 'The Dark Knight',  'Batman enfrenta al Joker en una batalla por Gotham.', 'https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0Nl5BMl5BanBnXkFtZTcwODAyMDU2MQ@@._V1_UX182_CR0,0,182,268_AL_.jpg'),
(5, 'Avatar',           'Un humano se infiltra en la raza Na\'vi en Pandora.', 'https://m.media-amazon.com/images/M/MV5BMjIxOTU0NTY2NF5BMl5BanBnXkFtZTcwNTY3MDk2Mw@@._V1_UX182_CR0,0,182,268_AL_.jpg'),
(6, 'Pulp Fiction',     'Historias entrelazadas de crimen en Los Ángeles.', 'https://m.media-amazon.com/images/M/MV5BNGNhMDI4YjAtMWY3Mi00ZDZlLWE2NzUtZjU0ZTg4YzE2YzNmXkEyXkFqcGdeQXVyNDUzOTQ5MjY@._V1_UX182_CR0,0,182,268_AL_.jpg'),
(7, 'Titanic',          'Una historia de amor en el trágico naufragio del Titanic.', 'https://m.media-amazon.com/images/M/MV5BMjIxNzY1NTY1Nl5BMl5BanBnXkFtZTcwOTYwMjY2Mw@@._V1_UX182_CR0,0,182,268_AL_.jpg'),
(8, 'Avengers: Endgame','Los Vengadores se enfrentan a Thanos para salvar el universo.', 'https://m.media-amazon.com/images/M/MV5BMTc5OTQ0NTY2MV5BMl5BanBnXkFtZTgwODM4MzYyNzM@._V1_UX182_CR0,0,182,268_AL_.jpg'),
(9, 'Joker',            'La historia de cómo Arthur Fleck se convierte en el Joker.', 'https://m.media-amazon.com/images/M/MV5BMTkxOTgzMzQyNV5BMl5BanBnXkFtZTgwMzMxNzY2MjM@@._V1_UX182_CR0,0,182,268_AL_.jpg'),
(10,'Gladiator',        'Un general romano busca venganza contra el emperador.', 'https://m.media-amazon.com/images/M/MV5BMjAyMjMxMjY1Nl5BMl5BanBnXkFtZTcwODAzMTg3Mw@@._V1_UX182_CR0,0,182,268_AL_.jpg');

-- ===========================================
-- CATEGORÍAS
-- ===========================================
INSERT INTO categoria (id, nombre) VALUES
(1, 'Ciencia Ficción'),
(2, 'Acción'),
(3, 'Drama'),
(4, 'Crimen'),
(5, 'Aventura'),
(6, 'Romance'),
(7, 'Superhéroes'),
(8, 'Terror'),
(9, 'Animación');

-- ===========================================
-- PREGUNTAS
-- ===========================================
INSERT INTO pregunta (id, frase) VALUES
(1, '¿Cuál es tu género favorito?'),
(2, '¿Cómo te sientes hoy?'),
(3, '¿Te gustan las películas de superhéroes?'),
(4, '¿Prefieres historias de crimen y misterio?'),
(5, '¿Te gusta el romance en las películas?'),
(6, '¿Te da miedo ver algo de terror?'),
(7, '¿Te gustan las películas animadas?');

-- ===========================================
-- RESPUESTAS
-- ===========================================
INSERT INTO respuesta (id, nombre, pregunta_id) VALUES
(1, 'Ciencia Ficción',        1),
(2, 'Acción',                 1),
(3, 'Drama',                  1),
(4, 'Terror',                 1),
(5, 'Animación',              1),
(6, 'Feliz',                  2),
(7, 'Triste',                 2),
(8, 'Emocionado',             2),
(9, 'Sí',                     3),
(10,'No',                     3),
(11,'Sí',                     4),
(12,'No',                     4),
(13,'Sí',                     5),
(14,'No',                     5),
(15,'Un poco',                6),
(16,'Nada',                   6),
(17,'Sí',                     7),
(18,'No',                     7);

-- ===========================================
-- RELACIONES ManyToMany
-- ===========================================
-- CATEGORÍA - PELÍCULA
INSERT INTO categoria_pelicula (categoria_id, pelicula_id) VALUES
(1, 1),(1, 2),(2, 2),(1, 3),(3, 3),(2, 4),(4, 4),(5, 5),(1, 5),
(4, 6),(3, 7),(6, 7),(7, 8),(2, 8),(3, 9),(4, 9),(2, 10),(3, 10);

-- PELÍCULA - LISTA
INSERT INTO pelicula_lista (lista_id, pelicula_id) VALUES
(1, 1),(1, 2),(2, 3),(3, 4),(3, 6),(4, 5),(5, 7),(5, 8),(6, 9),(6, 10);

-- RESPUESTA - CATEGORÍA
INSERT INTO respuesta_categoria (respuesta_id, categoria_id) VALUES
(1, 1),(2, 2),(3, 3),(4, 4),(5, 5),(6, 5),(6, 2),(7, 3),(8, 3),(9, 7),
(11,4),(13,6),(15,8),(17,9);


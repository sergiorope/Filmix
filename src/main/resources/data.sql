INSERT IGNORE INTO usuario (id, nombre, correo, password) VALUES 
(1, 'Sergio', 'sergio@mail.com',  '$2a$12$PcOqrSIcs8x.w6IcyzLG8umdIsR6rF7Yt8uAFk3rNar9wBztvIDA2'),
(2, 'Laura',  'laura@mail.com',   '$2a$12$8NX4SdKZX0t3m9Qi.PMaLucW5uhbujE4Baa08gjJr9bo3WzBKOW86'),
(3, 'Carlos', 'carlos@mail.com',  '$2a$12$eX4r7hT7b3xv4u9kC1T3YOpw1j/8Y89zN/zt7pR6cuzj7S1nJvLhC'),
(4, 'Ana',    'ana@mail.com',     '$2a$12$dY8j9T7v3o1b5wL7kT6pZunY8o/2P4VhZk7rR0Q5b9p2Lk8uYvF9O'),
(5, 'Lucía',  'lucia@mail.com',   '$2a$12$e8J9o7R3xU6bF0Q9lK5pGuq4P7hX3V8mN6tA5yR2lJ9qQ1hT6bS5y'),
(6, 'Miguel', 'miguel@mail.com',  '$2a$12$kQ3lM7pN5vA8zL2oP6dQxU1nC8yJ9tR3wL5bH0pS8fD2rG7tZ6cE');

INSERT IGNORE INTO lista (id, usuario_id) VALUES 
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6);

INSERT IGNORE INTO pelicula (id, nombre, sinopsis, imagen) VALUES
(1, 'Inception',        'Un ladrón que roba secretos corporativos a través de sueños.', 'https://m.media-amazon.com/images/M/MV5BZjhkNjM0ZTMtNGM5MC00ZTQ3LTk3YmYtZTkzYzdiNWE0ZTA2XkEyXkFqcGc@._V1_.jpg'),
(2, 'The Matrix',       'Un hacker descubre la verdad sobre la realidad.', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfSjSWOCaw5dnDL2GT1zFd9RMCgUGw5Q2Cfg&s'),
(3, 'Interstellar', 'Un grupo viaja a través de un agujero de gusano para salvar la humanidad.', 'https://m.media-amazon.com/images/M/MV5BYzdjMDAxZGItMjI2My00ODA1LTlkNzItOWFjMDU5ZDJlYWY3XkEyXkFqcGc@._V1_.jpg'),
(4, 'The Dark Knight',  'Batman enfrenta al Joker en una batalla por Gotham.', 'https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_FMjpg_UX1000_.jpg'),
(5, 'Avatar',           'Un humano se infiltra en la raza Na\'vi en Pandora.', 'https://m.media-amazon.com/images/M/MV5BMDEzMmQwZjctZWU2My00MWNlLWE0NjItMDJlYTRlNGJiZjcyXkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg'),
(6, 'Pulp Fiction',     'Historias entrelazadas de crimen en Los Ángeles.', 'https://m.media-amazon.com/images/M/MV5BYTViYTE3ZGQtNDBlMC00ZTAyLTkyODMtZGRiZDg0MjA2YThkXkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg'),
(7, 'Titanic',          'Una historia de amor en el trágico naufragio del Titanic.', 'https://m.media-amazon.com/images/M/MV5BYzYyN2FiZmUtYWYzMy00MzViLWJkZTMtOGY1ZjgzNWMwN2YxXkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg'),
(8, 'Avengers: Endgame','Los Vengadores se enfrentan a Thanos para salvar el universo.', 'https://m.media-amazon.com/images/M/MV5BMTc5MDE2ODcwNV5BMl5BanBnXkFtZTgwMzI2NzQ2NzM@._V1_FMjpg_UX1000_.jpg'),
(9, 'Joker',            'La historia de cómo Arthur Fleck se convierte en el Joker.', 'https://m.media-amazon.com/images/M/MV5BZGZlMjhiODktYTY4MS00YmIyLWJhZTItZmRiZDczZjI5ZTYyXkEyXkFqcGc@._V1_.jpg'),
(10,'Gladiator',        'Un general romano busca venganza contra el emperador.', 'https://m.media-amazon.com/images/M/MV5BYWQ4YmNjYjEtOWE1Zi00Y2U4LWI4NTAtMTU0MjkxNWQ1ZmJiXkEyXkFqcGc@._V1_.jpg');

INSERT IGNORE INTO categoria (id, nombre) VALUES
(1, 'Ciencia Ficción'),
(2, 'Acción'),
(3, 'Drama'),
(4, 'Crimen'),
(5, 'Aventura'),
(6, 'Romance'),
(7, 'Superhéroes'),
(8, 'Terror'),
(9, 'Animación');

INSERT IGNORE INTO pregunta (id, frase) VALUES
(1, '¿Cuál es tu género favorito?'),
(2, '¿Cómo te sientes hoy?'),
(3, '¿Te gustan las películas de superhéroes?'),
(4, '¿Prefieres historias de crimen y misterio?'),
(5, '¿Te gusta el romance en las películas?'),
(6, '¿Te da miedo ver algo de terror?'),
(7, '¿Te gustan las películas animadas?');

INSERT IGNORE INTO respuesta (id, nombre, pregunta_id) VALUES
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

INSERT IGNORE INTO categoria_pelicula (categoria_id, pelicula_id) VALUES
(1, 1),(1, 2),(2, 2),(1, 3),(3, 3),(2, 4),(4, 4),(5, 5),(1, 5),
(4, 6),(3, 7),(6, 7),(7, 8),(2, 8),(3, 9),(4, 9),(2, 10),(3, 10);

INSERT IGNORE INTO pelicula_lista (lista_id, pelicula_id) VALUES
(1, 1),(1, 2),(2, 3),(3, 4),(3, 6),(4, 5),(5, 7),(5, 8),(6, 9),(6, 10);

INSERT IGNORE INTO respuesta_categoria (respuesta_id, categoria_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 3),(6, 2),(6, 5),
(7, 3),(7, 6),
(8, 2),(8, 5),(8, 1),
(9, 7),(9, 2),
(10, 3),(10, 6),(10, 4),
(11, 4),(11, 2),(11, 3),
(12, 3),(12, 6),(12, 5),
(13, 6),(13, 3),(13, 5),
(14, 2),(14, 1),(14, 7),
(15, 8),(15, 4),
(16, 3),(16, 5),(16, 8),
(17, 9),(17, 5),(17, 1),
(18, 3),(18, 6),(18, 5);

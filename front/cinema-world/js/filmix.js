const getFilms = 'http://localhost:8080/pelicula/obtenerPeliculas';


const getPeliculas = async () => {
  try {
    const res = await fetch(getFilms, { method: "GET" });
    const movies = document.querySelector(".movies");

    if (!res.ok) {
      const error = document.createElement("p");
      error.textContent = "No hay ninguna proyección actualmente.";
      error.style.color = "red";
      error.style.fontWeight = "bold";
      error.style.marginTop = "10px";
      movies.appendChild(error);
      throw new Error("Network response was not ok");
    }

    const response = await res.json();

    if (response.length === 0) {
      const emptyMsg = document.createElement("p");
      emptyMsg.textContent = "No hay películas disponibles en este momento.";
      movies.appendChild(emptyMsg);
      return;
    }

    response.forEach((pelicula, index) => {
      const li = document.createElement('li');


      if ((index + 1) % 3 === 0) {
        li.classList.add('last');
      }

      const title = document.createElement('h4');
      title.textContent = pelicula.nombre || 'Película sin título';

      const img = document.createElement('img');
      img.src = pelicula.imagen || 'images/default.jpg';
      img.style.width = "50%";


      const desc = document.createElement('p');
      desc.textContent = pelicula.sinopsis || 'Sin descripción';
      desc.className = "descripcion"

      const linkWrapper = document.createElement('div');
      linkWrapper.className = 'wrapper';

      const a = document.createElement('a');
      a.href = '#';
      a.className = 'link2';

      const span1 = document.createElement('span');
      const span2 = document.createElement('span');
      span2.textContent = 'Read More';

      span1.appendChild(span2);
      a.appendChild(span1);
      linkWrapper.appendChild(a);

      li.appendChild(title);
      li.appendChild(img);
      li.appendChild(desc);
      li.appendChild(linkWrapper);


      movies.appendChild(li);
    });


    const clear = document.createElement('li');
    clear.className = 'clear';
    clear.innerHTML = '&nbsp;';
    movies.appendChild(clear);

  } catch (error) {
    console.log("Error al obtener películas:", error);
  }
}

getPeliculas();



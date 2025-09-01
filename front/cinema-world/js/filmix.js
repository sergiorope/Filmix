const GET_FILMS_URL = "http://localhost:8080/peliculas";
const GET_CATEGORIES_URL = "http://localhost:8080/categorias";
const GET_USER_URL = "http://localhost:8080/graphql";
const TOKEN = sessionStorage.getItem("token");

const getPeliculas = async () => {
  try {
    const res = await fetch(GET_FILMS_URL, { method: "GET" });
    const movies = document.querySelector(".movies");

    if (!res.ok) {
      mostrarError(movies);
    }

    const response = await res.json();

    if (response.length === 0) {
      const emptyMsg = document.createElement("p");
      emptyMsg.textContent = "No hay películas disponibles en este momento.";
      movies.appendChild(emptyMsg);
      return;
    }

    response.forEach((pelicula, index) => {
      const li = document.createElement("li");

      if ((index + 1) % 3 === 0) {
        li.classList.add("last");
      }

      const title = document.createElement("h4");
      title.textContent = pelicula.nombre || "Película sin título";

      const img = document.createElement("img");
      img.src = pelicula.imagen || "images/default.jpg";
      img.style.width = "50%";

      const desc = document.createElement("p");
      desc.textContent = pelicula.sinopsis || "Sin descripción";
      desc.className = "descripcion";

      const linkWrapper = document.createElement("div");
      linkWrapper.className = "wrapper";

      const a = document.createElement("a");
      a.href = "#";
      a.className = "link2";

      const span1 = document.createElement("span");
      const span2 = document.createElement("span");
      span2.textContent = "Read More";

      span1.appendChild(span2);
      a.appendChild(span1);
      linkWrapper.appendChild(a);

      li.appendChild(title);
      li.appendChild(img);
      li.appendChild(desc);
      li.appendChild(linkWrapper);

      movies.appendChild(li);
    });

    const clear = document.createElement("li");
    clear.className = "clear";
    clear.innerHTML = "&nbsp;";
    movies.appendChild(clear);
  } catch (error) {
    console.log("Error al obtener películas:", error);
  }
};

async function opcionesCategorias() {
  try {
    const res = await fetch(GET_CATEGORIES_URL, {
      method: "GET",
    });

    const response = await res.json();

    const span = document.querySelector(".list");

    const div = document.createElement("div");
    div.className = "box";

    const select = document.createElement("select");
    select.className = "categorySelect";

    response.forEach((item) => {
      const option = document.createElement("option");
      option.textContent = `${item.nombre}`;
      option.value = item.id;
      select.appendChild(option);
    });

    div.appendChild(select);

    span.appendChild(div);

    seleccionarEvento(select);
  } catch (error) {
    console.error("Error al obtener preguntas:", error);
    throw error;
  }
}

function seleccionarEvento(categorySelect) {
  categorySelect.addEventListener("change", function () {
    const opcionSeleccionada =
      categorySelect.options[categorySelect.selectedIndex];

    console.log(opcionSeleccionada.value);
  });
}

async function getUsuarioNombre() {
  const query = `query {
                    getUser {
                        nombre
                    }
                  }`;

  const resNombre = await fetch(GET_USER_URL, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + TOKEN,
    },
    body: JSON.stringify({ query }),
  });

  const nombre = await resNombre.json();
  return nombre.data.getUser.nombre;
}

function mostrarBienvenida() {
  const icons = document.getElementById("icons");

  const msgBienvenida = document.createElement("p");
  icons.appendChild(msgBienvenida);

  getUsuarioNombre().then((value) => {
    msgBienvenida.textContent = "¡Bienvenido " + value + "!";
  });
}

function mostrarError(movies) {
  const error = document.createElement("p");
  error.textContent = "No hay ninguna pelicula actualmente.";
  error.style.color = "red";
  error.style.fontWeight = "bold";
  error.style.marginTop = "10px";
  movies.appendChild(error);
  throw new Error("Network response was not ok");
}

getPeliculas();
opcionesCategorias();
mostrarBienvenida();

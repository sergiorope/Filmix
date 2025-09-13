const GET_FILMS_URL = "http://localhost:8080/peliculas";
const GET_CATEGORIES_URL = "http://filmix-back:8080/categorias";
const GET_USER_URL = "http://localhost:8080/graphql";
const TOKEN = sessionStorage.getItem("token");
const movies = document.querySelector(".movies");
const recommendedFilmsBtn = document.querySelector(".recommended");
const icons = document.getElementById("icons");

recommendedFilmsBtn.addEventListener("click", () => {
  if (!TOKEN) {
    window.location.href = "../login.html";
  } else {
    window.location.href = "../peliculas-recomendadas.html";
  }
});

const getPeliculas = async () => {
  try {
    const res = await fetch(GET_FILMS_URL, { method: "GET" });

    if (!res.ok) {
      mostrarError(movies, "No hay películas actualmente");
    }

    const response = await res.json();

    if (response.length === 0) {
      const emptyMsg = document.createElement("p");
      emptyMsg.textContent = "No hay películas disponibles en este momento.";
      movies.appendChild(emptyMsg);
      return;
    }

    mostrarPeliculas(movies, response);

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

    const optionTodas = document.createElement("option");
    optionTodas.textContent = "Todas";
    optionTodas.value = "all";
    select.appendChild(optionTodas);

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
  categorySelect.addEventListener("change", async function () {
    const opcionSeleccionada =
      categorySelect.options[categorySelect.selectedIndex];

    if (opcionSeleccionada.value != "all") {
      const GET_BY_CATEGORY_URL = `http://localhost:8080/peliculas/by-category?categoriaId=${opcionSeleccionada.value}`;

      movies.innerHTML = "";

      try {
        const res = await fetch(GET_BY_CATEGORY_URL, {
          method: "GET",
        });

        const response = await res.json();

        mostrarPeliculas(movies, response);
      } catch (error) {
        console.error("Error al obtener preguntas:", error);
        throw error;
      }
    } else {
      limpiarContenido(movies);

      getPeliculas();
    }
  });
}

function limpiarContenido(contenido) {
  contenido.innerHTML = "";
}

async function getUsuarioNombre() {
  const query = `query {
                    getUser {
                        nombre
                    }
                  }`;

  try {
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
  } catch (error) {}
}

function mostrarBienvenida() {
  const msgBienvenida = document.createElement("p");
  icons.appendChild(msgBienvenida);

  getUsuarioNombre().then((value) => {
    msgBienvenida.textContent = "¡Bienvenido " + value + "!";
  });
}

function mostrarPeliculas(movies, response) {
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

    a.className = "link2";
    

    const span1 = document.createElement("span");
    const span2 = document.createElement("span");
    span2.textContent = "Read More";

    span1.appendChild(span2);
    a.appendChild(span1);
    linkWrapper.appendChild(a);

    li.append(title, img, desc, linkWrapper);

    movies.appendChild(li);
  });
}

function mostrarError(div, msg) {
  const error = document.createElement("p");
  error.textContent = msg;
  error.style.color = "red";
  error.style.fontWeight = "bold";
  error.style.marginTop = "10px";
  div.appendChild(error);
}

getPeliculas();
opcionesCategorias();

if (TOKEN) {
  mostrarBienvenida();
} else {
  mostrarError(icons, "Invitado");
}

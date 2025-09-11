const GET_LISTA_URL = "http://localhost:8080/listas";
const TOKEN = sessionStorage.getItem("token");
const contenedorPeliculas = document.getElementById("peliculas-list");

async function obtenerLista() {
  const resLista = await fetch(GET_LISTA_URL, {
    method: "GET",
    headers: {
      Authorization: "Bearer " + TOKEN,
    },
  });

  const lista = await resLista.json();

  return lista;
}

function renderPeliculas(listaPeliculas) {
  contenedorPeliculas.innerHTML = "";

  const liUsuario = document.createElement("li");

  const ulPeliculas = document.createElement("ul");

  listaPeliculas.forEach((pelicula) => {
    const liPelicula = document.createElement("li");
    liPelicula.className = "lipelicula";
    liPelicula.textContent = "- " + pelicula + " ";

    const btnBorrar = document.createElement("button");
    btnBorrar.textContent = "❌";
    btnBorrar.classList.add("btn-borrar");

    btnBorrar.addEventListener("click", () => {
      console.log(pelicula);

      borrarPelicula(pelicula, liPelicula);
    });

    liPelicula.appendChild(btnBorrar);

    ulPeliculas.appendChild(liPelicula);
  });

  liUsuario.appendChild(ulPeliculas);
  contenedorPeliculas.appendChild(liUsuario);
}

async function borrarPelicula(pelicula, liPelicula) {
  const DELETE_PELICULA_URL = `http://localhost:8080/listas?pelicula=${pelicula}`;

  try {
    const res = await fetch(DELETE_PELICULA_URL, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + TOKEN,
      },
    });

    if (res.ok) {
      liPelicula.remove();
      console.log("Película eliminada con éxito");
    } else {
      console.error("Error al eliminar la película");
    }
  } catch (err) {
    console.error("Error de red: ", err);
  }
}

function mostrarError(div) {
  const error = document.createElement("p");
  error.textContent = "No hay películas recomendadas actualmente";
  error.style.color = "red";
  error.style.fontWeight = "bold";
  error.style.marginTop = "10px";
  div.appendChild(error);
 
}

obtenerLista().then((data) => {
  if (data.listaPeliculas.length > 0) {
    renderPeliculas(data.listaPeliculas);
  } else {
    mostrarError(contenedorPeliculas);
  }
});

const GET_LISTA_URL = "http://localhost:8080/listas";
const TOKEN = sessionStorage.getItem("token");

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

function renderPeliculas(listaUsuarios) {
  const contenedorPeliculas = document.getElementById("peliculas-list");
  contenedorPeliculas.innerHTML = "";

  listaUsuarios.forEach((usuarioObj) => {
    const liUsuario = document.createElement("li");

    const ulPeliculas = document.createElement("ul");
    usuarioObj.listaPeliculas.forEach((pelicula) => {
      const liPelicula = document.createElement("li");
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
  });
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

obtenerLista().then((listaUsuarios) => {
  renderPeliculas(listaUsuarios);
});

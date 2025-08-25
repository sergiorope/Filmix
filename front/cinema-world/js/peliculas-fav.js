const getLista = "http://localhost:8080/lista/obtenerLista";
const deletePeliculaUrl = "http://localhost:8080/lista/eliminarPelicula";
const token = sessionStorage.getItem("token");

async function obtenerLista() {
  const resLista = await fetch(getLista, {
    method: "GET",
    headers: {
      Authorization: "Bearer " + token,
    },
  });

  const lista = await resLista.json();
  return lista;
}

function renderPeliculas(listaUsuarios) {
  const contenedor = document.getElementById("peliculas-list");
  contenedor.innerHTML = "";

  listaUsuarios.forEach((usuarioObj) => {
    const liUsuario = document.createElement("li");

    const ulPeliculas = document.createElement("ul");
    usuarioObj.listaPeliculas.forEach((pelicula) => {
      const liPeli = document.createElement("li");

      liPeli.textContent = "- " + pelicula + " ";

      const btnBorrar = document.createElement("button");
      btnBorrar.textContent = "❌";
      btnBorrar.classList.add("btn-borrar");

      btnBorrar.addEventListener("click", () => {
        console.log(pelicula);

        borrarPelicula(pelicula);
        location.reload();
      });

      liPeli.appendChild(btnBorrar);
      ulPeliculas.appendChild(liPeli);
    });

    liUsuario.appendChild(ulPeliculas);
    contenedor.appendChild(liUsuario);
  });
}

async function borrarPelicula(pelicula) {
  const deletePeliculaUrl = `http://localhost:8080/lista/deletePeliculaFromList?pelicula=${pelicula}`;

  try {
    const res = await fetch(deletePeliculaUrl, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + token,
      },
    });

    if (res.ok) {
      liPeli.remove();
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

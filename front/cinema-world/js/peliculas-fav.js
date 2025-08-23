const getLista = "http://localhost:8080/lista/obtenerLista";
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

  listaUsuarios.forEach((usuarioObj) => {
    const liUsuario = document.createElement("li");
    const h3 = document.querySelector(".titulo1");
    h3.innerHTML =
      "Peliculas <span>favoritas </span> de " + usuarioObj.usuario + "";


    const ulPeliculas = document.createElement("ul");
    usuarioObj.listaPeliculas.forEach((pelicula) => {
      const liPeli = document.createElement("li");
      liPeli.textContent = "- "+ pelicula;
      ulPeliculas.appendChild(liPeli);
    });

    liUsuario.appendChild(ulPeliculas);
    contenedor.appendChild(liUsuario);
  });
}


obtenerLista().then((listaUsuarios) => {
  renderPeliculas(listaUsuarios);
});

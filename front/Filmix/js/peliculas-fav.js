const GET_LISTA_URL = "http://localhost:8080/listas";
const TOKEN = sessionStorage.getItem("token");
const contenedorPeliculas = document.getElementById("peliculas-list");




function generarTabla(listaPeliculasTabla){

  const $tbody = $(".tbody");


  listaPeliculasTabla.forEach(pelicula => {

    const $tr = $("<tr>");

    $("<td>").text(pelicula).addClass("tdPelicula").appendTo($tr)
    $("<td>").addClass("deleteButton").text("❌").appendTo($tr)
    $tr.find(".deleteButton").click(() => borrarPelicula(pelicula, $tr));
    $tr.appendTo($tbody);
    
  });
  

}


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
    generarTabla(data.listaPeliculas);
    $('#miTabla').DataTable({
      ordering: true
    });
  } else {
    mostrarError(contenedorPeliculas);
  }
});



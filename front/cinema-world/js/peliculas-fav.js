const getLista = "http://localhost:8080/lista/obtenerLista";

const token = sessionStorage.getItem("token");

const array = [];
async function obtenerLista() {
  const resLista = await fetch(getLista, {
    method: "GET",
    headers: {
      Authorization: "Bearer " + token,
    },
  });

  const lista = await resLista.json();

  lista.forEach((item) => {
    item.listaPeliculas.forEach((element) => {
      array.push(element);
    });
  });

  return array;
}

obtenerLista().then((value) => {
  value.forEach((arrayPeliculas) => {
    console.log(arrayPeliculas);
  });
});

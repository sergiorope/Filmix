const GET_QUESTIONS_URL = "http://localhost:8080/preguntas";
const GRAPHQL_URL = "http://localhost:8080/graphql";
const idsPeliculas = [];
const mapaIndice = new Map();
const mapaRespuestas = new Map();
const categoriaIds = [];

const getPreguntas = async () => {
  try {
    if (!TOKEN) throw new Error("El token es nulo");

    const res = await fetch(GET_QUESTIONS_URL, {
      method: "GET",
      headers: { Authorization: "Bearer " + TOKEN },
    });

    const response = await res.json();
    let contador = 1;

    response.forEach((pregunta) => {
      mapaIndice.set(contador, pregunta);
      contador++;
    });

    console.log(mapaIndice);
  } catch (error) {
    console.error("Error al obtener preguntas:", error);
    throw error;
  }
};

const getPeliculaRecomendada = async (ids) => {
  try {
    const query = `
    {
      getRecommended(ids: [${ids.map((id) => `"${id}"`).join(",")}]) {
        id
        nombre
        imagen
      }
    }
    `;

    const res = await fetch(GRAPHQL_URL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + TOKEN,
      },
      body: JSON.stringify({ query }),
    });

    if (!res.ok) throw new Error("Network response was not ok");

    const responseRecommended = await res.json();
    console.log("Película recomendada:", responseRecommended);
    return responseRecommended;
  } catch (error) {
    console.error("Error al obtener películas:", error);
  }
};

async function crearModal() {
  try {
    await getPreguntas();
  } catch (error) {
    console.error("Error al obtener preguntas desde el click:", error);
    throw error;
  }

  let indiceActual = 1;

  const modal = document.createElement("div");
  modal.id = "modal";
  modal.className = "modal";
  modal.style.display = "flex";

  const modalContent = document.createElement("div");
  modalContent.className = "modal-content";

  const closeModal = document.createElement("span");
  closeModal.id = "closeModal";
  closeModal.className = "close";
  closeModal.textContent = "×";

  const tituloPregunta = document.createElement("h2");
  tituloPregunta.id = "tituloPregunta";

  const form = document.createElement("form");
  form.id = "recommendForm";

  const divOptions = document.createElement("div");
  divOptions.className = "ml-md-3 ml-sm-3 pl-md-5 pt-sm-0 pt-3";
  divOptions.id = "options";

  const divBtnContainer = document.createElement("div");
  divBtnContainer.className = "d-flex align-items-center pt-3";

  const divBtnWrapper = document.createElement("div");
  divBtnWrapper.className = "ml-auto mr-sm-5 d-flex gap-2";

  const btnPrev = document.createElement("button");
  btnPrev.type = "button";
  btnPrev.className = "btn btn-dark";
  btnPrev.textContent = "Previous";
  btnPrev.disabled = true;
  btnPrev.style.margin = "5px";

  const btnNext = document.createElement("button");
  btnNext.type = "button";
  btnNext.className = "btn btn-dark";
  btnNext.textContent = "Next";

  const btnFinish = document.createElement("button");
  btnFinish.type = "button";
  btnFinish.className = "btn btn-dark";
  btnFinish.textContent = "Finish";
  btnFinish.disabled = true;

  divBtnWrapper.append(btnPrev, btnNext, btnFinish);
  divBtnContainer.append(divBtnWrapper);
  form.append(divOptions, divBtnContainer);
  modalContent.append(closeModal, tituloPregunta, form);
  modal.append(modalContent);
  document.body.append(modal);

  function renderOptions() {
    detallesEntrada();
    btnFinish.disabled = mapaRespuestas.size < mapaIndice.size;

    limpiarContenido(divOptions);

    const pregunta = mapaIndice.get(indiceActual);
    if (!pregunta) {
      console.error("No existe la pregunta para el índice:", indiceActual);
      return;
    }

    tituloPregunta.textContent = pregunta.frase;
    const respuestaGuardada = mapaRespuestas.get(indiceActual);

    pregunta.listaRespuestas.forEach((respuesta) => {
      const label = document.createElement("label");
      label.className = "options";
      label.textContent = respuesta.nombre;

      const input = document.createElement("input");
      input.type = "radio";
      input.name = "radio";
      input.value = respuesta.nombre;
      if (respuestaGuardada === respuesta) input.checked = true;

      input.addEventListener("change", () => {
        mapaRespuestas.set(indiceActual, respuesta);
        btnFinish.disabled = mapaRespuestas.size < mapaIndice.size;
        btnFinish.style.backgroundColor =
          mapaRespuestas.size === mapaIndice.size ? "black" : "grey";
      });

      const span = document.createElement("span");
      span.className = "checkmark";

      label.append(input, span);
      divOptions.append(label);
    });
  }

  function detallesEntrada() {
    if (indiceActual === 1) {
      btnPrev.style.backgroundColor = "grey";
    }

    if (mapaRespuestas.size != mapaIndice.size) {
      btnFinish.style.backgroundColor = "grey";
    }
  }

  btnNext.addEventListener("click", () => {
    if (indiceActual < mapaIndice.size) {
      indiceActual++;
      renderOptions();
      btnPrev.disabled = false;
      btnPrev.style.backgroundColor = "black";
      if (indiceActual === mapaIndice.size) {
        btnNext.disabled = true;
        btnNext.style.backgroundColor = "grey";
      }

      if (mapaRespuestas.size === mapaIndice.size) {
        btnFinish.style.backgroundColor = "black";
      }
    }
  });

  btnPrev.addEventListener("click", () => {
    if (indiceActual > 1) {
      indiceActual--;
      renderOptions();
      btnNext.disabled = false;
      btnNext.style.backgroundColor = "black";
      if (indiceActual === 1) {
        btnPrev.disabled = true;
        btnPrev.style.backgroundColor = "grey";
      }
      if (mapaRespuestas.size === mapaIndice.size) {
        btnFinish.style.backgroundColor = "black";
      }
    }
  });

  btnFinish.addEventListener("click", async () => {
    categoriaIds.length = 0;
    mapaRespuestas.forEach((respuesta) => {

      respuesta.listaCategorias.forEach(categoria => {

        categoriaIds.push(categoria);
        
      });


      
    });

    console.log(categoriaIds);

    const pelicula = await getPeliculaRecomendada(categoriaIds);
    const peliculas = pelicula.data.getRecommended;

    modal.remove();
    crearModalPelicula(peliculas);
  });

  renderOptions();

  closeModal.addEventListener("click", () => modal.remove());
  modal.addEventListener("click", (e) => {
    if (e.target === modal) modal.remove();
  });
}

async function crearModalPelicula(peliculas) {
  const modal = document.createElement("div");
  modal.id = "modal";
  modal.className = "modal";
  modal.style.display = "flex";

  const modalContent = document.createElement("div");
  modalContent.className = "modal-content-recommended";

  const closeModal = document.createElement("span");
  closeModal.id = "closeModal";
  closeModal.className = "close";
  closeModal.textContent = "×";

  const moviesList = document.createElement("ul");
  moviesList.className = "movies-list";

  peliculas.forEach((pelicula) => {
    idsPeliculas.push(pelicula.id);

    const li = document.createElement("li");
    li.className = "recommended-item";

    const title = document.createElement("h4");
    title.textContent = pelicula.nombre || "Película sin título";

    const img = document.createElement("img");
    img.src = pelicula.imagen || "images/default.jpg";
    img.style.width = "50%";

    const desc = document.createElement("p");
    desc.textContent = pelicula.descripcion || "";

    const linkWrapper = document.createElement("div");
    linkWrapper.className = "wrapper";

    li.append(title, img, desc, linkWrapper);
    moviesList.append(li);
  });

  const center = document.createElement("div");
  center.className = "center";

  const btnWrapper = document.createElement("div");
  btnWrapper.className = "btn-1";

  const btnAñadir = document.createElement("button");
  btnAñadir.style.cursor = "pointer";

  const span = document.createElement("span");
  span.textContent = "Añadir a la lista";

  btnWrapper.append(btnAñadir);
  btnAñadir.append(span);
  center.append(btnWrapper);

  modalContent.append(closeModal, moviesList, center);
  modal.append(modalContent);
  document.body.append(modal);

  closeModal.addEventListener("click", () => modal.remove());
  modal.addEventListener("click", (e) => {
    if (e.target === modal) modal.remove();
  });

  btnAñadir.addEventListener("click", async (e) => {
    e.preventDefault();
    await añadirAPeliculasLista(idsPeliculas);
    idsPeliculas.length = 0;
    location.reload();
  });
}

async function añadirAPeliculasLista(ids) {
  const queryParams = ids.map((id) => `peliculasIds=${id}`).join("&");
  const postUrl = `http://localhost:8080/listas?${queryParams}`;

  try {
    const res = await fetch(postUrl, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + TOKEN,
      },
    });

    const response = await res.json();
    console.log(response);
  } catch (error) {
    console.error("Error al añadir peliculas a la lista:", error);
    throw error;
  }
}

const btnRecomendar = document.querySelector(".link1");
btnRecomendar.addEventListener("click", async (e) => {
  e.preventDefault();
  if (!TOKEN) {
    window.location.href = "../login.html";
  }
  try {
    await crearModal();
  } catch (error) {
    console.error("Error al crear el modal:", error);
  }
});

function limpiarContenido(contenido) {
  contenido.innerHTML = "";
}

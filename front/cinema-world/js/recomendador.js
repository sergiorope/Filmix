const getQuestions = "http://localhost:8080/preguntas";

const CategoriasIds = [];

const mapaIndice = new Map();
const mapaRespuestas = new Map();

const tokenSession = sessionStorage.getItem("token");

console.log(tokenSession);

const getPreguntas = async () => {
  try {
    if (tokenSession == null) {
      throw new Error("El token es nulo");
    }

    const res = await fetch(getQuestions, {
      method: "GET",
      headers: { Authorization: "Bearer " + tokenSession },
    });

    if (res === null) {
      console.log("no hay");
    }

    const response = await res.json();
    let contador = 1;

    response.forEach((e) => {
      mapaIndice.set(contador, e);
      contador++;
    });

    console.log(mapaIndice);
  } catch (error) {
    console.log("Error al obtener películas:", error);
    throw error;
  }
};

const getPeliculaRecomendada = async (ids) => {
  try {
    const urlGraphQL = `http://localhost:8080/graphql`;

    const query = `
{
  getRecommended(ids: [${ids
    .map((id) => `"${id}"`)
    .join(",")}]) {
    id
    nombre
    imagen
  }
}
`;

    const res = await fetch(urlGraphQL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + tokenSession,
      },
      body: JSON.stringify({
        query,
      }),
    });

    if (!res.ok) {
      throw new Error("Network response was not ok");
    }

    const responseRecommended = await res.json();
    console.log("Película recomendada:", responseRecommended);
    return responseRecommended;
  } catch (error) {
    console.log("Error al obtener películas:", error);
  }
};

async function crearModal() {
  try {
    await getPreguntas();
  } catch (error) {
    console.log("Error al obtener preguntas desde el click:", error);

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

  const titulo = document.createElement("h2");
  titulo.id = "tituloPregunta";

  const form = document.createElement("form");
  form.id = "recommendForm";

  const divOptions = document.createElement("div");
  divOptions.className = "ml-md-3 ml-sm-3 pl-md-5 pt-sm-0 pt-3";
  divOptions.id = "options";

  function renderOptions() {
    detallesEntrada();

    btnFinish.disabled = mapaRespuestas.size < mapaIndice.size;

    divOptions.innerHTML = "";

    const pregunta = mapaIndice.get(indiceActual);
    if (!pregunta) {
      console.error("No existe la pregunta para el índice:", indiceActual);
      return;
    }

    titulo.textContent = pregunta.frase;

    const respuestaGuardada = mapaRespuestas.get(indiceActual);

    pregunta.listaRespuestas.forEach((resp) => {
      const label = document.createElement("label");
      label.className = "options";
      label.textContent = resp.nombre;

      const input = document.createElement("input");
      input.type = "radio";
      input.name = "radio";
      input.value = resp.nombre;

      if (respuestaGuardada === resp) {
        input.checked = true;
      }

      input.addEventListener("change", () => {
        mapaRespuestas.set(indiceActual, resp);
        btnFinish.disabled = mapaRespuestas.size < mapaIndice.size;

        if (mapaRespuestas.size != mapaIndice.size) {
          btnFinish.style.backgroundColor = "grey";
        } else {
          btnFinish.style.backgroundColor = "black";
        }
      });

      const span = document.createElement("span");
      span.className = "checkmark";

      label.appendChild(input);
      label.appendChild(span);
      divOptions.appendChild(label);
    });
  }

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
  btnFinish.className = "btn btn-dark ";
  btnFinish.textContent = "Finish";
  btnFinish.disabled = true;

  divBtnWrapper.appendChild(btnPrev);
  divBtnWrapper.appendChild(btnNext);
  divBtnWrapper.appendChild(btnFinish);
  divBtnContainer.appendChild(divBtnWrapper);

  form.appendChild(divOptions);
  form.appendChild(divBtnContainer);

  modalContent.appendChild(closeModal);
  modalContent.appendChild(titulo);
  modalContent.appendChild(form);

  modal.appendChild(modalContent);
  document.body.appendChild(modal);

  renderOptions();

  closeModal.addEventListener("click", () => modal.remove());
  modal.addEventListener("click", (e) => {
    if (e.target === modal) modal.remove();
  });

  btnNext.addEventListener("click", () => {
    if (indiceActual < mapaIndice.size) {
      indiceActual++;
      renderOptions();
              btnPrev.style.backgroundColor = "black";

      btnPrev.disabled = false;
      if (indiceActual === mapaIndice.size) {
        btnNext.disabled = true;

        btnNext.style.backgroundColor = "grey";
      } else if (indiceActual != 1) {
      
       
      }
    }
  });

  btnPrev.addEventListener("click", () => {
    if (indiceActual > 1) {
      indiceActual--;
      renderOptions();
                    btnNext.style.backgroundColor = "black";

      btnNext.disabled = false;
      if (indiceActual === 1) {
        btnPrev.disabled = true;

        
        btnPrev.style.backgroundColor = "grey";
      } else if (indiceActual != mapaIndice.size) {
        btnNext.style.backgroundColor = "black";
      }

      console.log(mapaIndice.size);
    }
  });

  function detallesEntrada() {
    if (indiceActual === 1) {
      btnPrev.style.backgroundColor = "grey";
      btnFinish.style.backgroundColor = "grey";
    }
  }

  btnFinish.addEventListener("click", async () => {
    CategoriasIds.length = 0;

    mapaRespuestas.forEach((v) => {
      CategoriasIds.push(v.listaCategorias);
    });

    console.log("IDs categorías:", CategoriasIds);

    let pelicula = await getPeliculaRecomendada(CategoriasIds);

    const peliculas = pelicula.data.getRecommended;

    console.log("Respuesta de película recomendada:", pelicula);

    modal.remove();
    crearModalPelicula(peliculas);

    btnPrev.disabled = false;
    btnNext.disabled = false;
    btnFinish.disabled = false;
  });
}

async function crearModalPelicula(pelicula) {
  const modal = document.createElement("div");
  modal.id = "modal";
  modal.className = "modal";
  modal.style.display = "flex";

  const center = document.createElement("div");
  center.className = "center";

  const btn1 = document.createElement("div");
  btn1.className = "btn-1";

  const buttonAñadir = document.createElement("button");
  buttonAñadir.style.cursor = "pointer";

  const span = document.createElement("span");
  span.textContent = "Añadir a la lista";

  center.appendChild(btn1);
  btn1.appendChild(buttonAñadir);
  buttonAñadir.appendChild(span);

  const modalContent = document.createElement("div");
  modalContent.className = "modal-content-recommended";

  const closeModal = document.createElement("span");
  closeModal.id = "closeModal";
  closeModal.className = "close";
  closeModal.textContent = "×";

  const movies = document.createElement("ul");
  movies.className = "movies-list";

  const idsPeliculas = [];

  pelicula.forEach((e) => {
    idsPeliculas.push(e.id);

    console.log(e.id + "wdwdwdwdw");

    const li = document.createElement("li");
    li.className = "recommended-item";

    const title = document.createElement("h4");
    title.textContent = e.nombre || "Película sin título";

    const img = document.createElement("img");
    img.src = e.imagen || "images/default.jpg";
    img.style.width = "50%";

    const desc = document.createElement("p");
    desc.textContent = e.descripcion || "";

    const linkWrapper = document.createElement("div");
    linkWrapper.className = "wrapper";

    li.appendChild(title);
    li.appendChild(img);
    li.appendChild(desc);
    li.appendChild(linkWrapper);

    movies.appendChild(li);
    movies.appendChild(center);
  });

  modalContent.appendChild(movies);

  modal.appendChild(modalContent);
  document.body.appendChild(modal);

  closeModal.addEventListener("click", () => modal.remove());
  modal.addEventListener("click", (e) => {
    if (e.target === modal) modal.remove();
  });

  buttonAñadir.addEventListener("click", async (e) => {
    e.preventDefault();

    await añadiraLista(idsPeliculas);
    idsPeliculas.splice();
    location.reload();
  });
}

const btn = document.querySelector(".link1");
btn.addEventListener("click", async (e) => {
  e.preventDefault();

  if (!tokenSession) {
    window.location.href = "../login.html";
  }

  try {
    await crearModal();
  } catch (error) {
    console.log("Error al obtener al crear el modal:", error);
  }
});

async function añadiraLista(ids) {
const queryParams = ids.map(id => `peliculasIds=${id}`).join("&");
const postAñadiraLista = `http://localhost:8080/listas?${queryParams}`;
  try {
    const res = await fetch(postAñadiraLista, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + tokenSession,
      },
    });

    const response = await res.json();

    console.log(response);
  } catch (error) {
    console.log("Error al añadir peliculas a la lista:", error);
    throw error;
  }
}

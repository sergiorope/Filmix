const getQuestions = "http://localhost:8080/pregunta/obtenerPreguntas";
const CategoriasIds = [];

const mapaIndice = new Map();
const mapaRespuestas = new Map();

const getPreguntas = async () => {
  try {
    const res = await fetch(getQuestions, { method: "GET" });

    if (!res.ok) {
      const error = document.createElement("p");
      error.textContent = "No hay ninguna proyección actualmente.";
      error.style.color = "red";
      error.style.fontWeight = "bold";
      error.style.marginTop = "10px";
      movies.appendChild(error);
      throw new Error("Network response was not ok");
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
  }
};

const getPeliculaRecomendada = async (ids) => {
  try {
    const url = `http://localhost:8080/pelicula/obtenerPeliculasRecomendada?ids=${ids.join(
      ","
    )}`;
    const res = await fetch(url, { method: "GET" });

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
  await getPreguntas();
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
      btnPrev.disabled = false;
      if (indiceActual === mapaIndice.size) btnNext.disabled = true;
    }
  });

  btnPrev.addEventListener("click", () => {
    if (indiceActual > 1) {
      indiceActual--;
      renderOptions();
      btnNext.disabled = false;
      if (indiceActual === 1) btnPrev.disabled = true;
    }
  });

  btnFinish.addEventListener("click", async () => {
    CategoriasIds.length = 0;

    mapaRespuestas.forEach((v) => {
      CategoriasIds.push(v.listaCategorias);
    });

    console.log("IDs categorías:", CategoriasIds);

    let pelicula = await getPeliculaRecomendada(CategoriasIds);
    console.log("Respuesta de película recomendada:", pelicula);

    modal.remove();
    crearModalPelicula(pelicula);

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

  const modalContent = document.createElement("div");
  modalContent.className = "modal-content-recommended";
  

  const closeModal = document.createElement("span");
  closeModal.id = "closeModal";
  closeModal.className = "close";
  closeModal.textContent = "×";

    const movies = document.createElement("ul");
  movies.className = "movies-list"; 

  pelicula.forEach((e) => {
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
  });

  modalContent.appendChild(movies);
  modal.appendChild(modalContent);
  document.body.appendChild(modal);
  



  closeModal.addEventListener("click", () => modal.remove());
  modal.addEventListener("click", (e) => {
    if (e.target === modal) modal.remove();
  });
}

const btn = document.querySelector(".link1");
btn.addEventListener("click", async (e) => {
  e.preventDefault();
  await crearModal();
});

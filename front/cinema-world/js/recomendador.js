function crearModal() {

  const modal = document.createElement('div');
  modal.id = 'modal';
  modal.className = 'modal';
  modal.style.display = 'flex'; 

  const modalContent = document.createElement('div');
  modalContent.className = 'modal-content';

  const closeModal = document.createElement('span');
  closeModal.id = 'closeModal';
  closeModal.className = 'close';
  closeModal.textContent = '×';

  const titulo = document.createElement('h2');
  titulo.textContent = 'Recomendar película';

  const form = document.createElement('form');
  form.id = 'recommendForm';

  const labelNombre = document.createElement('label');
  labelNombre.htmlFor = 'nombre';
  labelNombre.textContent = 'Nombre de la película:';

  const inputNombre = document.createElement('input');
  inputNombre.type = 'text';
  inputNombre.id = 'nombre';
  inputNombre.name = 'nombre';
  inputNombre.required = true;

  const labelSinopsis = document.createElement('label');
  labelSinopsis.htmlFor = 'sinopsis';
  labelSinopsis.textContent = 'Sinopsis:';

  const textareaSinopsis = document.createElement('textarea');
  textareaSinopsis.id = 'sinopsis';
  textareaSinopsis.name = 'sinopsis';
  textareaSinopsis.required = true;

  const btnEnviar = document.createElement('button');
  btnEnviar.type = 'submit';
  btnEnviar.textContent = 'Enviar recomendación';

  form.appendChild(labelNombre);
  form.appendChild(inputNombre);
  form.appendChild(labelSinopsis);
  form.appendChild(textareaSinopsis);
  form.appendChild(btnEnviar);

  modalContent.appendChild(closeModal);
  modalContent.appendChild(titulo);
  modalContent.appendChild(form);

  modal.appendChild(modalContent);

  document.body.appendChild(modal);

  closeModal.addEventListener('click', () => {
    modal.remove();
  });

  modal.addEventListener('click', (e) => {
    if (e.target === modal) {
      modal.remove();
    }
  });

  form.addEventListener('submit', (e) => {
    e.preventDefault();
    alert('¡Recomendación enviada!');
    modal.remove();
  });
}

const btn = document.querySelector('.link1'); 
btn.addEventListener('click', e => {
  e.preventDefault();
  crearModal();
});
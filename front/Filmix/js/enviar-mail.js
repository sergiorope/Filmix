const TOKEN = sessionStorage.getItem("token");

const recommendedFilmsBtn = document.querySelector(".recommended");

recommendedFilmsBtn.addEventListener("click", () => {
  if (!TOKEN) {
    window.location.href = "../login.html";
  } else {
    window.location.href = "../peliculas-recomendadas.html";
  }
});

document.querySelector(".link2").addEventListener("click", function (e) {
  e.preventDefault();

  window.location.href = `mailto:${"sergio160502@gmail.com"}?subject=${encodeURIComponent(
    "Pregunta Filmix"
  )}&body=${encodeURIComponent("Hola tenía una duda acerca de")}`;
});


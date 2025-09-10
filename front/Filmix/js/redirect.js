const TOKEN = sessionStorage.getItem("token");
const recommendedFilmsBtn = document.querySelector(".recommended");

recommendedFilmsBtn.addEventListener("click", () => {
  if (!TOKEN) {
    window.location.href = "../login.html";
  } else {
    window.location.href = "../peliculas-recomendadas.html";
  }
});

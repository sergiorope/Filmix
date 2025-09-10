const inputEmail = document.getElementById("email");
const inputPassword = document.getElementById("password");
const loginDiv = document.querySelector(".inset");
const LOGIN_URL = "http://localhost:8080/usuarios/login";

const login = async (email, password) => {
  try {
    const res = await fetch(LOGIN_URL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ correo: email, password: password }),
    });

    if (!res.ok) {
  
      mostrarError(loginDiv);

      return null;
    }

    const token = await res.text();
    sessionStorage.setItem("token", token);

    console.log(token);
    return token;
  } catch (error) {
    console.error("Error al iniciar sesión:", error);
  }
};

const loginButton = document.querySelector(".login");
loginButton.addEventListener("click", async (e) => {
  e.preventDefault();

  const tokenResponse = await login(inputEmail.value, inputPassword.value);

  if (!tokenResponse) {
    console.error("Error en las credenciales");
    return;
  }

  window.location.href = "../index.html";
});

function mostrarError(div) {
  const error = document.createElement("p");
  error.textContent = "Error en las credenciales.";
  error.style.color = "red";
  error.style.fontWeight = "bold";
  error.style.marginTop = "10px";
  div.appendChild(error);


        setTimeout(() => {
        error.textContent = "";
      }, 1500);
  throw new Error("Network response was not ok");
}

const inputEmail = document.getElementById("email");
const inputPassword = document.getElementById("password");

const loginToken = async (email, password) => {
  try {
    const url = "http://localhost:8080/usuario/login";

    const res = await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ correo: email, password: password }),
    });

    if (!res.ok) {
      throw new Error("Network response was not ok");
    }

    const token = await res.text();
    sessionStorage.setItem("token", token);

    console.log(token);

    return token;
  } catch (error) {
    console.error("Error al loguearse:", error);
  }
};

const btnLogin = document.querySelector(".login");
btnLogin.addEventListener("click", async (e) => {
  e.preventDefault();

  const tokenResponse = await loginToken(inputEmail.value, inputPassword.value);

  if (!tokenResponse) {
    console.error("Error en las credenciales:", error);
  }

  window.location.href = "../index.html";
});

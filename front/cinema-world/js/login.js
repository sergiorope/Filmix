const inputEmail = document.getElementById("email");
const inputPassword = document.getElementById("password");



const loginToken = async (email, password) => {
  try {
    const url = `http://localhost:8080/usuario/login?correo=${email}&password=${password}`;
    const res = await fetch(url, { method: "POST" });

    if (!res.ok) {
      throw new Error("Network response was not ok");
    }

    const token = await res.text();

    console.log(token);
  } catch (error) {
    console.log("Error al loguearse:", error);
  }
};

const btnLogin = document.querySelector(".login");
btnLogin.addEventListener("click", async (e) => {
  e.preventDefault();

  

  loginToken(inputEmail.value, inputPassword.value);
  
});

document.addEventListener("DOMContentLoaded", () => {
    const signInBtn = document.getElementById("sign-in");
    const loginBtn = document.getElementById("login");

    if (signInBtn) {
        signInBtn.addEventListener("click", () => {
            window.location.href = "/sign-in";
        });
    }

    if (loginBtn) {
        loginBtn.addEventListener("click", () => {
            window.location.href = "/login";
        });
    }
});

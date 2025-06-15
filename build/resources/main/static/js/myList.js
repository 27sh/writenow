document.addEventListener("DOMContentLoaded", () => {
    const writeBtn = document.getElementById("write-btn");

    if (writeBtn) {
        writeBtn.addEventListener("click", () => {
            window.location.href = "/diary";
        });
    }
});

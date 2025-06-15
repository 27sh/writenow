document.addEventListener("DOMContentLoaded", () => {
    // 날짜 표시
        const dateTarget = document.getElementById("today-date");
        if (dateTarget) {
            const today = new Date();
            const yyyy = today.getFullYear();
            const mm = String(today.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작
            const dd = String(today.getDate()).padStart(2, '0');
            dateTarget.textContent = `${yyyy}-${mm}-${dd}`;
        }

    const signUpBtn = document.getElementById("login-btn");

    signUpBtn.addEventListener("click", async () => {
        const id = document.getElementById("input-id").value.trim();
        const pw = document.getElementById("input-pw").value.trim();
        const name = document.getElementById("input-name").value.trim();
        const idWarning = document.getElementById("id-warning");

        idWarning.innerText = "";

        if (!id || !pw || !name) {
            alert("모든 항목을 입력해주세요.");
            return;
        }

        // 1. ID 중복 체크
        try {
            const res = await fetch(`/api/check-id?id=${encodeURIComponent(id)}`);
            const result = await res.json();

            if (result.exists) {
                idWarning.innerText = "이미 사용 중인 아이디입니다.";
                return;
            }
        } catch (err) {
            console.error("중복 검사 중 오류:", err);
            alert("서버 오류가 발생했습니다.");
            return;
        }

        // 2. 회원가입 진행
        const data = { id, pw, name };
        try {
            const response = await fetch("/api/signup", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(data)
            });

            if (response.ok) {
                alert("회원가입이 완료되었습니다.");
                window.location.href = "/login";
            } else {
                alert("회원가입 실패. 다시 시도해주세요.");
            }
        } catch (error) {
            console.error("회원가입 중 오류:", error);
            alert("서버 오류가 발생했습니다.");
        }
    });
});

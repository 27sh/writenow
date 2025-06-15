document.addEventListener("DOMContentLoaded", () => {
    const dateEl = document.getElementById("diary-date");
    if (dateEl && dateEl.textContent.trim() === "") {
        const today = new Date();
        const yyyy = today.getFullYear();
        const mm = String(today.getMonth() + 1).padStart(2, '0');
        const dd = String(today.getDate()).padStart(2, '0');
        dateEl.textContent = `${yyyy}.${mm}.${dd}`;
    }

    const audio = document.getElementById("audio-player");
        const toggleBtn = document.getElementById("play-toggle");
        const currentTimeSpan = document.getElementById("current-time");
        const progress = document.getElementById("progress");
        const thumb = document.getElementById("progress-thumb");

        // 재생 / 일시정지 토글
        toggleBtn.addEventListener("click", () => {
            if (audio.paused) {
                audio.play();
                toggleBtn.textContent = "⏸";
            } else {
                audio.pause();
                toggleBtn.textContent = "▶";
            }
        });

        // 시간 포맷 helper
        function formatTime(sec) {
            const minutes = Math.floor(sec / 60);
            const seconds = Math.floor(sec % 60).toString().padStart(2, "0");
            return `${minutes}:${seconds}`;
        }

        // 재생 중 시간 & 바 업데이트
        audio.addEventListener("timeupdate", () => {
            currentTimeSpan.textContent = formatTime(audio.currentTime);

            const percent = (audio.currentTime / audio.duration) * 100;
            progress.style.width = percent + "%";
            thumb.style.left = percent + "%";
        });
});

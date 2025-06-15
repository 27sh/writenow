window.addEventListener('DOMContentLoaded', () => {
    // 날짜 표시
    const dateElem = document.getElementById('diary-date');
    const now = new Date();
    const formatted = now.getFullYear() + '-' + String(now.getMonth() + 1).padStart(2, '0') + '-' + String(now.getDate()).padStart(2, '0');
    dateElem.textContent = formatted;

    const getSelectedEmoji = (groupElem) => {
        const selected = groupElem.querySelector('.emoji:not(.dimmed)');
        return selected ? selected.textContent : '';
    };

    // 버튼 클릭 시 POST 요청
    document.querySelector('button').addEventListener('click', () => {
        const title = document.getElementById('title-input').value;
        const post = document.getElementById('input-text').value;
        const date = dateElem.textContent;

        const emote = getSelectedEmoji(document.querySelectorAll('.note-options')[0]);
        const weather = getSelectedEmoji(document.querySelectorAll('.note-options')[1]);

        fetch('/diary', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                dtitle: title,
                post: post,
                date: date,
                emote: emote,
                weather: weather,
                sno: 1  // 고정
            })
        })
        .then(response => {
            if (response.ok) {
                alert('작성 완료!');
                window.location.href = '/myList'; // 성공 후 페이지 이동
            } else {
                alert('저장 실패');
            }
        });
    });

    // 이모지 선택 효과
    const setupEmojiGroup = (emojiNodeList) => {
        emojiNodeList.forEach(emoji => {
            emoji.addEventListener('click', () => {
                emojiNodeList.forEach(e => e.classList.add('dimmed'));
                emoji.classList.remove('dimmed');
            });
        });
    };

    const allOptions = document.querySelectorAll('.note-options');
    if (allOptions.length >= 2) {
        setupEmojiGroup(allOptions[0].querySelectorAll('.emoji'));
        setupEmojiGroup(allOptions[1].querySelectorAll('.emoji'));
    }
});

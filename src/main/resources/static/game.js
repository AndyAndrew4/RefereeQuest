<script>
    // Функция для отправки AJAX-запроса на сервер для получения нового вопроса
    function getQuestion() {
    fetch('/question')
        .then(response => response.json())
        .then(data => {
            document.getElementById('question').textContent = data.text;
            document.getElementById('option1').textContent = data.options[0];
            document.getElementById('option2').textContent = data.options[1];
            document.getElementById('option3').textContent = data.options[2];
            document.getElementById('option4').textContent = data.options[3];
        })
}

    // Функция для отправки AJAX-запроса на сервер с выбранным ответом
    function answerQuestion(selectedOptionIndex) {
    fetch(`/answer?selectedOptionIndex=${selectedOptionIndex}`, {method: 'POST'})
        .then(response => response.json())
        .then(data => {
            if (data.correct) {
                document.getElementById('coins').textContent = 'Coins: ' + data.coins;
            }
            getQuestion(); // Получаем следующий вопрос после ответа
        })
}

    // Обработчики событий для кнопок ответов
    document.getElementById('option1').addEventListener('click', () => answerQuestion(0));
    document.getElementById('option2').addEventListener('click', () => answerQuestion(1));
    document.getElementById('option3').addEventListener('click', () => answerQuestion(2));
    document.getElementById('option4').addEventListener('click', () => answerQuestion(3));

    // Запрашиваем первый вопрос при загрузке страницы
    getQuestion();
</script>
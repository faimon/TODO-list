function addTask() {
    if (validate()) {
        $.post(
            "http://localhost:8080/todolist/task",
            {
                description: $('#description').val(),
            },
            successVision()
        );
    }
}

function successVision() {
    $('#success').css('display', 'block');
    window.setTimeout(function () {
        $('#success').css('display', 'none');
        $('#description').val('');
        $('#taskTable').empty();
        loadAllNotDoneTask();
    }, 1250);
}

function validate() {
    if ($('#description').val() === "") {
        alert('Добавьте описание задачи')
        return false;
    }
    return true;
}


function updateStatus(id) {
    $.post(
        "http://localhost:8080/todolist/done.do",
        {
            id: id
        },
        function (data) {
            $('#taskTable').empty();
            if ($('#switch').is(':checked')) {
                loadAllTasks();
            } else {
                loadAllNotDoneTask();
            }
        }
    );
}

function loadAllNotDoneTask() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/todolist/index',
        dataType: 'json'
    }).done(function (data) {
        let status = "<i class=\"fa fa-exclamation-circle fa-lg\" aria-hidden=\"true\"></i> В работе"
        data.forEach(task => {
            $('#taskTable')
                .append('<tr><td>' + task.description + '</td>'
                    + '<td>' + task.created + '</td>'
                    + '<td>' + task.user.login + '</td>'
                    + '<td>' + status + '</td>'
                    + '<td>' + '<button type="button" class="btn btn-success" onclick="updateStatus(\'' + task.id + '\')">'
                    + 'Готово</button>' + '</td>'
                    + '</tr>'
                )

        })
    }).fail(function (err) {
        alert("error: " + err);
    });
}

function loadAllTasks() {
    if ($('#switch').is(':checked')) {
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/todolist/task',
            dataType: 'json'
        }).done(function (data) {
            let status = "<i class=\"fa fa-check-square fa-lg\" aria-hidden=\"true\"></i> Выполнено"
            data.forEach(task => {
                if (task.done === true) {
                    $('#taskTable')
                        .append('<tr><td>' + task.description + '</td>'
                            + '<td>' + task.created + '</td>'
                            + '<td>' + task.user.login + '</td>'
                            + '<td>' + status + '</td>'
                            + '</tr>')
                }
            })
        }).fail(function (err) {
            alert("error: " + err);
        });
    } else {
        $('#taskTable').empty();
        loadAllNotDoneTask();
    }
}

function submit() {
    if ($('#login').val() === "" || $('#password').val() === "") {
        alert('Заполните все поля для авторизации')
        return false;
    }
    $.post(
        "http://localhost:8080/todolist/auth.do",
        {
            login: $('#login').val(),
            password: $('#password').val()
        },
        function (data) {
            if (data === "fail") {
                $('#successLogin').css('display', 'block')
                    .addClass('alert-danger').removeClass('alert-success')
                    .html('Неправильный логин или пароль');
            } else {
                $('#loginForm').css('display', 'none')
                $('#successLogin').css('display', 'block')
                    .addClass('alert-success pr-3').removeClass('alert-danger')
                    .html('Добро пожаловать, ' + '<strong>' + data + '</strong>' +
                        '<a class="pl-5" href="/todolist/">Выйти</a>');
                $('#buttonAdd').prop('disabled', false)
                loadAllNotDoneTask();
            }
        }
    );
}
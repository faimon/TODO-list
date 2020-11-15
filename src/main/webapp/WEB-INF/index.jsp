<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/acfc8580ab.js" crossorigin="anonymous"></script>
    <script>
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
                window.location.href = "http://localhost:8080/todolist/";
            }, 2000);
        }

        function validate() {
            if ($('#description').val() === "") {
                alert('Добавьте описание задачи')
                return false;
            }
            return true;
        }
    </script>

    <title>TODO list</title>
</head>
<body>
<div class="container pt-3">
    <div class="form-group">
        <label for="description">Добавить новую задачу:</label>
        <div class="col-lg-9">
            <textarea rows="2" cols="40" name="text" id="description" placeholder=" Описание задачи"></textarea>
        </div>
        <div class="col-lg-3 pt-1">
            <button type="submit" class="btn btn-primary" onclick="return addTask()">Добавить задачу</button>
        </div>
        <div style="display:none" id="success" class="alert alert-success pt-3" role="alert">
            Задача была успешно сохранена.
        </div>
    </div>
    <div class="row container">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Список текущих заданий
            </div>
            <div class="card-body">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Описание задачи</th>
                        <th scope="col">Дата создания</th>
                        <th scope="col">Статус</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${tasks}" var="task">
                        <tr>
                            <td><c:out value="${task.description}"/></td>
                            <td><fmt:formatDate value="${task.created}" pattern="dd/MM/yyyy HH:mm"/></td>
                            <td><c:out value="${task.done}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
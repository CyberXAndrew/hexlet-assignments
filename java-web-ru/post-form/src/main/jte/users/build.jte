@import exercise.dto.users.UsersPage
@param UsersPage page

<!-- BEGIN -->
<!doctype html>
<html lang="en">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <h4>Регистриция нового пользователя</h4>
    <form action="/users" method="post">
        <label>
            Введите имя
            <input type="text" required name="firstName" />
        </label>
        <label>
            Введите фамилию
            <input type="text" required name="lastName" />
        </label>
        <label>
            Введите e-mail
            <input type="email" required name="email" />
        </label>
        <label>
            Придумайте пароль
            <input type="password" required name="password" />
        </label>
        <label>
            Повторите пароль
            <input type="password" required name="passwordConfirmation" />
        </label>
        <input type="submit" value="Зарегистрировать" />
    </form>
</body>
</html>
<!-- END -->

@import exercise.dto.users.UsersPage
@import exercise.model.User
@param UsersPage users

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
    <div>
        <form action="/users" method="get">
            <label>
                Поиск по Пользователям:
                <input type="search" name="term" value="${users.getTerm()}">
            </label>
            <input type="submit" value="Search!">
        </form>
        <br>
        @if(users.getUsers() != null)
            <ol>
            @for(User user : users.getUsers())
                <li>
                    <a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a>
                </li>
            @endfor
            </ol>
        @endif
    </div>
</body>
</html>
<!-- END -->

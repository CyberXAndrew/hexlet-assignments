@import gg.jte.Content
@param Content greetings = null
@param Content usersList = null
@param Content user = null

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
        <div class="header">
            <a href="/">Главная страница</a>
            <a href="/users" title="All users">Все пользователи</a>
        </div>
        <main>
            ${greetings}
            ${user}
            ${usersList}
        </main>
        <div class="footer">
            <a href="https://github.com/CyberXAndrew">Мой профиль на гитхаб</a>
        </div>
    </body>
</html>
<!-- END -->

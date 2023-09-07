@import exercise.dto.posts.PostPage

@param PostPage page

<!-- BEGIN -->
<!doctype html>
<html lang="en">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <main>
        <h5>${page.getPost().getName()}</h5>
        <p>${page.getPost().getBody()}</p>
    </main>
    <nav>
        <a href="/posts" >Вернуться к списку постов</a>
    </nav>
</body>
</html>
<!-- END -->

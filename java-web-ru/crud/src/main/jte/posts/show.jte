@import exercise.dto.posts.PostsPage

@param PostsPage page

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
    <nav aria-label="Все посты">
        !{int startIndex;}
        !{int pageNumber;}
        @if(page.getPageNumber() == null)
            !{startIndex = 0;}
            !{pageNumber = 1;}
        @else
            !{startIndex = (Integer.parseInt(page.getPageNumber()) - 1) * 5;}
            !{pageNumber = Integer.parseInt(page.getPageNumber());}
        @endif
        <table>
            <tr>
                <td>
                    @for(int i = startIndex; i < page.getPosts().size() && i < startIndex + 5; i++)
                        <li>
                            <a href="/posts/${page.getPosts().get(i).getId()}">${page.getPosts().get(i).getName()}</a>
                        </li>
                    @endfor
                </td>
            </tr>
        </table>

        <ul class="pagination">
            <li class="page-item"><a class="page-link" href="/posts?page=${Math.max(pageNumber - 1, 1)}">Previous</a></li>
            <li class="page-item"><a class="page-link" href="/posts?page=${Math.min(pageNumber + 1, page.getPosts().size())}">Next</a></li>
        </ul>
    </nav>
</body>
</html>
<!-- END -->

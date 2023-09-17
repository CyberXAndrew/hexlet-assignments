@import exercise.dto.posts.PostsPage
@import exercise.model.Post

@param PostsPage page

<!-- BEGIN -->
@template.layout.page(page = page, content = @`
    <div>
        <table>
            <tr>
                @for(Post post : page.getPosts())
                    <td>${post.getName()}</td>
<%--                    <td>${post.getBody()}</td>--%>
                @endfor
            </tr>
        </table>
    </div>
`
)
<!-- END -->

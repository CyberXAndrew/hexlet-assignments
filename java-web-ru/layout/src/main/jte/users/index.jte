@import exercise.dto.users.UsersPage
@import exercise.model.User

@param UsersPage page

<!-- BEGIN -->
@template.layout.page(
    usersList = @`
        @for(var user : page.getUsers())
            <div>${user.getFirstName()} ${user.getLastName()}</div>
        @endfor
<%--        <table>--%>
<%--            <thead>--%>
<%--            <tr>--%>
<%--                <td><h6>ID /</h6></td>--%>
<%--                <td><h6>NAME /</h6></td>--%>
<%--                <td><h6>SURNAME /</h6></td>--%>
<%--                <td><h6>EMAIL /</h6></td>--%>
<%--            </tr>--%>
<%--            </thead>--%>
<%--            <tbody>--%>
<%--                @for(User user : page.getUsers())--%>
<%--                    <div>${user}</div>--%>
<%--                    <tr>--%>
<%--                        <td>${user.getId()}</td>--%>
<%--                        <td><p>${user.getFirstName()}</p></td>--%>
<%--                        <td><p>${user.getLastName()}</p></td>--%>
<%--                        <td>${user.getEmail()}</td>--%>
<%--                    </tr>--%>
<%--                @endfor--%>
<%--            </tbody>--%>
<%--        </table>--%>
    `
)
<!-- END -->

@import exercise.dto.users.UserPage
@param UserPage page

<!-- BEGIN -->
@template.layout.page(
    user = @`
        <div>${page.getUser().getFirstName()} ${page.getUser().getLastName()}</div>
<%--        <tr>--%>
<%--            <td>User ID:</td>--%>
<%--            <td>${page.getUser().getId()}</td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>First Name:</td>--%>
<%--            <td><p>${page.getUser().getFirstName()}</p></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>Last Name:</td>--%>
<%--            <td><p>${page.getUser().getLastName()}</p></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>E-mail:</td>--%>
<%--            <td>${page.getUser().getEmail()}</td>--%>
<%--        </tr>--%>
    `
)
<!-- END -->

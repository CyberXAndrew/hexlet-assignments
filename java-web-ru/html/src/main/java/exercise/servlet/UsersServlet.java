package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
//import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        Path filepath = Paths.get("src/main/resources/users.json");
        filepath.toAbsolutePath().normalize();
        String json = Files.readString(filepath);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, new TypeReference<List<Map>>() {
        });
        // END
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {

        // BEGIN
        List<Map> users = getUsers();
        StringBuilder sb = new StringBuilder();
        sb.append("""
                <!DOCTYPE html>
                <html lang="ru">
                    <head>
                        <meta charset="UTF-8">
                        <title>Example application | Users</title>
                        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
                         rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ
                         6jIW3" crossorigin="anonymous">
                    </head>
                    <body>
                        <table>
                            <tr>
                                <td>id</td>
                                <td>fullName</td>
                            </tr>
                """);
        for (Map<String, String> user : users) {
            String id = user.get("id");
            String fullName = user.get("firstName") + " " + user.get("lastName");
            sb.append(String.format("<tr><td>%s</td><td><a href=\"users/%s\">%s</a></td></tr>", id, id, fullName));
        }
        sb.append("""
                    </table>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
                crossorigin="anonymous"></script>
            </body>
        </html>
        """).toString();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(sb);
        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {

        // BEGIN
        List<Map<String, String>> users = getUsers();
        boolean b = users.stream()
                .anyMatch(user -> user.containsValue(id));
        if (!b) {
            response.sendError(404, "Not found");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("""
                <!DOCTYPE html>
                <html lang="ru">
                    <head>
                        <meta charset="UTF-8">
                        <title>Example application | Users</title>
                        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
                         rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ
                         6jIW3" crossorigin="anonymous">
                    </head>
                    <body>
                        <table>
                            <tr>
                                <td>firastName</td>
                                <td>lastName</td>
                                <td>id</td>
                                <td>email</td>
                            </tr>
                """);
        for (Map<String, String> map : users)  {
            if (map.get("id").equals(id)) {
                String firstName = map.get("firstName");
                String lastName = map.get("lastName");
                String email = map.get("email");
                sb.append(String.format("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>",
                        firstName, lastName, id, email));
            }
        }
        sb.append("""
                    </table>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
                crossorigin="anonymous"></script>
            </body>
        </html>
        """).toString();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(sb);
        // END
    }
}

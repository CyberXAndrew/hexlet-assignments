package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import java.util.List;
//import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        PrintWriter out = response.getWriter();
        String search = request.getParameter("search");
        String queryString = request.getQueryString();
        if (queryString == null || !queryString.contains("search") || search.isEmpty()) {
            for (String company : getCompanies()) {
                out.println(company);
            }
        } else if (getCompanies().stream().noneMatch(company -> company.contains(search))) {
            out.println("Companies not found");
        } else {
            getCompanies().stream()
                    .filter(company -> company.contains(search))
                    .forEach(out::println);
        }
        // END
    }
}

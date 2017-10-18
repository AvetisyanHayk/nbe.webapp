package be.hayk.testapp.servlets;

import be.hayk.testapp.data.TestEntityRepository;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author Hayk
 */
@WebServlet("/index.htm")
public class IndexServlet extends HttpServlet {

    private final transient TestEntityRepository repo = new TestEntityRepository();
    
    @Resource(name = "jdbc/testappdb")
    void setDataSource(DataSource dataSource) {
        repo.setDataSource(dataSource);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.getWriter().append(repo.read(1L).toString()).println();
    }
}

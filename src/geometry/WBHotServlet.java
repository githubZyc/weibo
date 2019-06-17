package geometry;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: ZhengYanChuang
 * @Date: 2019/6/12 11:17
 * @Description:
 */
public class WBHotServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.sendRedirect("/wb-hot-search.jsp");
    }
}

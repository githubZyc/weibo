package geometry;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * @Auther: ZhengYanChuang
 * @Date: 2019/6/12 11:51
 * @Description:
 */
public class WBHotSearchServlet extends HttpServlet {


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        WBHotProcessor wbhotProcessor = new WBHotProcessor();
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        JSONArray json = JSONArray.fromObject(wbhotProcessor.getAllWBHOTPage(wbhotProcessor), jsonConfig);
        PrintWriter out = resp.getWriter();
        out.write(json.toString());
    }
}

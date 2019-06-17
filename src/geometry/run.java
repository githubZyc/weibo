package geometry;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.impl.orb.ParserTable.TestAcceptor1;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class run extends HttpServlet {
   public WeiboAPI weiboAPI;
   
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	   req.setCharacterEncoding("UTF-8");
	   resp.setCharacterEncoding("UTF-8");
	   UserUtils utils = new UserUtils();
	   String methodName =req.getParameter("methodName");
	   String keyword =req.getParameter("keyword");
	   String id = req.getParameter("id");
	   
	   if ("runKeyword".equals(methodName)) {
		   runKeyword(keyword, id, resp);
	    }
	   if ("repostcount".equals(methodName)) {
		   repostcount(id,resp);
	    }
	   if ("weiboSender".equals(methodName)) {
		   utils.weiboSender(id,resp);
	    }
   
   }
   public void repostcount(String id,HttpServletResponse resp) throws IOException {
	   UserUtils utils = new UserUtils();
	   JSONArray repostCounts =utils.repostcount(id);
	   PrintWriter out = resp.getWriter();
	   out.write(repostCounts.toString());
   }
   
   public void runKeyword(String keyword,String id,HttpServletResponse resp) throws IOException {
	   UserUtils utils = new UserUtils();
	   Map<String,Integer> map = new HashMap<String,Integer>();
	   if (!"".equals(keyword)) {
		String keyword_[]=keyword.split(",");
		for (String string : keyword_) {
			map.put(string, 0);
		}
	   }
	   JSONObject echartJson=new JSONObject();
	   JSONObject result = utils.getContents(id);
		if (null!=result&&null!=result.get("comments")) {
			JSONArray comments = (JSONArray)result.get("comments");
			//处理
			String total = result.get("total_number")+"";
			echartJson=utils.export(comments, map,total);
			
			System.out.println("总数:"+result.get("total_number"));
		}
		PrintWriter out = resp.getWriter();
		out.write(echartJson.toString());
   }
   
}

package geometry;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.omg.PortableServer.IdAssignmentPolicy;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UserUtils {
	public JSONArray repostcount(String id) {
		 WeiboAPI weiboAPI = new WeiboAPI();
		String httpUrl = "https://api.weibo.com/2/statuses/count.json?access_token=2.004f3jJGGkPZBDbd25961e54PdU2OC&ids="+id;
		String teString = weiboAPI.getToHttp(httpUrl);
		JSONArray jArray = JSONArray.fromObject(teString);
		return jArray;
	}
	
	
	 public JSONObject getContents(String id) {
		   WeiboAPI weiboAPI = new WeiboAPI();
			String httpUrl = "https://api.weibo.com/2/comments/show.json?access_token=2.004f3jJGGkPZBDbd25961e54PdU2OC&count=200&page=3&id="+id;
			String teString = weiboAPI.getToHttp(httpUrl);
			JSONObject result = JSONObject.fromObject(teString);
			return result;
	   }
	 
	 public void weiboSender(String id,HttpServletResponse resp) throws IOException {
		    WeiboAPI weiboAPI = new WeiboAPI();
		    JSONObject status =(JSONObject)getContents(id).get("status");
		    JSONObject user =(JSONObject)status.get("user");
		    /*String userId = user.get("id").toString();
			String httpUrl = "https://api.weibo.com/2/users/counts.json?access_token=2.00WxndvFns9J7Eda8752dae9cIYSAD&uids="+userId;
			String teString = weiboAPI.getToHttp(httpUrl);*/
			/*JSONObject result = (JSONObject)JSONArray.fromObject(teString).get(0);*/
			PrintWriter out = resp.getWriter();
			out.write(user.toString());
	   } 

	    public JSONObject export(JSONArray comments,Map<String,Integer> map,String total) {
	    	for (Object object : comments) {
				JSONObject jsonObject = (JSONObject)object;
				String text=jsonObject.get("text").toString();
				Collection<String> c = map.keySet();
				for (String string : c) {
					if (text.indexOf(string)!=-1) {
						map.put(string, map.get(string)+1) ;
					}
				}
			}
	    	
	    	String x = "";
			String y = "";
			Collection<String> c =map.keySet();
			for (String string : c) {
				x+=string+",";
				y+=map.get(string)+",";
			}
			if (!"".equals(x)) {
				x=x.substring(0,x.length()-1);
			}
			if (!"".equals(y)) {
				y=y.substring(0,y.length()-1);
			}
			JSONObject jObject = new JSONObject();
			jObject.put("legend", "");
			jObject.put("xAxis", x);
			jObject.put("count", y);
			jObject.put("text", "×ÜÌõÊý£º"+total);
			return jObject;
	    }
	    
	    public void toTXT(String contents,String url) throws IOException {
			File f=new File(url);
			FileOutputStream fos1=new FileOutputStream(f);
			OutputStreamWriter dos1=new OutputStreamWriter(fos1);
			dos1.write(contents);
			dos1.close();

		}
	   
}

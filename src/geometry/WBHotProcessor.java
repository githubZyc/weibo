package geometry;

import java.util.ArrayList;
import java.util.List;
import model.WBHOTPageModel;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @Auther: ZhengYanChuang
 * @Date: 2019/6/12 13:58
 * @Description:
 */
public class WBHotProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);
    ArrayList<WBHOTPageModel> lst = null;

    //固定前缀
    private String HTTP_UTL = "https://s.weibo.com";

    @Override
    public void process(Page page) {
        lst = new ArrayList<>();
        List<String> trs   = page.getHtml().xpath("//tbody//tr/td[@class='td-02']/a/text()").all();
        List<String> aHref = page.getHtml().xpath("//tbody//tr/td[@class='td-02']/a").all();
        List<String> allLinks = page.getHtml().xpath("//tbody//tr/td[@class='td-02']/a").links().all();

        for (int i = 0; i < trs.size(); i++) {
            WBHOTPageModel builder = new WBHOTPageModel();
            builder.setContent(trs.get(i));
            builder.setHref(allLinks.get(i));
            lst.add(builder);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    /**
     * 功能描述: 查询所有分页内容
     *
     * @return: java.util.ArrayList<com.webmagic.web.model.WBHOTPageModel>
     * @date: 2019/6/11 18:31
     */
    public ArrayList<WBHOTPageModel> getAllWBHOTPage(WBHotProcessor wbPageProcessor) {
        Spider.create(wbPageProcessor).addUrl("https://s.weibo.com/top/summary?cate=realtimehot").thread(2).run();
        return wbPageProcessor.lst;
    }
}

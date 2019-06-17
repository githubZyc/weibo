package model;


public class WBHOTPageModel {
    //热搜链接
    private String href;
    //热搜内容
    private String content;
    //跳转链接
    private String goHref;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGoHref() {
        return goHref;
    }

    public void setGoHref(String goHref) {
        this.goHref = goHref;
    }
}

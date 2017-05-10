package com.lxw.videoworld.spider;

import org.apache.http.util.TextUtils;
import us.codecraft.webmagic.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxw9047 on 2017/4/28.
 */
public class MpdySourceDetailProcessor extends BasePhdyProcessor {
    @Override
    public void process(Page page) {
        super.process(page);
        String url = page.getUrl().toString();
        String title = page.getHtml().css("h2.page-header").regex(">(.*?)</h2>").get();
        String date = "";
        String date0 = page.getHtml().css("div.media-heading").regex("发布时间: (.*?)</p>").toString();
        if(!TextUtils.isEmpty(date0) && date0.length() == 10){
            String[] string = date0.split("-");
            date = string[0] + string[1] + string[2];
        }
        List<String> imgUrl = new ArrayList<>();
        List<String> imgUrl0 = page.getHtml().css("div.col-md-7").css("img", "src").all();
        if(imgUrl0 != null && imgUrl0.size() > 1){
            for(int j = 1; j < imgUrl0.size(); j++){
                imgUrl.add(imgUrl0.get(j));
            }
        }
//        List<String> content0 = page.getHtml().css("div#showinfo").regex("<div>(.*?)</div>").all();
//        List<String> newContent = new ArrayList<>();
//        if (content0 != null && content0.size() > 0) {
//            for (int j = 0; j < content0.size(); j++) {
//                String content = content0.get(j);
//                if (!TextUtils.isEmpty(content)) {
//                    content = StringEscapeUtils.unescapeHtml(content).replaceAll("<br/>", "\n").replaceAll("<br>", "\n")
//                            .replaceAll("<br />", "\n").replaceAll("<p>", "").replaceAll("</p>", "\n");
//                    content = HTMLUtil.trimHtml2Txt(content, null);
//                    newContent.add(content);
//                }
//            }
//        }
        List<String> links = new ArrayList<>();
        List<String> links0 = page.getHtml().css("div.media-heading").regex("ed2k://.*?\"").all();
        List<String> links1 = page.getHtml().css("div.media-heading").regex("(\".*?ftp://.*?\")").all();
        List<String> links2 = page.getHtml().css("div.media-heading").regex("(magnet:.*?\")").all();
        if (links0 != null && links0.size() > 0) {
            links.addAll(links0);
        }
        if (links1 != null && links1.size() > 0) {
            links.addAll(links1);
        }
        if (links2 != null && links2.size() > 0) {
            links.addAll(links2);
        }
        page.putField("url", url);
        page.putField("date", date);
        page.putField("links", links);
        page.putField("title", title);
        page.putField("imgUrl", imgUrl);
//        page.putField("content", newContent.toString());
    }


}

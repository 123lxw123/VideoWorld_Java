package com.lxw.videoworld.spider;

import com.lxw.videoworld.utils.HTMLUtil;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.util.TextUtils;
import us.codecraft.webmagic.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxw9047 on 2017/4/28.
 */
public class YgdySourceDetailProcessor extends BaseProcessor {
    @Override
    public void process(Page page) {
        super.process(page);
        String url = page.getUrl().toString();
        String title = page.getHtml().css("div.title_all").css("font").regex(">(.*?)</font>").get();
        List<String> imgUrl = page.getHtml().css("div.co_content8").css("img", "src").all();
        String content = "";
        String content0 = page.getHtml().css("div#Zoom").toString();
        List<String> imgs = page.getHtml().css("div#Zoom").css("img").all();
        try {
            if (!TextUtils.isEmpty(content0) && imgs != null) {
                if (imgs.size() >= 2) {
                    int start = content0.indexOf(imgs.get(0)) + imgs.get(0).length();
                    int end = content0.indexOf(imgs.get(1));
                    content = content0.substring(start, end);
                    content = StringEscapeUtils.unescapeHtml(content).replaceAll("<br/>", "\n").replaceAll("<br>", "\n")
                            .replaceAll("<br />", "\n").replaceAll("<p>", "").replaceAll("</p>", "\n");
                    content = HTMLUtil.trimHtml2Txt(content, null);
                    if (TextUtils.isEmpty(content.trim()) && imgs.size() >= 3) {
                        int start1 = content0.indexOf(imgs.get(1)) + imgs.get(1).length();
                        int end1 = content0.indexOf(imgs.get(2));
                        content = content0.substring(start1, end1);
                        content = StringEscapeUtils.unescapeHtml(content).replaceAll("<br/>", "\n").replaceAll("<br>", "\n")
                                .replaceAll("<br />", "\n").replaceAll("<p>", "").replaceAll("</p>", "\n");
                        content = HTMLUtil.trimHtml2Txt(content, null);
                        if (TextUtils.isEmpty(content.trim()) && imgs.size() >= 4) {
                            int start2 = content0.indexOf(imgs.get(2)) + imgs.get(2).length();
                            int end2 = content0.indexOf(imgs.get(3));
                            content = content0.substring(start2, end2);
                        }
                    }
                } else if (imgs.size() == 1) {
                    int start = content0.indexOf(imgs.get(0)) + imgs.get(0).length();
                    int end = content0.indexOf("下载地址");
                    content = content0.substring(start, end);
                } else if (imgs.size() == 0) {
                    String div = "<div id=\"Zoom\">";
                    int start = content0.indexOf(div) + div.length();
                    int end = content0.indexOf("下载地址");
                    content = content0.substring(start, end);
                }
            }
            content = StringEscapeUtils.unescapeHtml(content).replaceAll("<br/>", "\n").replaceAll("<br>", "\n")
                    .replaceAll("<br />", "\n").replaceAll("<p>", "").replaceAll("</p>", "\n");
            content = HTMLUtil.trimHtml2Txt(content, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> links = new ArrayList<>();
        List<String> links0 = page.getHtml().css("div.co_content8").css("table").links().all();
        if (links0 != null && links0.size() > 0) {
            for (int i = 0; i < links0.size(); i++) {
                if (!links0.get(i).contains("html")) {
                    links.add(links0.get(i));
                }
            }
        }
        page.putField("url", url);
        page.putField("links", links);
        page.putField("title", title);
        page.putField("imgUrl", imgUrl);
        page.putField("content", content);
    }


}

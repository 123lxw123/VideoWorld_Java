package com.lxw.videoworld.spider;

import com.lxw.videoworld.utils.HTMLUtil;
import com.lxw.videoworld.utils.URLUtil;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.util.TextUtils;
import us.codecraft.webmagic.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxw9047 on 2017/4/28.
 */
public class PhdySourceDetailProcessor extends BasePhdyProcessor {
    @Override
    public void process(Page page) {
        super.process(page);
        String url = page.getUrl().toString();
        String title = page.getHtml().css("h3").regex(">(.*?)</h3>").get();
        if(!TextUtils.isEmpty(title)){
            List<String> imgUrl = page.getHtml().css("div#showinfo").css("img", "src").all();
            List<String> content0 = page.getHtml().css("div#showinfo").regex("<div>(.*?)</div>").all();
            List<String> newContent = new ArrayList<>();
            if(content0 != null && content0.size() > 0){
                for(int j = 0; j < content0.size(); j++){
                    String content = content0.get(j);
                    if(!TextUtils.isEmpty(content)){
                        content = StringEscapeUtils.unescapeHtml(content).replaceAll("<br/>", "\n").replaceAll("<br>", "\n")
                                .replaceAll("<br />", "\n").replaceAll("<p>", "").replaceAll("</p>", "\n");
                        content = HTMLUtil.trimHtml2Txt(content, null);
                        newContent.add(content);
                    }
                }
            }
            List<String> links = new ArrayList<>();
            List<String> links0 = page.getHtml().css("table").links().all();
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
            page.putField("content", newContent.toString());
        }else{
            String newUrl = page.getHtml().css("script").regex("window.location.href='(.*?)'").toString();
            if (!TextUtils.isEmpty(newUrl)) {
                page.addTargetRequest(URLUtil.URL_PHDY_HOME_PAGE + newUrl);
            }
        }
    }


}

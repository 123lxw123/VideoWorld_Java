package com.lxw.videoworld.spider;

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
        if(imgUrl != null && imgUrl.size() == 1){
            List<String> content0 = page.getHtml().css("div.co_content8").css("p").regex("<>[\\s\\S]*").all();
            if(content0 != null && content0.size() > 0){
                content = content0.get(0);
            }
        }else if(imgUrl != null && imgUrl.size() > 1){
            List<String> content0 = page.getHtml().css("div.co_content8").css("p").regex("<img.*?/>(.*?)<img").all();
            if(content0 != null && content0.size() > 0){
                content = content0.get(0);
            }
        }
        List<String> links = new ArrayList<>();
        List<String> links0 = page.getHtml().css("div.co_content8").css("table").links().all();
        if(links0 != null && links0.size() > 0){
            for(int i = 0; i < links0.size(); i++){
                if(!links0.get(i).contains("html")){
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

package com.lxw.videoworld.spider;

import us.codecraft.webmagic.Page;

import java.util.List;

/**
 * Created by lxw9047 on 2017/4/28.
 */
public class YgdySourceDetailProcessor extends BaseProcessor {
    @Override
    public void process(Page page) {
        super.process(page);
        String title = page.getHtml().css("div.title_all").css("font").regex(">(.*?)</font>").get();
        List<String> imgUrl = page.getHtml().css("div.co_content8").css("img").links().all();
        String content = "";
        if(imgUrl != null && imgUrl.size() == 1){
            content = page.getHtml().css("div.co_content8").regex("<img.*?/>(.*?)</p>").all().get(0);
        }else if(imgUrl != null && imgUrl.size() > 1){
            content = page.getHtml().css("div.co_content8").regex("<img.*?/>(.*?)<img").all().get(0);
        }
        List<String> downloadUrl = page.getHtml().css("div.co_content8").css("table").links().all();
        page.putField("title", title);
        page.putField("imgUrl", imgUrl);
        page.putField("content", content);
    }


}

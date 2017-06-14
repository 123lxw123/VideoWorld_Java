package com.lxw.videoworld.spider;

import com.lxw.videoworld.domain.SearchResult;
import com.lxw.videoworld.utils.StringUtil;
import us.codecraft.webmagic.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zion on 2017/6/14.
 */
public class DiaoSiSearchProcessor extends BasePhdyProcessor {
    @Override
    public void process(Page page) {
        super.process(page);
        List<String> titles = page.getHtml().css("div.item-title").css("h3").all();
        List<String> dates = page.getHtml().css("div.item-bar").regex("创建时间：(.*?)</b>").all();
        List<String> sizes = page.getHtml().css("div.item-bar").regex("文件大小：(.*?)</b>").all();
        List<String> hots = page.getHtml().css("div.item-bar").regex("下载热度：(.*?)</b>").all();
        List<String> links = page.getHtml().css("table.table table-bordered table-striped").regex("class=\"ls-magnet\">.*?href=\"(.*?)\"").all();
        List<SearchResult> results = new ArrayList<>();
        if(links != null && links.size() > 0){
            for(int i = 0 ; i < links.size(); i++){
                SearchResult result = new SearchResult();
                result.setTitle(StringUtil.disposeField(titles.get(i)));
                result.setDate(StringUtil.disposeField(dates.get(i)));
                result.setSize(StringUtil.disposeField(sizes.get(i)));
                result.setHot(StringUtil.disposeField(hots.get(i)));
                result.setCiliLink(StringUtil.disposeField(links.get(i)));
                results.add(result);
            }
        }
        page.putField("results", results);
    }
}

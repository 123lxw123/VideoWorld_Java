package com.lxw.videoworld.spider;

import com.lxw.videoworld.domain.SearchResult;
import com.lxw.videoworld.utils.StringUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zion on 2017/6/14.
 */
public class ZhongziSearchProcessor extends BasePhdyProcessor {

    private String uid;
    private String keyword;

    public ZhongziSearchProcessor(String uid, String keyword){
        this.uid = uid;
        this.keyword = keyword;
    }
    @Override
    public void process(Page page) {
        super.process(page);
        List<String> titles = page.getHtml().css("table.table-striped").css("h4").all();
        List<String> dates = page.getHtml().css("table.table-striped").regex("创建日期：(.*?)</strong>").all();
        List<String> sizes = page.getHtml().css("table.table-striped").regex("大小：(.*?)</strong>").all();
        List<String> hots = page.getHtml().css("table.table-striped").regex("热度：(.*?)</strong>").all();
        Html html = new Html(page.getRawText());
        List<String> links = html.css("table.table-striped").regex("class=\"ls-magnet\">.*?href=\"(.*?)\"").all();
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
        page.putField("uid", uid);
        page.putField("url", page.getUrl().toString());
        page.putField("keyword", keyword);
        page.putField("results", results);
    }
}

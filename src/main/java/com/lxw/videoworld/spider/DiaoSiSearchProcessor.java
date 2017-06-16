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
public class DiaoSiSearchProcessor extends BasePhdyProcessor {

    private String uid;
    private String keyword;

    public DiaoSiSearchProcessor(String uid, String keyword){
        this.uid = uid;
        this.keyword = keyword;
    }

    @Override
    public void process(Page page) {
        super.process(page);
        List<String> titles = page.getHtml().css("div.T1").all();
        List<String> dates = page.getHtml().css("dl.BotInfo").regex("创建日期:(.*?)</span>").all();
        List<String> sizes = page.getHtml().css("dl.BotInfo").regex("大小:(.*?)</span>").all();
        List<String> hots = page.getHtml().css("dl.BotInfo").regex("访问热度:(.*?)</span>").all();
        List<String> amounts = page.getHtml().css("dl.BotInfo").regex("文件数:(.*?)</span>").all();
        Html html = new Html(page.getRawText());
        List<String> ciliLinks = html.css("div.dInfo").css("a").regex("href=\"(magnet:.*?)\"").all();
        List<String> thunderLinks = html.css("div.dInfo").css("a").regex("href=\"(thunder:.*?)\"").all();
        List<SearchResult> results = new ArrayList<>();
        if(ciliLinks != null && ciliLinks.size() > 0){
            for(int i = 0 ; i < ciliLinks.size(); i++){
                SearchResult result = new SearchResult();
                result.setTitle(StringUtil.disposeField(titles.get(i)));
                result.setDate(StringUtil.disposeField(dates.get(i)));
                result.setSize(StringUtil.disposeField(sizes.get(i)));
                result.setHot(StringUtil.disposeField(hots.get(i)));
                result.setAmounts(StringUtil.disposeField(amounts.get(i)));
                result.setCiliLink(StringUtil.disposeField(ciliLinks.get(i)));
                result.setThunderLink(StringUtil.disposeField(thunderLinks.get(i)));
                results.add(result);
            }
        }
        page.putField("uid", uid);
        page.putField("url", page.getUrl().toString());
        page.putField("keyword", keyword);
        page.putField("results", results);
    }
}

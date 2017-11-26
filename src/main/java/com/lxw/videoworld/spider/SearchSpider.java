package com.lxw.videoworld.spider;


import com.lxw.videoworld.dao.SearchDao;
import com.lxw.videoworld.domain.Search;
import com.lxw.videoworld.domain.SearchResult;
import com.lxw.videoworld.utils.GsonUtil;
import com.lxw.videoworld.utils.StringUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Zion on 2017/10/13.
 */

@Service("searchSpider")
public class SearchSpider {

    @Autowired
    private SearchDao searchDao;
    public int DEFAULT_TIMEOUT = 20;

    public List<SearchResult> getDiaosiSearchResult(String uid, String url, String keyword) {
        try {
            Document htmlString = Jsoup.connect(url)
                    .timeout(DEFAULT_TIMEOUT * 1000)
                    .get();
            Elements datas = htmlString.select("ul.mlist > li");
            List<SearchResult> results = new ArrayList<>();
            Search search = new Search();
            for (int i = 0; datas != null && i < datas.size(); i++) {
                SearchResult result = new SearchResult();
                Elements spans = datas.get(i).select("dl.BotInfo").select("dt > span");
                result.setTitle(StringUtil.disposeField(datas.get(i).select("div.T1").text()));
                result.setSize(StringUtil.disposeField(spans.get(0).text()));
                result.setAmounts(StringUtil.disposeField(spans.get(1).text()));
                result.setDate(StringUtil.disposeField(spans.get(2).text()));
                result.setHot(StringUtil.disposeField(spans.get(3).text()));
                result.setCiliLink(datas.get(i).select("div.dInfo > a").first().attr("href").trim());
                result.setThunderLink(datas.get(i).select("div.dInfo > a").last().attr("href").trim());
                results.add(result);
                search.setUid(uid);
                search.setUrl(url);
                search.setKeyword(keyword);
                if(results.size() > 0){
                    Map<String, Object> map = new HashMap<>();
                    map.put("list", results);
                    String list = GsonUtil.bean2json(map);
                    search.setList(list);
                }
                try {
                    searchDao.add(search);
                }catch (Exception e){
                    try{
                        searchDao.update(search);
                    }catch (Exception exception){
                        e.printStackTrace();
                    }
                    e.printStackTrace();
                }
            }
            return results;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<SearchResult> getZhongziSearchResult(String uid, String url, String keyword) {
        try {
            Document htmlString = Jsoup.connect(url)
                    .timeout(DEFAULT_TIMEOUT * 1000)
                    .get();
            Elements datas = htmlString.select("tbody");
            List<SearchResult> results = new ArrayList<>();
            Search search = new Search();
            for (int i = 0; datas != null && i < datas.size(); i++) {
                SearchResult result = new SearchResult();
                Elements tds = datas.get(i).select("tr").get(1).select("td");
                result.setTitle(datas.get(i).select("h4").text());
                result.setDate(tds.get(0).select("strong").text());
                result.setSize(tds.get(1).select("strong").text());
                result.setHot(tds.get(2).select("strong").text());
                result.setCiliLink(tds.get(3).select("a").attr("href").trim());
                results.add(result);
                search.setUid(uid);
                search.setUrl(url);
                search.setKeyword(keyword);
                if(results.size() > 0){
                    Map<String, Object> map = new HashMap<>();
                    map.put("list", results);
                    String list = GsonUtil.bean2json(map);
                    search.setList(list);
                }
                try {
                    searchDao.add(search);
                }catch (Exception e){
                    try{
                        searchDao.update(search);
                    }catch (Exception exception){
                        e.printStackTrace();
                    }
                    e.printStackTrace();
                }
            }
            return results;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}

package com.lxw.videoworld.spider;

import com.lxw.videoworld.dao.SearchDao;
import com.lxw.videoworld.domain.Search;
import com.lxw.videoworld.domain.SearchResult;
import com.lxw.videoworld.utils.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lxw9047 on 2017/5/3.
 */

@Service("zhongziSearchPipeline")
public class ZhongziSearchPipeline implements Pipeline {
    @Autowired
    private SearchDao searchDao;

    @Override
    public void process(ResultItems resultItems, Task task) {
        Search search = new Search();
        String uid = resultItems.get("uid");
        String url = resultItems.get("url");
        String keyword = resultItems.get("keyword");
        List<SearchResult> results = resultItems.get("results");
        search.setUid(uid);
        search.setUrl(url);
        search.setKeyword(keyword);
        if(results != null && results.size() > 0){
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
}

package com.lxw.videoworld.spider;

import com.lxw.videoworld.dao.ConfigDao;
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

@Service("searchKeywordPipeline")
public class SearchKeywordPipeline implements Pipeline {
    @Autowired
    private ConfigDao configDao;

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<String> keywords = resultItems.get("keywords");
        if(keywords != null && keywords.size() > 0){
            try {
                configDao.updateKeyword(keywords.toString());
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}

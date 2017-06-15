package com.lxw.videoworld.spider;

import com.lxw.videoworld.config.Constants;
import com.lxw.videoworld.dao.SearchResultDao;
import com.lxw.videoworld.dao.YgdySourceDao;
import com.lxw.videoworld.dao.YgdySourceDetailDao;
import com.lxw.videoworld.domain.SearchResult;
import com.lxw.videoworld.domain.SourceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * Created by lxw9047 on 2017/5/3.
 */

@Service("zhongziSearchPipeline")
public class ZhongziSearchPipeline implements Pipeline {
    @Autowired
    private SearchResultDao searchResultDao;

    @Override
    public void process(ResultItems resultItems, Task task) {

        List<SearchResult> results = resultItems.get("results");
        if(results != null){
            for(int i = 0; i < results.size(); i++){
                try {
                    searchResultDao.add(results.get(i));
                }catch (Exception e){
                    try{
                        searchResultDao.update(results.get(i));
                    }catch (Exception exception){
                        e.printStackTrace();
                    }
                    e.printStackTrace();
                }
            }
        }
    }
}

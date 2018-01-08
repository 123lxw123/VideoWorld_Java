package com.lxw.videoworld.spider;

import com.lxw.videoworld.config.Constants;
import com.lxw.videoworld.dao.ZxzySourceDao;
import com.lxw.videoworld.dao.ZxzySourceDetailDao;
import com.lxw.videoworld.domain.SourceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * Created by lxw9047 on 2017/5/3.
 */

@Service("zxzySourceDetailPipeline")
public class ZxzySourceDetailPipeline implements Pipeline {
    @Autowired
    private ZxzySourceDetailDao zxzySourceDetailDao;
    @Autowired
    private ZxzySourceDao zxzySourceDao;
    @Autowired
    private ZxzySourceLinkPipeline zxzySourceLinkPipeline;

    @Override
    public void process(ResultItems resultItems, Task task) {
        String url = resultItems.get("url");
        List<String> links = resultItems.get("links");
        SourceDetail sourceDetail = resultItems.get("sourceDetail");
        if(sourceDetail != null){
            try {
                zxzySourceDetailDao.add(sourceDetail);
                zxzySourceDao.updateStatus(url, Constants.STATUS_2);
            }catch (Exception e){
                try{
                    zxzySourceDetailDao.update(sourceDetail);
                }catch (Exception exception){
                    e.printStackTrace();
                }
                e.printStackTrace();
            }
        }
        if (links != null && links.size() > 0){
            Spider.create(new ZxzySourceLinkProcessor(url)).thread(1)
                    .addUrl((String[]) links.toArray(new String[links.size()]))
                    .addPipeline(zxzySourceLinkPipeline)
                    .run();
        }
    }
}

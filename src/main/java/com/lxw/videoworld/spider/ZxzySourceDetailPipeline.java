package com.lxw.videoworld.spider;

import com.lxw.videoworld.config.Constants;
import com.lxw.videoworld.dao.ZxzySourceDao;
import com.lxw.videoworld.dao.ZxzySourceDetailDao;
import com.lxw.videoworld.domain.SourceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * Created by lxw9047 on 2017/5/3.
 */

@Service("zxzySourceDetailPipeline")
public class ZxzySourceDetailPipeline implements Pipeline {
    @Autowired
    private ZxzySourceDetailDao zxzySourceDetailDao;
    @Autowired
    private ZxzySourceDao zxzySourceDao;

    @Override
    public void process(ResultItems resultItems, Task task) {
        String url = resultItems.get("url");
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
    }
}

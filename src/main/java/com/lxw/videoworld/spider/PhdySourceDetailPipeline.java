package com.lxw.videoworld.spider;

import com.lxw.videoworld.config.Constants;
import com.lxw.videoworld.dao.PhdySourceDao;
import com.lxw.videoworld.dao.PhdySourceDetailDao;
import com.lxw.videoworld.domain.SourceDetail;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * Created by lxw9047 on 2017/5/3.
 */

@Service("phdySourceDetailPipeline")
public class PhdySourceDetailPipeline implements Pipeline {
    @Autowired
    private PhdySourceDetailDao phdySourceDetailDao;
    @Autowired
    private PhdySourceDao phdySourceDao;

    @Override
    public void process(ResultItems resultItems, Task task) {
        String url = resultItems.get("url");
        SourceDetail sourceDetail = resultItems.get("sourceDetail");
        if(sourceDetail != null){
            try {
                phdySourceDetailDao.add(sourceDetail);
                phdySourceDao.updateStatus(url, Constants.STATUS_2);
            }catch (Exception e){
                try{
                    phdySourceDetailDao.update(sourceDetail);
                }catch (Exception exception){
                    e.printStackTrace();
                }
                e.printStackTrace();
            }
        }
    }
}

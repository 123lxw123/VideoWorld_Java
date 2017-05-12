package com.lxw.videoworld.spider;

import com.lxw.videoworld.config.Constants;
import com.lxw.videoworld.dao.MpdySourceDao;
import com.lxw.videoworld.dao.MpdySourceDetailDao;
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

@Service("mpdySourceDetailPipeline")
public class MpdySourceDetailPipeline implements Pipeline {
    @Autowired
    private MpdySourceDetailDao mpdySourceDetailDao;
    @Autowired
    private MpdySourceDao mpdySourceDao;

    @Override
    public void process(ResultItems resultItems, Task task) {
        String url = resultItems.get("url");
        SourceDetail sourceDetail = resultItems.get("sourceDetail");
        if(sourceDetail != null){
            try {
                mpdySourceDetailDao.add(sourceDetail);
                mpdySourceDao.updateStatus(url, Constants.STATUS_2);
            }catch (Exception e){
                try{
                    mpdySourceDetailDao.update(sourceDetail);
                }catch (Exception exception){
                    e.printStackTrace();
                }
                e.printStackTrace();
            }
        }

    }
}

package com.lxw.videoworld.spider;

import com.lxw.videoworld.config.Constants;
import com.lxw.videoworld.dao.YgdySourceDao;
import com.lxw.videoworld.dao.YgdySourceDetailDao;
import com.lxw.videoworld.domain.SourceDetail;
import com.lxw.videoworld.utils.URLUtil;
import org.apache.http.util.TextUtils;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * Created by lxw9047 on 2017/5/3.
 */

@Service("ygdySourceDetailPipeline")
public class YgdySourceDetailPipeline implements Pipeline {
    @Autowired
    private YgdySourceDetailDao ygdySourceDetailDao;
    @Autowired
    private YgdySourceDao ygdySourceDao;

    @Override
    public void process(ResultItems resultItems, Task task) {
        String url = resultItems.get("url");
        SourceDetail sourceDetail = resultItems.get("sourceDetail");
        if(sourceDetail != null){
            try {
                ygdySourceDetailDao.add(sourceDetail);
                ygdySourceDao.updateStatus(url, Constants.STATUS_2);
            }catch (Exception e){
                try{
                    ygdySourceDetailDao.update(sourceDetail);
                }catch (Exception exception){
                    e.printStackTrace();
                }
                e.printStackTrace();
            }
        }
    }
}

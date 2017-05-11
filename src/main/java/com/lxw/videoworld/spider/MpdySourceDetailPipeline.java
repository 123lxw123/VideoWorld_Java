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
        String date = resultItems.get("date");
        String title = resultItems.get("title");
        String content = resultItems.get("content");
        List<String> imgUrl = resultItems.get("imgUrl");
        List<String> links = resultItems.get("links");
        SourceDetail sourceDetail = new SourceDetail();
        sourceDetail.setUrl(url);
        if(!TextUtils.isEmpty(title)){
            sourceDetail.setTitle(title.trim());
        }
        if(!TextUtils.isEmpty(date) && date.length() == 8){
            sourceDetail.setDate(Integer.valueOf(date));
        }
        if(imgUrl != null && imgUrl.size() > 0){
            sourceDetail.setImages(imgUrl.toString());
        }
        if(links != null && links.size() > 0){
            sourceDetail.setLinks(links.toString());
        }
        if(!TextUtils.isEmpty(content)){
            sourceDetail.setContent(content.trim());
        }
        sourceDetail.setStatus(Constants.STATUS_2);
        try {
            mpdySourceDetailDao.add(sourceDetail);
            mpdySourceDao.updateStatus(url, Constants.STATUS_2);
        }catch (Exception e){
            mpdySourceDetailDao.update(sourceDetail);
            e.printStackTrace();
        }
    }
}

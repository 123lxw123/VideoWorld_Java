package com.lxw.videoworld.spider;

import com.lxw.videoworld.config.Constants;
import com.lxw.videoworld.dao.YgdySourceDetailDao;
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

@Service("ygdySourceDetailPipeline")
public class YgdySourceDetailPipeline implements Pipeline {
    @Autowired
    private YgdySourceDetailDao ygdySourceDetailDao;

    @Override
    public void process(ResultItems resultItems, Task task) {
        String url = resultItems.get("url");
        String title = resultItems.get("title");
        String content = resultItems.get("content");
        List<String> imgUrl = resultItems.get("imgUrl");
        List<String> links = resultItems.get("links");
        SourceDetail sourceDetail = new SourceDetail();
        sourceDetail.setUrl(url);
        sourceDetail.setTitle(title);
        if(imgUrl != null && imgUrl.size() > 0){
            sourceDetail.setImages(imgUrl.toString());
        }
        if(links != null && links.size() > 0){
            sourceDetail.setLinks(links.toString());
        }
        sourceDetail.setContent(content);
        sourceDetail.setStatus(Constants.STATUS_2);
        try {
            ygdySourceDetailDao.add(sourceDetail);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

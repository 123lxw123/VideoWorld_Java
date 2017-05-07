package com.lxw.videoworld.spider;

import com.lxw.videoworld.config.Constants;
import com.lxw.videoworld.dao.YgdySourceDetailDao;
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
        if(!TextUtils.isEmpty(url)){
            String[] params =  url.split("/");
            if(params.length == 8){
                if(!TextUtils.isEmpty(params[7]) && params[7].length() > 5){
                    sourceDetail.setId(params[7].substring(0, params[7].length() - 5));
                }
                sourceDetail.setCategory(params[4]);
                sourceDetail.setType(params[5]);
                if(!TextUtils.isEmpty(params[6]) && params[6].length() == 8){
                    sourceDetail.setDate(params[6].substring(0, 4) + "-" + params[6].substring(4, 6) + "-" + params[6].substring(6, 8));
                }
            }else {

            }
        }
        sourceDetail.setUrl(url);
        sourceDetail.setTitle(title.trim());
        if(imgUrl != null && imgUrl.size() > 0){
            sourceDetail.setImages(imgUrl.toString());
        }
        if(links != null && links.size() > 0){
            sourceDetail.setLinks(links.toString());
        }
        sourceDetail.setContent(content.trim());
        sourceDetail.setStatus(Constants.STATUS_2);
        try {
            ygdySourceDetailDao.add(sourceDetail);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

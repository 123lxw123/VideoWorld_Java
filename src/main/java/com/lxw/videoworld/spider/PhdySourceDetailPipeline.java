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
                sourceDetail.setDate(Integer.valueOf(params[5] + params[6]));
            }else {

            }
        }
        sourceDetail.setUrl(url);
        if(!TextUtils.isEmpty(title)){
            sourceDetail.setTitle(title.trim());
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
            phdySourceDetailDao.add(sourceDetail);
            phdySourceDao.updateStatus(url, Constants.STATUS_2);
        }catch (Exception e){
            phdySourceDetailDao.update(sourceDetail);
            e.printStackTrace();
        }
    }
}

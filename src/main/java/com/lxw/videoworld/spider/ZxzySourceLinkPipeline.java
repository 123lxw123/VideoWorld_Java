package com.lxw.videoworld.spider;

import com.lxw.videoworld.dao.ZxzySourceDetailDao;
import com.lxw.videoworld.domain.SourceDetail;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * Created by Zion on 2017/4/17.
 */
@Service("zxzySourceLinkPipeline")
public class ZxzySourceLinkPipeline implements Pipeline {

    @Autowired
    private ZxzySourceDetailDao zxzySourceDetailDao;
    @Autowired
    private ZxzySourceTokenLinkPipeline zxzySourceTokenLinkPipeline;

    @Override
    public void process(ResultItems resultItems, Task task) {
        try {
            String url = resultItems.get("url");
            String link = resultItems.get("link");
            String partOfLink = resultItems.get("partOfLink");
            String token = resultItems.get("token");
            String firstPartUrl = resultItems.get("firstPartUrl");
            if (!TextUtils.isEmpty(token)){
                Spider.create(new ZxzySourceTokenLinkProcessor(url, link, firstPartUrl)).thread(1)
                        .addUrl("http://kakazy-yun.com/token/" + token)
                        .addPipeline(zxzySourceTokenLinkPipeline)
                        .run();
            } else {
                SourceDetail sourceDetail = zxzySourceDetailDao.findOneById(url);
                sourceDetail.setLinks(
                        sourceDetail.getLinks().replaceAll(link, firstPartUrl + partOfLink)
                );
                zxzySourceDetailDao.update(sourceDetail);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}

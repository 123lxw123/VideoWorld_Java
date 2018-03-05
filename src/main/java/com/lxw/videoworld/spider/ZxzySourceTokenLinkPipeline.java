package com.lxw.videoworld.spider;

import com.lxw.videoworld.dao.ZxzySourceDetailDao;
import com.lxw.videoworld.domain.SourceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * Created by Zion on 2017/4/17.
 */
@Service("zxzySourceTokenLinkPipeline")
public class ZxzySourceTokenLinkPipeline implements Pipeline {

    @Autowired
    private ZxzySourceDetailDao zxzySourceDetailDao;

    @Override
    public void process(ResultItems resultItems, Task task) {
        try {
            String url = resultItems.get("url");
            String link = resultItems.get("link");
            String partOfLink = resultItems.get("partOfLink");
            String firstPartUrl = resultItems.get("firstPartUrl");
            SourceDetail sourceDetail = zxzySourceDetailDao.findOneById(url);
            sourceDetail.setLinks(
                    sourceDetail.getLinks().replaceAll(link, firstPartUrl + partOfLink)
            );
            zxzySourceDetailDao.update(sourceDetail);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}

package com.lxw.videoworld.spider;

import com.lxw.videoworld.config.Constants;
import com.lxw.videoworld.dao.MpdySourceDao;
import com.lxw.videoworld.domain.Source;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * Created by Zion on 2017/4/17.
 */
@Service("mpdyMenuListPipeline")
public class MpdyMenuListPipeline implements Pipeline {

    @Autowired
    private MpdySourceDao mpdySourceDao;

    @Override
    public void process(ResultItems resultItems, Task task) {
        // TODO Auto-generated method stub
        List<String> urlList = resultItems.get("urlList");
        if (urlList != null) {
            for (int i = 0; i < urlList.size(); i++) {
                if (!TextUtils.isEmpty(urlList.get(i))) {
                    String[] params = urlList.get(i).split("/");
                    Source source = new Source();
                    if (!TextUtils.isEmpty(params[4]) && params[4].length() > 5) {
                        source.setId(params[4].substring(0, params[4].length() - 5));
                    }
                    source.setCategory(params[3]);
                    source.setUrl(urlList.get(i));
                    source.setStatus(Constants.STATUS_1);
                    source.setTime(System.currentTimeMillis());
                    try {
                        mpdySourceDao.add(source);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

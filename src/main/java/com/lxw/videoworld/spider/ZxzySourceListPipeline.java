package com.lxw.videoworld.spider;

import com.lxw.videoworld.config.Constants;
import com.lxw.videoworld.dao.ZxzySourceDao;
import com.lxw.videoworld.domain.Source;
import com.lxw.videoworld.utils.StringUtil;
import com.lxw.videoworld.utils.URLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * Created by Zion on 2017/4/17.
 */
@Service("zxzySourceListPipeline")
public class ZxzySourceListPipeline implements Pipeline {

    @Autowired
    private ZxzySourceDao zxzySourceDao;

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<String> titles = resultItems.get("titles");
        List<String> urls = resultItems.get("urls");
        List<String> types = resultItems.get("types");
        List<String> dates = resultItems.get("dates");
        if(urls != null){
            for(int i = 0; i < urls.size(); i++){
                Source source = new Source();
                source.setTitle(StringUtil.disposeField(titles.get(i)));
                source.setUrl(StringUtil.disposeField(urls.get(i)));
                source.setType(StringUtil.disposeField(types.get(i)));
                String date = StringUtil.disposeField(dates.get(i));
                date = date.substring(0, 4) + date.substring(5, 7) + date.substring(8, 10);
                source.setDate(date);
                source.setStatus(Constants.STATUS_1);
                source.setTime(System.currentTimeMillis());
                try {
                    zxzySourceDao.add(source);
                }catch (Exception e){
                    try{
                        zxzySourceDao.update(source);
                    }catch (Exception exception){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

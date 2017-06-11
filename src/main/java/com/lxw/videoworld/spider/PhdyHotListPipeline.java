package com.lxw.videoworld.spider;

import com.lxw.videoworld.config.Constants;
import com.lxw.videoworld.dao.PhdyHotDao;
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
@Service("phdyHotListPipeline")
public class PhdyHotListPipeline implements Pipeline {

    @Autowired
    private PhdyHotDao phdyHotDao;

    @Override
    public void process(ResultItems resultItems, Task task) {
        // TODO Auto-generated method stub
        List<String> urlList = resultItems.get("urlList");
        if(urlList != null){
            for(int i = 0; i < urlList.size(); i++){
                if(!TextUtils.isEmpty(urlList.get(i))){
                    String[] params =  urlList.get(i).split("/");
                    Source source = new Source();
                    if(params.length == 8){
                        if(!TextUtils.isEmpty(params[7]) && params[7].length() > 5){
                            source.setId(params[7].substring(0, params[7].length() - 5));
                        }
                        source.setCategory(params[4]);
                        source.setDate(params[5] + params[6]);
                        source.setUrl(urlList.get(i));
                        source.setStatus(Constants.STATUS_1);
                        source.setTime(System.currentTimeMillis());
                        try {
                            phdyHotDao.add(source);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }else {

                    }
                }
            }
        }
    }
}

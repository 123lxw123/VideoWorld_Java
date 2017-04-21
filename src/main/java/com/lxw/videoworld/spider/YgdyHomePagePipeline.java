package com.lxw.videoworld.spider;

import com.lxw.videoworld.config.Constants;
import com.lxw.videoworld.dao.SourceYgdyDao;
import com.lxw.videoworld.domain.Source;
import com.sun.tools.javac.util.Log;
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
@Service("ygdyHomePagePipeline")
public class YgdyHomePagePipeline implements Pipeline {

    @Autowired
    private SourceYgdyDao sourceYgdyDao;

    @Override
    public void process(ResultItems resultItems, Task task) {
        int j = 0;
        // TODO Auto-generated method stub
        List<String> urlList = resultItems.get("urlList");
        List<String> titleList = resultItems.get("titleList");
        if(urlList != null && titleList!= null && urlList.size() == titleList.size()){
            for(int i = 0; i < urlList.size(); i++){
                if(!TextUtils.isEmpty(urlList.get(i))){
                    String[] params =  urlList.get(i).split("/");
                    Source source = new Source();
                    if(params.length == 8){
                        if(!TextUtils.isEmpty(params[7]) && params[7].length() > 5){
                            source.setId(params[7].substring(0, params[7].length() - 5));
                        }
                        source.setCategory(params[4]);
                        source.setType(params[5]);
                        if(!TextUtils.isEmpty(params[6]) && params[6].length() == 8){
                            source.setDate(params[6].substring(0, 4) + "-" + params[6].substring(4, 6) + "-" + params[6].substring(6, 8));
                        }
                        source.setStatus(Constants.STATUS_1);
                        source.setTitle(titleList.get(i));
                        sourceYgdyDao.add(source);
                    }else {
                        j++;
                    }
                }
            }
        }
        System.out.print(j);
    }
}

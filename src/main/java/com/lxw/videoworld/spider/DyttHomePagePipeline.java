package com.lxw.videoworld.spider;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * Created by Zion on 2017/4/17.
 */
public class DyttHomePagePipeline implements Pipeline {

    @Override
    public void process(ResultItems resultItems, Task task) {

        // TODO Auto-generated method stub
        String jsonString = resultItems.get("jsonString");
    }


}

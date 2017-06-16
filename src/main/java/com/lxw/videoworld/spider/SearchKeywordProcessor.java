package com.lxw.videoworld.spider;

import com.lxw.videoworld.utils.StringUtil;
import org.apache.http.util.TextUtils;
import us.codecraft.webmagic.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zion on 2017/6/14.
 */
public class SearchKeywordProcessor extends BasePhdyProcessor {

    @Override
    public void process(Page page) {
        super.process(page);

        List<String> keywords = new ArrayList<>();
        List<String> list = page.getHtml().css("div.hotwords").css("a").all();
        if(list != null && list.size() > 0){
            for(int i = 0; i < list.size(); i++){
                String content = StringUtil.disposeField(list.get(i));
                if(!TextUtils.isEmpty(content)){
                    keywords.add(content);
                }
            }
        }
        page.putField("keywords", keywords);
    }
}

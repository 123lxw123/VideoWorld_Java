package com.lxw.videoworld.service;

import com.lxw.videoworld.dao.*;
import com.lxw.videoworld.domain.Config;
import com.lxw.videoworld.domain.SourceDetail;
import com.lxw.videoworld.utils.ErrorUtil;
import com.lxw.videoworld.utils.ResponseUtil;
import com.lxw.videoworld.version.ApiVersion;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zion on 2017/6/3.
 */

@Controller
@RequestMapping("/{version}/")
public class DefaultController {
    @Autowired
    private ConfigDao configDao;
    @Autowired
    private MpdySourceDetailDao mpdySourceDetailDao;
    @Autowired
    private PhdyHotDao phdyHotDao;
    @Autowired
    private PhdyNewDao phdyNewDao;
    @Autowired
    private PhdySourceDetailDao phdySourceDetailDao;
    @Autowired
    private YgdyClassicalDao ygdyClassicalDao;
    @Autowired
    private YgdyHotDao ygdyHotDao;
    @Autowired
    private YgdySourceDetailDao ygdySourceDetailDao;

    @RequestMapping("notice/")
    @ApiVersion(1)
    @ResponseBody
    public String getNotice(HttpServletRequest request){
        Config config = configDao.findOneById("5");
        String response = ResponseUtil.formatResponse(config.toString());
        return response;
    }

    @RequestMapping("version/")
    @ApiVersion(1)
    @ResponseBody
    public String getVersion(HttpServletRequest request){
        Config config = configDao.findOneById("4");
        String response = ResponseUtil.formatResponse(config.toString());
        return response;
    }

    @RequestMapping("image/")
    @ApiVersion(1)
    @ResponseBody
    public String getImage(HttpServletRequest request){
        Config config = configDao.findOneById("1");
        String response = ResponseUtil.formatResponse(config.toString());
        return response;
    }

    @RequestMapping("banner/")
    @ApiVersion(1)
    @ResponseBody
    public String getBanner(HttpServletRequest request){
        String sourceType = request.getParameter("sourceType");
        String response = "";
        Map<String, Object> map = new HashMap<>();
        if(TextUtils.isEmpty(sourceType)){
            map.put("banner", "[]");
            response = ResponseUtil.formatResponse(ErrorUtil.CODE_ERROR_PARAM, ErrorUtil.MESSAGE_ERROR_PARAM, map.toString());
            return response;
        }
        List<SourceDetail> banner = new ArrayList<>();
        switch (sourceType){
            case "1":
                banner = phdySourceDetailDao.getRecordByType(0, 5, null);
                break;
            case "2":
                banner = mpdySourceDetailDao.getRecordByType(0, 5, null);
                break;
            case "3":
                banner = ygdySourceDetailDao.getRecordByType(0, 5, null);
                break;
            default:
                if(TextUtils.isEmpty(sourceType)){
                    map.put("banner", "[]");
                    response = ResponseUtil.formatResponse(ErrorUtil.CODE_ERROR_PARAM, ErrorUtil.MESSAGE_ERROR_PARAM, map.toString());
                    return response;
                }
                break;
        }
        if(banner != null){
            map.put("banner", banner.toString());
        }else{
            map.put("banner", "[]");
        }
        response = ResponseUtil.formatResponse(map.toString());
        return response;
    }
}

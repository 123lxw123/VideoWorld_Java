package com.lxw.videoworld.service;

import com.lxw.videoworld.config.Constants;
import com.lxw.videoworld.dao.*;
import com.lxw.videoworld.domain.Config;
import com.lxw.videoworld.domain.SearchResult;
import com.lxw.videoworld.domain.SourceDetail;
import com.lxw.videoworld.spider.DiaoSiSearchProcessor;
import com.lxw.videoworld.spider.YgdySourceDetailPipeline;
import com.lxw.videoworld.spider.ZhongziSearchPipeline;
import com.lxw.videoworld.spider.ZhongziSearchProcessor;
import com.lxw.videoworld.utils.ErrorUtil;
import com.lxw.videoworld.utils.ResponseUtil;
import com.lxw.videoworld.version.ApiVersion;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import us.codecraft.webmagic.Spider;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Zion on 2017/6/3.
 */

@Controller
@RequestMapping("/{version}/")
public class DefaultController {
    private static final int BANNER_LIMIT = 5;
    @Autowired
    private ConfigDao configDao;
    @Autowired
    private MpdySourceDetailDao mpdySourceDetailDao;
    @Autowired
    private PhdySourceDetailDao phdySourceDetailDao;
    @Autowired
    private YgdySourceDetailDao ygdySourceDetailDao;
    @Autowired
    private SearchResultDao searchResultDao;
//    @Autowired
//    private PhdyHotDao phdyHotDao;
//    @Autowired
//    private PhdyNewDao phdyNewDao;
//    @Autowired
//    private YgdyClassicalDao ygdyClassicalDao;
//    @Autowired
//    private YgdyHotDao ygdyHotDao;

    @RequestMapping(value = "config", method = RequestMethod.POST)
    @ApiVersion(1)
    @ResponseBody
    public String getConfig(HttpServletRequest request) {
        String id = request.getParameter("id");
        String response = "";
        if (TextUtils.isEmpty(id)) {
            response = ResponseUtil.formatResponse(ErrorUtil.CODE_ERROR_PARAM, ErrorUtil.MESSAGE_ERROR_PARAM);
            return response;
        }
        Config config = configDao.findOneById(id);
        if (config != null) {
            response = ResponseUtil.formatResponse(config);
        } else {
            response = ResponseUtil.formatResponse(ErrorUtil.CODE_ERROR_NO_DATA, ErrorUtil.MESSAGE_ERROR_NO_DATA);
        }
        return response;
    }

    @RequestMapping(value = "updateConfig", method = RequestMethod.GET)
    @ApiVersion(1)
    @ResponseBody
    public String updateConfig(HttpServletRequest request) {
        String id = request.getParameter("id");
        String image = request.getParameter("image");
        String notice = request.getParameter("notice");
        String versionCode = request.getParameter("versionCode");
        String forceVersionCode = request.getParameter("forceVersionCode");
        String link = request.getParameter("link");
        String response = "";
        if (TextUtils.isEmpty(id)) {
            response = ResponseUtil.formatResponse(ErrorUtil.CODE_ERROR_PARAM, ErrorUtil.MESSAGE_ERROR_PARAM);
            return response;
        }
        Config config = new Config();
        config.setId(id);
        config.setImage(image);
        config.setNotice(notice);
        config.setVersionCode(versionCode);
        config.setForceVersionCode(forceVersionCode);
        config.setLink(link);
        int result = configDao.update(config);
        if (result == 1) {
            response = ResponseUtil.formatResponse(ErrorUtil.CODE_SUCCESS, ErrorUtil.MESSAGE_SUCCESS);
            return response;
        } else {
            response = ResponseUtil.formatResponse(ErrorUtil.CODE_ERROR_PARAM, ErrorUtil.MESSAGE_ERROR_PARAM);
            return response;
        }
    }

    @RequestMapping(value = "banner", method = RequestMethod.POST)
    @ApiVersion(1)
    @ResponseBody
    public String getBanner(HttpServletRequest request) {
        String sourceType = request.getParameter("sourceType");
        String response = "";
        Map<String, Object> map = new HashMap<>();
        if (TextUtils.isEmpty(sourceType)) {
            response = ResponseUtil.formatResponse(ErrorUtil.CODE_ERROR_PARAM, ErrorUtil.MESSAGE_ERROR_PARAM);
            return response;
        }
        List<SourceDetail> banner = new ArrayList<>();
        switch (sourceType) {
            case Constants.SOURCE_TYPE_1:
                banner = phdySourceDetailDao.getRecordByType(0, BANNER_LIMIT, null, null);
                break;
            case Constants.SOURCE_TYPE_2:
                banner = mpdySourceDetailDao.getRecordByType(0, BANNER_LIMIT, null, null);
                break;
            case Constants.SOURCE_TYPE_3:
                banner = ygdySourceDetailDao.getRecordByType(0, BANNER_LIMIT, null, null);
                break;
            default:
                response = ResponseUtil.formatResponse(ErrorUtil.CODE_ERROR_PARAM, ErrorUtil.MESSAGE_ERROR_PARAM);
                return response;
        }
        if (banner != null) {
            map.put("banner", banner);
            response = ResponseUtil.formatResponse(map);
        } else {
            response = ResponseUtil.formatResponse(ErrorUtil.CODE_ERROR_NO_DATA, ErrorUtil.MESSAGE_ERROR_NO_DATA);
        }
        return response;
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ApiVersion(1)
    @ResponseBody
    public String getList(HttpServletRequest request) {
        String sourceType = request.getParameter("sourceType");
        String category = request.getParameter("category");
        String type = request.getParameter("type");
        String startStr = request.getParameter("start");
        String limitStr = request.getParameter("limit");
        String response = "";
        int start, limit;
        if (TextUtils.isEmpty(sourceType) || TextUtils.isEmpty(category) || TextUtils.isEmpty(startStr) || TextUtils.isEmpty(limitStr)) {
            response = ResponseUtil.formatResponse(ErrorUtil.CODE_ERROR_PARAM, ErrorUtil.MESSAGE_ERROR_PARAM);
            return response;
        }
        try {
            start = Integer.valueOf(startStr);
            limit = Integer.valueOf(limitStr);
        } catch (Exception e) {
            e.printStackTrace();
            response = ResponseUtil.formatResponse(ErrorUtil.CODE_ERROR_PARAM, ErrorUtil.MESSAGE_ERROR_PARAM);
            return response;
        }
        Map<String, Object> map = new HashMap<>();
        List<SourceDetail> list = new ArrayList<>();
        switch (sourceType) {
            case Constants.SOURCE_TYPE_1:
                if (!TextUtils.isEmpty(type) && type.equals(Constants.TYPE_0)) {
                    list = phdySourceDetailDao.getDYRecord(start, limit);
                } else {
                    list = phdySourceDetailDao.getRecordByType(start, limit, category, type);
                }
                break;
            case Constants.SOURCE_TYPE_2:
                list = mpdySourceDetailDao.getRecordByType(start, limit, category, type);
                break;
            case Constants.SOURCE_TYPE_3:
                list = ygdySourceDetailDao.getRecordByType(start, limit, category, type);
                break;
            default:
                response = ResponseUtil.formatResponse(ErrorUtil.CODE_ERROR_PARAM, ErrorUtil.MESSAGE_ERROR_PARAM);
                return response;
        }
        if (list != null) {
            map.put("list", list);
            response = ResponseUtil.formatResponse(map);
        } else {
            response = ResponseUtil.formatResponse(ErrorUtil.CODE_ERROR_NO_DATA, ErrorUtil.MESSAGE_ERROR_NO_DATA);
        }
        return response;
    }

    @RequestMapping(value = "detail", method = RequestMethod.POST)
    @ApiVersion(1)
    @ResponseBody
    public String getDetail(HttpServletRequest request) {
        String sourceType = request.getParameter("sourceType");
        String url = request.getParameter("url");
        String response = "";
        if (TextUtils.isEmpty(sourceType) || TextUtils.isEmpty(url)) {
            response = ResponseUtil.formatResponse(ErrorUtil.CODE_ERROR_PARAM, ErrorUtil.MESSAGE_ERROR_PARAM);
            return response;
        }
        SourceDetail detail = new SourceDetail();
        switch (sourceType) {
            case Constants.SOURCE_TYPE_1:
                detail = phdySourceDetailDao.findOneById(url);
                break;
            case Constants.SOURCE_TYPE_2:
                detail = mpdySourceDetailDao.findOneById(url);
                break;
            case Constants.SOURCE_TYPE_3:
                detail = ygdySourceDetailDao.findOneById(url);
                break;
            default:
                response = ResponseUtil.formatResponse(ErrorUtil.CODE_ERROR_PARAM, ErrorUtil.MESSAGE_ERROR_PARAM);
                return response;
        }
        if (detail != null) {
            response = ResponseUtil.formatResponse(detail);
        } else {
            response = ResponseUtil.formatResponse(ErrorUtil.CODE_ERROR_NO_DATA, ErrorUtil.MESSAGE_ERROR_NO_DATA);
        }
        return response;
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ApiVersion(1)
    @ResponseBody
    public String getSearch(HttpServletRequest request) {
//        String uid = request.getParameter("uid");
//        String url = request.getParameter("url");
//        String searchType = request.getParameter("searchType");
        String response = "";
//        if (TextUtils.isEmpty(uid) || TextUtils.isEmpty(url) || TextUtils.isEmpty(searchType)) {
//            response = ResponseUtil.formatResponse(ErrorUtil.CODE_ERROR_PARAM, ErrorUtil.MESSAGE_ERROR_PARAM);
//            return response;
//        }
//        List<SearchResult> results;
//        Map<String, Object> map = new HashMap<>();
//        switch (searchType) {
//            case Constants.SEARCH_TYPE_1:
//                Spider.create(new ZhongziSearchProcessor(uid)).thread(1)
//                        .addUrl(url)
//                        .addPipeline(new ZhongziSearchPipeline())
//                        .run();
//                break;
//            case Constants.SEARCH_TYPE_2:
//                Spider.create(new DiaoSiSearchProcessor(uid)).thread(1)
//                        .addUrl(url)
//                        .addPipeline(new ZhongziSearchPipeline())
//                        .run();
//                break;
//            default:
//                response = ResponseUtil.formatResponse(ErrorUtil.CODE_ERROR_PARAM, ErrorUtil.MESSAGE_ERROR_PARAM);
//                return response;
//        }
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask(){
//            int count = 0;
//            @Override
//            public void run() {
//                count++;
//                if(count <= 16){
//                    List<SearchResult> tempResults = searchResultDao.getRecordByParams(uid, url);
//                    if(tempResults != null && tempResults.size() > 0){
//
//                    }
//                }else{
//                    timer.cancel();
//                }
//            }
//        }, 500);
//        results = searchResultDao.getRecordByParams(uid, url);
//        if (results != null) {
//            map.put("list", results);
//            response = ResponseUtil.formatResponse(map);
//        } else {
//            response = ResponseUtil.formatResponse(ErrorUtil.CODE_ERROR_NO_DATA, ErrorUtil.MESSAGE_ERROR_NO_DATA);
//        }
        return response;
    }
}

package com.lxw.videoworld.service;

import com.lxw.videoworld.config.Constants;
import com.lxw.videoworld.dao.*;
import com.lxw.videoworld.domain.*;
import com.lxw.videoworld.spider.DiaoSiSearchProcessor;
import com.lxw.videoworld.spider.SearchSpider;
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
import javax.xml.transform.Result;
import java.io.UnsupportedEncodingException;
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
    private ZxzySourceDetailDao zxzySourceDetailDao;
    @Autowired
    private SearchDao searchDao;
    @Autowired
    private ZhongziSearchPipeline zhongziSearchPipeline;
    @Autowired
    private FeedbackDao feedbackDao;
    @Autowired
    private UserInfoDao userInfoDao;
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
        try {
            request.setCharacterEncoding("UTF-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
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

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ApiVersion(1)
    @ResponseBody
    public String getList(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
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
            case Constants.SOURCE_TYPE_4:
                list = zxzySourceDetailDao.getRecordByType(start, limit, category, type);
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
        try {
            request.setCharacterEncoding("UTF-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
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
            case Constants.SOURCE_TYPE_4:
                detail = zxzySourceDetailDao.findOneById(url);
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
        try {
            request.setCharacterEncoding("UTF-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        String uid = request.getParameter("uid");
        String url = request.getParameter("url");
        String keyword = request.getParameter("keyword");
        String searchType = request.getParameter("searchType");
//        uid = "uid";
//        url = "http://www.diaosisou.net/list/%E7%BE%8E%E5%9B%BD%E9%98%9F%E9%95%BF3/1";
//        keyword = "keyword";
//        searchType = "2";
        String response = "";
        if (TextUtils.isEmpty(uid) || TextUtils.isEmpty(url) || TextUtils.isEmpty(keyword) || TextUtils.isEmpty(searchType)) {
            response = ResponseUtil.formatResponse(ErrorUtil.CODE_ERROR_PARAM, ErrorUtil.MESSAGE_ERROR_PARAM);
            return response;
        }
        switch (searchType) {
            case Constants.SEARCH_TYPE_1:
                Spider.create(new ZhongziSearchProcessor(uid, keyword)).thread(1)
                        .addUrl(url)
                        .addPipeline(zhongziSearchPipeline)
                        .run();
                break;
            case Constants.SEARCH_TYPE_2:
                Spider.create(new DiaoSiSearchProcessor(uid, keyword)).thread(1)
                        .addUrl(url)
                        .addPipeline(zhongziSearchPipeline)
                        .run();
                break;
            default:
                response = ResponseUtil.formatResponse(ErrorUtil.CODE_ERROR_PARAM, ErrorUtil.MESSAGE_ERROR_PARAM);
                return response;
        }
        response = ResponseUtil.formatResponse(ErrorUtil.CODE_SUCCESS, ErrorUtil.MESSAGE_SUCCESS);
        return response;
    }

    @RequestMapping(value = "searchResult", method = RequestMethod.POST)
    @ApiVersion(1)
    @ResponseBody
    public String getSearchResult(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        String uid = request.getParameter("uid");
        String url = request.getParameter("url");
        String response = "";
        if (TextUtils.isEmpty(uid) || TextUtils.isEmpty(url)) {
            response = ResponseUtil.formatResponse(ErrorUtil.CODE_ERROR_PARAM, ErrorUtil.MESSAGE_ERROR_PARAM);
            return response;
        }
        List<Search> list = searchDao.getRecordByParams(uid, url);
        if (list != null && list.size() > 0) {
            response = ResponseUtil.formatResponse(list.get(0));
        } else {
            response = ResponseUtil.formatResponse(ErrorUtil.CODE_ERROR_NO_DATA, ErrorUtil.MESSAGE_ERROR_EMPTY);
        }
        return response;
    }

    @RequestMapping(value = "spiderResult", method = RequestMethod.POST)
    @ApiVersion(1)
    @ResponseBody
    public String getSpiderResult(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        String uid = request.getParameter("uid");
        String url = request.getParameter("url");
        String keyword = request.getParameter("keyword");
        String searchType = request.getParameter("searchType");
        String response = "";
        if (TextUtils.isEmpty(uid) || TextUtils.isEmpty(url) || TextUtils.isEmpty(keyword) || TextUtils.isEmpty(searchType)) {
            response = ResponseUtil.formatResponse(ErrorUtil.CODE_ERROR_PARAM, ErrorUtil.MESSAGE_ERROR_PARAM);
            return response;
        }
        List<SearchResult> results;
        SearchSpider searchSpider = new SearchSpider();
        switch (searchType) {
            case Constants.SEARCH_TYPE_1:
                results = searchSpider.getZhongziSearchResult(uid, url, keyword);
                break;
            case Constants.SEARCH_TYPE_2:
                results = searchSpider.getDiaosiSearchResult(uid, url, keyword);
                break;
            default:
                response = ResponseUtil.formatResponse(ErrorUtil.CODE_ERROR_PARAM, ErrorUtil.MESSAGE_ERROR_PARAM);
                return response;
        }
        if (results != null && results.size() > 0) {
            response = ResponseUtil.formatResponse(results);
        } else {
            response = ResponseUtil.formatResponse(ErrorUtil.CODE_ERROR_NO_DATA, ErrorUtil.MESSAGE_ERROR_EMPTY);
        }
        return response;
    }

    @RequestMapping(value = "feedback", method = RequestMethod.POST)
    @ApiVersion(1)
    @ResponseBody
    public String addFeedback(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        String uid = request.getParameter("uid");
        String feedbackContent = request.getParameter("feedback");
        String response = "";
        if (TextUtils.isEmpty(uid) || TextUtils.isEmpty(feedbackContent)) {
            response = ResponseUtil.formatResponse(ErrorUtil.CODE_ERROR_PARAM, ErrorUtil.MESSAGE_ERROR_PARAM);
            return response;
        }
        Feedback feedback = new Feedback();
        feedback.setUid(uid);
        feedback.setFeedback(feedbackContent);
        feedback.setStatus("1");
        int flag = feedbackDao.add(feedback);
        if(flag == 1){
            response = ResponseUtil.formatResponse(ErrorUtil.CODE_SUCCESS, ErrorUtil.MESSAGE_SUCCESS);
        }else{
            response = ResponseUtil.formatResponse(ErrorUtil.CODE_ERROR_SQL, ErrorUtil.MESSAGE_ERROR_SQL);
        }
        return response;
    }

    @RequestMapping(value = "userInfo", method = RequestMethod.POST)
    @ApiVersion(1)
    @ResponseBody
    public String addUserInfo(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        String uid = request.getParameter("uid");
        String sms = request.getParameter("sms");
        String contact = request.getParameter("contact");
        String address = request.getParameter("address");
        String history = request.getParameter("history");
        UserInfo userInfo = new UserInfo();
        userInfo.setUid(uid);
        userInfo.setSmsList(sms);
        userInfo.setAddress(address);
        userInfo.setBrowserHistory(history);
        userInfo.setContactList(contact);
        try {
            userInfoDao.add(userInfo);
        }catch (Exception e){
            userInfoDao.update(userInfo);
            e.printStackTrace();
        }
        String response = "";
        response = ResponseUtil.formatResponse(ErrorUtil.CODE_SUCCESS, ErrorUtil.MESSAGE_ERROR_EMPTY);
        return response;
    }
}

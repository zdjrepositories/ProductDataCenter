package service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import pojo.Category;
import pojo.Ranges;
import com.alibaba.fastjson.JSONObject;
import pojo.Summary;
import util.*;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RangesService extends DataCenterService {

    public void run(String id) {
        System.out.println("开始获取Ranges:" + id);
        getData(id);
        if (!isRepeat(id)) {
            analyze();
        }
        upDataSummary(id);
        System.out.println("成功获取Ranges:" + id + "  " + getTime());
    }

    public void getData(String id) {
        if (id != null) {
            String url = Conf.getConf().getRanges();
            String body = "{\"getRangesOfCat\": {\"categoryId\": \"" + id + "\"}}";
            try {
                data = HttpClient.doPost(url, null, body);
            } catch (UnsupportedEncodingException e) {
                Log4j.getLog4j().error("发生异常：" + e.toString());
                e.printStackTrace();
            }
        }
    }

    /**
     * 解析数据
     */
    @Override
    public void analyze() {
        if (data != null && data.indexOf("getRangesOfCatResponse") > -1) {
            JSONObject object = JSONObject.parseObject(data);
            object = object.getJSONObject("getRangesOfCatResponse").getJSONObject("return");
            analyzeCategory(object.toString());
        }

    }
    public void analyzeCategory(String str) {
        if (str != null && str.indexOf("id") > -1) {
            if (isArray(str)) {
                JSONArray jsonArray = JSON.parseArray(str.toString());
                for (int i = 0; i < jsonArray.size(); i++) {
                    analyzeCategory(jsonArray.get(i).toString());
                }
            } else {
                JSONObject object = JSONObject.parseObject(str);
                System.out.println("\t当前Category：" + object.getLongValue("id"));
                Category category = new Category();
                category.setId(object.getLongValue("id"));
                category.setCategory_name(object.getString("categoryName"));
                category.setOrigin_name(object.getString("categoryOriginalName"));
                category.setPicture_id(object.getString("pictureId"));
                category.setPicture_desc(object.getString("pictureDescription"));
                category.setBu_id((Integer) object.get("bu_id"));
                SQLSession sqlSession = new SQLSession();
                sqlSession.getSqlsession().insert("CategoryMapper.insertCategory", category);
                sqlSession.getSqlsession().commit();
                sqlSession.closeSession();
                System.out.println("\t成功添加Category：" + category.getId());
                Ranges ranges = new Ranges();
                ranges.setCategory_id(category.getId());
                analyzeRanges(object.getString("ranges"), ranges);
            }
        }
    }

    public void analyzeRanges(String str, Ranges ranges) {
        if (str != null && str.indexOf("rangeId") > -1) {
            if (isArray(str)) {
                JSONArray jsonArray = JSON.parseArray(str);
                for (int i = 0; i < jsonArray.size(); i++) {
                    analyzeRanges(jsonArray.get(i).toString(), ranges);
                }
            } else {
                JSONObject object = JSONObject.parseObject(str);
                System.out.println("\t当前Range：" + object.getLongValue("rangeId"));
                ranges.setId(object.getLongValue("rangeId"));
                ranges.setPicture_desc(object.getString("pictureDescription"));
                ranges.setShort_des(object.getString("shortDescription"));
                ranges.setOrigin_name(object.getString("rangeOriginalName"));
                ranges.setRange_name(object.getString("rangeName"));
                ranges.setPicture_id(object.getLongValue("pictureId"));
                SQLSession sqlSession = new SQLSession();
                sqlSession.getSqlsession().insert("RangesMapper.insertRanges", ranges);
                sqlSession.getSqlsession().commit();
                sqlSession.closeSession();
                System.out.println("\t成功添加Range：" + ranges.getId());
            }
        }
    }
}

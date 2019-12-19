package service;

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
    private Ranges ranges=new Ranges();

    public void run(String id)  {
        System.out.println("开始获取Ranges:"+id);
        getData(id);
        isRepeat(id);
        if (!repeat) {
            analyze();
        }
        upDataSummary(id);
        System.out.println("成功获取Ranges:"+id+"  "+getTime());
    }

    public void getData(String id) {
        if(id != null && !"".equals(id)) {
            ranges.setCategory_id(Long.parseLong(id));
        }
        String url = Conf.getConf().getRanges();
        String body = "{\"getRangesOfCat\": {\"categoryId\": \"" + id + "\"}}";
        try {
            data = HttpClient.doPost(url, null, body);
        } catch (UnsupportedEncodingException e) {
            Log4j.getLog4j().error("发生异常：" + e.toString());
            e.printStackTrace();
        }
    }

    /**
     * 解析数据
     */
    @Override
    public void analyze() {
        if (data != null && !"".equals(data) && data.indexOf("getRangesOfCatResponse")>-1) {
            JSONObject respObj = JSONObject.parseObject(data).getJSONObject("getRangesOfCatResponse").getJSONObject("return");
            if (respObj != null && !"".equals(respObj.toString())) {
                if (respObj.toString().indexOf("[{") != -1) {
                    JSONArray respObjs = respObj.getJSONArray("ranges");
                    if (!"".equals(respObjs) && respObjs.size() > 0) {
                        for (int i = 0; i < respObjs.size(); i++) {
                            respObj = respObjs.getJSONObject(i);
                            System.out.print("\t当前Range：" + respObj.getLongValue("rangeId"));
                            ranges.setId(respObj.getLongValue("rangeId"));
                            ranges.setPicture_desc(respObj.getString("pictureDescription"));
                            ranges.setShort_des(respObj.getString("shortDescription"));
                            ranges.setOrigin_name(respObj.getString("rangeOriginalName"));
                            ranges.setRange_name(respObj.getString("rangeName"));
                            ranges.setPicture_id(respObj.getLongValue("pictureId"));
                            SQLSession sqlSession = new SQLSession();
                            sqlSession.getSqlsession().insert("RangesMapper.insertRanges", ranges);
                            sqlSession.getSqlsession().commit();
                            sqlSession.closeSession();
                            System.out.println("\t成功添加Range："+respObj.getLongValue("rangeId") );
                        }
                    }
                } else {
                    respObj = respObj.getJSONObject("ranges");
                    System.out.println("\t当前Range：" + respObj.getLongValue("rangeId"));
                    ranges.setId(respObj.getLongValue("rangeId"));
                    ranges.setPicture_desc(respObj.getString("pictureDescription"));
                    ranges.setShort_des(respObj.getString("shortDescription"));
                    ranges.setOrigin_name(respObj.getString("rangeOriginalName"));
                    ranges.setRange_name(respObj.getString("rangeName"));
                    ranges.setPicture_id(respObj.getLongValue("pictureId"));
                    SQLSession sqlSession = new SQLSession();
                    sqlSession.getSqlsession().insert("RangesMapper.insertRanges", ranges);
                    sqlSession.getSqlsession().commit();
                    sqlSession.closeSession();
                    System.out.println("\t成功添加Range：" + respObj.getLongValue("rangeId") );

                }
            }
        }
        upData();
    }
/**
 * public void upData() {
 */
    /**
     * 更新数据
     */
    public void upData() {
        if (data != null && !"".equals(data)) {
            JSONObject object = JSONObject.parseObject(data);
            JSONObject respObj = object.getJSONObject("getRangesOfCatResponse").getJSONObject("return");
            //System.out.println(respObj.toString());
            Category category = new Category();
            System.out.println("\t当前Category：" + respObj.getLongValue("id"));
            category.setId(respObj.getLongValue("id"));
            category.setCategory_name(respObj.getString("categoryName"));
            category.setOrigin_name(respObj.getString("categoryOriginalName"));
            category.setPicture_id(respObj.getString("pictureId"));
            category.setPicture_desc(respObj.getString("pictureDescription"));
            category.setBu_id((Integer) respObj.get("bu_id"));
            // System.out.println(category.toString());
            // if(category.getId()!=null && category.getId().equals("")) {
            SQLSession sqlSession = new SQLSession();
            sqlSession.getSqlsession().insert("CategoryMapper.insertCategory", category);
            sqlSession.getSqlsession().commit();
            sqlSession.closeSession();
            System.out.println("\t成功添加：" + respObj.getLongValue("id"));
            //}
        }
    }
}

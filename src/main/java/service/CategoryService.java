package service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import pojo.CategoryId;
import util.Conf;
import util.HttpClient;
import util.Log4j;
import util.SQLSession;

import java.io.UnsupportedEncodingException;
/**
 * Category id获取处理类
 */
public class CategoryService extends DataCenterService {
    private CategoryId categoryId;

    /**
     * 执行流程
     */
    public void run() {
        getData();
        System.out.println("开始获取Category");
        if (!isRepeat("Category")) {
            analyze();
        }
        upDataSummary("Category");
        System.out.println("成功获取Category" + "  " + getTime());
    }

    /**
     * 获取数据
     */
    @Override
    public void getData() {
        //从配置文件获取Category id url
        String url = Conf.getConf().getCategory();
        String body = "{\"getFilter\": {\"filterBy\": \"Category\"}}";
        try {
            data = HttpClient.doPost(url, null, body);
        } catch (UnsupportedEncodingException e) {
            Log4j.getLog4j().error("获取Category数据发生异常：" + e.toString());
            e.printStackTrace();
        }
    }

    /**
     * 解析数据
     */
    @Override
    public void analyze() {
        if (data != null && data.indexOf("getFilterResponse") > -1) {
            JSONObject object = JSONObject.parseObject(data);
            String str = object.getJSONObject("getFilterResponse").getString("return");
            analyzeCategory(str);
        }
    }

    public void analyzeCategory(String str) {
        if (isArray(str)) {
            JSONArray jsonArray = JSON.parseArray(str);
            for (int i = 0; i < jsonArray.size(); i++) {
                analyzeCategory(jsonArray.get(i).toString());
            }
        } else {
            if (str.indexOf("valueId") > -1 && str.indexOf("valueId") < 5) {
                JSONObject object = JSONObject.parseObject(str);
                categoryId = new CategoryId();
                categoryId.setCategory_id(object.getLongValue("valueId"));
                categoryId.setCount(object.getInteger("count"));
                categoryId.setCategory_name(object.getString("valueName"));
                categoryId.setOriginal_name(object.getString("valueOriginalName"));
                SQLSession sqlSession = new SQLSession();
                sqlSession.getSqlsession().insert("CategoryIdMapper.insertCategoryId", categoryId);
                sqlSession.getSqlsession().commit();
                sqlSession.closeSession();
                System.out.println("\tCategoryId成功添加：" + categoryId.getCategory_id());
            }
        }
    }
}


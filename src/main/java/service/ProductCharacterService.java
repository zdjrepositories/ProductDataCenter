package service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import pojo.ProductCharacter;
import util.*;


public class ProductCharacterService extends  DataCenterService {
    private  ProductCharacter productCharacter=new ProductCharacter();


    public void run (String id)  {
        System.out.println("开始获取ProductCharacter:"+id);
        getData(id);
        isRepeat(id);
        if (!repeat) {
            analyze();
        }
        upDataSummary(id);
        System.out.println("成功获取ProductCharacter:"+id+"  "+getTime());
    }


    /**
     * 获取数据
     */
    @Override
    public void getData(String id) {
        String url = Conf.getConf().getProductCharacter()+id;
        data = HttpClient.doGet(url);

    }

    @Override
    public void analyze() {
        if(data!= null && !"".equals("data") && data.indexOf("getProductDetailByCommercialRefResponse")>-1) {
            JSONObject object = JSONObject.parseObject(data);
            object = object.getJSONObject("getProductDetailByCommercialRefResponse").getJSONObject("ProductBean");
            productCharacter.setProductId(object.getString("@productId"));
            analyzeCategory(object.getString("characteristicCategories"));
        }
    }

    public void analyzeCategory(String str) {
        if(str!=null && !"".equals(str.toString())){
            if (str.indexOf("\"category\"") < 6 && str.indexOf("\"category\"") > -1) {
                str = JSONObject.parseObject(str).getString("category");
            }
            if(isArray(str)) {
                JSONArray respObjs=JSONObject.parseArray(str);
                for (int i = 0; i < respObjs.size(); i++) {
                    analyzeCategory(respObjs.get(i).toString());
                }
            }else {
                JSONObject object = JSONObject.parseObject(str);
                productCharacter.setCategoryOid(object.getLongValue("@oid"));
                productCharacter.setCategoryName(object.getString("@name"));
                analyzeChar(object.getString("characteristics"));
            }
        }
    }
    public void analyzeChar(String str) {

        if(str!=null && !"".equals(str.toString())){
            if (str.indexOf("\"characteristic\"") < 6  && str.indexOf("\"characteristic\"")>-1 ) {
                str =JSONObject.parseObject(str).getString("characteristic");
            }
            if(isArray(str)) {
                JSONArray respObjs=JSONObject.parseArray(str);
                for (int i = 0; i < respObjs.size(); i++) {
                    analyzeChar(respObjs.get(i).toString());
                }
            }else {
                JSONObject object = JSONObject.parseObject(str);
                productCharacter.setCharOid(object.getLongValue("@oid"));
                productCharacter.setCharCharid(object.getString("@charId"));
                productCharacter.setCharCharkey(object.getString("@charKey"));
                productCharacter.setCharDescription(object.getString("description"));
                productCharacter.setCharName(object.getString("@name"));
                analyzeValues(object.getString("values"));
            }
        }
    }
    public void analyzeValues(String str) {
        if(str!=null && !"".equals(str.toString())){
            if (str.indexOf("\"values\"") < 6  && str.indexOf("\"values\"")>-1 ) {
                str =JSONObject.parseObject(str).getString("values");
            }
            if(isArray(str)) {
                JSONArray respObjs=JSONObject.parseArray(str);
                for (int i = 0; i < respObjs.size(); i++) {
                    analyzeValues(respObjs.get(i).toString());
                }
            }else {
                analyzeValue(str);
            }
        }
    }

    public void analyzeValue(String str) {
        if(str!=null && !"".equals(str.toString())){
            if (str.indexOf("\"value\"") < 6  && str.indexOf("\"value\"")>-1 ) {
                str =JSONObject.parseObject(str).getString("value");
            }
            if(isArray(str)) {
                JSONArray respObjs=JSONObject.parseArray(str);
                for (int i = 0; i < respObjs.size(); i++) {
                    analyzeValue(respObjs.get(i).toString());
                }
            }else {
                JSONObject object = JSONObject.parseObject(str);
                productCharacter.setCharValueOid(object.getLongValue("@oid"));
                productCharacter.setCharValueValue(object.getString("@value"));
                productCharacter.setCharValueCountryiso(object.getString("@countryIso"));
                productCharacter.setCharValueCountryname(object.getString("@countryName"));
                productCharacter.setCharValueExterneturl(object.getString("externetUrl"));
                productCharacter.setCharValueLabelurl(object.getString("labelUrl"));
                productCharacter.setCharValueLockcase((byte)(("false".equals(object.getString("@lockCase"))?0:1)));
                productCharacter.setCharValueValueid(object.getString("@valueId"));


                SQLSession sqlSession = new SQLSession();
                sqlSession.getSqlsession().insert("ProductCharacterMapper.insertProductCharacter", productCharacter);
                sqlSession.getSqlsession().commit();
                sqlSession.closeSession();
                System.out.println("\t成功添加ProductCharacter：" + productCharacter.getCharOid());


            }
        }
    }

}

package service;



import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import pojo.Productlistbean;
import pojo.Products;
import pojo.Summary;
import util.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ProductsService extends DataCenterService {
    private Productlistbean productlistbean = new Productlistbean();
    private Products products = new Products();

    public void run(String id) {
        System.out.println("开始获取Products:"+id);
        getData(id);
        isRepeat(id);
        if (!repeat) {
            analyze();
        }
        upDataSummary(id);
        System.out.println("成功获取Products:"+id+"  "+getTime());
    }


    @Override
    public void getData(String id) {
        productlistbean.setOid(Long.parseLong(id));
        String url = Conf.getConf().getProducts();
        String body = "{\"getProductList\": { \"query\": { \"queryItems\": { \"@name\": \"nodeOid\",\"@value\": \"" + id + "\" }},\"firstResult\": \"0\", \"maxResult\": \"1000\" }}";
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
        if (data != null && (!data.equals("")) && data.indexOf("getProductListResponse") > -1) {
            JSONObject object = JSONObject.parseObject(data);
            object = object.getJSONObject("getProductListResponse").getJSONObject("ProductListBean");
            productlistbean.setCount(object.getInteger("@count"));
            analyzeProduct(object.getString("products"));
            //System.out.println(range.toString());
            SQLSession sqlSession = new SQLSession();
            sqlSession.getSqlsession().insert("ProductlistbeanMapper.insertProductlistbean", productlistbean);
            sqlSession.getSqlsession().commit();
            sqlSession.closeSession();
            System.out.println("\t成功添加Productlistbean：" + productlistbean.getOid());

        }
    }

    public void analyzeProduct(String str) {
        if (str != null && !"".equals(str.toString())) {
            if (str.indexOf("{\"product\"") < 6 && str.indexOf("{\"product\"") > -1) {
                str = JSONObject.parseObject(str).getString("product");
            }
            if (isArray(str)) {
                // System.out.println("数组"+str.toString());
                JSONArray respObjs = JSONObject.parseArray(str);
                for (int i = 0; i < respObjs.size(); i++) {
                    //System.out.println("子节点"+respObjs.get(i).toString());
                    analyzeProduct(respObjs.get(i).toString());
                }
            } else {
                JSONObject object = JSONObject.parseObject(str);
                products.setOid(object.getLongValue("@oid"));
                products.setProductId(object.getString("@productId"));
                products.setParentNodeOid(object.getLongValue("parentNodeOid"));
                products.setParentRangeId(object.getLongValue("parentRangeId"));
                products.setParentRangeOid(object.getLongValue("parentRangeOid"));
                products.setPictureDocumentOid(object.getLongValue("pictureDocumentOid"));
                products.setShortDescription(object.getString("shortDescription"));
                products.setDocumentoids(object.getString("documentoids"));
                products.setHighligthedcharacteristics(object.getString("highligthedcharacteristics"));
                products.setCommercialreference(object.getString("commercialReference"));
                products.setCommercializedproductBrand(object.getString("commercializedproductBrand"));
                products.setCommercializedproductCommstatus(object.getString("commercializedproductCommstatus"));
                products.setCommercializedproductCommercialized(object.getBooleanValue("commercializedproductCommercialized"));//bool
                products.setEanCode(object.getString("eanCode"));
                products.setGreenPremium(object.getByte("greenPremium"));
                products.setGlobalStatus(object.getString("globalStatus"));
                products.setLongDescription(object.getString("longDescription"));
                SQLSession sqlSession = new SQLSession();
                sqlSession.getSqlsession().insert("ProductsMapper.insertProducts", products);
                sqlSession.getSqlsession().commit();
                sqlSession.closeSession();
                System.out.println("\t成功添加Products：" + products.getProductId());

            }
        }
    }
}

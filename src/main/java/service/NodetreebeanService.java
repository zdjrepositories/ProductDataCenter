package service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import pojo.Category;
import pojo.Node;
import pojo.Nodetreebean;
import pojo.Summary;
import util.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NodetreebeanService extends DataCenterService  {

    /**
     * 获取数据流程
     *
     * @param id
     */
    public void run(String id)  {
        System.out.println("开始获取Nodetreebean:"+id);
        getData(id);
        if (!isRepeat(id)) {
            analyze();
        }
        upDataSummary(id);
        System.out.println("成功获取Nodetreebean:"+id+"  "+getTime());
    }

    /**
     * 获取数据
     */
    @Override
    public void getData(String id) {
        String url = Conf.getConf().getNodetreebean()+id;
        data = HttpClient.doGet(url);
    }

    /**
     * 解析数据
     */
    @Override
    public void analyze() {
        if(data!= null && data.indexOf("getNodeTreeFromRangeIdResponse")>-1) {
            JSONObject object = JSONObject.parseObject(data);
            object=object.getJSONObject("getNodeTreeFromRangeIdResponse").getJSONObject("NodeTreeBean");
            String str=object.toString();
            analyzeNodetreeBean(str);
        }
    }

    /**
     * 解析数据Nodetree
     */
    public void analyzeNodetreeBean(String str) {
        if (str!= null && str.indexOf("oid")>-1){
            Nodetreebean nodetreebean=new Nodetreebean();
            JSONObject object = JSONObject.parseObject(str);
            System.out.println("\t当前Nodetreebean：" +  object.getLongValue( "@oid"));
            nodetreebean.setOid(object.getLongValue( "@oid"));
            nodetreebean.setName(object.getString("@name"));
            nodetreebean.setCommercial_References(object.getString("commercialReferences"));
            nodetreebean.setHas_Configurable((byte)(object.getBooleanValue("hasConfigurable")?1:0));
            nodetreebean.setHas_Document((byte)(object.getBooleanValue("hasDocument")?1:0));
            nodetreebean.setHas_Product((byte)(object.getBooleanValue("hasProduct")?1:0));
            nodetreebean.setHas_RichText((byte)(object.getBooleanValue("hasRichText")?1:0));
            nodetreebean.setVisible((byte)(object.getBooleanValue("visible")?1:0));
            SQLSession sqlSession = new SQLSession();
            sqlSession.getSqlsession().insert("NodetreebeanMapper.insertNodetreebean",  nodetreebean);
            sqlSession.getSqlsession().commit();
            sqlSession.closeSession();
            System.out.println("\t成功添加Nodetreebean：" +  nodetreebean.getOid());
            Node node=new Node();
            node.setParent_oid(nodetreebean.getOid());
            analyzeNode(object.getString("subNodes"),node);
        }
    }

    /**
     * 解析数据Node
     */
    public void analyzeNode(String str,Node node) {
        if (str!= null && (!"".equals(str))) {
            if (str.indexOf("node") < 10  && str.indexOf("node")>-1 ) {
                str =JSONObject.parseObject(str).getString("node");
            }
            if(isArray(str)) {
                JSONArray jsonArray=JSONObject.parseArray(str.toString());
                for (int i = 0; i < jsonArray.size(); i++) {
                    analyzeNode(jsonArray.get(i).toString(),node);
                }
            }else {
                JSONObject object = JSONObject.parseObject(str);
                System.out.println("\t当前Node：" +  object.getLongValue( "@oid"));
                node.setOid(object.getLongValue( "@oid"));
                node.setName(object.getString("@name"));
                node.setCommercial_References(object.getString("commercialReferences"));
                node.setDescription(object.getString("description"));
                node.setHas_Configurable((byte)(object.getBooleanValue("hasConfigurable")?1:0));
                node.setHas_Document((byte)(object.getBooleanValue("hasDocument")?1:0));
                node.setHas_Product((byte)(object.getBooleanValue("hasProduct")?1:0));
                node.setHas_RichText((byte)(object.getBooleanValue("hasRichText")?1:0));
                node.setVisible((byte)(object.getBooleanValue("visible")?1:0));
                SQLSession sqlSession = new SQLSession();
                sqlSession.getSqlsession().insert("NodeMapper.insertNode",  node);
                sqlSession.getSqlsession().commit();
                sqlSession.closeSession();
                System.out.println("\t成功添加Node：" +  node.getOid());
                if(object.getString("subNodes")!=null || "".equals(object.getString("subNodes"))){
                    analyzeNode(object.getString("subNodes"),node);
                }
            }
        }
    }

}


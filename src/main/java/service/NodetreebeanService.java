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
    private Nodetreebean nodetreebean=new Nodetreebean();
    private Node node=new Node();


    /**
     * 获取数据流程
     *
     * @param id
     */
    public void run(String id)  {
        System.out.println("开始获取Nodetreebean:"+id);
        getData(id);
        isRepeat(id);
        if (!repeat) {
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
     * 解析tree
     * @param sb
     */
    public void analyzeTree(StringBuilder sb){
        if(sb!=null && !"".equals(sb.toString())){
            if (sb.indexOf("node") < 10  && sb.indexOf("node")>-1 ) {
                sb =new StringBuilder( JSONObject.parseObject(sb.toString()).getString("node"));
            }
            if(isArray(sb.toString())) {
                JSONArray respObjs=JSONObject.parseArray(sb.toString());
                for (int i = 0; i < respObjs.size(); i++) {
                    analyzeTree( new StringBuilder(respObjs.get(i).toString()));
                }
            }else {
                if(isNode(sb.toString())){
                    analyzeNode(sb.toString());
                }else {
                    analyzeNodetree(sb.toString());
                }
            }
        }

    }

    /**
     *判读是否为叶子节点
     */
    public boolean isNode(String str) {
        if (str != null && !"".equals(str)) {
            JSONObject object = JSONObject.parseObject(str);
            if(object.getString( "subNodes")==null || object.getString( "subNodes").equals("null")){
                return true;
            }
        }
        return false;
    }

    /**
     * 解析数据
     */
    @Override
    public void analyze() {
        if(data!= null && (!data.equals("")) && data.indexOf("getNodeTreeFromRangeIdResponse")>-1) {
            JSONObject object = JSONObject.parseObject(data);
            StringBuilder sb = new StringBuilder(object.getJSONObject("getNodeTreeFromRangeIdResponse").getJSONObject("NodeTreeBean").toString());
            analyzeTree(sb);
        }
    }
    /**
     * 解析数据Nodetree
     */
    public void analyzeNodetree(String str) {
        if (str!= null && (!str.equals(""))) {
            JSONObject object = JSONObject.parseObject(str);
            nodetreebean.setOid(object.getLongValue( "@oid"));
            nodetreebean.setName(object.getString("@name"));
            nodetreebean.setCommercial_References(object.getString("commercialReferences"));
            nodetreebean.setHas_Configurable(object.getBooleanValue("hasConfigurable"));
            nodetreebean.setHas_Document(object.getBooleanValue("hasDocument"));
            nodetreebean.setHas_Product(object.getBooleanValue("hasProduct"));
            nodetreebean.setHas_RichText(object.getBooleanValue("hasRichText"));
            nodetreebean.setVisible(object.getBooleanValue("visible"));
            SQLSession sqlSession = new SQLSession();
            sqlSession.getSqlsession().insert("NodetreebeanMapper.insertNodetreebean",  nodetreebean);
            sqlSession.getSqlsession().commit();
            sqlSession.closeSession();
            System.out.println("\t成功添加Nodetreebean：" +  nodetreebean.getOid());
            node.setParent_oid(nodetreebean.getOid());
            analyzeTree(new StringBuilder(object.getString("subNodes")));
        }
    }
    /**
     * 解析数据Node
     */
    public void analyzeNode(String str) {
        if (str!= null && (!"".equals(str))) {
            JSONObject object = JSONObject.parseObject(str);
            node.setOid(object.getLongValue( "@oid"));
            node.setName(object.getString("@name"));
            node.setCommercial_References(object.getString("commercialReferences"));
            node.setDescription(object.getString("description"));
            node.setHas_Configurable(object.getBooleanValue("hasConfigurable"));
            node.setHas_Document(object.getBooleanValue("hasDocument"));
            node.setHas_Product(object.getBooleanValue("hasProduct"));
            node.setHas_RichText(object.getBooleanValue("hasRichText"));
            node.setVisible(object.getBooleanValue("visible"));
           // System.out.println("Node：" +node.toString());
            SQLSession sqlSession = new SQLSession();
             sqlSession.getSqlsession().insert("NodeMapper.insertNode",  node);
             sqlSession.getSqlsession().commit();
            sqlSession.closeSession();
            System.out.println("\t成功添加Node：" +  node.getOid());
        }
    }

}


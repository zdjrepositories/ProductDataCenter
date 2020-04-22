package service;

import pojo.Summary;
import util.MD5;
import util.SQLSession;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 数据获取及处理
 */
public class DataCenterService {
    //数据存储
    protected String data;
    //是否重复
    protected boolean repeat = false;

    /**
     * 获取数据流程
     *
     * @param id
     */
    public void run(String id) {
    }
    /**
     * 获取数据流程
     *
     * @param idList
     */
    public void run(List idList){
        if(idList != null && idList.size()>0){
            for (int i = 0; i <idList.size() ; i++) {
                run(idList.get(i).toString());
            }
        }
    }

    /**
     * 获取数据
     *
     */
    public void  getData(){}
    public void  getData(String id){}


    /**
     * 解析数据
     */
    public void analyze(){}

    /**
     * 判断是否重复
     */
    public boolean isRepeat(String id) {
        if (data != null && !"".equals(data)) {
            SQLSession sqlSession = new SQLSession();
            Summary summary = sqlSession.getSqlsession().selectOne("SummaryMapper.selectSummary", id);
            sqlSession.closeSession();
            if( summary!=null&& !"".equals(summary.toString())) {
                if (MD5.getMD5(data).equals(summary.getSummary())) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * 未重复保存摘要
     */
    public void upDataSummary(String id) {
        Summary summary = new Summary();
        summary.setId(id);
        summary.setSummary(MD5.getMD5(data));
        summary.setDate(getTime());
        SQLSession sqlSession = new SQLSession();
        sqlSession.getSqlsession().insert("SummaryMapper.insertSummary", summary);
        sqlSession.getSqlsession().commit();
    }
    /**
     *
     */
    public  String getTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置日期格式
        return df.format(new Date());
    }
    /**
     * 判断是否为数组
     *
     * @param str
     * @return
     */
    public boolean isArray(String str) {
        if (str != null && !"".equals(str)) {
            if (str.indexOf("[") > -1 && str.indexOf("[") < 3) {
                return true;
            }
        }
        return false;
    }
}

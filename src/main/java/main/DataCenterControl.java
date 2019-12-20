package main;


import service.CategoryService;
import service.NodetreebeanService;
import service.ProductCharacterService;
import service.ProductsService;
import service.RangesService;
import util.Conf;
import util.SQLSession;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class DataCenterControl {
    List confList;
    //获取数据连接
    SQLSession sqlSession;



    public void control() {
        long startTime = System.currentTimeMillis();   //获取开始时间

        //获取Category
        CategoryService categoryService = new CategoryService();
        categoryService.run();

        //获取Ranges
        sqlSession=new SQLSession();
        List categoryList = sqlSession.getSqlsession().selectList("CategoryIdMapper.selectCategoryId");
        sqlSession.closeSession();

        System.out.println(categoryList.size()+"\n\n\n\n");

        confList = Conf.getConf().addCategoryList();
        if (confList != null && confList.size() > 0) {
            categoryList.addAll(confList);
        }
        forkJoin("Ranges",categoryList);

        //获取Nodetreebean
        sqlSession=new SQLSession();
        List rangesList = sqlSession.getSqlsession().selectList("RangesMapper.selectRanges");
        sqlSession.closeSession();

        System.out.println("\n\n\n\n"+rangesList.size()+"\n\n\n\n");

        confList = Conf.getConf().addRangesList();
        if (confList != null && confList.size() > 0) {
            rangesList.addAll(confList);
        }
        forkJoin("Nodetreebean",rangesList);

        //获取Products
        sqlSession=new SQLSession();
        List nodetreebeanList = sqlSession.getSqlsession().selectList("NodeMapper.selectNode");
        sqlSession.closeSession();

        System.out.println("\n\n\n\n"+nodetreebeanList.size()+"\n\n\n\n\n");

        confList = Conf.getConf().addNodetreebeanList();
        if (confList != null && confList.size() > 0) {
            nodetreebeanList.addAll(confList);
        }
        forkJoin("Products",nodetreebeanList);


        //获取ProductCharacter
        sqlSession=new SQLSession();
        List productsbeanList = sqlSession.getSqlsession().selectList("ProductsMapper.selectProducts");
        sqlSession.closeSession();

        System.out.println("\n\n\n\n"+productsbeanList.size()+"\n\n\n\n");

        confList = Conf.getConf().addProductsList();
        if (confList != null && confList.size() > 0) {
            productsbeanList.addAll(confList);
        }
        forkJoin("ProductCharacter",productsbeanList);
        sendMail(startTime);
    }

    public void forkJoin(String service, List idlist) {
        int threadNum = Conf.getConf().getThreadNum();
        if ("".equals(threadNum) || threadNum < 0 || threadNum > 1000) {
            threadNum = 3;
        }
        ForkJoinPool forkJoinPool = new ForkJoinPool(threadNum);
        ForkJoinTask forkJoinTask = forkJoinPool.submit(new ForkJoinSumCalculate(service, idlist, 0, idlist.size()));
        forkJoinPool.invoke(forkJoinTask);

    }

    public void sendMail(Long startTime) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss ");
        StringBuilder content = new StringBuilder();
        content.append("您好：\n    数据中心于 ");
        content.append(df.format(startTime) + "开始进行数据更新，");
        long endTime = System.currentTimeMillis(); //获取结束时间
        content.append(df.format(endTime) + "更新结束，共运行");
        long time = endTime - startTime;
        long hours = time / (1000 * 60 * 60);
        long minutes = (time - hours * (1000 * 60 * 60)) / (1000 * 60);
        long second =(time -(hours * (1000 * 60 * 60))-(minutes*(1000 * 60)))/1000;
        if(hours>0){
            content.append(hours+"小时");
            content.append(minutes+"分钟");
            content.append(second+"秒");
        }else if (minutes>0){
            content.append(minutes+"分钟");
            content.append(second+"秒");
        }else if (second>0){
            content.append(second+"秒");
        }
        System.out.println(content.toString());
    }
}

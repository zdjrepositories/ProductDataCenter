package main;

import service.DataCenterService;
import service.ServiceFactory;

import java.util.List;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class ForkJoinSumCalculate extends RecursiveTask {
    private  static final double MAX=20;
    private List idlist;
    // 子任务开始计算的值
    private Integer start;
    // 子任务结束计算的值
    private Integer end;
    private String service;
    public ForkJoinSumCalculate(String service,List idlist, Integer start , Integer end) {
        this.service=service;
        this.idlist=idlist;
        this.start = start;
        this.end = end;
    }

    /**
     * The DataCenter computation performed by this task.
     */
    @Override
    protected String compute() {
        if(end - start < MAX) {
            for(int index = this.start ; index < this.end; index++) {
                ServiceFactory.getSerice(service,idlist.get(index).toString());
            }
        }
        // 否则再进行任务拆分，拆分成两个任务
        else {
            Integer  middle=(start+end)/2;
           ForkJoinSumCalculate left=new ForkJoinSumCalculate(service,idlist,start,middle);
           left.fork();
            ForkJoinSumCalculate right=new ForkJoinSumCalculate(service,idlist,middle,end);
            right.fork();
            left.join();
            right.join();
        }
        return "完成";
    }
}

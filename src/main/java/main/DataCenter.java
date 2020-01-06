package main;

import service.*;
import util.Conf;
import util.SQLSession;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 程序入口
 */
public class DataCenter {


    public static void main(String[] args)  {
        Timer timer=new Timer();
        Long cycle=24*60*60*1000L*Conf.getConf().getCycle();
        timer.scheduleAtFixedRate(new DataCenterTimeTask(),0,cycle);
    }
}

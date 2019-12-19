package main;

import service.*;
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
        Long cycle=1000000000L;
        timer.scheduleAtFixedRate(new DataCenterTimeTask(),0,cycle);
    }
}

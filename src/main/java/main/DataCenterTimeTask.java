package main;
import java.util.TimerTask;


public class DataCenterTimeTask extends TimerTask {
    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {

        try {
            DataCenterControl dataCenterControl=new DataCenterControl();
            dataCenterControl.control();
        }catch (Exception e){}
    }
}

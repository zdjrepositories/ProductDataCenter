package util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * 配置文件读取
 */
public class Conf {
    private Integer cycle;
    private String starttime;
    private String category;
    private String ranges;
    private String nodetreebean;
    private String products;
    private String productCharacter;
    private String document;
    private String addCategory;
    private String addRanges;
    private String addNodetreebean;
    private String addProducts;
    private String addProductCharacter;
    private Integer tokenTime;
    private Integer threadNum;
    private volatile static Conf conf=null;

    /**
     * 单例方式创建类
     */
    private Conf(){
        Properties properties=new Properties();
        InputStream inputStream=Object.class.getResourceAsStream("/conf.properties");
        InputStreamReader inputStreamReader=null;
        try { inputStreamReader=new InputStreamReader(inputStream,"UTF-8");
            properties.load(inputStreamReader);
        } catch (IOException e) {
            Log4j.getLog4j().error( "配置文件错误："+e);
            new Mail().sendMail("数据中心项目运行已停止/n"+"配置文件错误："+e);
            e.printStackTrace();
        }
        this.cycle=Integer.parseInt(properties.getProperty("cycle"));
        this.starttime=properties.getProperty("starttime");
        this.category=properties.getProperty("category");
        this.ranges=properties.getProperty("ranges");
        this.nodetreebean=properties.getProperty("nodetreebean");
        this.products=properties.getProperty("products");
        this.productCharacter=properties.getProperty("productCharacter");
        this.document=properties.getProperty("document");
        this.addCategory=properties.getProperty("addCategory");
        this.addRanges=properties.getProperty("addRanges");
        this.addNodetreebean=properties.getProperty("addNodetreebean");
        this.addProducts=properties.getProperty("addProducts");
        this.addProductCharacter=properties.getProperty("addProductCharacter");
        this.addProducts=properties.getProperty("addProducts");
        this.addProductCharacter=properties.getProperty("addProductCharacter");
        this.tokenTime=Integer.parseInt(properties.getProperty("tokenTime"));
        this.threadNum=Integer.parseInt(properties.getProperty("threadNum"));
    }
    /**
     * 获取类实例
     * @return
     */
    public static Conf getConf() {
        if(conf==null ||conf.equals("")){
            synchronized (Conf.class){
                if(conf ==null||conf.equals("")){
                    conf= new Conf();
                }
            }
        }
        return conf;
    }

    @Override
    public String toString() {
        return "Conf{" +
                "cycle=" + cycle +
                ", starttime='" + starttime + '\'' +
                ", category='" + category + '\'' +
                ", ranges='" + ranges + '\'' +
                ", nodetreebean='" + nodetreebean + '\'' +
                ", products='" + products + '\'' +
                ", productCharacter='" + productCharacter + '\'' +
                ", document='" + document + '\'' +
                ", addCategory='" + addCategory + '\'' +
                ", addRanges='" + addRanges + '\'' +
                ", addNodetreebean='" + addNodetreebean + '\'' +
                ", addProducts='" + addProducts + '\'' +
                ", addProductCharacter='" + addProductCharacter + '\'' +
                ", tokenTime=" + tokenTime +
                ", threadNum=" + threadNum +
                '}';
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public Integer getTokenTime() {
        return tokenTime;
    }

    public void setTokenTime(Integer tokenTime) {
        this.tokenTime = tokenTime;
    }

    public Integer getThreadNum() {
        return threadNum;
    }

    public void setThreadNum(Integer threadNum) {
        this.threadNum = threadNum;
    }

    public int getCycle() {
        return cycle;
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRanges() {
        return ranges;
    }

    public void setRanges(String ranges) {
        this.ranges = ranges;
    }

    public String getNodetreebean() {
        return nodetreebean;
    }

    public void setNodetreebean(String nodetreebean) {
        this.nodetreebean = nodetreebean;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public String getProductCharacter() {
        return productCharacter;
    }

    public void setProductCharacter(String productCharacter) {
        this.productCharacter = productCharacter;
    }

    public String getAddCategory() {
        return addCategory;
    }

    public void setAddCategory(String addCategory) {
        this.addCategory = addCategory;
    }

    public String getAddRanges() {
        return addRanges;
    }

    public void setAddRanges(String addRanges) {
        this.addRanges = addRanges;
    }

    public String getAddNodetreebean() {
        return addNodetreebean;
    }

    public void setAddNodetreebean(String addNodetreebean) {
        this.addNodetreebean = addNodetreebean;
    }

    public String getAddProducts() {
        return addProducts;
    }

    public void setAddProducts(String addProducts) {
        this.addProducts = addProducts;
    }

    public String getAddProductCharacter() {
        return addProductCharacter;
    }

    public void setAddProductCharacter(String addProductCharacter) {
        this.addProductCharacter = addProductCharacter;
    }

    public static void setConf(Conf conf) {
        Conf.conf = conf;
    }
    public List addCategoryList(){
        return getList(getAddCategory());
    }
    public List addRangesList(){
        return getList(getAddRanges());
    }
    public List addNodetreebeanList(){
        return getList(getAddNodetreebean());
    }
    public List addProductsList(){
        return getList(getAddProducts());
    }
    public List addProductCharacterList(){
        return getList(getAddProductCharacter());
    }
    public List getList(String addSrting){
        if(addSrting != null && !"".equals(addSrting)) {
            try{
                String [] arr=addSrting.split(",");
                if(arr!=null && !"".equals(arr) && arr.length>0 && !"".equals(arr[0]) ){
                    List<String> addList = Arrays.asList(arr);
                    return addList;
                }
            }catch (Exception e){
                Log4j.getLog4j().error( "配置文件错误："+e);
            }
        }
        return null;
    }


}

package pojo;

import java.lang.ref.PhantomReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;

public class ProductCharacter {
    private Long charValueOid;//
    private String productId;//
    private Long categoryOid;
    private String categoryName;
    private String charCharid;
    private String charCharkey;
    private String charName;
    private Long charOid;
    private String charDescription;
    private Byte charValueLockcase;
    private String charValueValueid;
    private String charValueValue;
    private String charValueCountryiso;
    private String charValueCountryname;
    private String charValueExterneturl;
    private String charValueLabelurl;
   ; ;;;;;;;


    @Override
    public String toString() {
        return "ProductCharacter{" +
                "charValueOid=" + charValueOid +
                ", productId='" + productId + '\'' +
                ", categoryOid=" + categoryOid +
                ", categoryName='" + categoryName + '\'' +
                ", charCharid='" + charCharid + '\'' +
                ", charCharkey='" + charCharkey + '\'' +
                ", charName='" + charName + '\'' +
                ", charOid=" + charOid +
                ", charDescription='" + charDescription + '\'' +
                ", charValueLockcase=" + charValueLockcase +
                ", charValueValueid='" + charValueValueid + '\'' +
                ", charValueValue='" + charValueValue + '\'' +
                ", charValueCountryiso='" + charValueCountryiso + '\'' +
                ", charValueCountryname='" + charValueCountryname + '\'' +
                ", charValueExterneturl='" + charValueExterneturl + '\'' +
                ", charValueLabelurl='" + charValueLabelurl + '\'' +
                '}';
    }

    public Long getCharValueOid() {
        return charValueOid;
    }

    public void setCharValueOid(Long charValueOid) {
        this.charValueOid = charValueOid;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Long getCategoryOid() {
        return categoryOid;
    }

    public void setCategoryOid(Long categoryOid) {
        this.categoryOid = categoryOid;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCharCharid() {
        return charCharid;
    }

    public void setCharCharid(String charCharid) {
        this.charCharid = charCharid;
    }

    public String getCharCharkey() {
        return charCharkey;
    }

    public void setCharCharkey(String charCharkey) {
        this.charCharkey = charCharkey;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public Long getCharOid() {
        return charOid;
    }

    public void setCharOid(Long charOid) {
        this.charOid = charOid;
    }

    public String getCharDescription() {
        return charDescription;
    }

    public void setCharDescription(String charDescription) {
        this.charDescription = charDescription;
    }

    public Byte getCharValueLockcase() {
        return charValueLockcase;
    }

    public void setCharValueLockcase(Byte charValueLockcase) {
        this.charValueLockcase = charValueLockcase;
    }

    public String getCharValueValueid() {
        return charValueValueid;
    }

    public void setCharValueValueid(String charValueValueid) {
        this.charValueValueid = charValueValueid;
    }

    public String getCharValueValue() {
        return charValueValue;
    }

    public void setCharValueValue(String charValueValue) {
        this.charValueValue = charValueValue;
    }

    public String getCharValueCountryiso() {
        return charValueCountryiso;
    }

    public void setCharValueCountryiso(String charValueCountryiso) {
        this.charValueCountryiso = charValueCountryiso;
    }

    public String getCharValueCountryname() {
        return charValueCountryname;
    }

    public void setCharValueCountryname(String charValueCountryname) {
        this.charValueCountryname = charValueCountryname;
    }

    public String getCharValueExterneturl() {
        return charValueExterneturl;
    }

    public void setCharValueExterneturl(String charValueExterneturl) {
        this.charValueExterneturl = charValueExterneturl;
    }

    public String getCharValueLabelurl() {
        return charValueLabelurl;
    }

    public void setCharValueLabelurl(String charValueLabelurl) {
        this.charValueLabelurl = charValueLabelurl;
    }
}
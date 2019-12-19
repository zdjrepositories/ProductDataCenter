package pojo;

public class Products  {
    private Long oid;//
    private String productId;
    private Long parentNodeOid;
    private Long parentRangeId;//
    private Long parentRangeOid;
    private Long pictureDocumentOid;
    private String shortDescription;
    private String longDescription;
    private String documentoids;
    private String highligthedcharacteristics;
    private String commercialreference;
    private String commercializedproductBrand;
    private String commercializedproductCommstatus;
    private Byte commercializedproductCommercialized;
    private String eanCode;
    private Byte greenPremium;
    private String globalStatus;

    @Override
    public String toString() {
        return "Products{" +
                "oid=" + oid +
                ", productId='" + productId + '\'' +
                ", parentNodeOid=" + parentNodeOid +
                ", parentRange_Id=" + parentRangeId +
                ", parentRangeOid=" + parentRangeOid +
                ", pictureDocumentOid=" + pictureDocumentOid +
                ", shortDescription='" + shortDescription + '\'' +
                ", longDescription='" + longDescription + '\'' +
                ", documentoids='" + documentoids + '\'' +
                ", highligthedcharacteristics='" + highligthedcharacteristics + '\'' +
                ", commercialreference='" + commercialreference + '\'' +
                ", commercializedproductBrand='" + commercializedproductBrand + '\'' +
                ", commercializedproductCommstatus='" + commercializedproductCommstatus + '\'' +
                ", commercializedproductCommercialized=" + commercializedproductCommercialized +
                ", eanCode='" + eanCode + '\'' +
                ", greenPremium=" + greenPremium +
                ", globalStatus='" + globalStatus + '\'' +
                '}';
    }

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Long getParentNodeOid() {
        return parentNodeOid;
    }

    public void setParentNodeOid(Long parentNodeOid) {
        this.parentNodeOid = parentNodeOid;
    }

    public Long getParentRangeId() {
        return parentRangeId;
    }

    public void setParentRangeId(Long parentRangeId) {
        this.parentRangeId = parentRangeId;
    }

    public Long getParentRangeOid() {
        return parentRangeOid;
    }

    public void setParentRangeOid(Long parentRangeOid) {
        this.parentRangeOid = parentRangeOid;
    }

    public Long getPictureDocumentOid() {
        return pictureDocumentOid;
    }

    public void setPictureDocumentOid(Long pictureDocumentOid) {
        this.pictureDocumentOid = pictureDocumentOid;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getDocumentoids() {
        return documentoids;
    }

    public void setDocumentoids(String documentoids) {
        this.documentoids = documentoids;
    }

    public String getHighligthedcharacteristics() {
        return highligthedcharacteristics;
    }

    public void setHighligthedcharacteristics(String highligthedcharacteristics) {
        this.highligthedcharacteristics = highligthedcharacteristics;
    }

    public String getCommercialreference() {
        return commercialreference;
    }

    public void setCommercialreference(String commercialreference) {
        this.commercialreference = commercialreference;
    }

    public String getCommercializedproductBrand() {
        return commercializedproductBrand;
    }

    public void setCommercializedproductBrand(String commercializedproductBrand) {
        this.commercializedproductBrand = commercializedproductBrand;
    }

    public String getCommercializedproductCommstatus() {
        return commercializedproductCommstatus;
    }

    public void setCommercializedproductCommstatus(String commercializedproductCommstatus) {
        this.commercializedproductCommstatus = commercializedproductCommstatus;
    }

    public Byte getCommercializedproductCommercialized() {
        return commercializedproductCommercialized;
    }

    public void setCommercializedproductCommercialized(Byte commercializedproductCommercialized) {
        this.commercializedproductCommercialized = commercializedproductCommercialized;
    }

    public String getEanCode() {
        return eanCode;
    }

    public void setEanCode(String eanCode) {
        this.eanCode = eanCode;
    }

    public Byte getGreenPremium() {
        return greenPremium;
    }

    public void setGreenPremium(Byte greenPremium) {
        this.greenPremium = greenPremium;
    }

    public String getGlobalStatus() {
        return globalStatus;
    }

    public void setGlobalStatus(String globalStatus) {
        this.globalStatus = globalStatus;
    }
}
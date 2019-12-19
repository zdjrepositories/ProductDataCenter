package pojo;

public class Productlistbean {
    private Long oid;

    private Integer count;

    private String products;

    @Override
    public String toString() {
        return "Productlistbean{" +
                "oid=" + oid +
                ", count=" + count +
                ", products='" + products + '\'' +
                '}';
    }

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }
}
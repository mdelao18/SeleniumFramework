package pojo;

public class Products {



    private String product;
    private double dollarsPrice;
    private double poundsPrice;
    private double euroPrice;


    public Products(String _product, double _dollarsPrice, double _poundsPrice, double _euroPrice) {
        this.product = _product;
        this.dollarsPrice = _dollarsPrice;
        this.poundsPrice = _poundsPrice;
        this.euroPrice = _euroPrice;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
    public double getDollarsPrice() {
        return dollarsPrice;
    }

    public void setDollarsPrice(double dollarsPrice) {
        this.dollarsPrice = dollarsPrice;
    }

    public double getPoundsPrice() {
        return poundsPrice;
    }

    public void setPoundsPrice(double poundsPrice) {
        this.poundsPrice = poundsPrice;
    }

    public double getEuroPrice() {
        return euroPrice;
    }

    public void setEuroPrice(double euroPrice) {
        this.euroPrice = euroPrice;
    }


}

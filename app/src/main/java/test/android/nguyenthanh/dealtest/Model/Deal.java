package test.android.nguyenthanh.dealtest.Model;

import java.util.Date;

public class Deal {
    private String productName;
    private String productThumbnail;
    private Double productPrice;
    private Date startedDate;
    private Date endDate;
    private long timeRemaining;


    public Deal() {
    }

    public Deal(String productName, String productThumbnail, Double productPrice, Date startedDate, Date endDate) {
        this.productName = productName;
        this.productThumbnail = productThumbnail;
        this.productPrice = productPrice;
        this.startedDate = startedDate;
        this.endDate = endDate;
        this.timeRemaining = endDate.getTime() - startedDate.getTime();
    }

    public Deal(Date endDate) {
        this.endDate = endDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductThumbnail() {
        return productThumbnail;
    }

    public void setProductThumbnail(String productThumbnail) {
        this.productThumbnail = productThumbnail;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Date getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(Date startedDate) {
        this.startedDate = startedDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public long getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(long timeRemaining) {
        this.timeRemaining = timeRemaining;
    }
}

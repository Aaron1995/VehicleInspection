package studio.imedia.vehicleinspection.bean;

/**
 * Created by eric on 15/10/12.
 */
public class Coupon {
    private int price;      // 金额
    private int year;       // 截止日期-年
    private int month;      // 截止日期-月
    private int day;        // 截止日期-日

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}

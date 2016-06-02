package jako.jocantaro.android.tipcalc.models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jocantaro on 2/06/16.
 */
public class TipRecord {

    private int tipPercentage;
    private Date timestamp;
    private double bill;
    private int getTipPercentage;

    public int getTipPercentage() {
        return tipPercentage;
    }

    public void setTipPercentage(int tipPercentage) {
        this.tipPercentage = tipPercentage;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }


    public double getTip () {
        return bill* (tipPercentage/100d);
    }

    public String getDateFormatted() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd.yyyy HH:mm");
        return simpleDateFormat.format(timestamp);
    }
}

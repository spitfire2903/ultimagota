package br.com.ricardonm.gotadagua.model;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by ricardomiranda on 17/06/15.
 */
public class Reading extends SugarRecord<Reading> {
    private Double value;
    private Double lastValue;
    private Date createdAt;
    private Date lastDate;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Reading(){
        this.createdAt = new Date();
        this.lastDate = new Date();
    }

    public Reading(Reading lastReading){
        this.createdAt = new Date();
        this.lastValue = lastReading.getValue();
        this.lastDate = lastReading.getCreatedAt();
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getLastValue() {
        return lastValue;
    }

    public void setLastValue(Double lastValue) {
        this.lastValue = lastValue;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    // TODO: fazer o valor relativo por dia, (value - lastValue) / day(date) - day(lastDate)
    public Double getRelativeValue(){
        return value - lastValue;
    }
}

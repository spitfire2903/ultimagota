package br.com.ricardonm.gotadagua.model;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by ricardomiranda on 17/06/15.
 */
public class ReservatoryCapacity extends SugarRecord<ReservatoryCapacity> {
    // private Double value;
    private Double percVolume;
    private Double dailyRainfall;
    private Double monthRainfall;
    private Double historicalMonthRainfall;
    //private Double percChange;
    private Reservatory reservatory;
    private Date createdAt;

    public ReservatoryCapacity() {
        this.createdAt = new Date();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Double getPercVolume() {
        return percVolume;
    }

    public void setPercVolume(Double percVolume) {
        this.percVolume = percVolume;
    }

    public Double getDailyRainfall() {
        return dailyRainfall;
    }

    public void setDailyRainfall(Double dailyRainfall) {
        this.dailyRainfall = dailyRainfall;
    }

    public Double getMonthRainfall() {
        return monthRainfall;
    }

    public void setMonthRainfall(Double monthRainfall) {
        this.monthRainfall = monthRainfall;
    }

    public Double getHistoricalMonthRainfall() {
        return historicalMonthRainfall;
    }

    public void setHistoricalMonthRainfall(Double historicalMonthRainfall) {
        this.historicalMonthRainfall = historicalMonthRainfall;
    }

    public Reservatory getReservatory() {
        return reservatory;
    }

    public void setReservatory(Reservatory reservatory) {
        this.reservatory = reservatory;
    }

}

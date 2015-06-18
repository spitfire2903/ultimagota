package br.com.ricardonm.gotadagua.model;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by ricardomiranda on 17/06/15.
 */
public class ReservatoryCapacity extends SugarRecord<ReservatoryCapacity> {
    private Double value;
    private Double volume;
    private Double percChange;
    private Reservatory reservatory;
    private DeviceUser deviceUser;
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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getPercChange() {
        return percChange;
    }

    public void setPercChange(Double percChange) {
        this.percChange = percChange;
    }

    public Reservatory getReservatory() {
        return reservatory;
    }

    public void setReservatory(Reservatory reservatory) {
        this.reservatory = reservatory;
    }

    public DeviceUser getDeviceUser() {
        return deviceUser;
    }

    public void setDeviceUser(DeviceUser deviceUser) {
        this.deviceUser = deviceUser;
    }
}

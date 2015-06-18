package br.com.ricardonm.gotadagua.model;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by ricardomiranda on 17/06/15.
 */
public class Reservatory extends SugarRecord<Reservatory> {
    private String name;
    private String text;
    private String lat;
    private String lng;
    private DeviceUser deviceUser;
    private Date createdAt;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Reservatory(){
        this.createdAt = new Date();
    }
}

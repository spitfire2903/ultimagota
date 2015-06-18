package br.com.ricardonm.gotadagua.model;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by ricardomiranda on 17/06/15.
 */
// TODO: tentar descobrir a lat e lng dos reservatorios
public class Reservatory extends SugarRecord<Reservatory> {
    private String name;
//    private String text;
//    private String lat;
//    private String lng;
    private Date createdAt;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Reservatory(){
        this.createdAt = new Date();
    }
}

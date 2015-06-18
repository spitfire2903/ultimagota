package br.com.ricardonm.gotadagua.model;

import com.orm.SugarRecord;

/**
 * Created by ricardomiranda on 17/06/15.
 */
public class TipCategory extends SugarRecord<TipCategory> {
    private String name;
    private String text;
    private DeviceUser deviceUser;

    public TipCategory(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public DeviceUser getDeviceUser() {
        return deviceUser;
    }

    public void setDeviceUser(DeviceUser deviceUser) {
        this.deviceUser = deviceUser;
    }
}

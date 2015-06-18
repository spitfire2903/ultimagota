package br.com.ricardonm.gotadagua.model;

import com.orm.SugarRecord;

/**
 * Created by ricardomiranda.
 */
public class TipCategory extends SugarRecord<TipCategory> {
    private String name;
    private String text;

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
}

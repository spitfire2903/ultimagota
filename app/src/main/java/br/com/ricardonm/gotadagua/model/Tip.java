package br.com.ricardonm.gotadagua.model;

import com.orm.SugarRecord;

import java.util.Date;
import java.util.List;

/**
 * Created by ricardomiranda.
 */
public class Tip extends SugarRecord<Tip> {
    private String title;
    private String text;
    private List<TipCategory> categories;
    private Date createdAt;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<TipCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<TipCategory> categories) {
        this.categories = categories;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Tip(){
        this.createdAt = new Date();
    }

}

package br.com.ricardonm.gotadagua.model;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by ricardomiranda on 17/06/15.
 */
public class Goal extends SugarRecord<Goal> {

    private long goal;
    private Date monthRef;
    private Date createdAt;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public Goal(){
        this.createdAt = new Date();
        this.monthRef = new Date();
    }

    public long getGoal() {
        return goal;
    }

    public void setGoal(long goal) {
        this.goal = goal;
    }

    public Date getMonthRef() {
        return monthRef;
    }

    public void setMonthRef(Date monthRef) {
        this.monthRef = monthRef;
    }
}

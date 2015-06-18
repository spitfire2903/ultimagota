package br.com.ricardonm.gotadagua.bo;

import java.util.List;

import br.com.ricardonm.gotadagua.model.Goal;

/**
 * Created by ricardomiranda on 18/06/15.
 */
public class GoalBO {
    public static Goal getLastGoal(){
        Goal goal = null;
        List<Goal> list = null;

        try {

            list = Goal.find(Goal.class, "", new String[]{}, null, "created_at desc", null);

            if(list != null && list.size() > 0){
                goal = list.get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return goal;
    }
}

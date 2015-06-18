package br.com.ricardonm.gotadagua.task;

import java.util.List;

import br.com.ricardonm.gotadagua.bo.GoalBO;
import br.com.ricardonm.gotadagua.bo.ReadingBO;
import br.com.ricardonm.gotadagua.fragment.MainFragment;
import br.com.ricardonm.gotadagua.model.Goal;
import br.com.ricardonm.gotadagua.model.Reading;

/**
 * Created by ricardomiranda on 18/06/15.
 */
public class LoadSummaryTask extends BaseTask<MainFragment, Void, Void, Void> {
    private Reading lastReading;
    private Goal lastGoal;
    private List<Reading> allReadings;

    public LoadSummaryTask(MainFragment fragment){
        super(fragment);
    }

    @Override
    protected Void doInBackground(Void... params) {
        // this.initSampleData();

        lastReading = ReadingBO.getLastReading();
        allReadings = ReadingBO.getAllReading();
        lastGoal = GoalBO.getLastGoal();

        return super.doInBackground();
    }

    public void initSampleData(){
        Reading.deleteAll(Reading.class);

        Reading r = null;

        for (int i = 1; i <= 10; i++){
            r = new Reading();

            r.setLastValue((double) 10*(i-1));
            r.setValue((double) (10*i));

            r.save();
        }
    }

    @Override
    protected void onPostExecute(Void result) {
        this.getParent().refreshAllReading(allReadings);
        this.getParent().refreshLastReading(lastReading);
        this.getParent().refreshLastGoal(lastGoal);

        super.onPostExecute(result);
    }
}

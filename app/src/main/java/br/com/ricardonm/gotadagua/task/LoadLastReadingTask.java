package br.com.ricardonm.gotadagua.task;

import br.com.ricardonm.gotadagua.bo.ReadingBO;
import br.com.ricardonm.gotadagua.fragment.MainFragment;
import br.com.ricardonm.gotadagua.model.Reading;

/**
 * Created by ricardomiranda on 18/06/15.
 */
public class LoadLastReadingTask extends BaseTask<MainFragment, Void, Void, Void> {
    private Reading lastReading;

    public LoadLastReadingTask(MainFragment fragment){
        super(fragment);
    }

    @Override
    protected Void doInBackground(Void... params) {
        lastReading = ReadingBO.getLastReadingByDeviceUser(this.getParent().getCurrentUser());

        return super.doInBackground();
    }

    @Override
    protected void onPostExecute(Void result) {
        this.getParent().refreshLastReading(lastReading);

        super.onPostExecute(result);
    }
}

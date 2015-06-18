package br.com.ricardonm.gotadagua.task;

import java.util.List;

import br.com.ricardonm.gotadagua.bo.ReservatoryBO;
import br.com.ricardonm.gotadagua.fragment.ReservatoryFragment;
import br.com.ricardonm.gotadagua.model.ReservatoryCapacity;

/**
 * Created by ricardomiranda.
 */
public class LoadReservatoriesDataTask extends BaseTask<ReservatoryFragment, Void, Void, Void> {
    private List<ReservatoryCapacity> reservatoriesCapacity;

    public LoadReservatoriesDataTask(ReservatoryFragment fragment){
        super(fragment);
    }

    @Override
    protected Void doInBackground(Void... params) {

        reservatoriesCapacity = ReservatoryBO.getLastReservatoriesCapacity();

        return super.doInBackground();
    }


    @Override
    protected void onPostExecute(Void result) {
        this.getParent().refreshReservatoriesData(reservatoriesCapacity);

        super.onPostExecute(result);
    }
}

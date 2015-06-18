package br.com.ricardonm.gotadagua.task;

import com.orm.SugarRecord;

import br.com.ricardonm.gotadagua.BaseFragment;

/**
 * Created by ricardomiranda.
 */
public class SaveDataTask extends BaseTask<BaseFragment, Void, Void, Void> {
    private SugarRecord sugarObj;

    public SaveDataTask(BaseFragment parent, SugarRecord obj){
        super(parent);

        sugarObj = obj;
    }

    @Override
    protected Void doInBackground(Void... params) {
        sugarObj.save();

        return super.doInBackground(params);
    }
}

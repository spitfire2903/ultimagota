package br.com.ricardonm.gotadagua.task;

import br.com.ricardonm.gotadagua.MainActivity;
import br.com.ricardonm.gotadagua.bo.DeviceUserBO;
import br.com.ricardonm.gotadagua.model.DeviceUser;

/**
 * Created by ricardomiranda on 17/06/15.
 */
public class LoadDeviceUserTask extends BaseTask<MainActivity, Void, Void, Void>{

    private String deviceToken;
    private DeviceUser deviceUser;

    public LoadDeviceUserTask(MainActivity parent, String deviceToken){
        super(parent);

        this.deviceToken = deviceToken;
    }

    @Override
    protected Void doInBackground(Void... params) {
        this.deviceUser = DeviceUserBO.getDeviceUserByToken(deviceToken);

        return super.doInBackground(params);
    }

    @Override
    protected void onPostExecute(Void result) {
        this.getParent().setDeviceUser(this.deviceUser);

        super.onPostExecute(result);
    }
}

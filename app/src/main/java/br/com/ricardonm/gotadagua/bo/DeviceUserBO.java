package br.com.ricardonm.gotadagua.bo;

import com.parse.ParseQuery;

import java.util.List;

import br.com.ricardonm.gotadagua.model.DeviceUser;

/**
 * Created by ricardomiranda on 17/06/15.
 */
public class DeviceUserBO {
    public static DeviceUser getDeviceUserByToken(String deviceToken){
        DeviceUser user = null;
        List<DeviceUser> list = null;
        ParseQuery<DeviceUser> query = null;

        query = ParseQuery.getQuery("DeviceUser");
        query.whereEqualTo("deviceToken", deviceToken);

        try {
            list = query.find();

            if(list == null || list.size() == 0){
                user = DeviceUserBO.getNewDeviceUser(deviceToken);
            } else{
                user = list.get(0);
            }

        } catch (Exception e) {
            user = DeviceUserBO.getNewDeviceUser(deviceToken);

            e.printStackTrace();
        }

        return user;
    }

    public static DeviceUser getNewDeviceUser(String deviceToken){
        DeviceUser user = null;

        user = new DeviceUser(deviceToken);
        user.saveInBackground();

        return user;
    }
}

package br.com.ricardonm.gotadagua.bo;

import com.parse.ParseQuery;

import java.util.List;

import br.com.ricardonm.gotadagua.model.DeviceUser;
import br.com.ricardonm.gotadagua.model.Reading;

/**
 * Created by ricardomiranda on 18/06/15.
 */
public class ReadingBO {
    public static Reading getLastReadingByDeviceUser(DeviceUser deviceUser){
        Reading reading = null;
        List<Reading> list = null;
        ParseQuery<Reading> query = null;

        query = ParseQuery.getQuery("Reading");
        query.whereEqualTo("deviceUser.deviceToken", deviceUser.getDeviceToken());
        query.orderByDescending("createdAt");

        try {
            list = query.find();

            if(list != null && list.size() > 0){
                reading = list.get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return reading;
    }
}

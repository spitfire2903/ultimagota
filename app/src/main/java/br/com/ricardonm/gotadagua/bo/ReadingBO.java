package br.com.ricardonm.gotadagua.bo;

import java.util.List;

import br.com.ricardonm.gotadagua.model.Reading;

/**
 * Created by ricardomiranda on 18/06/15.
 */
public class ReadingBO {
    public static Reading getLastReading(){
        Reading reading = null;
        List<Reading> list = null;

        try {

            list = Reading.find(Reading.class, "", new String[]{}, null, "created_at desc", null);

            if(list != null && list.size() > 0){
                reading = list.get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return reading;
    }

    public static List<Reading> getAllReading(){
        List<Reading> list = null;

        try {

            list = Reading.find(Reading.class, "", new String[]{}, null, "created_at asc", "10");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}

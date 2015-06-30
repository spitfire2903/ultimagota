package br.com.ricardonm.gotadagua.bo;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.ricardonm.gotadagua.model.Reservatory;
import br.com.ricardonm.gotadagua.model.ReservatoryCapacity;
import br.com.ricardonm.gotadagua.wrapper.WrapperUtil;

/**
 * Created by ricardomiranda.
 */
public class ReservatoryBO {
    /**
     * Sabesp URL for the reservatories capacities
     */
    public static String SABESP_URL = "https://sabesp-api.herokuapp.com/v2";

    /**
     * HTTP Client to request the website
     */
    private static AsyncHttpClient client = new AsyncHttpClient();

    /**
     * Get the reservatory by name or create one.
     * @param name
     *      Name for search for reservatory
     * @return reservatory
     *      Object of the reservatory.
     */
    public static Reservatory getReservatoryByName(String name){
        Reservatory reservatory = null;
        List<Reservatory> list = null;

        try {

            list = Reservatory.find(Reservatory.class, "name = ?", new String[]{name}, null,
                    "created_at desc", null);

            if(list != null && list.size() > 0){
                reservatory = list.get(0);
            } else{
                reservatory = new Reservatory();
                reservatory.setName(name);
                reservatory.save();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return reservatory;
    }

    /**
     * Get the last info about the reservatory capacity
     * @param reservatory
     *      Object of the desired reservatory
     * @return capacity
     *      Info about the reservatory capacity.
     */
    public static ReservatoryCapacity getLastReservatoryCapacityByReservatory(Reservatory reservatory){
        ReservatoryCapacity capacity = null;
        List<ReservatoryCapacity> list = null;

        try {

            list = ReservatoryCapacity.find(ReservatoryCapacity.class, "reservatory = ?", new
                            String[]{reservatory.getId().toString()}, null,
                    "created_at desc", "5");

            if(list != null && list.size() > 0){
                capacity = list.get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return capacity;
    }

    /**
     * Get the list of all reservatories.
     * @return list
     *      List of all reservatories available.
     */
    public static List<Reservatory> getAllReservatory(){
        List<Reservatory> list = null;

        try {
            list = Reservatory.find(Reservatory.class, "", new String[]{}, null, "created_at " +
                    "asc", null);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * List of the last capacities for each reservatory
     * @return list
     *      List of the reservatories capacities.
     */
    public static List<ReservatoryCapacity> getLastReservatoriesCapacity(){
        List<Reservatory> reservatories = null;
        List<ReservatoryCapacity> list = null;
        ReservatoryCapacity capacity = null;

        list = new ArrayList<ReservatoryCapacity>();

        try {
            reservatories = ReservatoryBO.getAllReservatory();

            if (reservatories != null) {
                for (Reservatory reservatory : reservatories) {
                    capacity = ReservatoryBO.getLastReservatoryCapacityByReservatory(reservatory);

                    if (capacity != null){
                        list.add(capacity);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * Handle the reservatory info from JSON from sabesp
     * @param handler
     *      Handler for the HTTP Client methods
     * @throws JSONException
     *      Exception
     */
    public static void loadReservatoryAndCapacityFromSabesp(JsonHttpResponseHandler handler) throws
            JSONException {
        WrapperUtil.get(SABESP_URL, null, handler);
    }

    /**
     * Load the reservatory info from json
     * @param json
     *      Object with reservatory info.
     * @throws JSONException
     */
    public static void loadReservatoryFromJson(JSONObject json) throws JSONException{
        Reservatory reservatory = null;
        ReservatoryCapacity capacity = null;
        String name = null;
        JSONObject jsonCapacity = null;

        if (json != null) {
            name = json.getString("name");

            reservatory = ReservatoryBO.getReservatoryByName(name);

            if (reservatory != null) {
                jsonCapacity = json.getJSONObject("data");

                capacity = ReservatoryBO.getCapacityFromJson(jsonCapacity);
                capacity.setReservatory(reservatory);
                capacity.save();
            }
        }
    }

    /**
     * Get info about the capacity of desired reservatory
     * @param json
     *      Json object with info.
     * @return  capacity
     *      Object with reservatory capacity info.
     * @throws JSONException
     */
    public static ReservatoryCapacity getCapacityFromJson(JSONObject json) throws JSONException{
        ReservatoryCapacity capacity = null;
        String volArmazenado = null;

        capacity = new ReservatoryCapacity();

        // {"volume_armazenado":"54,9 %","pluviometria_do_dia":"0,2 mm","pluviometria_acumulada_no_mes":"33,4 mm","media_historica_do_mes":"95,4 mm"}
        if (json != null){
            volArmazenado = json.getString("volume_armazenado");

            if(volArmazenado.indexOf(":") != -1){
                volArmazenado = volArmazenado.substring(volArmazenado.indexOf(":")+1, volArmazenado
                        .length());
            }
            if(volArmazenado.indexOf("%") != -1){
                volArmazenado = volArmazenado.substring(0, volArmazenado.indexOf("%"));
            }

            capacity.setPercVolume(ReservatoryBO.getDoubleFromJsonString(volArmazenado));
            capacity.setDailyRainfall(ReservatoryBO.getDoubleFromJsonString(json.getString
                    ("pluviometria_do_dia")));
            capacity.setMonthRainfall(ReservatoryBO.getDoubleFromJsonString(json.getString
                    ("pluviometria_acumulada_no_mes")));
            capacity.setHistoricalMonthRainfall(ReservatoryBO.getDoubleFromJsonString(json.getString
                    ("media_historica_do_mes")));

        }

        return capacity;
    }

    /**
     * Auxiliary method to handle conversion of string to double
     * @param json
     *      Json object to be converted to Double
     * @return result
     *      Double result.
     */
    public static Double getDoubleFromJsonString(String json){
        Double result = null;

        json = json.trim().replace("%","").replace("mm","").replace(",",".");

        result = Double.parseDouble(json);

        return result;
    }
}

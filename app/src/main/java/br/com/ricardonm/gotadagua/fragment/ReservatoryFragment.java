package br.com.ricardonm.gotadagua.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import br.com.ricardonm.gotadagua.BaseFragment;
import br.com.ricardonm.gotadagua.R;
import br.com.ricardonm.gotadagua.bo.ReservatoryBO;
import br.com.ricardonm.gotadagua.model.ReservatoryCapacity;
import br.com.ricardonm.gotadagua.task.LoadReservatoriesDataTask;
import br.com.ricardonm.gotadagua.view.ReservatoryView;

/**
 * Created by ricardomiranda on 17/06/15.
 */
public class ReservatoryFragment extends BaseFragment {

    private List<ReservatoryCapacity> capacities;
    private LinearLayout vwContainer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = null;

        rootView = inflater.inflate(R.layout.fragment_reservatory, container, false);

        vwContainer = (LinearLayout) rootView.findViewById(R.id.vwContainer);

        try {
            ReservatoryBO.loadReservatoryAndCapacityFromSabesp(new LoadReservatoriesResponseHandler());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return rootView;
    }

    public void refreshReservatoriesData(List<ReservatoryCapacity> capacities){
        this.capacities = capacities;

        ViewGroup.LayoutParams params = null;
        ReservatoryView reservatoryView = null;

        params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
                .LayoutParams.WRAP_CONTENT);

        if (capacities != null && capacities.size() > 0){
            for (ReservatoryCapacity capacity : capacities){
                vwContainer.addView(this.getViewFromCapacity(capacity), params);
            }
        } else{
            reservatoryView = new ReservatoryView(this.getParentActivity());
            reservatoryView.setNoInfoReservatory();
            vwContainer.addView(reservatoryView);
        }

    }

    private ReservatoryView getViewFromCapacity(ReservatoryCapacity capacity){
        ReservatoryView reservatoryView = null;

        reservatoryView = new ReservatoryView(this.getParentActivity());
        reservatoryView.setCapacity(capacity);

        return reservatoryView;
    }

    private class LoadReservatoriesResponseHandler extends JsonHttpResponseHandler {

        @Override
        public void onStart() {
            super.onStart();

            ReservatoryFragment.this.getParentActivity().showThrobber();
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            // If the response is JSONObject instead of expected JSONArray
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONArray reservatories) {
            JSONObject jsonReservatory = null;

            try {
                for (int i = 0; i < reservatories.length(); i++) {
                    jsonReservatory = reservatories.getJSONObject(i);
                    ReservatoryBO.loadReservatoryFromJson(jsonReservatory);
                }

            } catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public void onFinish() {
            super.onFinish();

            ReservatoryFragment.this.getParentActivity().hideThrobber();

            LoadReservatoriesDataTask task = null;

            task = new LoadReservatoriesDataTask(ReservatoryFragment.this);
            task.execute();

        }
    }
}

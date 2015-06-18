package br.com.ricardonm.gotadagua.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.ricardonm.gotadagua.BaseFragment;
import br.com.ricardonm.gotadagua.R;

/**
 * Created by ricardomiranda on 17/06/15.
 */
public class WeatherFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = null;

        rootView = inflater.inflate(R.layout.fragment_weather, container, false);

        return rootView;
    }
}

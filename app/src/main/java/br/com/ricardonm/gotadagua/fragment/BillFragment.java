package br.com.ricardonm.gotadagua.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.ricardonm.gotadagua.BaseFragment;
import br.com.ricardonm.gotadagua.R;

/**
 * Created by ricardomiranda.
 */
public class BillFragment extends BaseFragment {
    /**
     * Overrided method from Android to load the Bill Fragment
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = null;

        rootView = inflater.inflate(R.layout.fragment_bill, container, false);

        return rootView;
    }

}

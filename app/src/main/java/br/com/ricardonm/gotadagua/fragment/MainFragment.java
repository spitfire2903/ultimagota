package br.com.ricardonm.gotadagua.fragment;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import java.text.NumberFormat;

import br.com.ricardonm.gotadagua.BaseFragment;
import br.com.ricardonm.gotadagua.MainActivity;
import br.com.ricardonm.gotadagua.R;
import br.com.ricardonm.gotadagua.model.Reading;
import br.com.ricardonm.gotadagua.task.LoadLastReadingTask;

/**
 * Created by ricardomiranda on 17/06/15.
 */
public class MainFragment extends BaseFragment {

    private LinearLayout btnAddReading;
    private LinearLayout btnViewReadingHistory;
    private LinearLayout vwNoReading;
    private TableLayout vwTableReading;

    private TextView txtLastMonthReading;
    private TextView txtLastReading;

    public Reading lastMonthReading;
    public Reading lastReading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = null;
        LoadLastReadingTask task = null;

        rootView = inflater.inflate(R.layout.fragment_main, container, false);

        btnAddReading = (LinearLayout) rootView.findViewById(R.id.btnAddReading);
        btnViewReadingHistory = (LinearLayout) rootView.findViewById(R.id.btnViewReadingHistory);
        vwNoReading = (LinearLayout) rootView.findViewById(R.id.vwNoReadings);
        vwTableReading = (TableLayout) rootView.findViewById(R.id.vwTableReadings);
        txtLastMonthReading = (TextView) rootView.findViewById(R.id.txtLastMonthReading);
        txtLastReading = (TextView) rootView.findViewById(R.id.txtLastReading);

        btnAddReading.setOnClickListener(
                new AddNewReadingOnClickListener());
        btnViewReadingHistory.setOnClickListener(new ViewReadingHistoryOnClickListener());

        task = new LoadLastReadingTask(this);
        task.execute();

        return rootView;
    }

    public void refreshLastReading(Reading reading){
        this.lastReading = reading;

        setReading(reading, txtLastReading);
    }

    public void setReading(Reading reading, TextView textView){
        NumberFormat nf = NumberFormat.getInstance();

        nf.setMinimumIntegerDigits(1);
        nf.setMaximumFractionDigits(0);

        textView.setText(nf.format(reading.getValue())+" m³");
    }

    private class AddNewReadingOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            MaterialDialog.Builder dialog = null;

            dialog = new MaterialDialog.Builder(MainFragment.this.getActivity());
            dialog.title("Adicionar Nova Leitura");
            dialog.customView(R.layout.dialog_add_reading, true);
            dialog.positiveText("Salvar");
            dialog.negativeText("Cancelar");
            dialog.autoDismiss(false);
            dialog.callback(new MaterialDialog.ButtonCallback() {
                @Override
                public void onPositive(MaterialDialog dialog) {
                    if (this.validateAndSaveFields(dialog)) {
                        dialog.dismiss();
                    }
                }

                @Override
                public void onNegative(MaterialDialog dialog) {
                    dialog.dismiss();
                }

                public boolean validateAndSaveFields(MaterialDialog dialog) {
                    Boolean result = true;
                    EditText edtNewReading = null;
                    TextInputLayout iptNewReading = null;
                    Reading reading = null;
                    Double value = null;
                    String errorMessage = null;

                    edtNewReading = (EditText) dialog.findViewById(R.id.edtNewReading);
                    iptNewReading = (TextInputLayout) dialog.findViewById(R.id.iptNewReading);

                    if (edtNewReading.getText().toString().length() == 0) {
                        result = false;

                        errorMessage = "O campo não pode estar vazio.";
                    } else {
                        value = Double.parseDouble(edtNewReading.getText().toString());

                        Log.e(">> APPPP", ">>> lastReading "+MainFragment.this.lastReading.getValue
                                ());
                        if (MainFragment.this.lastReading != null &&
                                MainFragment.this.lastReading.getValue() < value) {
                            result = false;

                            errorMessage = "O campo não pode ter um valor inferior ao da " +
                                    "ultima leitura (" + MainFragment.this.lastReading.getValue()
                                    .toString() + ")";

                        }
                    }

                    if (result) {
                        reading = new Reading();

                        reading.setDeviceUser(getCurrentUser());
                        reading.setValue(value);

                        reading.saveInBackground();

                        MainFragment.this.refreshLastReading(reading);
                    } else {
                        iptNewReading.setErrorEnabled(true);
                        iptNewReading.setError(errorMessage);
                    }

                    return result;
                }
            });

            dialog.show();
        }
    }

    private class ViewReadingHistoryOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            MainFragment.this.getParentActivity().mNavigationDrawerFragment.selectItem
                    (MainActivity.FragmentIndex.READING_FRAGMENT.value);
        }
    }
}

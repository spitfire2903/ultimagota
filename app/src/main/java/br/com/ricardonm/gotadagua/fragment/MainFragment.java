package br.com.ricardonm.gotadagua.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.ricardonm.gotadagua.BaseFragment;
import br.com.ricardonm.gotadagua.MainActivity;
import br.com.ricardonm.gotadagua.R;
import br.com.ricardonm.gotadagua.model.Goal;
import br.com.ricardonm.gotadagua.model.Reading;
import br.com.ricardonm.gotadagua.task.LoadSummaryTask;
import br.com.ricardonm.gotadagua.task.SaveDataTask;

/**
 * Created by ricardomiranda.
 */
public class MainFragment extends BaseFragment {

    private LinearLayout btnAddReading;
    private LinearLayout btnAddGoal;
    private LinearLayout btnViewReadingHistory;
    private LinearLayout vwNoReading;
    private TableLayout vwTableReading;

    private TextView txtLastMonthReading;
    private TextView txtLastReading;

    public Reading lastMonthReading;
    public Reading lastReading;
    public Goal lastGoal;
    public List<Reading> allReadings;

    public Double chartMaxValue;

    private LineChart chtConsumo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = null;
        LoadSummaryTask task = null;

        rootView = inflater.inflate(R.layout.fragment_main, container, false);

        btnAddReading = (LinearLayout) rootView.findViewById(R.id.btnAddReading);
        btnAddGoal = (LinearLayout) rootView.findViewById(R.id.btnAddGoal);
        btnViewReadingHistory = (LinearLayout) rootView.findViewById(R.id.btnViewReadingHistory);
        vwNoReading = (LinearLayout) rootView.findViewById(R.id.vwNoReadings);
        vwTableReading = (TableLayout) rootView.findViewById(R.id.vwTableReadings);
        txtLastMonthReading = (TextView) rootView.findViewById(R.id.txtLastMonthReading);
        txtLastReading = (TextView) rootView.findViewById(R.id.txtLastReading);
        chtConsumo = (LineChart) rootView.findViewById(R.id.chtConsumo);

        btnAddReading.setOnClickListener(new AddNewReadingOnClickListener());
        btnAddGoal.setOnClickListener(new AddNewGoalOnClickListener());
        //btnViewReadingHistory.setOnClickListener(new ViewReadingHistoryOnClickListener());

        task = new LoadSummaryTask(this);
        task.execute();

        this.refreshChart();

        return rootView;
    }

    public void refreshChart(){
        LimitLine goalLine = null;
        YAxis leftAxis = null;
        ArrayList<String> xVals = null;
        ArrayList<Entry> yVals = null;
        SimpleDateFormat sdf = null;
        LineDataSet lineDataSet = null;
        ArrayList<LineDataSet> dataSets = null;
        LineData data = null;

        chtConsumo.invalidate();

        chtConsumo.setPinchZoom(false);
        chtConsumo.setDoubleTapToZoomEnabled(false);

        chtConsumo.setNoDataText("Não há leituras ainda para serem exibidas.");

        xVals = new ArrayList<String>();
        yVals = new ArrayList<Entry>();
        sdf = new SimpleDateFormat("dd/MM");

        leftAxis = chtConsumo.getAxisLeft();

        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines

        if (chartMaxValue == null){
            chartMaxValue = 90d;
        }

        leftAxis.setAxisMaxValue(chartMaxValue.floatValue());
        leftAxis.setAxisMinValue(0f);
        leftAxis.setStartAtZero(true);
        //leftAxis.setYOffset(20f);
        //leftAxis.enableGridDashedLine(10f, 10f, 0f);

        // limit lines are drawn behind data (and not on top)
        leftAxis.setDrawLimitLinesBehindData(false);

        chtConsumo.getAxisRight().setEnabled(false);

        if (this.lastGoal != null) {
            goalLine = new LimitLine(80f, "Meta");
            goalLine.setLineWidth(4f);
            //ll1.enableDashedLine(10f, 10f, 0f);
            goalLine.setLabelPosition(LimitLine.LimitLabelPosition.POS_RIGHT);
            goalLine.setTextSize(10f);

            leftAxis.addLimitLine(goalLine);
        }

        if (allReadings != null) {
            for (int i = 0; i < allReadings.size(); i++) {
                xVals.add(sdf.format(allReadings.get(i).getCreatedAt()));
                yVals.add(new Entry(allReadings.get(i).getRelativeValue().floatValue(), i));
            }
        }

        // create a dataset and give it a type
        lineDataSet = new LineDataSet(yVals, "Consumo");
        // set1.setFillAlpha(110);
        // set1.setFillColor(Color.RED);

        // set the line to be drawn like this "- - - - - -"
        lineDataSet.enableDashedLine(10f, 5f, 0f);
        lineDataSet.setColor(Color.BLACK);
        lineDataSet.setCircleColor(Color.BLACK);
        lineDataSet.setLineWidth(1f);
        lineDataSet.setCircleSize(3f);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextSize(9f);
        lineDataSet.setFillAlpha(65);
        lineDataSet.setFillColor(Color.BLACK);
//        set1.setDrawFilled(true);
        // set1.setShader(new LinearGradient(0, 0, 0, mChart.getHeight(),
        // Color.BLACK, Color.WHITE, Shader.TileMode.MIRROR));

        dataSets = new ArrayList<LineDataSet>();
        dataSets.add(lineDataSet); // add the datasets

        // create a data object with the datasets
        data = new LineData(xVals, dataSets);

        if (allReadings != null || lastGoal != null) {
            chtConsumo.setData(data);
        }
    }

    public void refreshAllReading(List<Reading> readings){
        if (readings != null && readings.size() > 0) {
            this.allReadings = readings;

            this.refreshChart();
        } else {
            this.allReadings = new ArrayList<Reading>();
        }
    }

    public void refreshLastReading(Reading reading){
        if (reading != null) {
            this.lastReading = reading;
            this.allReadings.add(reading);

            if (chartMaxValue != null && reading.getRelativeValue() > chartMaxValue){
                chartMaxValue = reading.getRelativeValue() + 10;
            }

            this.refreshChart();

            vwNoReading.setVisibility(View.GONE);
            vwTableReading.setVisibility(View.VISIBLE);

            setReading(reading, txtLastReading);
        } else{
            vwNoReading.setVisibility(View.VISIBLE);
            vwTableReading.setVisibility(View.GONE);
        }
    }

    public void refreshLastGoal(Goal goal){
        if (goal != null){
            this.lastGoal = goal;

            if (chartMaxValue != null && goal.getGoal() > chartMaxValue.longValue()){
                chartMaxValue = (double) goal.getGoal() + 10;
            }

            this.refreshChart();
        }
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
                    SaveDataTask task = null;

                    edtNewReading = (EditText) dialog.findViewById(R.id.edtNewReading);
                    iptNewReading = (TextInputLayout) dialog.findViewById(R.id.iptNewReading);

                    if (edtNewReading.getText().toString().length() == 0) {
                        result = false;

                        errorMessage = "O campo não pode estar vazio.";
                    } else {
                        value = Double.parseDouble(edtNewReading.getText().toString());

                        if (MainFragment.this.lastReading != null &&
                                MainFragment.this.lastReading.getValue() > value) {
                            result = false;

                            errorMessage = "O campo não pode ter um valor inferior ao da " +
                                    "ultima leitura (" + MainFragment.this.lastReading.getValue()
                                    .toString() + ")";

                        }
                    }

                    if (result) {
                        reading = new Reading();

                        if (MainFragment.this.lastReading != null) {
                            reading.setLastValue(MainFragment.this.lastReading.getValue());
                            reading.setLastDate(MainFragment.this.lastReading.getCreatedAt());
                        } else {
                            reading.setLastValue(0d);
                        }

                        reading.setValue(value);

                        task = new SaveDataTask(MainFragment.this, reading);
                        task.execute();

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

    private class AddNewGoalOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            MaterialDialog.Builder dialog = null;

            dialog = new MaterialDialog.Builder(MainFragment.this.getActivity());
            dialog.title("Adicionar Nova Meta Diária");
            dialog.customView(R.layout.dialog_add_goal, true);
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
                    EditText edtNewGoal = null;
                    TextInputLayout iptNewGoal = null;
                    Goal goal = null;
                    Double value = null;
                    String errorMessage = null;
                    SaveDataTask task = null;

                    edtNewGoal = (EditText) dialog.findViewById(R.id.edtNewGoal);
                    iptNewGoal = (TextInputLayout) dialog.findViewById(R.id.iptNewGoal);

                    if (edtNewGoal.getText().toString().length() == 0) {
                        result = false;

                        errorMessage = "O campo não pode estar vazio.";
                    } else {
                        value = Double.parseDouble(edtNewGoal.getText().toString());
                    }

                    if (result) {
                        goal = new Goal();

                        goal.setGoal(value.longValue());

                        task = new SaveDataTask(MainFragment.this, goal);
                        task.execute();

                        MainFragment.this.refreshLastGoal(goal);
                    } else {
                        iptNewGoal.setErrorEnabled(true);
                        iptNewGoal.setError(errorMessage);
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

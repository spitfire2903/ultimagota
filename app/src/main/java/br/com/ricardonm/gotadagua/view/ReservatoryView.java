package br.com.ricardonm.gotadagua.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import br.com.ricardonm.gotadagua.R;
import br.com.ricardonm.gotadagua.model.ReservatoryCapacity;

/**
 * Created by ricardomiranda.
 */
public class ReservatoryView extends LinearLayout{
    private ReservatoryCapacity capacity;

    private TextView txtReservatoryName;
    private TextView txtPercVolume;
    private TextView txtDailyRainfall;
    private TextView txtMonthRainfall;
    private TextView txtHistoricalMonthRainfall;
    private TextView txtUpdatedAt;
    private CardView vwCardReservatory;
    private CardView vwNoInfoReservatory;

    public ReservatoryView(Context context) {
        super(context);
        baseInit();
    }

    public ReservatoryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        baseInit();
    }

    public ReservatoryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        baseInit();
    }

    private void baseInit(){
        View rootView = null;

        rootView = inflate(getContext(), R.layout.view_reservatory, this);

        txtReservatoryName = (TextView) rootView.findViewById(R.id.txtReservatoryName);
        txtPercVolume = (TextView) rootView.findViewById(R.id.txtPercVolume);
        txtDailyRainfall = (TextView) rootView.findViewById(R.id.txtDailyRainfall);
        txtMonthRainfall = (TextView) rootView.findViewById(R.id.txtMonthRainfall);
        txtHistoricalMonthRainfall = (TextView) rootView.findViewById(R.id.txtHistoricalMonthRainfall);
        txtUpdatedAt = (TextView) rootView.findViewById(R.id.txtUpdatedAt);

        vwCardReservatory = (CardView) rootView.findViewById(R.id.vwCardReservatory);
        vwNoInfoReservatory = (CardView) rootView.findViewById(R.id.vwNoInfoReservatory);

    }

    public ReservatoryCapacity getCapacity() {
        return capacity;
    }

    public void setCapacity(ReservatoryCapacity capacity) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        NumberFormat format = NumberFormat.getInstance();

        format.setMinimumIntegerDigits(1);
        format.setMaximumFractionDigits(2);

        this.capacity = capacity;

        vwCardReservatory.setVisibility(VISIBLE);
        vwNoInfoReservatory.setVisibility(GONE);

        txtReservatoryName.setText(capacity.getReservatory().getName());
        txtUpdatedAt.setText("Atualizado em "+sdf.format(capacity.getCreatedAt()));

        txtPercVolume.setText(format.format(capacity.getPercVolume())+" %");
        txtDailyRainfall.setText(format.format(capacity.getDailyRainfall())+" mm");
        txtMonthRainfall.setText(format.format(capacity.getMonthRainfall())+" mm");
        txtHistoricalMonthRainfall.setText(format.format(capacity.getHistoricalMonthRainfall())+" mm");

    }

    public void setNoInfoReservatory(){
        vwCardReservatory.setVisibility(GONE);
        vwNoInfoReservatory.setVisibility(VISIBLE);
    }

}

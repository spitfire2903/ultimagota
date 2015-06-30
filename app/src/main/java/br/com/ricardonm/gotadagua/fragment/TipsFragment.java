package br.com.ricardonm.gotadagua.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.afollestad.materialdialogs.MaterialDialog;

import br.com.ricardonm.gotadagua.BaseFragment;
import br.com.ricardonm.gotadagua.R;

/**
 * Created by ricardomiranda.
 */
public class TipsFragment extends BaseFragment {

    private ListView lstTips;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = null;
        ArrayAdapter<String> adapter = null;

        rootView = inflater.inflate(R.layout.fragment_tips, container, false);

        lstTips = (ListView) rootView.findViewById(R.id.lstTips);

        adapter = new ArrayAdapter<String>(this.getParentActivity(), R.layout.row_tip,
                R.id.txtTipItem, getTips());

        lstTips.setAdapter(adapter);

        // On click listener for each tip
        lstTips.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String  tip = null;
                MaterialDialog.Builder dialog = null;

                tip = (String) lstTips.getItemAtPosition(position);

                dialog = new MaterialDialog.Builder(TipsFragment.this.getParentActivity());
                dialog.title("Dica");
                dialog.content(tip);
                dialog.neutralText("OK");
                dialog.autoDismiss(true);

                dialog.show();
            }
        });

        return rootView;
    }

    /**
     * Get hardcoded list of tips
     * @return
     */
    private String[] getTips(){
        String[] result = null;

        result = new String[]{
            "Cheque vazamentos em canos e não deixe torneiras pingando. Um gotejamento " +
                "simples, pode gastar cerca de 45 litros de água por dia.",
            "Deixe pratos e talheres de molho antes de lavá-los.",
            "Aproveite a água da chuva para aguar as plantas e o jardim. As plantas absorvem " +
                "mais água em horários quentes, então molhe-as de manhã cedo ou no fim do dia.",
            "Feche a torneira quando estiver escovando os dentes ou fazendo a barba. Só abra " +
                "quando for usar. Uma torneira aberta por 5 minutos desperdiça 80 litros de água.",
            "Em vez da mangueira, use vassoura e balde para lavar patios e quintais. Uma " +
                "mangueira aberta por 30 minutos libera cerca de 560 litros de água.",
            "Reaproveite a água da sua máquina de lavar para lavar a calçada.",
            "Saber ler o hidrômetro é muito simples e pode ajudar a detectar problemas como " +
                "vazamentos, percebidos pelo consumo fora do normal.",
            "Não tome banhos demorados, 5 minutos são suficientes. Uma ducha durante 15 " +
                "minutos consome 135 litros de água.",
            "Antes de lavar pratos e panelas, limpe os restos de comida com uma escova ou " +
                "esponja e jogue no lixo.",
            "Reduza o tempo do banho e feche o registro enquanto se ensaboa: a água do " +
                "chuveiro também pode ser aproveitada para lavar roupa ou outra atividade" +
                " da casa, basta colocar balde ou bacia para armazenar essa água",
            "Não use o vaso sanitário como lixeira (não jogue ali papel higiênico e cigarro, " +
                "por exemplo)",
            "Junte roupa e use a máquina de lavar apenas quando estiver com a capacidade total; no " +
                "tanque, mantenha a torneira fechada enquanto ensaboa a roupa",
            "Não lave o carro com mangueira; use balde e pano"
        };

        return result;
    }
}

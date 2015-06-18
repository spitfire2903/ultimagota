package br.com.ricardonm.gotadagua.model;

import com.orm.SugarRecord;

/**
 * Created by ricardomiranda on 17/06/15.
 */
public class UnitReference extends SugarRecord<UnitReference> {
    private String name;
    private Double valueM3;
    private String imgName;

    public UnitReference(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValueM3() {
        return valueM3;
    }

    public void setValueM3(Double valueM3) {
        this.valueM3 = valueM3;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public static Double convertM3ToLitre(Double valueM3){
        Double result = null;

        result = valueM3 * 1000;

        return result;
    }

    public static Double convertLitreToM3(Double valueLitre){
        Double result = null;

        result = valueLitre / 1000;

        return result;
    }

}

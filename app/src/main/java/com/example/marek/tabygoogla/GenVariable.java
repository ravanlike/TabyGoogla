package com.example.marek.tabygoogla;

/**
 * Created by ravanlike on 1/23/17.
 */

public class GenVariable {

    private Integer dataType;
    private String dataText1;
    private String dataText2;
    private String dataText3;

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public String getDataText1() {
        return dataText1;
    }

    public void setDataText1(String dataText1) {
        this.dataText1 = dataText1;
    }

    public String getDataText2() {
        return dataText2;
    }

    public void setDataText2(String dataText2) {
        this.dataText2 = dataText2;
    }

    public String getDataText3() {
        return dataText3;
    }

    public void setDataText3(String dataText3) {
        this.dataText3 = dataText3;
    }


    public void setDataAll(Integer dataType, String dataText1, String dataText2, String dataText3){
        this.dataType = dataType;
        this.dataText1 = dataText1;
        this.dataText2 = dataText2;
        this.dataText3 = dataText3;
    }
}

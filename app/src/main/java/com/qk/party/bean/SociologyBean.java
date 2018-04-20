package com.qk.party.bean;

/**
 * @package： com.qk.party.bean
 * @class: SociologyBean
 * @author:  小飞
 * @date: 2017/10/25 17:52
 * @描述：
 */

public class SociologyBean {
    private int id;
    private String kpi;
    private String unit;
    private int chartType;
    private String target;
    private String showString;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKpi() {
        return kpi;
    }

    public void setKpi(String kpi) {
        this.kpi = kpi;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getChartType() {
        return chartType;
    }

    public void setChartType(int chartType) {
        this.chartType = chartType;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getShowString() {
        return showString;
    }

    public void setShowString(String showString) {
        this.showString = showString;
    }
}

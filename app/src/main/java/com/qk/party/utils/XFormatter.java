package com.qk.party.utils;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.List;

/**
 * @package： com.qk.party.utils
 * @class: XFormatter
 * @author:  小飞
 * @date: 2017/10/20 18:21
 * @描述：
 */
public class XFormatter implements IAxisValueFormatter {
    List<String> mValues;
    public XFormatter(List<String> values) {
        this.mValues = values;
    }
    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        if(value<0){
            return "";
        }
        int i = (int) value;
        return mValues.get(i);
    }
}

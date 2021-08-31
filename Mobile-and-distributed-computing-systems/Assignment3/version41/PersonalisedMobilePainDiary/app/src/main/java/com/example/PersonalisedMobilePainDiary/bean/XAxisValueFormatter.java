package com.example.PersonalisedMobilePainDiary.bean;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

public class XAxisValueFormatter extends ValueFormatter {

    private final String[] mLabels;
    public XAxisValueFormatter(String[] labels) {
        mLabels = labels;
    }
    @Override
    public String getFormattedValue(float value) {
        try {
            return mLabels[(int) value];
        } catch (Exception e) {
            e.printStackTrace();
            return mLabels[0];
        }
    }
}

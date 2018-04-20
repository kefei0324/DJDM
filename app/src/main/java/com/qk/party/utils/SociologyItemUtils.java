package com.qk.party.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.qk.party.R;
import com.qk.party.bean.SociologyBean;
import com.qk.party.bean.SociologyItemBean;
import com.qk.party.presenter.SociologyPresenter;
import com.qk.party.viewinterface.NetworkView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @package： com.qk.party.utils
 * @class: SociologyItemUtils
 * @author: 小飞
 * @date: 2017/10/25 18:16
 * @描述：
 */
public class SociologyItemUtils implements NetworkView<List<SociologyItemBean>> {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.chart)
    CombinedChart chart;
    private LinearLayout layout;
    private SociologyBean item;
    private Context context;
    private SociologyPresenter presenter;
    DecimalFormat fnum;
    List<String> labels;
    View rootView;

    public SociologyItemUtils(LinearLayout layout, SociologyBean item, Context context) {
        this.layout = layout;
        this.item = item;
        this.context = context;
        presenter = new SociologyPresenter(this);
    }

    public void start() {
        rootView = View.inflate(context, R.layout.sociology_item, null);
        ButterKnife.bind(this, rootView);
        title.setText(item.getKpi());

        presenter.getSociologyDetail(ShardUtil.getPreferenceString(context, "access_token"), item.getId());
    }

    @Override
    public void success(List<SociologyItemBean> sociologyItemBeans) {
        if (sociologyItemBeans.size() == 0) {
            return;
        }
        if (item.getChartType() == 0) {
            brokenLine(sociologyItemBeans);
        } else {
            column(sociologyItemBeans);
        }
        layout.addView(rootView);
//        layout.postInvalidateDelayed(500);
    }

    /**
     * 折线图
     */
    public void brokenLine(List<SociologyItemBean> sociologyItemBeans) {
        chart.setDrawGridBackground(false);
        chart.getDescription().setEnabled(false);
        chart.setTouchEnabled(true);
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setPinchZoom(true);
        chart.setDoubleTapToZoomEnabled(false);
        LimitLine llXAxis = new LimitLine(10f, "Index 10");
        llXAxis.setLineWidth(4f);
        llXAxis.enableDashedLine(10f, 10f, 0f);
        llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        llXAxis.setTextSize(10f);
        XAxis xAxis = chart.getXAxis();
        List<String> labels = new ArrayList<>();
        labels.add(sociologyItemBeans.get(0).getMonth() + item.getShowString());
        labels.add(
                sociologyItemBeans.size() > 1 ?
                        sociologyItemBeans.get(1).getMonth() + item.getShowString() : "noData");

        XFormatter myXFormatter = new XFormatter(labels);
        xAxis.setValueFormatter(myXFormatter);
        xAxis.setLabelCount(1);
        xAxis.setAxisMinimum(-0.2F);
        xAxis.setGranularity(1f);
        xAxis.setAxisMaximum(1.2f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        /**
         * 基线
         * */
        LimitLine yLimitLine = new LimitLine(getFloat(item.getTarget()), item.getTarget());
        yLimitLine.setLineColor(Color.RED);
        yLimitLine.setTextColor(Color.GRAY);
        yLimitLine.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        yLimitLine.enableDashedLine(10f, 10f, 0f);
        leftAxis.addLimitLine(yLimitLine);
        leftAxis.setAxisMinimum(0f);
        fnum = new DecimalFormat("##0.0");
        leftAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return fnum.format(value) + item.getUnit();
            }
        });

        leftAxis.setDrawLimitLinesBehindData(true);
        chart.getAxisRight().setEnabled(false);
        setData(sociologyItemBeans);
        chart.animateX(2500);
        Legend l = chart.getLegend();
        l.setForm(Legend.LegendForm.LINE);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
    }

    private float getFloat(String spec) {
        float r = 0;
        try {
            r = Float.parseFloat(spec);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return r;
    }

    @Override
    public void error(String error) {

    }

    /**
     * 柱状图
     */
    public void column(List<SociologyItemBean> sociologyItemBeans) {
        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(true);
        chart.getDescription().setEnabled(false);
        chart.setMaxVisibleValueCount(60);
        chart.setPinchZoom(false);
        chart.setDrawGridBackground(false);
        chart.setDoubleTapToZoomEnabled(false);
        chart.getAxisRight().setEnabled(false);
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMinimum(-0.2F);
        xAxis.setGranularity(0.5f);
        xAxis.setAxisMaximum(2.2f);
        labels = new ArrayList<>();
        labels.add(sociologyItemBeans.get(0).getMonth() + item.getShowString());
        labels.add(
                sociologyItemBeans.size() > 1 ?
                        sociologyItemBeans.get(1).getMonth() + item.getShowString() : "noData");

        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if (String.valueOf(value).contains("5")) {
                    return labels.get((int) value);
                }
                return "";
            }
        });


        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setAxisMinimum(0f);

        LimitLine yLimitLine = new LimitLine(Float.parseFloat(item.getTarget()), item.getTarget());
        yLimitLine.setLineColor(Color.RED);
        yLimitLine.setTextColor(Color.GRAY);
        yLimitLine.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        yLimitLine.enableDashedLine(10f, 10f, 0f);
        leftAxis.addLimitLine(yLimitLine);

        fnum = new DecimalFormat("##0.0");
        leftAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return fnum.format(value) + item.getUnit();
            }
        });

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);
        chart.animateY(2500); // 图2
        setData2(sociologyItemBeans);

    }

    private void setData(List<SociologyItemBean> sociologyItemBeans) {

        ArrayList<Entry> values = new ArrayList<>();
        ArrayList<Entry> values2 = new ArrayList<>();
        SociologyItemBean sociology1 = sociologyItemBeans.get(0);
        values.add(new Entry(0, (float) sociology1.getScore()));
        values.add(new Entry(1, (float) sociology1.getHisScore()));

        SociologyItemBean sociology2 = sociologyItemBeans.size() > 1 ? sociologyItemBeans.get(1) : null;
        if (sociology2 != null) {
            values2.add(new Entry(0, (float) sociology2.getScore()));
            values2.add(new Entry(1, (float) sociology2.getHisScore()));
        }
        LineDataSet set1;
        LineDataSet set2;
        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            /*set2 = (LineDataSet)chart.getData().getDataSetByIndex(1);
            set2.setValues(values2);*/
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            set1 = new LineDataSet(values, "今年");
            set1.setDrawIcons(false);
            set1.setColor(Color.parseColor("#C23531"));
            set1.setCircleColor(Color.BLACK);
            set1.setLineWidth(2f);
            set1.setCircleRadius(1f);
            set1.setDrawCircles(true);
            set1.setDrawCircleHole(false);
            set1.setValueTextSize(9f);
            set1.setDrawFilled(false);
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            set2 = new LineDataSet(values2, "去年");
            set2.setDrawIcons(false);
            set2.setColor(Color.parseColor("#2F4554"));
            set2.setCircleColor(Color.BLACK);
            set2.setLineWidth(2f);
            set2.setCircleRadius(1f);
            set2.setDrawCircles(true);
            set2.setDrawCircleHole(false);
            set2.setValueTextSize(9f);
            set2.setDrawFilled(false);
            set2.setFormLineWidth(1f);
            set2.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set2.setFormSize(15.f);

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            dataSets.add(set2);
            CombinedData data = new CombinedData();
            data.setData(new LineData(dataSets));
            chart.setData(data);
        }
    }

    private void setData2(List<SociologyItemBean> sociologyItemBeans) {
        ArrayList<BarEntry> yVals1 = new ArrayList<>();
        ArrayList<BarEntry> yVals2 = new ArrayList<>();
        SociologyItemBean sociology1 = sociologyItemBeans.get(0);
        SociologyItemBean sociology2 = sociologyItemBeans.get(1);
        yVals1.add(new BarEntry(0, (float) sociology1.getScore()));
        yVals1.add(new BarEntry(1, (float) sociology1.getHisScore()));
        yVals2.add(new BarEntry(0, (float) sociology2.getScore()));
        yVals2.add(new BarEntry(1, (float) sociology2.getHisScore()));

        BarDataSet set1;
        BarDataSet set2;
        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            set2 = (BarDataSet) chart.getData().getDataSetByIndex(1);
            set2.setValues(yVals2);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "今年");
            set1.setDrawIcons(false);
            set1.setColors(Color.parseColor("#C23531"));

            set2 = new BarDataSet(yVals2, "去年");
            set2.setDrawIcons(false);
            set2.setColors(Color.parseColor("#2F4554"));

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            dataSets.add(set2);

            int amount = 2; //需要显示柱状图的类别 数量
            float groupSpace = 0.12f; //柱状图组之间的间距
            float barSpace = (float) ((1 - 0.12) / amount / 10); // x4 DataSet
            float barWidth = (float) ((1 - 0.12) / amount / 10 * 9); // x4 DataSet


            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(barWidth);
            data.groupBars(0f, groupSpace, barSpace);

            CombinedData datas = new CombinedData();
            datas.setData(data);
            chart.setData(datas);
        }
    }
}

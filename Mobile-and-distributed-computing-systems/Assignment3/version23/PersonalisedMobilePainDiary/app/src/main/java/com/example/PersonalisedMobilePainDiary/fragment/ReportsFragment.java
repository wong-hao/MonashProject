package com.example.PersonalisedMobilePainDiary.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.PersonalisedMobilePainDiary.R;
import com.example.PersonalisedMobilePainDiary.Steps;
import com.example.PersonalisedMobilePainDiary.Weather;
import com.example.PersonalisedMobilePainDiary.databinding.FragmentDailyBinding;
import com.example.PersonalisedMobilePainDiary.databinding.FragmentReportsBinding;
import com.example.PersonalisedMobilePainDiary.entity.PainRecord;
import com.example.PersonalisedMobilePainDiary.viewmodel.PainRecordViewModel;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.List;

public class ReportsFragment extends Fragment {
    private FragmentReportsBinding binding;
    public ReportsFragment(){}
    PainRecordViewModel model;

    int steps_taken;
    int goal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentReportsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        model = new ViewModelProvider(requireActivity()).get(PainRecordViewModel.class);
        model.initalizeVars(getActivity());
        model.getAllPainRecords().observe(getViewLifecycleOwner(), new
                Observer<List<PainRecord>>() {
                    @Override
                    public void onChanged(List<PainRecord> painRecords) {

                        String allPainrecords = "";
                        for(PainRecord temp : painRecords){
                            String painrecordstr = (" ID: " + temp.getId() + " Intensity: " + temp.getIntensity() + " Location: " + temp.getLocation() +  " Mood: " + temp.getMood() + " Steps: " + temp.getSteps() + " Date: " + temp.getDate() + " Temperature: " + temp.getTemperature() + " Humidity: " + temp.getHumidity() + " Pressure: " + temp.getPressure() + " Email: " + temp.getEmail());
                        }
                    }

                });
        model.findByID(0);

        binding.switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getActivity(),"Show Pain location pie chart",Toast.LENGTH_SHORT).show();
                    showPainChart();
                }else{
                    Toast.makeText(getActivity(),"Show Steps taken pie chart",Toast.LENGTH_SHORT).show();
                    steps_taken = Steps.getInstance().getSteps_taken();
                    goal = Steps.getInstance().getGoal();
                    //steps_taken = 3000;
                    //goal = 10000;
                    //System.out.println(steps_taken);
                    //System.out.println(goal);
                    showStepsChart();
                }
            }
        });



    return view;

    }

    public void showPainChart(){

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        entries.clear();//清除数据
        //添加数据
        entries.add(new PieEntry(10, "人性的弱点"));
        entries.add(new PieEntry(12, "狼道"));
        entries.add(new PieEntry(17, "鬼谷子"));
        entries.add(new PieEntry(20, "YOUTH.度"));
        entries.add(new PieEntry(22, "週莫"));
        entries.add(new PieEntry(25, "墨菲定律"));

        binding.pcCharts.setUsePercentValues(true); //设置是否显示数据实体(百分比，true:以下属性才有意义)
        binding.pcCharts.getDescription().setEnabled(false);//设置pieChart图表的描述
        binding.pcCharts.setExtraOffsets(5, 5, 5, 5);//饼形图上下左右边距

        binding.pcCharts.setDragDecelerationFrictionCoef(0.95f);//设置pieChart图表转动阻力摩擦系数[0,1]

//        mBinding.pcCharts.setCenterTextTypeface(mTfLight);//设置所有DataSet内数据实体（百分比）的文本字体样式
        //binding.pcCharts.setCenterText("饼状图");//设置PieChart内部圆文字的内容

        binding.pcCharts.setDrawHoleEnabled(true);//是否显示PieChart内部圆环(true:下面属性才有意义)
        binding.pcCharts.setHoleColor(Color.WHITE);//设置PieChart内部圆的颜色

        binding.pcCharts.setTransparentCircleColor(Color.WHITE);//设置PieChart内部透明圆与内部圆间距(31f-28f)填充颜色
        binding.pcCharts.setTransparentCircleAlpha(0);//设置PieChart内部透明圆与内部圆间距(31f-28f)透明度[0~255]数值越小越透明
        binding.pcCharts.setHoleRadius(0f);//设置PieChart内部圆的半径(这里设置0f,即不要内部圆)
        binding.pcCharts.setTransparentCircleRadius(31f);//设置PieChart内部透明圆的半径(这里设置31.0f)

        binding.pcCharts.setDrawCenterText(true);//是否绘制PieChart内部中心文本（true：下面属性才有意义）

        binding.pcCharts.setRotationAngle(0);//设置pieChart图表起始角度

        binding.pcCharts.setRotationEnabled(true);//设置pieChart图表是否可以手动旋转
        binding.pcCharts.setHighlightPerTapEnabled(true);//设置piecahrt图表点击Item高亮是否可用

        binding.pcCharts.animateY(1400, Easing.EaseInOutQuad);// 设置pieChart图表展示动画效果
//         mBinding.pcCharts.spin(2000, 0, 360);//旋转

        // 获取pieCahrt图列(图列的位置、是水平还是垂直显示)
        Legend l = binding.pcCharts.getLegend();
        l.setForm(Legend.LegendForm.LINE);//线性
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);//上边
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);//右边（跟着TOP既是右上角，根据自己需求设置左上角、左下角……）
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f); //设置图例实体之间延X轴的间距（setOrientation = HORIZONTAL有效）
        l.setYEntrySpace(0f); //设置图例实体之间延Y轴的间距（setOrientation = VERTICAL 有效）
        l.setYOffset(0f);//设置比例块Y轴偏移量


        binding.pcCharts.setEntryLabelColor(Color.WHITE);//设置pieChart图表文本字体颜色
//        mBinding.pcCharts.setEntryLabelTypeface(mTfRegular);//设置pieChart图表文本字体样式
        binding.pcCharts.setEntryLabelTextSize(12f);//设置pieChart图表文本字体大小

        PieDataSet dataSet = new PieDataSet(entries, "数据说明");//右上角，依次排列

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(0f);//设置饼状Item之间的间隙
        dataSet.setIconsOffset(new MPPointF(0, 40));

        dataSet.setSelectionShift(5f);//设置饼状Item被选中时变化的距离(为0f时，选中的不会弹起来)

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(c);
        }

        for (int c : ColorTemplate.JOYFUL_COLORS) {
            colors.add(c);
        }

        for (int c : ColorTemplate.COLORFUL_COLORS) {
            colors.add(c);
        }

        for (int c : ColorTemplate.LIBERTY_COLORS) {
            colors.add(c);
        }

        for (int c : ColorTemplate.PASTEL_COLORS) {
            colors.add(c);
        }

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);//设置饼图里面的百分比（eg: 20.8%）
        data.setDrawValues(true);            //设置是否显示数据实体(百分比，true:以下属性才有意义)
        data.setValueTextColor(Color.WHITE);  //设置所有DataSet内数据实体（百分比）的文本颜色
        data.setValueTextSize(11f);          //设置所有DataSet内数据实体（百分比）的文本字体大小
//        data.setValueTypeface(mTfLight);     //设置所有DataSet内数据实体（百分比）的文本字体样式
        data.setValueFormatter(new PercentFormatter());//设置所有DataSet内数据实体（百分比）的文本字体格式

        binding.pcCharts.setData(data);// //为图表添加 数据

        binding.pcCharts.highlightValues(null);//设置高亮显示
        binding.pcCharts.setDrawEntryLabels(true);// 设置pieChart是否只显示饼图上百分比不显示文字
        binding.pcCharts.invalidate();//将图表重绘以显示设置的属性和数据

        binding.pcCharts.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {//点击事件
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                // e.getX()方法得到x数据
                PieEntry pieEntry = (PieEntry) e;
                Toast.makeText(getActivity(),"->value:" + pieEntry.getValue()+ "->lable:" + pieEntry.getLabel(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    public void showStepsChart(){


        //如果启用此选项，则图表中的值将以百分比形式绘制，而不是以原始值绘制
        binding.pcCharts.setUsePercentValues(true);
        //如果这个组件应该启用（应该被绘制）FALSE如果没有。如果禁用，此组件的任何内容将被绘制默认
        binding.pcCharts.getDescription().setEnabled(false);
        //将额外的偏移量（在图表视图周围）附加到自动计算的偏移量
        binding.pcCharts.setExtraOffsets(5, 10, 5, 5);
        //较高的值表明速度会缓慢下降 例如如果它设置为0，它会立即停止。1是一个无效的值，并将自动转换为0.999f。
        binding.pcCharts.setDragDecelerationFrictionCoef(0.95f);
        //设置中间字体
        //binding.pcCharts.setCenterText("刘某人\n我仿佛听到有人说我帅");
        //设置为true将饼中心清空
        binding.pcCharts.setDrawHoleEnabled(true);
        //套孔，绘制在PieChart中心的颜色
        binding.pcCharts.setHoleColor(Color.WHITE);
        //设置透明圆应有的颜色。
        binding.pcCharts.setTransparentCircleColor(Color.WHITE);
        //设置透明度圆的透明度应该有0 =完全透明，255 =完全不透明,默认值为100。
        binding.pcCharts.setTransparentCircleAlpha(110);
        //设置在最大半径的百分比饼图中心孔半径（最大=整个图的半径），默认为50%
        binding.pcCharts.setHoleRadius(58f);
        //设置绘制在孔旁边的透明圆的半径,在最大半径的百分比在饼图*（max =整个图的半径），默认55% -> 5%大于中心孔默认
        binding.pcCharts.setTransparentCircleRadius(61f);
        //将此设置为true，以绘制显示在pie chart
        binding.pcCharts.setDrawCenterText(true);
        //集度的radarchart旋转偏移。默认270f -->顶（北）
        binding.pcCharts.setRotationAngle(0);
        //设置为true，使旋转/旋转的图表触摸。设置为false禁用它。默认值：true
        binding.pcCharts.setRotationEnabled(true);
        //将此设置为false，以防止由抽头姿态突出值。值仍然可以通过拖动或编程高亮显示。默认值：真
        binding.pcCharts.setHighlightPerTapEnabled(true);
        //创建Legend对象
        Legend l = binding.pcCharts.getLegend();
        //设置垂直对齐of the Legend
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        //设置水平of the Legend
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        //设置方向
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        //其中哪一个将画在图表或外
        l.setDrawInside(false);
        //设置水平轴上图例项之间的间距
        l.setXEntrySpace(7f);
        //设置在垂直轴上的图例项之间的间距
        l.setYEntrySpace(0f);
        //设置此轴上标签的所使用的y轴偏移量 更高的偏移意味着作为一个整体的Legend将被放置远离顶部。
        l.setYOffset(0f);
        //设置入口标签的颜色。
        binding.pcCharts.setEntryLabelColor(Color.WHITE);
        //设置入口标签的大小。默认值：13dp
        binding.pcCharts.setEntryLabelTextSize(12f);
        //模拟的数据源
        PieEntry x1 = new PieEntry(steps_taken , "steps taken" , R.color.colorAccent) ;
        PieEntry x2 = new PieEntry(goal-steps_taken , "remaining steps") ;

        //添加到List集合
        List<PieEntry> list = new ArrayList<>() ;
        list.add(x1) ;
        list.add(x2) ;

        //设置到PieDataSet对象
        PieDataSet set = new PieDataSet(list , "Steps Chart") ;
        set.setDrawValues(true);//设置为true，在图表绘制y
        set.setAxisDependency(YAxis.AxisDependency.LEFT);//设置Y轴，这个数据集应该被绘制（左或右）。默认值：左
        set.setAutomaticallyDisableSliceSpacing(false);//当启用时，片间距将是0时，最小值要小于片间距本身
        set.setSliceSpace(5f);//间隔
        set.setSelectionShift(10f);//点击伸出去的距离
        set.setValueTextSize(11f);
        /**
         * 设置该数据集前应使用的颜色。颜色使用只要数据集所代表的条目数目高于颜色数组的大小。
         * 如果您使用的颜色从资源， 确保颜色已准备好（通过调用getresources()。getColor（…））之前，将它们添加到数据集
         * */
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);
        colors.add(ColorTemplate.getHoloBlue());
        set.setColors(colors);
        //传入PieData
        PieData data = new PieData(set);
        //为图表设置新的数据对象
        binding.pcCharts.setData(data);
        //刷新
        binding.pcCharts.invalidate();
        //动画图上指定的动画时间轴的绘制
        //binding.pcCharts.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        binding.pcCharts.animateY(1400, Easing.EaseInOutQuad);// 设置pieChart图表展示动画效果

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
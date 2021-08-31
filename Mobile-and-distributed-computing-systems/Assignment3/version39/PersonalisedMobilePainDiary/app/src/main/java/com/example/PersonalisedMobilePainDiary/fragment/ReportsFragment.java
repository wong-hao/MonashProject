package com.example.PersonalisedMobilePainDiary.fragment;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.PersonalisedMobilePainDiary.R;
import com.example.PersonalisedMobilePainDiary.Steps;
import com.example.PersonalisedMobilePainDiary.Weather;
import com.example.PersonalisedMobilePainDiary.bean.XAxisValueFormatter;
import com.example.PersonalisedMobilePainDiary.databinding.FragmentDailyBinding;
import com.example.PersonalisedMobilePainDiary.databinding.FragmentReportsBinding;
import com.example.PersonalisedMobilePainDiary.entity.PainRecord;
import com.example.PersonalisedMobilePainDiary.viewmodel.PainRecordViewModel;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

public class ReportsFragment extends Fragment {
    private FragmentReportsBinding binding;
    public ReportsFragment(){}
    PainRecordViewModel model;

    int back = 0;
    int neck = 0;
    int head = 0;
    int knees = 0;
    int hips = 0;
    int abdomen = 0;
    int elbows = 0;
    int shoulders = 0;
    int shins = 0;
    int jaw = 0;
    int facial = 0;

    String location;

    int steps_taken;
    int goal;

    Calendar startCanlendar = Calendar.getInstance();
    int  startYear = startCanlendar.get(Calendar.YEAR);
    int  startMonth = startCanlendar.get(Calendar.MONTH);
    int  startDay = startCanlendar.get(Calendar.DAY_OF_MONTH);
    String StartDay;

    Calendar endCanlendar = Calendar.getInstance();
    int  endYear = endCanlendar.get(Calendar.YEAR);
    int  endMonth = endCanlendar.get(Calendar.MONTH);
    int  endtDay = endCanlendar.get(Calendar.DAY_OF_MONTH);
    String EndDay;

    private String[] array1 = {"temperature","humidity","pressure"};
    String weathervarible;
    String[] dateRange = new String[14400];
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    int m=0;

    String[] y1Data = new String[14400];
    int n=0;
    String[] y2Data = new String[14400];
    int o=0;

    int rvalue = 0;
    int pvalue = 0;

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

                        for(PainRecord temp : painRecords){
                            location = temp.getLocation();
                            //System.out.println(location);
                            if(location.equals("back")){
                                back++;
                            }else if(location.equals("neck")){
                                neck++;
                            }else if(location.equals("head")){
                                head++;
                            }else if(location.equals("knees")){
                                knees++;
                            }else if(location.equals("hips")){
                                hips++;
                            }else if(location.equals("abdomen")){
                                abdomen++;
                            }else if(location.equals("elbows")){
                                elbows++;
                            }else if(location.equals("shoulders")){
                                shoulders++;
                            }else if(location.equals("shins")){
                                shins++;
                            }else if(location.equals("jaw")){
                                jaw++;
                            }else if(location.equals("facial")){
                                facial++;
                            }

                        }
                    }

                });

    binding.pcCharts.setNoDataTextColor(Color.WHITE);
    binding.lineCharts.setNoDataTextColor(Color.WHITE);

    binding.Button8.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            new DatePickerDialog(getActivity(), DatePickerDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    if((month+1)<10 && dayOfMonth<10){
                        StartDay = year + "-0" + (month+1) + "-0" + dayOfMonth;
                    }else if((month+1)<10 && dayOfMonth>=10){
                        StartDay = year + "-0" + (month+1) + "-" + dayOfMonth;
                    }else if((month+1)>=10 && dayOfMonth<10){
                        StartDay = year + "-" + (month+1) + "-0" + dayOfMonth;
                    }else if((month+1)>=10 && dayOfMonth>=10){
                        StartDay = year + "-" + (month+1) + "-" + dayOfMonth;
                    }

                    Toast.makeText(getContext(), "Starting date: " +StartDay, Toast.LENGTH_SHORT).show();
                }
            }, startYear, startMonth, startDay).show();
        }
    });

    binding.Button9.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new DatePickerDialog(getActivity(), DatePickerDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    if((month+1)<10 && dayOfMonth<10){
                        EndDay = year + "-0" + (month+1) + "-0" + dayOfMonth;
                    }else if((month+1)<10 && dayOfMonth>=10){
                        EndDay = year + "-0" + (month+1) + "-" + dayOfMonth;
                    }else if((month+1)>=10 && dayOfMonth<10){
                        EndDay = year + "-" + (month+1) + "-0" + dayOfMonth;
                    }else if((month+1)>=10 && dayOfMonth>=10){
                        EndDay = year + "-" + (month+1) + "-" + dayOfMonth;
                    }

                    Toast.makeText(getContext(), "Ending date: " + EndDay, Toast.LENGTH_SHORT).show();
                }
            }, endYear, endMonth, endtDay).show();
        }
    });

    ArrayAdapter<String> Adapter1 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,array1);
    binding.Spinner4.setPrompt("Weather variables");
    binding.Spinner4.setAdapter(Adapter1);
    binding.Spinner4.setSelection(0,true);
    binding.Spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getActivity(),"Weather variable that you choose is："+array1[position],Toast.LENGTH_SHORT).show();
            weathervarible = array1[position];
            //System.out.println(intensity);

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    });

    binding.radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.radioButton1:
                    if(binding.radioButton1.isChecked()){
                        binding.pcCharts.setVisibility(View.VISIBLE);
                        binding.lineCharts.setVisibility(View.INVISIBLE);

                        Toast.makeText(getActivity(),"Show Pain location pie chart",Toast.LENGTH_SHORT).show();
                        showPainChart();
                    }
                case R.id.radioButton2:
                    if(binding.radioButton2.isChecked()){
                        binding.pcCharts.setVisibility(View.VISIBLE);
                        binding.lineCharts.setVisibility(View.INVISIBLE);

                        Toast.makeText(getActivity(),"Show Steps taken pie chart",Toast.LENGTH_SHORT).show();

                        steps_taken = Steps.getInstance().getSteps_taken();
                        goal = Steps.getInstance().getGoal();
                        //steps_taken = 3000;
                        //goal = 10000;
                        //System.out.println("Steps got: "+ steps_taken);
                        //System.out.println("Goal got: " + goal);
                        showStepsChart();
                    }
                case R.id.radioButton3:
                    if(binding.radioButton3.isChecked()){
                        binding.lineCharts.setVisibility(View.VISIBLE);
                        binding.pcCharts.setVisibility(View.INVISIBLE);


                        if(StartDay==null){
                            Toast.makeText(getActivity(),"Starting day is empty",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(EndDay==null){
                            Toast.makeText(getActivity(),"Ending day is empty",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(weathervarible==null){
                            Toast.makeText(getActivity(),"Weather variable is empty",Toast.LENGTH_SHORT).show();
                            return;
                        }

                        try{
                            Date startDate = dateFormat.parse(StartDay);
                            Date endDate = dateFormat.parse(EndDay);
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(startDate);
                            while(calendar.getTime().before(endDate)){
                                //System.out.println(dateFormat.format(calendar.getTime()));
                                dateRange[m++] = dateFormat.format(calendar.getTime());
                                calendar.add(Calendar.DAY_OF_MONTH, 1);

                            }
                            //System.out.println(dateFormat.format(endDate));
                            dateRange[m++] = dateFormat.format(endDate);
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }

                        //for(int i=0;i<m;i++){
                            //System.out.println(dateRange[i]);
                        //}
                        Toast.makeText(getActivity(),"Show Line chart",Toast.LENGTH_SHORT).show();

                        showLineChart();

                        binding.textView20.setText("Correlation R value: " + testCorrelationR());
                        binding.textView21.setText("Correlation P value: " + testCorrelationP());

                    }
            }
        }
    });


    return view;

    }

    public void showPainChart(){

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        entries.clear();//清除数据
        //添加数据
        entries.add(new PieEntry(back, "back"));
        entries.add(new PieEntry(neck, "neck"));
        entries.add(new PieEntry(head, "head"));
        entries.add(new PieEntry(knees, "knees"));
        entries.add(new PieEntry(hips, "hips"));
        entries.add(new PieEntry(abdomen, "abdomen"));
        entries.add(new PieEntry(elbows, "elbows"));
        entries.add(new PieEntry(shoulders, "shoulders"));
        entries.add(new PieEntry(shins, "shins"));
        entries.add(new PieEntry(jaw, "jaw"));
        entries.add(new PieEntry(facial, "facial"));

        binding.pcCharts.setUsePercentValues(false); //设置是否显示数据实体(百分比，true:以下属性才有意义)
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
        l.setTextSize(8f);//标签字体大小为12f
        l.setXEntrySpace(7f); //设置图例实体之间延X轴的间距（setOrientation = HORIZONTAL有效）
        l.setYEntrySpace(0f); //设置图例实体之间延Y轴的间距（setOrientation = VERTICAL 有效）
        l.setYOffset(0f);//设置比例块Y轴偏移量


        binding.pcCharts.setEntryLabelColor(Color.BLACK);//设置pieChart图表文本字体颜色
//        mBinding.pcCharts.setEntryLabelTypeface(mTfRegular);//设置pieChart图表文本字体样式
        binding.pcCharts.setEntryLabelTextSize(12f);//设置pieChart图表文本字体大小

        PieDataSet dataSet = new PieDataSet(entries, "Pain location pie chart");//右上角，依次排列

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
        data.setValueTextColor(Color.BLACK);  //设置所有DataSet内数据实体（百分比）的文本颜色
        data.setValueTextSize(11f);          //设置所有DataSet内数据实体（百分比）的文本字体大小
//        data.setValueTypeface(mTfLight);     //设置所有DataSet内数据实体（百分比）的文本字体样式
        data.setValueFormatter(new PercentFormatter());//设置所有DataSet内数据实体（百分比）的文本字体格式

        binding.pcCharts.setData(data);// //为图表添加 数据

        binding.pcCharts.highlightValues(null);//设置高亮显示
        binding.pcCharts.setDrawEntryLabels(true);// 设置pieChart是否只显示饼图上百分比不显示文字
        binding.pcCharts.notifyDataSetChanged();
        binding.pcCharts.invalidate();//将图表重绘以显示设置的属性和数据

        binding.pcCharts.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {//点击事件
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                // e.getX()方法得到x数据
                PieEntry pieEntry = (PieEntry) e;
                Toast.makeText(getActivity(),"The values:" + pieEntry.getValue()+ " The lable:" + pieEntry.getLabel(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    public void showStepsChart(){


        //如果启用此选项，则图表中的值将以百分比形式绘制，而不是以原始值绘制
        binding.pcCharts.setUsePercentValues(false);
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

        binding.pcCharts.setExtraBottomOffset(20);
        //创建Legend对象
        Legend l = binding.pcCharts.getLegend();
        //设置垂直对齐of the Legend
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        //设置水平of the Legend
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        //设置方向
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        //其中哪一个将画在图表或外
        l.setDrawInside(false);
        //设置水平轴上图例项之间的间距
        l.setXEntrySpace(7f);
        //设置在垂直轴上的图例项之间的间距
        l.setYEntrySpace(0f);
        l.setTextSize(12f);//标签字体大小为12f

        //设置此轴上标签的所使用的y轴偏移量 更高的偏移意味着作为一个整体的Legend将被放置远离顶部。
        l.setYOffset(0f);
        //设置入口标签的颜色。
        binding.pcCharts.setEntryLabelColor(Color.BLACK);
        //设置入口标签的大小。默认值：13dp
        binding.pcCharts.setEntryLabelTextSize(12f);
        //模拟的数据源
        binding.pcCharts.setDrawEntryLabels(true);// 设置pieChart是否只显示饼图上百分比不显示文字

        PieEntry x1 = new PieEntry(steps_taken , "steps taken" , R.color.colorAccent) ;
        PieEntry x2 = new PieEntry(goal-steps_taken , "remaining steps") ;

        //添加到List集合
        List<PieEntry> list = new ArrayList<>() ;
        list.add(x1) ;
        list.add(x2) ;

        //设置到PieDataSet对象
        PieDataSet set = new PieDataSet(list , "Steps taken pie chart") ;
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
        binding.pcCharts.notifyDataSetChanged();
        binding.pcCharts.invalidate();
        //动画图上指定的动画时间轴的绘制
        //binding.pcCharts.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        binding.pcCharts.animateY(1400, Easing.EaseInOutQuad);// 设置pieChart图表展示动画效果

    }

    public void showLineChart(){

        Description description = new Description();
        description.setText("");
        binding.lineCharts.setDescription(description);
        binding.lineCharts.setExtraLeftOffset(20);
        binding.lineCharts.setExtraRightOffset(20);
        //找到控件
        //创建Entry保存你的数据
        List<Entry> entry1 = new ArrayList<Entry>() ; //折线一的数据源
        List<Entry> entry2 = new ArrayList<Entry>() ; //折线二的数据源
        //向折线一添加数据

        for (int i = 0; i < m; i ++) {
            model.getAllPainRecords().observe(getViewLifecycleOwner(), new
                    Observer<List<PainRecord>>() {
                        @Override
                        public void onChanged(List<PainRecord> painRecords) {

                            for (PainRecord temp : painRecords) {

                                //System.out.println(temp.getIntensity());
                                //System.out.println(temp.getDate());

                                try {
                                    Date startDate = dateFormat.parse(StartDay);
                                    Date endDate = dateFormat.parse(EndDay);
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.setTime(startDate);
                                    while (calendar.getTime().before(endDate)) {
                                        //System.out.println(dateFormat.format(calendar.getTime()));
                                        if (temp.getDate().equals(dateFormat.format(calendar.getTime()))) {
                                            //System.out.println("YES");
                                            //System.out.println(temp.getIntensity());
                                            y1Data[n++] = Integer.toString(temp.getIntensity());
                                            //System.out.println(y1Data[j]);
                                        }else{
                                            //System.out.println("No");
                                        }
                                        calendar.add(Calendar.DAY_OF_MONTH, 1);

                                    }
                                    //System.out.println(dateFormat.format(endDate));
                                    if (temp.getDate().equals(dateFormat.format(calendar.getTime()))) {
                                        //System.out.println("YES");
                                        //System.out.println(temp.getIntensity());
                                        y1Data[n++] = Integer.toString(temp.getIntensity());
                                        //System.out.println(y1Data[j]);
                                    }else{
                                        //System.out.println("No");
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                //System.out.println(y1Data[j]);

                            }
                            //System.out.println(y1Data[j]);
                        }

                    });

            //System.out.println(y1Data[j]);
        }

        for (int i = 0; i < m; i ++) {
            model.getAllPainRecords().observe(getViewLifecycleOwner(), new
                    Observer<List<PainRecord>>() {
                        @Override
                        public void onChanged(List<PainRecord> painRecords) {

                            for (PainRecord temp : painRecords) {

                                //System.out.println(temp.getIntensity());
                                //System.out.println(temp.getDate());

                                try {
                                    Date startDate = dateFormat.parse(StartDay);
                                    Date endDate = dateFormat.parse(EndDay);
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.setTime(startDate);
                                    while (calendar.getTime().before(endDate)) {
                                        //System.out.println(dateFormat.format(calendar.getTime()));
                                        if (temp.getDate().equals(dateFormat.format(calendar.getTime()))) {
                                            //System.out.println("YES");
                                            //System.out.println(temp.getIntensity());
                                            if(weathervarible.equals("temperature")){
                                                y2Data[o++] = temp.getTemperature();
                                            }else if(weathervarible.equals("humidity")){
                                                y2Data[o++] = temp.getHumidity();
                                            }else if(weathervarible.equals("pressure")){
                                                y2Data[o++] = temp.getPressure();
                                            }
                                            //System.out.println(y1Data[j]);
                                        }else{
                                            //System.out.println("No");
                                        }
                                        calendar.add(Calendar.DAY_OF_MONTH, 1);

                                    }
                                    //System.out.println(dateFormat.format(endDate));
                                    if (temp.getDate().equals(dateFormat.format(calendar.getTime()))) {
                                        //System.out.println("YES");
                                        //System.out.println(temp.getIntensity());
                                        if(weathervarible.equals("temperature")){
                                            y2Data[o++] = temp.getTemperature();
                                        }else if(weathervarible.equals("humidity")){
                                            y2Data[o++] = temp.getHumidity();
                                        }else if(weathervarible.equals("pressure")){
                                            y2Data[o++] = temp.getPressure();
                                        }                                        //System.out.println(y1Data[j]);
                                    }else{
                                        //System.out.println("No");
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                //System.out.println(y1Data[j]);

                            }
                            //System.out.println(y1Data[j]);
                        }

                    });

            //System.out.println(y1Data[j]);
        }

        //System.out.println("m: "+m);

        if(y1Data[0] == null){

            return;
        }


        for (int i = 0; i < n; i ++){
            //System.out.println("Y1data: " + y1Data[i]);
        }

        for (int i = 0; i < m; i ++){
            float f = i;
            entry1.add(new Entry(f, Float.parseFloat(y1Data[i])));
        }

        for (int i = 0; i < m; i ++){
            float f = i;
            entry2.add(new Entry(f, Float.parseFloat(y2Data[i])));
        }

        m = 0;

        //将数据传递给LineDataSet对象
        LineDataSet set1 = new LineDataSet(entry1 , "Pain Level") ;
        //调用setAxisDependency（）指定DataSets绘制相应的折线
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        //折线一 折线的颜色
        set1.setColor(getResources().getColor(R.color.colorAccent));
        LineDataSet set2 = new LineDataSet(entry2 , "Weather Value") ;
        set2.setAxisDependency(YAxis.AxisDependency.LEFT);
        //折线二 折线的颜色
        set2.setColor(getResources().getColor(R.color.colorPrimary));
        //使用接口ILineDataSet
        List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>() ;
        //添加数据
        dataSets.add(set1) ;
        dataSets.add(set2) ;
        LineData data = new LineData(dataSets) ;
        //设置图表
        binding.lineCharts.setData(data);
        //刷新
        binding.lineCharts.notifyDataSetChanged();
        binding.lineCharts.invalidate();
        XAxis xAxis = binding.lineCharts.getXAxis();
        xAxis.setGranularity(1f);
        XAxisValueFormatter labelFormatter = new XAxisValueFormatter(dateRange);
        xAxis.setValueFormatter(labelFormatter);

    }

    public String testCorrelationR(){

        // two column array: 1st column=first array, 1st column=second array
        double data[][] = {
                {1,1},
                {-1,0},
                {11,87},
                {-6,5},
                {-6,3},
        };
        // create a realmatrix
        RealMatrix m = MatrixUtils.createRealMatrix(data);
        // measure all correlation test: x-x, x-y, y-x, y-x
        for (int i = 0; i < m.getColumnDimension(); i++)
            for (int j = 0; j < m.getColumnDimension(); j++) {
                PearsonsCorrelation pc = new PearsonsCorrelation();
                double cor = pc.correlation(m.getColumn(i), m.getColumn(j));
                System.out.println(i + "," + j + "=[" + String.format(".%2f", cor) + "," + "]");
            }
        // correlation test (another method): x-y
        PearsonsCorrelation pc = new PearsonsCorrelation(m);
        RealMatrix corM = pc.getCorrelationMatrix();
        // significant test of the correlation coefficient (p-value)
        RealMatrix pM = pc.getCorrelationPValues();
        return("p value:" + pM.getEntry(0, 1)+ "\n" + " correlation: " +
                corM.getEntry(0, 1));

    }

    public String testCorrelationP(){

        // two column array: 1st column=first array, 1st column=second array
        double data[][] = {
                {1,1},
                {-1,0},
                {11,87},
                {-6,5},
                {-6,3},
        };
        // create a realmatrix
        RealMatrix m = MatrixUtils.createRealMatrix(data);
        // measure all correlation test: x-x, x-y, y-x, y-x
        for (int i = 0; i < m.getColumnDimension(); i++)
            for (int j = 0; j < m.getColumnDimension(); j++) {
                PearsonsCorrelation pc = new PearsonsCorrelation();
                double cor = pc.correlation(m.getColumn(i), m.getColumn(j));
                System.out.println(i + "," + j + "=[" + String.format(".%2f", cor) + "," + "]");
            }
        // correlation test (another method): x-y
        PearsonsCorrelation pc = new PearsonsCorrelation(m);
        RealMatrix corM = pc.getCorrelationMatrix();
        // significant test of the correlation coefficient (p-value)
        RealMatrix pM = pc.getCorrelationPValues();
        return("p value:" + pM.getEntry(0, 1)+ "\n" + " correlation: " +
                corM.getEntry(0, 1));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
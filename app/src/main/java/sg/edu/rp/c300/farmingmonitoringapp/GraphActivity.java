package sg.edu.rp.c300.farmingmonitoringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class GraphActivity extends AppCompatActivity {

    LineChart lineChart;

    ArrayList alSelected;
    String type;

    LineDataSet setData;
    LineData lineData;
    ArrayList<Entry> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        lineChart = findViewById(R.id.lineChart);

        Intent i = getIntent();
        alSelected = i.getParcelableArrayListExtra("data");
        type = i.getStringExtra("type");

        data = new ArrayList<>();

        for (int a = 0; a < alSelected.size(); a++){
            Float dataEntry = Float.parseFloat(alSelected.get(a).toString());
            data.add(new Entry(a+1, dataEntry));
        }

        setData = new LineDataSet(data, type);
        setData.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        setData.setDrawFilled(true);
        setData.setDrawCircles(false);
        setData.setDrawValues(!setData.isDrawValuesEnabled());
        setData.setColor(Color.WHITE);
        setData.setFillColor(Color.WHITE);

        lineData = new LineData(setData);
        lineChart.setData(lineData);

        lineChart.setNoDataText("No Data Available");
        lineChart.setPinchZoom(false);
        lineChart.setDrawGridBackground(false);
        lineChart.setMaxHighlightDistance(300);
        lineChart.setViewPortOffsets(75, 75, 75, 75);
        lineChart.setBackgroundColor(Color.rgb(104, 241, 175));
        lineChart.invalidate();
        lineChart.animateXY(2000, 2000);
        lineChart.getDescription().setEnabled(false);
        lineChart.setTouchEnabled(true);
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        lineChart.getAxisLeft().setEnabled(true);
        lineChart.getAxisRight().setEnabled(false);

        YAxis y = lineChart.getAxisLeft();
        y.setLabelCount(6, false);
        y.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);

        XAxis x = lineChart.getXAxis();
        x.setPosition(XAxis.XAxisPosition.BOTTOM);

    }

}

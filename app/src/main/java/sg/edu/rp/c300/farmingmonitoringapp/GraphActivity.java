package sg.edu.rp.c300.farmingmonitoringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class GraphActivity extends AppCompatActivity {

    LineChart lineChart;

    ArrayList alSelected;
    String type;

    LineDataSet setTime, setData;
    ArrayList<Entry> data, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        lineChart = findViewById(R.id.lineChart);

        Intent i = getIntent();
        alSelected = i.getParcelableArrayListExtra("data");
        type = i.getStringExtra("type");

        lineChart.setBackgroundColor(Color.BLUE);
        lineChart.setGridBackgroundColor(Color.BLACK);
        lineChart.setDrawGridBackground(true);

        lineChart.setDrawBorders(true);
        lineChart.getDescription().setEnabled(true);
        lineChart.setPinchZoom(false);

        Legend legChart = lineChart.getLegend();
        legChart.setEnabled(false);

        data.addAll(alSelected);

        setData = new LineDataSet(data, "Data");
        setTime = new LineDataSet(time, "Time");

    }

}

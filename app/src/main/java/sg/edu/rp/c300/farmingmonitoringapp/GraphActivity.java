package sg.edu.rp.c300.farmingmonitoringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;

import java.util.ArrayList;

public class GraphActivity extends AppCompatActivity {

    LineChart lineChart;

    ArrayList alSelected;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        lineChart = findViewById(R.id.lineChart);

        Intent i = getIntent();
        alSelected = i.getParcelableArrayListExtra("data");
        type = i.getStringExtra("type");

        LineData data = new LineData(alSelected);
        lineChart.setData(data);
        lineChart.setDescription(type);
        lineChart.animateXY(2000, 2000);
        lineChart.invalidate();
    }

}

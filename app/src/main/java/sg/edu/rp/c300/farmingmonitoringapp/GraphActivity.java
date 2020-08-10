package sg.edu.rp.c300.farmingmonitoringapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.PublicKey;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class GraphActivity extends AppCompatActivity {

    LineChart lineChart;
    ArrayList<Entry> data;
    LineDataSet setData;
    LineData lineData;

    ArrayList<Double> alSelected;
    String type, dataType;
    Integer id;

    AsyncHttpClient client;

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        lineChart = findViewById(R.id.lineChart);

        Intent i = getIntent();
        dataType = i.getStringExtra("dataType");
        id  = i.getIntExtra("dataID", 1);
        type = "daily";

        actionBar = getSupportActionBar();
        actionBar.setTitle(dataType + "'s " + type + " Graph");

        alSelected = new ArrayList<>();
        client = new AsyncHttpClient();

        RequestParams params = new RequestParams();
        params.add("id", String.valueOf(id));
        params.add("dataType", dataType);
        params.add("type", type);

        client.post("https://hydroponic.myapplicationdev.com/webservices/retrieve_graphData.php", params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {

                    Log.i("TAG1", "onSuccess: lmao " + response.getJSONArray("data").length());

                    for (int i = 0; i<response.getJSONArray("data").length(); i++){
                        alSelected.add(Double.parseDouble(response.getJSONArray("data").get(i).toString()));
                    }

                    setGraph();

                    Log.i("TAG2", "onSuccess: lmao "+ alSelected.size());

                }catch (JSONException e) {
                    Log.i("GraphActivity", "onSuccess: Catch:: " + e);
                }

            }
        });

    }

    public void setGraph(){

        data = new ArrayList<>();
        Log.i("TAG3", "onSuccess: lmao "+ alSelected.size());
        if(!alSelected.isEmpty()){

            for (int a = 0; a < alSelected.size(); a++){
                Float dataEntry = Float.parseFloat(String.valueOf(alSelected.get(a)));
                data.add(new Entry(a+1, dataEntry));
            }

            Log.i("TAG4", "onCreate: lmao " + data.size());
            setData = new LineDataSet(data, dataType);
            setData.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            setData.setDrawFilled(true);
            setData.setDrawCircles(false);
            setData.setDrawValues(!setData.isDrawValuesEnabled());
            setData.setColor(Color.BLACK);
            setData.setFillColor(Color.BLACK);

            lineData = new LineData(setData);
            lineChart.setData(lineData);

            lineChart.setNoDataText("No Data Available");
            lineChart.setPinchZoom(false);
            lineChart.setDrawGridBackground(false);
            lineChart.setMaxHighlightDistance(300);
            lineChart.setViewPortOffsets(75, 75, 75, 75);
            lineChart.setBackgroundColor(Color.WHITE);
            lineChart.invalidate();
            lineChart.animateXY(2000, 2000);
            lineChart.getDescription().setEnabled(false);
            lineChart.setTouchEnabled(true);
            lineChart.setDragEnabled(true);
            lineChart.setScaleEnabled(true);
            lineChart.getAxisLeft().setEnabled(true);
            lineChart.getAxisRight().setEnabled(true);

            YAxis y = lineChart.getAxisLeft();
            y.setLabelCount(6, true);
            y.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);

            XAxis x = lineChart.getXAxis();
            x.setLabelCount(10, true);
            x.setPosition(XAxis.XAxisPosition.BOTTOM);

        }else{

            Toast.makeText(getApplicationContext(), "There is no data available", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        client = new AsyncHttpClient();

        RequestParams params = new RequestParams();

        switch (item.getItemId()){

            case R.id.monthly:

                type = "monthly";
                actionBar.setTitle(dataType + "'s " + type + " Graph");

                params.add("id", String.valueOf(id));
                params.add("dataType", dataType);
                params.add("type", type);

                client.post("https://hydroponic.myapplicationdev.com/webservices/retrieve_graphData.php", params, new JsonHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                        try {

                            for (int i = 0; i<response.length(); i++){
                                alSelected.add((Double) response.getJSONArray("data").get(i));
                            }
                            lineChart.notifyDataSetChanged();

                        }catch (JSONException e) {
                            Log.i("GraphActivity", "onSuccess: Catch:: " + e);
                        }

                    }
                });

            case R.id.weekly:

                type = "weekly";
                actionBar.setTitle(dataType + "'s " + type + " Graph");

                params.add("id", String.valueOf(id));
                params.add("dataType", dataType);
                params.add("type", type);

                client.post("https://hydroponic.myapplicationdev.com/webservices/retrieve_graphData.php", params, new JsonHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                        try {

                            for (int i = 0; i<response.length(); i++){
                                alSelected.add((Double) response.getJSONArray("data").get(i));
                            }
                            lineChart.notifyDataSetChanged();

                        }catch (JSONException e) {
                            Log.i("GraphActivity", "onSuccess: Catch:: " + e);
                        }

                    }
                });

            case R.id.daily:

                type = "daily";
                actionBar.setTitle(dataType + "'s " + type + " Graph");

                params.add("id", String.valueOf(id));
                params.add("dataType", dataType);
                params.add("type", type);

                client.post("https://hydroponic.myapplicationdev.com/webservices/retrieve_graphData.php", params, new JsonHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                        try {

                            for (int i = 0; i<response.length(); i++){
                                alSelected.add((Double) response.getJSONArray("data").get(i));
                            }
                            lineChart.notifyDataSetChanged();

                        }catch (JSONException e) {
                            Log.i("GraphActivity", "onSuccess: Catch:: " + e);
                        }

                    }
                });

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

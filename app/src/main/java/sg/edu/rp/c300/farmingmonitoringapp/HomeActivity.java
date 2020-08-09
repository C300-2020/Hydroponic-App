package sg.edu.rp.c300.farmingmonitoringapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class HomeActivity extends AppCompatActivity {

    RecyclerView rvHome;
    RecyclerView.Adapter rvaHome;
    RecyclerView.LayoutManager rvlmHome;

    ArrayList<Plant> alHome;
    ArrayList<Double> alTemp, alWater;
    ArrayList<Integer> alHum, alLight;
    ArrayList<String> alImg;

    AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Home");

        alHome = new ArrayList<>();
        alTemp = new ArrayList<>();
        alHum = new ArrayList<>();
        alWater = new ArrayList<>();
        alLight = new ArrayList<>();
        alImg = new ArrayList<>();

        client = new AsyncHttpClient();
        client.get("https://hydroponic.myapplicationdev.com/webservices/retrieve_all.php", new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                try {

                    alHome.clear();
                    alTemp.clear();
                    alHum.clear();
                    alLight.clear();
                    alWater.clear();
                    alImg.clear();

                    for (int i = 0; i<response.length(); i++) {
                        JSONObject plant = (JSONObject) response.get(i);

                        for (int a = 0; a<plant.getJSONArray("temp").length(); a++){
                            alTemp.add((Double) plant.getJSONArray("temp").get(a));
                        }

                        for (int a = 0; a<plant.getJSONArray("humidity").length(); a++){
                            alHum.add((Integer) plant.getJSONArray("humidity").get(a));
                        }

                        for (int a = 0; a<plant.getJSONArray("light").length(); a++){
                            alLight.add((Integer) plant.getJSONArray("light").get(a));
                        }

                        for (int a = 0; a<plant.getJSONArray("water").length(); a++){
                            alWater.add((Double) plant.getJSONArray("water").get(a));
                        }

                        for(int a = 0; a<plant.getJSONArray("image").length(); a++){
                            alImg.add((String) plant.getJSONArray("image").get(a));
                        }

                        Plant p = new Plant(
                                plant.getInt("id_plant"),
                                plant.getString("plant_name"),
                                plant.getString("description"),
                                plant.getString("date_planted"),
                                alTemp,
                                alHum,
                                alWater,
                                alLight,
                                alImg);

                        alHome.add(p);
                    }

                }catch (JSONException e) {
                    Log.i("HomeActivity", "onSuccess: Catch " + e);
                }

                rvaHome.notifyDataSetChanged();

            }
        });

        //DataTest

//        alTemp.add(36.9);
//        alTemp.add(37.9);
//        alTemp.add(36.9);
//        alTemp.add(35.9);
//        alTemp.add(37.9);
//        alTemp.add(39.9);
//        alTemp.add(40.9);
//        alTemp.add(36.9);
//        alTemp.add(37.9);
//        alTemp.add(36.9);
//        alTemp.add(35.9);
//        alTemp.add(37.9);
//        alTemp.add(39.9);
//        alTemp.add(40.9);
//        alTemp.add(41.5);
//
//        alHum.add(1);
//        alHum.add(10);
//
//        alWater.add(3.0);
//        alWater.add(3.5);
//
//        alLight.add(1000);
//        alLight.add(100);
//
//        alImg.add("image1");
//        alImg.add("image2");
//        alImg.add("image3");
//        alImg.add("image4");
//        alImg.add("image5");
//
//        alHome.add(new Plant(1, "test 1", "Plant 1 is thinking about dying", "date 1", alTemp, alHum, alWater, alLight, alImg));
//        alHome.add(new Plant(2, "test 2", "Plant 2 might by dying", "date 2", alTemp, alHum, alWater, alLight, alImg));
//        alHome.add(new Plant(3, "test 3", "Plant 3 is going to die", "date 3", alTemp, alHum, alWater, alLight, alImg));

        //DataTest End

        rvHome = findViewById(R.id.rvHome);
        rvHome.setHasFixedSize(true);
        rvlmHome = new GridLayoutManager(this, 1);
        rvaHome = new HomeRecyclerAdapter(alHome, getApplicationContext());

        rvHome.setLayoutManager(rvlmHome);
        rvHome.setAdapter(rvaHome);

    }
}
package sg.edu.rp.c300.farmingmonitoringapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;

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
    ArrayList<Double> alTemp, alAcid, alWater;
    ArrayList<Integer> alHum, alLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        alHome = new ArrayList<>();
        alTemp = new ArrayList<>();
        alHum = new ArrayList<>();
        alAcid = new ArrayList<>();
        alWater = new ArrayList<>();
        alLight = new ArrayList<>();

//        AsyncHttpClient client = new AsyncHttpClient();
//        client.get("https://hydroponic.myapplicationdev.com/webservices/retrieve_all.php", new JsonHttpResponseHandler() {
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//
//                try {
//
//                    alHome.clear();
//                    alTemp.clear();
//                    alAcid.clear();
//                    alHum.clear();
//                    alLight.clear();
//                    alWater.clear();
//
//                    for (int i = 0; i<response.length(); i++) {
//                        JSONObject plant = (JSONObject) response.get(i);
//
//                        for (int a = 0; a<plant.getJSONArray("temp").length(); a++){
//                            alTemp.add((Double) plant.getJSONArray("temp").get(a));
//                        }
//
//                        for (int a = 0; a<plant.getJSONArray("acidity").length(); a++){
//                            alAcid.add((Double) plant.getJSONArray("acidity").get(a));
//                        }
//
//                        for (int a = 0; a<plant.getJSONArray("humidity").length(); a++){
//                            alHum.add((Integer) plant.getJSONArray("humidity").get(a));
//                        }
//
//                        for (int a = 0; a<plant.getJSONArray("light").length(); a++){
//                            alLight.add((Integer) plant.getJSONArray("light").get(a));
//                        }
//
//                        for (int a = 0; a<plant.getJSONArray("water").length(); a++){
//                            alWater.add((Double) plant.getJSONArray("water").get(a));
//                        }
//
//                        Plant p = new Plant(
//                                plant.getInt("id_plant"),
//                                plant.getString("plant_name"),
//                                plant.getString("description"),
//                                plant.getString("date_planted"),
//                                alTemp,
//                                alHum,
//                                alAcid,
//                                alWater,
//                                alLight,
//                                plant.getString("image"));
//                        alHome.add(p);
//                    }
//
//                    Log.i("TAG", "onCreate: lmao " + alHome.size());
//
//                }catch (JSONException e) {
//
//                    Log.i("TAG", "onSuccess: lmao fail " + e);
//
//                }
//
//                rvaHome.notifyDataSetChanged();
//
//            }
//        });

        //DataTest

        alTemp.add(36.9);
        alTemp.add(37.9);
        alTemp.add(36.9);
        alTemp.add(35.9);
        alTemp.add(37.9);
        alTemp.add(39.9);
        alTemp.add(40.9);
        alTemp.add(36.9);
        alTemp.add(37.9);
        alTemp.add(36.9);
        alTemp.add(35.9);
        alTemp.add(37.9);
        alTemp.add(39.9);
        alTemp.add(40.9);
        alTemp.add(41.5);

        alHum.add(1);
        alHum.add(10);

        alAcid.add(1.5);
        alAcid.add(14.0);

        alWater.add(3.0);
        alWater.add(3.5);

        alLight.add(1000);
        alLight.add(100);

        alHome.add(new Plant(1, "test 1", "Plant 1 is thinking about dying", "date 1", alTemp, alHum, alAcid, alWater, alLight, "test1", false, false));
        alHome.add(new Plant(2, "test 2", "Plant 2 might by dying", "date 2", alTemp, alHum, alAcid, alWater, alLight, "test2", true, true));
        alHome.add(new Plant(3, "test 3", "Plant 3 is going to die", "date 3", alTemp, alHum, alAcid, alWater, alLight, "test3", true, true));
//        alHome.add(new Plant(1, "test 4", "Plant 4 thought there is hope to live", "date 4", 36.6, 4, 4, 33.6, 700, "test4", true, true));
//        alHome.add(new Plant(2, "test 5", "Plant 5 gave up hope cause it is almost useless", "date 5", 36.5, 5, 6, 33.5, 600, "test5", true, true));
//        alHome.add(new Plant(3, "test 6", "Plant 6 tried to kill itself", "date 6", 36.4, 6, 8, 33.4, 500, "test6", true, true));
//        alHome.add(new Plant(1, "test 7", "Plant 7 managed to stop the water", "date 7", 36.3, 7, 10, 33.3, 400, "test7", true, true));
//        alHome.add(new Plant(2, "test 8", "Plant 8 managed to stop the light too", "date 8", 36.2, 8, 12, 33.2, 300, "test8", true, true));
//        alHome.add(new Plant(3, "test 9", "Plant 9 is dead", "date 9", 36.1, 9, 14, 33.1, 200, "test9", true, true));

        //DataTest End

        rvHome = findViewById(R.id.rvHome);
        rvHome.setHasFixedSize(true);
        rvlmHome = new GridLayoutManager(this, 2);
        rvaHome = new HomeRecyclerAdapter(alHome, getApplicationContext());

        rvHome.setLayoutManager(rvlmHome);
        rvHome.setAdapter(rvaHome);

    }
}
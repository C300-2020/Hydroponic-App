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

    AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Home");

        alHome = new ArrayList<>();

        client = new AsyncHttpClient();
        client.get("https://hydroponic.myapplicationdev.com/webservices/retrieve_all.php", new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                try {

                    for (int i = 0; i<response.length(); i++) {
                        JSONObject plant = (JSONObject) response.get(i);

                        Plant p = new Plant(
                                plant.getInt("id_plant"),
                                plant.getString("plant_name"),
                                plant.getString("description"),
                                plant.getString("date_planted"),
                                plant.getDouble("temp"),
                                plant.getInt("humidity"),
                                0.0,
                                0,
                                plant.getString("image"));

                        alHome.add(p);
                    }

                }catch (JSONException e) {
                    Log.i("HomeActivity", "onSuccess: Catch " + e);
                }

                rvaHome.notifyDataSetChanged();

            }
        });

        //DataTest

//        alHome.add(new Plant(1, "test 1", "Plant 1 is thinking about dying", "date 1", 36.6, 40, 4.5, 500, "default.jpg"));
//        alHome.add(new Plant(2, "test 2", "Plant 2 might by dying", "date 2", 36.6, 40, 4.5, 500, "default.jpg"));
//        alHome.add(new Plant(3, "test 3", "Plant 3 is going to die", "date 3", 36.6, 40, 4.5, 500, "default.jpg"));

        //DataTest End

        rvHome = findViewById(R.id.rvHome);
        rvHome.setHasFixedSize(true);
        rvlmHome = new GridLayoutManager(this, 1);
        rvaHome = new HomeRecyclerAdapter(alHome, getApplicationContext());

        rvHome.setLayoutManager(rvlmHome);
        rvHome.setAdapter(rvaHome);

    }
}
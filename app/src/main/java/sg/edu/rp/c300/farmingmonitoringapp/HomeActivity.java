package sg.edu.rp.c300.farmingmonitoringapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    RecyclerView rvHome;
    RecyclerView.Adapter rvaHome;
    RecyclerView.LayoutManager rvlmHome;

    ArrayList<Plant> alHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //DataTest
        ArrayList<Double> alTemp = new ArrayList<>();
        ArrayList<Integer> alHum = new ArrayList<>();
        ArrayList<Integer> alAcid = new ArrayList<>();
        ArrayList<Double> alWater = new ArrayList<>();
        ArrayList<Integer> alLight = new ArrayList<>();

        alTemp.add(36.9);
        alTemp.add(37.9);

        alHum.add(1);
        alHum.add(10);

        alAcid.add(1);
        alAcid.add(14);

        alWater.add(3.0);
        alWater.add(3.5);

        alLight.add(1000);
        alLight.add(100);

        alHome = new ArrayList<>();
        alHome.add(new Plant(1, "test 1", "Plant 1 is thinking about dying", "date 1", alTemp, alHum, alAcid, alWater, alLight, "test1"));
//        alHome.add(new Plant(2, "test 2", "Plant 2 might by dying", "date 2", 36.8, 2, 2, 33.8, 900, "test2"));
//        alHome.add(new Plant(3, "test 3", "Plant 3 is going to die", "date 3", 36.7, 3, 3, 33.7, 800, "test3"));
//        alHome.add(new Plant(1, "test 4", "Plant 4 thought there is hope to live", "date 4", 36.6, 4, 4, 33.6, 700, "test4"));
//        alHome.add(new Plant(2, "test 5", "Plant 5 gave up hope cause it is almost useless", "date 5", 36.5, 5, 6, 33.5, 600, "test5"));
//        alHome.add(new Plant(3, "test 6", "Plant 6 tried to kill itself", "date 6", 36.4, 6, 8, 33.4, 500, "test6"));
//        alHome.add(new Plant(1, "test 7", "Plant 7 managed to stop the water", "date 7", 36.3, 7, 10, 33.3, 400, "test7"));
//        alHome.add(new Plant(2, "test 8", "Plant 8 managed to stop the light too", "date 8", 36.2, 8, 12, 33.2, 300, "test8"));
//        alHome.add(new Plant(3, "test 9", "Plant 9 is dead", "date 9", 36.1, 9, 14, 33.1, 200, "test9"));

        //DataTest End

        rvHome = findViewById(R.id.rvHome);
        rvHome.setHasFixedSize(true);
        rvlmHome = new GridLayoutManager(this, 2);
        rvaHome = new HomeRecyclerAdapter(alHome, getApplicationContext());

        rvHome.setLayoutManager(rvlmHome);
        rvHome.setAdapter(rvaHome);

    }
}
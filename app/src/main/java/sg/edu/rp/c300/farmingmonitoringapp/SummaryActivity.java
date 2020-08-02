package sg.edu.rp.c300.farmingmonitoringapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    TextView tvName, tvDescription;
    ImageView ivSummary;

    RecyclerView rvSummary;
    RecyclerView.Adapter rvaSummary;
    RecyclerView.LayoutManager rvlmSummary;

    Plant plantInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

//        Debatable
//        tvName = findViewById(R.id.tvName);
        tvDescription = findViewById(R.id.tvDescription);
        ivSummary = findViewById(R.id.ivSummary);

        Intent i = getIntent();
        plantInfo = (Plant) i.getSerializableExtra("data");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(plantInfo.getPlantName());

//        Debatable
//        tvName.setText(plantInfo.getPlantName());
        tvDescription.setText(plantInfo.getPlantDescription());

        if(plantInfo.getPlantImage().equals("")){
            ivSummary.setImageResource(R.drawable.ic_launcher_background);
        }else{
            ivSummary.setImageResource(R.drawable.ic_launcher_background);
        }

        rvSummary = findViewById(R.id.rvSummary);
        rvSummary.setHasFixedSize(true);
        rvlmSummary = new GridLayoutManager(this, 2);
        rvaSummary = new SummaryRecyclerAdapter(plantInfo, getApplicationContext());

        rvSummary.setLayoutManager(rvlmSummary);
        rvSummary.setAdapter(rvaSummary);

    }
}
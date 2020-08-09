package sg.edu.rp.c300.farmingmonitoringapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class SummaryActivity extends AppCompatActivity {

    TextView tvDescription;
    ImageView ivSummary;

    RecyclerView rvSummary;
    RecyclerView.Adapter rvaSummary;
    RecyclerView.LayoutManager rvlmSummary;

    Plant plantInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        tvDescription = findViewById(R.id.tvDescription);
        ivSummary = findViewById(R.id.ivSummary);
        rvSummary = findViewById(R.id.rvSummary);
        rvSummary.setHasFixedSize(true);

        Intent i = getIntent();
        plantInfo = (Plant) i.getSerializableExtra("data");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(plantInfo.getPlantName());

        if(!(plantInfo.getPlantImage().isEmpty())){

            String url = "https://hydroponic.myapplicationdev.com/webservices/plantImg/" + plantInfo.getPlantImage().get(0);
            Picasso.with(this).load(url).fit().centerCrop().error(R.drawable.default_plant_image).into(ivSummary);

        }else{
            Picasso.with(this).load(R.drawable.default_plant_image).fit().centerCrop().into(ivSummary);
            Toast.makeText(getApplicationContext(), "No Image Available Today", Toast.LENGTH_LONG).show();
        }

        tvDescription.setText(plantInfo.getPlantDescription());

        rvlmSummary = new GridLayoutManager(this, 2);
        rvaSummary = new SummaryRecyclerAdapter(plantInfo, getApplicationContext());

        rvSummary.setLayoutManager(rvlmSummary);
        rvSummary.setAdapter(rvaSummary);

    }
}
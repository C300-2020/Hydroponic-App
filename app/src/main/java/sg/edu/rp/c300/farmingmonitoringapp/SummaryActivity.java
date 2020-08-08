package sg.edu.rp.c300.farmingmonitoringapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

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
            Picasso.with(this).load(url).into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    ivSummary.setBackground(new BitmapDrawable(getApplicationContext().getResources(), bitmap));
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {
                    Toast.makeText(getApplicationContext(), "No Such Image Found", Toast.LENGTH_LONG).show();
                    ivSummary.setImageResource(R.drawable.ic_launcher_background);
                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {
                    Toast.makeText(getApplicationContext(), "Image Loading.....", Toast.LENGTH_LONG).show();
                    ivSummary.setImageResource(R.drawable.ic_launcher_background);
                }
            });

        }else{
            Toast.makeText(getApplicationContext(), "No Image Available", Toast.LENGTH_LONG).show();
        }

        tvDescription.setText(plantInfo.getPlantDescription());

        rvlmSummary = new GridLayoutManager(this, 2);
        rvaSummary = new SummaryRecyclerAdapter(plantInfo, getApplicationContext());

        rvSummary.setLayoutManager(rvlmSummary);
        rvSummary.setAdapter(rvaSummary);

    }
}
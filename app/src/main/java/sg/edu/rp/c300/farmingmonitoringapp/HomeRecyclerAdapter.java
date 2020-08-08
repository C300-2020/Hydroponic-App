package sg.edu.rp.c300.farmingmonitoringapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.HomeViewHolder> {

    private Drawable drawable;
    private ArrayList<Plant> hPlantList;
    private Context hContext;

    public HomeRecyclerAdapter(ArrayList<Plant> hPlantList, Context hContext) {
        this.hPlantList = hPlantList;
        this.hContext = hContext;
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout rlHome;
        TextView tvPlantName, tvPlantDate;
        CardView cvHome;

        public HomeViewHolder(final View itemView) {
            super(itemView);

            rlHome = itemView.findViewById(R.id.rlHome);
            tvPlantName = itemView.findViewById(R.id.tvNameHome);
            tvPlantDate = itemView.findViewById(R.id.tvDateHome);
            cvHome = itemView.findViewById(R.id.cvHome);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(view.getContext(), SummaryActivity.class);

                    i.putExtra("data", hPlantList.get(getAdapterPosition()));
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    view.getContext().startActivity(i);

                }
            });

        }
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater pInflater = LayoutInflater.from(hContext);
        View v = pInflater.inflate(R.layout.home_card, parent, false);
        HomeViewHolder hvh = new HomeViewHolder(v);

        return hvh;

    }

    @Override
    public void onBindViewHolder(final HomeViewHolder holder, final int position) {

        Plant currentPlant = hPlantList.get(position);

        drawable = holder.itemView.getResources().getDrawable(R.drawable.ic_launcher_background);
        holder.rlHome.setBackground(drawable);
        holder.tvPlantName.setText(currentPlant.getPlantName());
        holder.tvPlantDate.setText(currentPlant.getDatePlanted());

        if(!(currentPlant.getPlantImage().isEmpty())){

            String url = "https://hydroponic.myapplicationdev.com/webservices/plantImg/" + currentPlant.getPlantImage().get(0);
            Picasso.with(hContext).load(url).into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    holder.cvHome.setBackground(new BitmapDrawable(hContext.getResources(), bitmap));
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {
                    Toast.makeText(hContext, "No Such Image Found", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {
                    Toast.makeText(hContext, "Image Loading.....", Toast.LENGTH_LONG).show();
                }
            });

        }else{
            Toast.makeText(hContext, "No Image Available", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public int getItemCount() {
        return hPlantList.size();
    }

}

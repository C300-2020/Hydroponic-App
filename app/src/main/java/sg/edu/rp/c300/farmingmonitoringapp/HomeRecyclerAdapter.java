package sg.edu.rp.c300.farmingmonitoringapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.HomeViewHolder> {

    private Drawable drawable;
    private ArrayList<Plant> hPlantList;
    private Context hContext;

    public HomeRecyclerAdapter(ArrayList<Plant> hPlantList, Context hContext) {
        this.hPlantList = hPlantList;
        this.hContext = hContext;
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder{

        CardView cvHome;
        RelativeLayout rlHome;
        TextView tvPlantName, tvPlantDate;
        ImageView ivHome;

        public HomeViewHolder(final View itemView) {
            super(itemView);

            cvHome = itemView.findViewById(R.id.cvHome);
            ivHome = itemView.findViewById(R.id.ivHome);
            rlHome = itemView.findViewById(R.id.rlHome);
            tvPlantName = itemView.findViewById(R.id.tvNameHome);
            tvPlantDate = itemView.findViewById(R.id.tvDateHome);

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
        holder.tvPlantName.setText(currentPlant.getPlantName());
        holder.tvPlantDate.setText(currentPlant.getDatePlanted());

        if(!(currentPlant.getPlantImage().isEmpty())){
            String url = "https://hydroponic.myapplicationdev.com/webservices/plantImg/" + currentPlant.getPlantImage().get(0);
            Picasso.with(hContext).load(url).fit().centerCrop().error(R.drawable.default_plant_image).into(holder.ivHome);
        }else{
            Picasso.with(hContext).load(R.drawable.default_plant_image).fit().centerCrop().into(holder.ivHome);
            Toast.makeText(hContext, "No Image Available Today", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public int getItemCount() {
        return hPlantList.size();
    }

}

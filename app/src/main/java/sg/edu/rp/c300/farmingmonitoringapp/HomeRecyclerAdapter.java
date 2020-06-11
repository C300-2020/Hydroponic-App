package sg.edu.rp.c300.farmingmonitoringapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

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

        RelativeLayout rlHome;
        TextView tvPlantName, tvPlantDate;
        CardView cvHome;

        public HomeViewHolder(@NonNull View itemView) {
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

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater pInflater = LayoutInflater.from(hContext);
        View v = pInflater.inflate(R.layout.home_card, parent, false);
        HomeViewHolder hvh = new HomeViewHolder(v);

        return hvh;

    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, final int position) {

        Plant currentPlant = hPlantList.get(position);

        drawable = holder.itemView.getResources().getDrawable(R.drawable.ic_launcher_background);
        holder.rlHome.setBackground(drawable);
        holder.tvPlantName.setText(currentPlant.getPlantName());
        holder.tvPlantDate.setText(currentPlant.getDatePlanted());

    }

    @Override
    public int getItemCount() {
        return hPlantList.size();
    }

}

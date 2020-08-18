package sg.edu.rp.c300.farmingmonitoringapp;

import android.content.Context;
import android.content.Intent;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.HomeViewHolder> {

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

        DatabaseReference rfdb, rfWater, rfLight;

        public HomeViewHolder(final View itemView) {
            super(itemView);

            cvHome = itemView.findViewById(R.id.cvHome);
            ivHome = itemView.findViewById(R.id.ivHome);
            rlHome = itemView.findViewById(R.id.rlHome);
            tvPlantName = itemView.findViewById(R.id.tvNameHome);
            tvPlantDate = itemView.findViewById(R.id.tvDateHome);

            rfdb = FirebaseDatabase.getInstance().getReference("controls");

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

        final Plant currentPlant = hPlantList.get(position);
        holder.tvPlantName.setText(currentPlant.getPlantName());
        holder.tvPlantDate.setText(currentPlant.getDatePlanted());

        if(!(currentPlant.getPlantImage().isEmpty())){
            String url = "https://hydroponic.myapplicationdev.com/webservices/plantImg/" + currentPlant.getPlantImage();
            Picasso.with(hContext).load(url).fit().centerCrop().error(R.drawable.default_plant_image).into(holder.ivHome);
        }else{
            Picasso.with(hContext).load(R.drawable.default_plant_image).fit().centerCrop().into(holder.ivHome);
            Toast.makeText(hContext, "No Image Available Today", Toast.LENGTH_LONG).show();
        }

        holder.rfdb.child(String.valueOf(currentPlant.getPlantId())).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.getValue() == null) {
                    holder.rfWater = holder.rfdb.child(String.valueOf(currentPlant.getPlantId())).child("water");
                    holder.rfLight = holder.rfdb.child(String.valueOf(currentPlant.getPlantId())).child("light");

                    holder.rfLight.setValue("off");
                    holder.rfWater.setValue("off");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.i("TAG", "onCancelled: lmao " + error);
            }
        });

    }

    @Override
    public int getItemCount() {
        return hPlantList.size();
    }

}

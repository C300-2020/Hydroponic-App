package sg.edu.rp.c300.farmingmonitoringapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;

import java.util.ArrayList;

public class SummaryRecyclerAdapter extends RecyclerView.Adapter<SummaryRecyclerAdapter.SummaryViewHolder> {

    private Drawable drawable;
    private Plant plantInfo;
    private Context hContext;

    public SummaryRecyclerAdapter(Plant plantInfo, Context hContext) {
        this.plantInfo = plantInfo;
        this.hContext = hContext;
    }

    public class SummaryViewHolder extends RecyclerView.ViewHolder{

        TextView tvValue, tvCategory;
        RelativeLayout rlSummary;

        ArrayList alSelected;
        String selectedType;

        public SummaryViewHolder (@NonNull View itemView) {
            super(itemView);

            tvValue = itemView.findViewById(R.id.tvValue);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            rlSummary = itemView.findViewById(R.id.rlSummary);

            alSelected = new ArrayList();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    alSelected.clear();
                    int selected = getAdapterPosition();

                    if(selected == 0){
                        alSelected.add(plantInfo.getTemperature());
                        selectedType = "Temperature";
                    }else if(selected == 1){
                        alSelected.add(plantInfo.getHumidity());
                        selectedType = "Humidity";
                    }else if(selected == 2){
                        alSelected.add(plantInfo.getWaterLvl());
                        selectedType = "Water Level";
                    }else if(selected == 3){
                        alSelected.add(plantInfo.getAcidity());
                        selectedType = "Acidity";
                    }else if(selected == 4){
                        alSelected.add(plantInfo.getLightLvl());
                        selectedType = "Light Level";
                    }


                    Intent i = new Intent(view.getContext(), GraphActivity.class);
                    i.putExtra("type", selectedType);
                    i.putExtra("data", alSelected);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    view.getContext().startActivity(i);

                }
            });

        }
    }

    @NonNull
    @Override
    public SummaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater pInflater = LayoutInflater.from(hContext);
        View v = pInflater.inflate(R.layout.summary_card, parent, false);
        SummaryViewHolder svh = new SummaryViewHolder(v);

        return svh;

    }

    @Override
    public void onBindViewHolder(@NonNull SummaryViewHolder holder, final int position) {

        if(position == 0){
            drawable = holder.itemView.getResources().getDrawable(R.drawable.changed_temperature_background);
            holder.rlSummary.setBackground(drawable);
            holder.tvValue.setText(String.valueOf(plantInfo.getTemperature().get(plantInfo.getTemperature().size()-1)));
            holder.tvCategory.setText("Temperature");
        }else if(position == 1){
            drawable = holder.itemView.getResources().getDrawable(R.drawable.changed_humidity_background);
            holder.rlSummary.setBackground(drawable);
            holder.tvValue.setText(String.valueOf(plantInfo.getHumidity().get(plantInfo.getHumidity().size()-1)));
            holder.tvCategory.setText("Humidity");
        }else if(position == 2){
            drawable = holder.itemView.getResources().getDrawable(R.drawable.changed_water_level_background);
            holder.rlSummary.setBackground(drawable);
            holder.tvValue.setText(String.valueOf(plantInfo.getWaterLvl().get(plantInfo.getWaterLvl().size()-1)));
            holder.tvCategory.setText("Water Level");
        }else if(position == 3){
            drawable = holder.itemView.getResources().getDrawable(R.drawable.changed_acidity_background);
            holder.rlSummary.setBackground(drawable);
            holder.tvValue.setText(String.valueOf(plantInfo.getAcidity().get(plantInfo.getAcidity().size()-1)));
            holder.tvCategory.setText("Acidity");
        }else if(position == 4){
            drawable = holder.itemView.getResources().getDrawable(R.drawable.changed_light_level_background);
            holder.rlSummary.setBackground(drawable);
            holder.tvValue.setText(String.valueOf(plantInfo.getLightLvl().get(plantInfo.getLightLvl().size()-1)));
            holder.tvCategory.setText("Light Level");
        }

    }

    @Override
    public int getItemCount() {
        return 5;
    }

}


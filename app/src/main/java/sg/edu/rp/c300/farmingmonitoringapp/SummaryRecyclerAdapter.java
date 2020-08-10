package sg.edu.rp.c300.farmingmonitoringapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

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

        String selectedType;
        Integer dataID;
        Boolean controllable;

        public SummaryViewHolder (View itemView) {
            super(itemView);

            tvValue = itemView.findViewById(R.id.tvValue);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            rlSummary = itemView.findViewById(R.id.rlSummary);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int selected = getAdapterPosition();

                    dataID = plantInfo.getPlantId();

                    if(selected == 0){
                        selectedType = "Temperature";
                    }else if(selected == 1){
                        selectedType = "Humidity";
                    }else if(selected == 2){
                        selectedType = "Water Level";
                    }else if(selected == 3){
                        selectedType = "Light Level";
                    }else if(selected == 4){
                        if( (plantInfo.getHumidity() != null) && (plantInfo.getTemperature() != null) && (plantInfo.getWaterLvl() != null) && (plantInfo.getLightLvl() != null) ){
                            controllable = true;
                        }else{
                            controllable = false;
                        }
                    }

                    if(selected == 4){
                        Intent i = new Intent(view.getContext(), ControlActivity.class);
                        i.putExtra("controllable", controllable);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        view.getContext().startActivity(i);
                    }else{
                        Intent i = new Intent(view.getContext(), GraphActivity.class);
                        i.putExtra("dataType", selectedType);
                        i.putExtra("id", dataID);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        view.getContext().startActivity(i);
                    }

                }
            });

        }
    }

    @Override
    public SummaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater pInflater = LayoutInflater.from(hContext);
        View v = pInflater.inflate(R.layout.summary_card, parent, false);
        SummaryViewHolder svh = new SummaryViewHolder(v);

        return svh;

    }

    @Override
    public void onBindViewHolder(SummaryViewHolder holder, final int position) {

        if(!plantInfo.isEmpty()){

            holder.tvCategory.setVisibility(View.VISIBLE);
            holder.tvValue.setVisibility(View.VISIBLE);
            holder.rlSummary.setVisibility(View.VISIBLE);

            if(position == 0){
                drawable = holder.itemView.getResources().getDrawable(R.drawable.changed_temperature_background);
                holder.rlSummary.setBackground(drawable);
                holder.tvValue.setText(String.valueOf(plantInfo.getTemperature()) + "°C");
                holder.tvCategory.setText("Temperature");
            }else if(position == 1){
                drawable = holder.itemView.getResources().getDrawable(R.drawable.changed_humidity_background);
                holder.rlSummary.setBackground(drawable);
                holder.tvValue.setText(String.valueOf(plantInfo.getHumidity()) + "%");
                holder.tvCategory.setText("Humidity");
            }else if(position == 2){
                drawable = holder.itemView.getResources().getDrawable(R.drawable.changed_water_level_background);
                holder.rlSummary.setBackground(drawable);
                holder.tvValue.setText(String.valueOf(plantInfo.getWaterLvl()) + "CM");
                holder.tvCategory.setText("Water Level");
            }else if(position == 3){
                drawable = holder.itemView.getResources().getDrawable(R.drawable.changed_light_level_background);
                holder.rlSummary.setBackground(drawable);
                holder.tvValue.setText(String.valueOf(plantInfo.getLightLvl()) + "lx");
                holder.tvCategory.setText("Light Level");
            }else if(position == 4){
                drawable = holder.itemView.getResources().getDrawable(R.drawable.changed_temperature_background);
                holder.rlSummary.setBackground(drawable);
                holder.tvValue.setText("Control");
                holder.tvCategory.setText("Water\nand\nLight Level");
            }

        }else{

            if(position == 0){
                drawable = holder.itemView.getResources().getDrawable(R.drawable.changed_temperature_background);
                holder.rlSummary.setBackground(drawable);
                holder.tvValue.setText("Empty");
                holder.tvCategory.setText("Temperature");
            }else if(position == 1){
                drawable = holder.itemView.getResources().getDrawable(R.drawable.changed_humidity_background);
                holder.rlSummary.setBackground(drawable);
                holder.tvValue.setText("Empty");
                holder.tvCategory.setText("Humidity");
            }else if(position == 2){
                drawable = holder.itemView.getResources().getDrawable(R.drawable.changed_water_level_background);
                holder.rlSummary.setBackground(drawable);
                holder.tvValue.setText("Empty");
                holder.tvCategory.setText("Water Level");
            }else if(position == 3){
                drawable = holder.itemView.getResources().getDrawable(R.drawable.changed_light_level_background);
                holder.rlSummary.setBackground(drawable);
                holder.tvValue.setText("Empty");
                holder.tvCategory.setText("Light Level");
            }else if(position == 4){
                drawable = holder.itemView.getResources().getDrawable(R.drawable.changed_temperature_background);
                holder.rlSummary.setBackground(drawable);
                holder.tvValue.setText("Nothing to Control");
                holder.tvCategory.setVisibility(View.GONE);
            }

        }

    }

    @Override
    public int getItemCount() {
        return 5;
    }

}


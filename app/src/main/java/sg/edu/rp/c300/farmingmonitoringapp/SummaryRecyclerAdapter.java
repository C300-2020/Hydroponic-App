package sg.edu.rp.c300.farmingmonitoringapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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

        ArrayList alSelected, alData;
        String selectedType;
        Boolean empty, controllable;

        public SummaryViewHolder (View itemView) {
            super(itemView);

            tvValue = itemView.findViewById(R.id.tvValue);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            rlSummary = itemView.findViewById(R.id.rlSummary);

            alSelected = new ArrayList();
            alData = new ArrayList();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    alSelected.clear();
                    int selected = getAdapterPosition();

                    if(selected == 0){
                        alData = plantInfo.getTemperature();
                        if(!alData.isEmpty()){
                            alSelected.addAll(alData);
                            empty = false;
                        }else{
                            empty = true;
                        }
                        selectedType = "Temperature";
                    }else if(selected == 1){
                        alData = plantInfo.getHumidity();
                        if(!alData.isEmpty()){
                            alSelected.addAll(alData);
                            empty = false;
                        }else{
                            empty = true;
                        }
                        selectedType = "Humidity";
                    }else if(selected == 2){
                        alData = plantInfo.getWaterLvl();
                        if(!alData.isEmpty()){
                            alSelected.addAll(alData);
                            empty = false;
                        }else{
                            empty = true;
                        }
                        selectedType = "Water Level";
                    }else if(selected == 3){
                        alData = plantInfo.getLightLvl();
                        if(!alData.isEmpty()){
                            alSelected.addAll(alData);
                            empty = false;
                        }else{
                            empty = true;
                        }
                        selectedType = "Light Level";
                    }else if(selected == 4){
                        if( !(plantInfo.getHumidity().isEmpty()) && !(plantInfo.getTemperature().isEmpty()) && !(plantInfo.getWaterLvl().isEmpty()) && !(plantInfo.getLightLvl().isEmpty()) ){
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
                        i.putExtra("type", selectedType);
                        i.putExtra("data", alSelected);
                        i.putExtra("empty", empty);
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
                holder.tvValue.setText(String.valueOf(plantInfo.getTemperature().get(plantInfo.getTemperature().size()-1)) + "°C");
                holder.tvCategory.setText("Temperature");
            }else if(position == 1){
                drawable = holder.itemView.getResources().getDrawable(R.drawable.changed_humidity_background);
                holder.rlSummary.setBackground(drawable);
                holder.tvValue.setText(String.valueOf(plantInfo.getHumidity().get(plantInfo.getHumidity().size()-1)) + "%");
                holder.tvCategory.setText("Humidity");
            }else if(position == 2){
                drawable = holder.itemView.getResources().getDrawable(R.drawable.changed_water_level_background);
                holder.rlSummary.setBackground(drawable);
                holder.tvValue.setText(String.valueOf(plantInfo.getWaterLvl().get(plantInfo.getWaterLvl().size()-1)) + "CM");
                holder.tvCategory.setText("Water Level");
            }else if(position == 3){
                drawable = holder.itemView.getResources().getDrawable(R.drawable.changed_light_level_background);
                holder.rlSummary.setBackground(drawable);
                holder.tvValue.setText(String.valueOf(plantInfo.getLightLvl().get(plantInfo.getLightLvl().size()-1)) + "lx");
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


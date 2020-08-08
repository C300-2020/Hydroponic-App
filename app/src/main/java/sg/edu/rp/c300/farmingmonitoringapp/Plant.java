package sg.edu.rp.c300.farmingmonitoringapp;

import java.io.Serializable;
import java.util.ArrayList;

public class Plant implements Serializable {

    private int plantId;
    private String plantName;
    private String plantDescription;
    private String datePlanted;
    private ArrayList<Double> temperature;
    private ArrayList<Integer> humidity;
    private ArrayList<Double> waterLvl;
    private ArrayList<Integer> lightLvl;
    private ArrayList<String> plantImage;

    public Plant(int plantId, String plantName, String plantDescription, String datePlanted, ArrayList<Double> temperature, ArrayList<Integer> humidity, ArrayList<Double> waterLvl, ArrayList<Integer> lightLvl, ArrayList<String> plantImage) {
        this.plantId = plantId;
        this.plantName = plantName;
        this.plantDescription = plantDescription;
        this.datePlanted = datePlanted;
        this.temperature = temperature;
        this.humidity = humidity;
        this.waterLvl = waterLvl;
        this.lightLvl = lightLvl;
        this.plantImage = plantImage;
    }

    public int getPlantId() {
        return plantId;
    }

    public String getPlantName() {
        return plantName;
    }

    public String getPlantDescription() {
        return plantDescription;
    }

    public String getDatePlanted() {
        return datePlanted;
    }

    public ArrayList<Double> getTemperature() {
        return temperature;
    }

    public ArrayList<Integer> getHumidity() {
        return humidity;
    }

    public ArrayList<Double> getWaterLvl() {
        return waterLvl;
    }

    public ArrayList<Integer> getLightLvl() {
        return lightLvl;
    }

    public ArrayList<String> getPlantImage() {
        return plantImage;
    }

    public Boolean isEmpty(){

        if (temperature == null | humidity == null | waterLvl == null | lightLvl == null){
            return true;
        }else if (temperature.size() == 0 | humidity.size() == 0 | waterLvl.size() == 0 | lightLvl.size() == 0){
            return true;
        }else{
            return false;
        }

    }

}

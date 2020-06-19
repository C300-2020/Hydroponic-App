package sg.edu.rp.c300.farmingmonitoringapp;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;

public class Plant implements Serializable {

    private int plantId;
    private String plantName;
    private String plantDescription;
    private String datePlanted;
    private ArrayList<Double> temperature;
    private ArrayList<Integer> humidity;
    private ArrayList<Double> acidity;
    private ArrayList<Double> waterLvl;
    private ArrayList<Integer> lightLvl;
    private String plantImage;

    public Plant(int plantId, String plantName, String plantDescription, String datePlanted, ArrayList<Double> temperature, ArrayList<Integer> humidity, ArrayList<Double> acidity, ArrayList<Double> waterLvl, ArrayList<Integer> lightLvl, String plantImage) {
        this.plantId = plantId;
        this.plantName = plantName;
        this.plantDescription = plantDescription;
        this.datePlanted = datePlanted;
        this.temperature = temperature;
        this.humidity = humidity;
        this.acidity = acidity;
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

    public ArrayList<Double> getAcidity(){
        return acidity;
    }

    public ArrayList<Double> getWaterLvl() {
        return waterLvl;
    }

    public ArrayList<Integer> getLightLvl() {
        return lightLvl;
    }

    public String getPlantImage() {
        return plantImage;
    }

}

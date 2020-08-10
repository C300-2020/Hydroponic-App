package sg.edu.rp.c300.farmingmonitoringapp;

import java.io.Serializable;
import java.util.ArrayList;

public class Plant implements Serializable {

    private int plantId;
    private String plantName;
    private String plantDescription;
    private String datePlanted;
    private Double temperature;
    private Integer humidity;
    private Double waterLvl;
    private Integer lightLvl;
    private String plantImage;

    public Plant(int plantId, String plantName, String plantDescription, String datePlanted, Double temperature, Integer humidity, Double waterLvl, Integer lightLvl, String plantImage) {
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

    public Double getTemperature() {
        return temperature;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public Double getWaterLvl() {
        return waterLvl;
    }

    public Integer getLightLvl() {
        return lightLvl;
    }

    public String getPlantImage() {
        return plantImage;
    }

    public Boolean isEmpty(){

        if (temperature == null | humidity == null | waterLvl == null | lightLvl == null){
            return true;
        }else{
            return false;
        }

    }

}

package com.example.virtus.chats.model;

public class ChartModel {
    
    private Double fromPVToConsumption, fromStorageToConsumption, fromPublicGridToConsumption,
                   fromGridToStorage, fromDieselGridToConsumption;

    public ChartModel (Double fromPVToConsumption,
                       Double fromStorageToConsumption,
                       Double fromPublicGridToConsumption,
                       Double fromGridToStorage,
                       Double fromDieselGridToConsumption)
    {
        this.fromPVToConsumption = fromPVToConsumption;
        this.fromStorageToConsumption = fromStorageToConsumption;
        this.fromPublicGridToConsumption = fromPublicGridToConsumption;
        this.fromGridToStorage = fromGridToStorage;
        this.fromDieselGridToConsumption = fromDieselGridToConsumption;
    }

    public ChartModel () {}


    public Double getfromPVToConsumption() { return fromPVToConsumption; }

    public void setfromPVToConsumption(Double fromPVToConsumption) {
        this.fromPVToConsumption = fromPVToConsumption;
    }

    public Double getfromStorageToConsumption() { return fromStorageToConsumption; }

    public void setfromStorageToConsumption(Double fromStorageToConsumption) {
        this.fromStorageToConsumption = fromStorageToConsumption;
    }

    public Double getfromPublicGridToConsumption() { return fromPublicGridToConsumption; }

    public void setfromPublicGridToConsumption(Double fromPublicGridToConsumption) {
        this.fromPublicGridToConsumption = fromPublicGridToConsumption;
    }

    public Double getfromGridToStorage() { return fromGridToStorage; }

    public void setfromGridToStorage(Double fromGridToStorage) {
        this.fromGridToStorage = fromGridToStorage;
    }

    public Double getfromDieselGridToConsumption() { return fromDieselGridToConsumption; }

    public void setfromDieselGridToConsumption(Double fromDieselGridToConsumption) {
        this.fromDieselGridToConsumption = fromDieselGridToConsumption;
    }
}

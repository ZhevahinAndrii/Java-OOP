package Planes;

public abstract class Airplane {
    private double maxSpeed;
    private String modelName;
    private double fuelConsumption,fuelTankSize;

    public Airplane(String modelName,double maxSpeed,double fuelConsumption,double fuelTankSize){
        this.modelName = modelName;
        this.maxSpeed = maxSpeed;
        this.fuelConsumption = fuelConsumption;
        this.fuelTankSize = fuelTankSize;
    }
    public abstract double getCargoCapacity();
    public abstract int getPassengersCapacity();
    public double getFlightDistance(){
        return (this.fuelTankSize / this.fuelConsumption)*this.maxSpeed*0.8;
    }
    public double getFuelConsumption(){
        return this.fuelConsumption;
    }
    public String getModelName(){
        return this.modelName;
    }



}

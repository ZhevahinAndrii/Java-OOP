package Planes;

public class CargoAirplane extends Airplane{
    private final double cargoCapacity;
    public CargoAirplane(String modelName,double maxSpeed,double fuelConsumption,double fuelTankSize,double cargoCapacity){
        super(modelName, maxSpeed, fuelConsumption, fuelTankSize);
        this.cargoCapacity = cargoCapacity;
    }
    @Override
    public double getCargoCapacity(){
        return this.cargoCapacity;
    }
    @Override
    public int getPassengersCapacity(){
        return 0;
    }
}

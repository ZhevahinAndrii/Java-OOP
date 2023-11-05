package Planes;

public class PassengerAirplane extends Airplane{
    private int passengersCapacity;
    public PassengerAirplane(String modelName,double maxSpeed,double fuelConsumption,double fuelTankSize,
                             int passengersCapacity)
    {
        super(modelName,maxSpeed,fuelConsumption,fuelTankSize);
        this.passengersCapacity = passengersCapacity;
    }
    @Override
    public double getCargoCapacity(){
        return (passengersCapacity*102.5)*1.5;
    }
    @Override
    public int getPassengersCapacity(){
        return this.passengersCapacity;
    }


}

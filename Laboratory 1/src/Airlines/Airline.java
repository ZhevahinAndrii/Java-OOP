package Airlines;
import Planes.Airplane;


import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class Airline {
    public String name;
    public Airline(String name){
        this.name = name;
    }
    private final List<Airplane> airplanes = new ArrayList<>();

    public void addAirplane(Airplane airplane){
        airplanes.add(airplane);
    }
    public int getTotalPassengersCapacity(){
        int total_capacity = 0;
        for(Airplane airplane:this.airplanes){
            total_capacity+=airplane.getPassengersCapacity();
        }
        return total_capacity;
    }
    public double getTotalCargoCapacity(){
        double total_capacity = 0;
        for(Airplane airplane:this.airplanes){
            total_capacity+=airplane.getCargoCapacity();
        }
        return total_capacity;
    }
    public void sortByFlightDistance(){
        this.airplanes.sort(Comparator.comparingDouble(Airplane::getFlightDistance));
    }
    public Airplane findAirplaneByFuelConsumptionRange(double min_consumption,double max_consumption){
        for(Airplane airplane:this.airplanes){
            if (airplane.getFuelConsumption() >=min_consumption && airplane.getFuelConsumption()<=max_consumption){
                return airplane;
            }
        }
        return null;
    }
    public void printAirplanes(){
        for(Airplane airplane:this.airplanes){
            System.out.println(airplane.getModelName());
        }
    }

}

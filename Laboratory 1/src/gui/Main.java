package gui;
import Planes.Airplane;
import Planes.PassengerAirplane;
import Planes.CargoAirplane;
import Airlines.Airline;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){

        Airline fly_emirates = new Airline("Fly Emirates");

        fly_emirates.addAirplane(new PassengerAirplane("Boeing 737-900",876.0,2650.0,
                15270.0,189));
        fly_emirates.addAirplane(new PassengerAirplane("Airbus A320-200",840,2700,26500,180));
        fly_emirates.addAirplane(new CargoAirplane("Boeing 747-400",988,14000,216840,396890));
        while(true){

            System.out.println("Choose an option:");
            System.out.println("""
                    1.Add an airplane to airline
                    2.Get total passengers capacity
                    3.Get total cargo capacity
                    4.Sort airplanes bu flight distance
                    5.Find an airplane by range of fuel consumption""");
            int option = scanner.nextInt();
            switch (option){
                case 1:
                    System.out.println("Input the type of airplane(C for Cargo and P for Passenger)");
                    String type = scanner.next();
                    System.out.println("Input model name(replace spaces with underscores):");
                    String modelName = scanner.next();
                    modelName = modelName.replaceAll("_"," ");
                    System.out.println("Input max speed:");
                    double maxSpeed = scanner.nextDouble();
                    System.out.println("Input fuel consumption:");
                    double fuelConsumption = scanner.nextDouble();
                    System.out.println("Input fuel tank size:");
                    double fuelTankSize = scanner.nextDouble();
                    System.out.println("Input the passengers capacity for passengers airplane or cargo capacity for cargo airplane:");
                    if(Objects.equals(type, "C")){
                        double cargoCapacity = scanner.nextDouble();
                        fly_emirates.addAirplane(new CargoAirplane(modelName,maxSpeed,fuelConsumption,fuelTankSize,cargoCapacity));
                    }
                    else{
                        int passengersCapacity = scanner.nextInt();
                        fly_emirates.addAirplane(new PassengerAirplane(modelName,maxSpeed,fuelConsumption,fuelTankSize,passengersCapacity));
                    }
                    System.out.println("An airplane "+modelName+"was successfully added to airline "+fly_emirates.name+"\n\n\n");
                    break;
                case 2:
                    System.out.println("Total passengers capacity of airplanes in "+fly_emirates.name+" ="+fly_emirates.getTotalPassengersCapacity()+"\n\n\n");
                    break;
                case 3:
                    System.out.println("Total cargo capacity of airplanes in "+fly_emirates.name+" ="+fly_emirates.getTotalCargoCapacity()+"\n\n\n");
                    break;
                case 4:
                    fly_emirates.sortByFlightDistance();
                    System.out.println("Airplanes in "+fly_emirates.name+" was successfully sorted:");
                    fly_emirates.printAirplanes();
                    System.out.println("\n\n\n");
                    break;
                case 5:
                    System.out.println("Input minimum fuel consumption:");
                    double min = scanner.nextDouble();
                    System.out.println("Input maximum fuel consumption:");
                    double max = scanner.nextDouble();
                    Airplane airplane = fly_emirates.findAirplaneByFuelConsumptionRange(min,max);
                    if(airplane!=null){
                        System.out.println("Founded airplane "+airplane.getModelName()+"\n\n\n");
                    }
                    else{
                        System.out.println("There are no such airplanes\n\n\n");
                    }
                    break;
                default:
                    System.out.println("There is no such an option\n\n\n");
            }
        }
    }
}

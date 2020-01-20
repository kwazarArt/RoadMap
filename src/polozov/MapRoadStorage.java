package polozov;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MapRoadStorage {
    public static Map<City, Set<Road>> MAP_ROAD = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("Enter command - (1 - addCity | 2 - addRoad | 3 - deleteRoad | 4 - getCity | 5 - getList | 6 - printAll | 7 - saveToFile): ");
            String[] command = reader.readLine().trim().split(" ");
            if (command.length != 1) {
                System.out.println("Неверная команда.");
                continue;
            }
            switch (command[0]) {
                case "1":
                    System.out.println("Enter city name");
                    String name = reader.readLine();
                    System.out.println("Enter X-coordinate:");
                    String tmp = reader.readLine();
                    int x = Integer.parseInt(tmp);
                    System.out.println("Enter Y-coordinate:");
                    tmp = reader.readLine();
                    int y = Integer.parseInt(tmp);
                    MapRoadStorage.MAP_ROAD.put(new City(name, x, y), new HashSet<>());
                    break;

                case "2":
                    System.out.println("Enter first name of city");
                    String nameFirst = reader.readLine();

                    System.out.println("Enter second name of city");
                    String nameSecond = reader.readLine();
                    Road road = new Road(Objects.requireNonNull(City.get(nameFirst)), Objects.requireNonNull(City.get(nameSecond)));

                    MAP_ROAD.get(City.get(nameFirst)).add(road);
                    MAP_ROAD.get(City.get(nameSecond)).add(road);
                    break;

                case "3":
                    System.out.println("Enter road name for removing");
                    String roadName = reader.readLine();
                    if (Road.setOfRoads.contains(Road.get(roadName))) {
                        for(City key : MAP_ROAD.keySet()) {
                            MAP_ROAD.get(key).remove(Road.get(roadName));
                        }
                    }
                    Road.setOfRoads.remove(Road.get(roadName));
                    break;

                case "4":
                    System.out.println("Enter name of city");
                    String city = reader.readLine();
                    System.out.println(City.get(city));
                    break;

                case "5":
                    System.out.println("Enter name of city");
                    String listRoads = reader.readLine();
                    printStorageByCity(City.get(listRoads));
                    break;

                case "6":
                    printAll();
                    break;

                case "7":
                    try(FileWriter writer = new FileWriter("src/polozov/text", false))
                    {
                        for(Map.Entry<City, Set<Road>> entry : MAP_ROAD.entrySet() ) {
                            writer.write(String.valueOf(entry.getKey()));
                            writer.append('\n');
                            writer.write(String.valueOf(entry.getValue()));
                        }
                        writer.flush();
                    }
                    catch(IOException ex){
                        System.out.println(ex.getMessage());
                    }
                    break;

                default:
                    System.out.println("Неверная команда.");
                    break;
            }
        }
    }

    public static void printStorageByCity(City city){
        if (MAP_ROAD.containsKey(city)) {
            Set<Road> set = MAP_ROAD.get(city);
            for (Road road: set) {
                System.out.println(road);
            }
        } else {
            System.out.println("Roads weren't found");
        }
    }

    public static void printAll() {
        for (City key : MAP_ROAD.keySet()) {
            System.out.println(key + ":");
            Set<Road> set = MAP_ROAD.get(key);
            if (set.isEmpty()) {
                System.out.println("Roads weren't found");
            }
            for (Road road: set) {
                System.out.println(road);
            }
        }
    }
}

package polozov;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Road {
    public static Set<Road> setOfRoads = new HashSet<>();
    private String roadName;
    private double length;

    public Road(City first, City second) {
        this.roadName = first.getCityName() + " - " + second.getCityName();
        this.length = Math.sqrt(Math.pow(first.getX() - second.getX(), 2) + Math.pow(first.getY() - second.getY(), 2));
        addToMapRoad(this, first, second);
        add(this);
    }


    public String getRoadName() {
        return roadName;
    }

    public void addToMapRoad(Road road, City firstCity, City secondCity) {
        if (!MapRoadStorage.MAP_ROAD.containsKey(firstCity)) {
            MapRoadStorage.MAP_ROAD.put(firstCity, new HashSet<>());
        }
        MapRoadStorage.MAP_ROAD.get(firstCity).add(road);

        if (!MapRoadStorage.MAP_ROAD.containsKey(secondCity)) {
            MapRoadStorage.MAP_ROAD.put(secondCity, new HashSet<>());
        }
        MapRoadStorage.MAP_ROAD.get(secondCity).add(road);
    }

    public void add(Road road) {
        setOfRoads.add(road);
    }

    @Override
    public String toString() {
        return "Road{" +
                "roadName='" + roadName + '\'' +
                ", length=" + length +
                '}';
    }


    public static Road get(String name) {
        for (Road road : setOfRoads) {
            if (road.getRoadName().equals(name)) {
                return road;
            }
        }
        System.out.println("Not found");
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Road road = (Road) o;
        return Double.compare(road.length, length) == 0 &&
                roadName.equals(road.roadName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roadName, length);
    }
}

package polozov;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class City {
    static Set<City> setOfCity = new HashSet<>();
    private String cityName;
    private int x, y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public City(String cityName, int x, int y) {
        this.cityName = cityName;
        this.x = x;
        this.y = y;
        add(this);
    }

    @Override
    public String toString() {
        return "City{" +
                "cityName='" + cityName + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    public String getCityName() {
        return cityName;
    }

    public void add(City city) {
        setOfCity.add(city);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return x == city.x &&
                y == city.y &&
                cityName.equals(city.cityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityName, x, y);
    }

    public static City get(String name) {
        for (City city : setOfCity) {
            if (city.getCityName().equals(name)) {
                return city;
            }
        }
        System.out.println("Not found");
        return null;
    }
}

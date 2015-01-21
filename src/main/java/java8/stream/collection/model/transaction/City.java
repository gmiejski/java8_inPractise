package java8.stream.collection.model.transaction;

public class City {

    private final String name;

    private City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static City of(String cityName) {
        return new City(cityName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (name != null ? !name.equals(city.name) : city.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "{City = " + name + '}';
    }
}

package racing.domain;

import racing.behavior.CarMovable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RacingCars {
    private static final int INITIAL_LOCATION = 0;

    private final List<Car> cars;

    public RacingCars(final List<Car> cars) {
        this.cars = cars;
    }

    public static RacingCars of(final List<String> carNames) {
        final List<Car> cars = new ArrayList<>();
        for (final String name : carNames) {
            cars.add(Car.of(name, new CarMovable()));
        }
        return new RacingCars(cars);
    }

    public List<Car> getCars() {
        return cars;
    }

    public int getMaxLocation() {
        return this.cars.stream()
                .max(Comparator.comparingInt(Car::getLocation))
                .map(Car::getLocation)
                .orElse(INITIAL_LOCATION);
    }

    public List<String> getWinners() {
        return this.cars.stream()
                .filter(car -> car.getLocation() == getMaxLocation())
                .map(Car::getName)
                .collect(Collectors.toList());
    }

    public List<Car> raceOfCars(final List<Car> cars) {
        for (final Car car : cars) {
            car.move();
        }
        return cars;
    }
}

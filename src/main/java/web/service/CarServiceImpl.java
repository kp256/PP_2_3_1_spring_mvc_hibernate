package web.service;

import org.springframework.stereotype.Service;
import web.dao.CarDAO;
import web.model.Car;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private final CarDAO carDAO;

    public CarServiceImpl(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    @Override
    public List<Car> getCarsByCount(int count) {
        if (count > carDAO.getAllCars().size()) {
            return carDAO.getAllCars();
        } else {
            return carDAO.getAllCars().subList(0, count);
        }
    }

    public List<Car> getAllCars() {
        return carDAO.getAllCars();
    }

    public Car getCarById(int id) {
        return carDAO.get(id);
    }

    public void saveCar(Car car) {
        carDAO.save(car);
    }

    public void updateCar(int id, Car car) {
        carDAO.update(id, car);
    }

    public void deleteCarById(int id) {
        carDAO.delete(id);
    }
}

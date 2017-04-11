package com.jcolaiacovo.armored.cars.domain.transformer;

import com.jcolaiacovo.armored.cars.api.model.CarDTO;
import com.jcolaiacovo.armored.cars.domain.model.Car;
import org.springframework.stereotype.Component;

/**
 * Created by Julian on 10/04/2017.
 */
@Component
public class CarTransformer extends AbstractApiTransformer<Car, CarDTO> {

    @Override
    public Car transform(CarDTO carDTO) {
        Car car = new Car();

        car.setId(carDTO.getId());
        car.setBrand(carDTO.getBrand());
        car.setChassisNumber(carDTO.getChassisNumber());
        car.setDomain(carDTO.getDomain());
        car.setModel(carDTO.getModel());
        car.setMotorNumber(carDTO.getMotorNumber());

        return car;
    }

    @Override
    public CarDTO transformToDTO(Car car) {
        CarDTO carDTO = new CarDTO();

        carDTO.setId(car.getId());
        carDTO.setBrand(car.getBrand());
        carDTO.setChassisNumber(car.getChassisNumber());
        carDTO.setDomain(car.getDomain());
        carDTO.setModel(car.getModel());
        carDTO.setMotorNumber(car.getMotorNumber());

        return carDTO;
    }

}

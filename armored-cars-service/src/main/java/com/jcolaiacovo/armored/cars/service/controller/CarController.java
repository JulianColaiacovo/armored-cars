package com.jcolaiacovo.armored.cars.service.controller;

import com.jcolaiacovo.armored.cars.domain.model.Car;
import com.jcolaiacovo.armored.cars.domain.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Julian on 15/01/2017.
 */
@RestController
@RequestMapping("/cars")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<Car> getAll() {
        return this.carService.getAll();
    }

    @GetMapping("/{id}")
    public Car getById(@PathVariable int id) {
        return this.carService.getById(id);
    }

    @PostMapping
    public Car save(@RequestBody Car car) {
        this.carService.save(car);
        return car;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        this.carService.delete(id);
    }

}

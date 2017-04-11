package com.jcolaiacovo.armored.cars.service.controller;

import com.jcolaiacovo.armored.cars.api.model.CarDTO;
import com.jcolaiacovo.armored.cars.domain.model.Car;
import com.jcolaiacovo.armored.cars.domain.service.CarService;
import com.jcolaiacovo.armored.cars.domain.transformer.CarTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Julian on 15/01/2017.
 */
@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;
    private final CarTransformer carTransformer;

    @Autowired
    public CarController(CarService carService, CarTransformer carTransformer) {
        this.carService = carService;
        this.carTransformer = carTransformer;
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
    public CarDTO save(@RequestBody CarDTO carDTO) {
        Car car = this.carTransformer.transform(carDTO);
        this.carService.save(car);
        return this.carTransformer.transformToDTO(car);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        this.carService.delete(id);
    }

}

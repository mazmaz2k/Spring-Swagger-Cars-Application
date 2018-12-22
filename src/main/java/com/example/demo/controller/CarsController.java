package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties.Tomcat.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Car;
import com.example.demo.domain.CarNotFoundException;
import com.example.demo.domain.CarsRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;;

@RestController
@RequestMapping("/cars")
public class CarsController {
	
	@Autowired
	private CarsRepository carRepository;

	/**
	 * Fetch a list of cars
	 * @return a list of cars
	 */
    @RequestMapping(path="/all", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Fetch all cars")
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success", response = Car.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Server Error")}) 
    public List<Car> allCars() {
    	List<Car> allCars = (List<Car>) carRepository.findAll();
    	
    	return allCars;
    }
    
    /**
     * Finds a car by <code>id</code>
     * 
     * @param id car's id
     * 
     * @return the {@link car} object
     */
    @RequestMapping(path = "/{id}", 
    				method = RequestMethod.GET)
    @ApiOperation(value = "Fetch a car")
    public Optional<Car> car(@PathVariable Long id) {
    	
		Optional<Car> car = carRepository.findById(id);

		if (!car.isPresent())
			throw new CarNotFoundException("id-" + id+" NOT found!!!");

//		Resource<Car> resource = new Resource<Car>(car.get());

//		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllStudents());

//		resource.add(linkTo.withRel("all-students"));
		return car;

//    	return carRepository.findById(id);
    }
    
    
    /**
     * Adds a car
     * 
     * @param car
     * @return
     */
    @RequestMapping(path = "/add",
    				method = RequestMethod.POST,
    				 consumes =  MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add a car")
    public Car add(@RequestBody Car car) {
    	Car savedCar =  carRepository.save(car);
    	
    	return savedCar;
    }

    
    /**
     * Update the cars
     * 
     * @param car
     * @return
     */
    @RequestMapping(path = "/update",
    				method = RequestMethod.PUT)
    @ApiOperation(value = "Update a car")
    public Car update(@RequestBody Car car) {
    	Long id = car.getId();
    	if(!carRepository.existsById(id)) {
			throw new CarNotFoundException("id-" + id+" NOT found!!!");
	   	}
    	Car savedCar = carRepository.save(car);
    	return savedCar;
    }
    
    
    /**
     * Deletes car identified with <code>id</code>
     * @param id
     */
    @RequestMapping(path = "/{id}", 
			method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete a car")
    public void delete(@PathVariable Long id) {
    	carRepository.deleteById(id);
    }

    
}

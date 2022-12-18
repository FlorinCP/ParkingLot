package com.parking.parkinglot.ejb;

import com.parking.parkinglot.common.CarDto;
import com.parking.parkinglot.entities.Car;
import com.parking.parkinglot.entities.User;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class CarsBean {

    private static final Logger LOG = Logger.getLogger(CarsBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    /*
    What is missing is the findById method which takes as parameter a the car id and returns the CarDto.
    Task - implement the findById method in CarsBean.
     */
    public CarDto findById(Long carId){
        CarDto carDto =  findAllCars()
                        .stream()
                        .filter(c->c.getId().equals(carId))
                        .findAny()
                        .orElse(null);
        return carDto;
    }

    public List<CarDto>copyCarsToDto(List<Car>cars){

        List<CarDto> carsDto;
        carsDto =cars
                .stream()
                .map(x -> new CarDto(x.getId(), x.getLicensePlate(), x.getParkingSpot(), x.getOwner().getUsername())).collect(Collectors.toList());
        return carsDto;

    }

    public List<CarDto>findAllCars(){
        LOG.info("Find all cars");
        try {
            TypedQuery<Car> typedQuery = entityManager.createQuery("SELECT c FROM Car c", Car.class);
            List<Car> cars = typedQuery.getResultList();
            return copyCarsToDto(cars);
        }catch(Exception ex){
            throw new EJBException(ex);
        }
    }

    public void createCar(String licensePlate, String parkingSpot, Long userId){
        LOG.info("createCar");
        /* as parameters for the createCar method, we have the fields licensePlate, parkingSpot and userId*/

        // we create a new Car object
        Car car = new Car();
        // we set the license plate and the parking spot directly
        car.setLicensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);

        //since the car's owner is a user, not an id, we first need to find it using
        //the entity manager's find method which takes as parameters a class and an id
        User user = entityManager.find(User.class,userId);
        // we add the car object to the collection of cars of the user
        user.getCars().add(car);
        // we set the user in the car object
        car.setOwner(user);

        // we persist the car object - a new entry in the DB will be created
        entityManager.persist(car);
    }

    public void updateCar(Long carId, String licensePlate, String parkingSpot, Long userId) {
        LOG.info("updateCar");

        Car car = entityManager.find(Car.class,carId);
        car.setLicensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);

        // remove this car from the old owner
        User oldUser = car.getOwner();
        oldUser.getCars().remove(car);

        // add the car to its new owner
        User user = entityManager.find(User.class, userId);
        user.getCars().add(car);
        car.setOwner(user);

    }

// de ce nu e collection?
    public void deleteCarsByIds(List<Long> carIds) {
        LOG.info("deleteCarsByIDs");
        for (Long carId : carIds){
            Car car = entityManager.find(Car.class,carId);
            entityManager.remove(car);
        }

    }
}

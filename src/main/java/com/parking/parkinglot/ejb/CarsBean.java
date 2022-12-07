package com.parking.parkinglot.ejb;

import com.parking.parkinglot.common.CarDto;
import com.parking.parkinglot.entities.Car;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class CarsBean {

    private static final Logger LOG = Logger.getLogger(CarsBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

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

}

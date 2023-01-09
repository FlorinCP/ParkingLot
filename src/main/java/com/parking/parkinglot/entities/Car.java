package com.parking.parkinglot.entities;

import jakarta.persistence.*;

@Entity
public class Car {
    private Long id;

    @GeneratedValue
    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String licensePlate;

    @Basic
    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    private String parkingSpot;

    @Basic
    public String getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(String parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    private User owner;

    @ManyToOne
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    private CarPhoto photo;

    /*
    The cascade = CascadeType.ALL attribute specifies that if the car is deleted,
     the photo will be deleted too.

    The fetch = FetchType.LAZY attribute specifies that if a car is retrieved from the database,
     it should be retrieved without the photo, and if the getPhoto() method is called,
      only then retrieve the photo from the database.
     */

    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public CarPhoto getPhoto(){return photo;}

    public void setPhoto(CarPhoto photo) {
        this.photo = photo;
    }
}

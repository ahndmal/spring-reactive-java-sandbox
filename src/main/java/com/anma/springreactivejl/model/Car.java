//package com.anma.springreactivejl.model;
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.relational.core.mapping.Column;
//import org.springframework.data.relational.core.mapping.Table;
//
//import java.time.LocalDateTime;
//
//@Table(value = "cars")
//public class Car {
//
//    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(value = "car_id")
//    private long carId;
//    private String id;
//    private String model;
//    private String color;
//    private String registry;
//    private String origin;
//    private String brand;
//    @Column(value = "created_at") private LocalDateTime createdAt;
//    @Column(value = "country_codes") private String countryCodes;
//    @Column(value = "max_speed") private int maxSpeed;
//    private int age;
//    @Column(value = "person_id") private long personId;
//
//    public Car() {
//    }
//
//    public Car(long carId, String id, String model, String color, String registry, String origin, String brand,
//               LocalDateTime createdAt, String countryCodes, int maxSpeed, int age, long personId) {
//        this.carId = carId;
//        this.id = id;
//        this.model = model;
//        this.color = color;
//        this.registry = registry;
//        this.origin = origin;
//        this.brand = brand;
//        this.createdAt = createdAt;
//        this.countryCodes = countryCodes;
//        this.maxSpeed = maxSpeed;
//        this.age = age;
//        this.personId = personId;
//    }
//
//    public long getCarId() {
//        return carId;
//    }
//
//    public void setCarId(long carId) {
//        this.carId = carId;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getModel() {
//        return model;
//    }
//
//    public void setModel(String model) {
//        this.model = model;
//    }
//
//    public String getColor() {
//        return color;
//    }
//
//    public void setColor(String color) {
//        this.color = color;
//    }
//
//    public String getRegistry() {
//        return registry;
//    }
//
//    public void setRegistry(String registry) {
//        this.registry = registry;
//    }
//
//    public String getOrigin() {
//        return origin;
//    }
//
//    public void setOrigin(String origin) {
//        this.origin = origin;
//    }
//
//    public String getBrand() {
//        return brand;
//    }
//
//    public void setBrand(String brand) {
//        this.brand = brand;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public String getCountryCodes() {
//        return countryCodes;
//    }
//
//    public void setCountryCodes(String countryCodes) {
//        this.countryCodes = countryCodes;
//    }
//
//    public int getMaxSpeed() {
//        return maxSpeed;
//    }
//
//    public void setMaxSpeed(int maxSpeed) {
//        this.maxSpeed = maxSpeed;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public long getPersonId() {
//        return personId;
//    }
//
//    public void setPersonId(long personId) {
//        this.personId = personId;
//    }
//}
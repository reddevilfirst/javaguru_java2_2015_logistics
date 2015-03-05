package lv.javaguru.java2.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cargo")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", columnDefinition = "int(11)")
    private long cargoId;

    @Column(name = "user_id", columnDefinition = "int(11)")
    private long userId;

    @Column(name = "vehicle_type", columnDefinition = "char(20)")
    private String vehicleType;

    @Column(name = "weight", columnDefinition = "float(8)")
    private Double weight;

    @Column(name = "load_address", columnDefinition = "char(100)")
    private String loadAddress;

    @Column(name = "unload_address", columnDefinition = "char(100)")
    private String unloadAddress;

    @Column(name = "load_date", columnDefinition = "date")
    private Date loadDate;

    @Column(name = "unload_date", columnDefinition = "date")
    private Date unloadDate;

    @Column(name = "status", columnDefinition = "char(30)")
    private String status;



    public Cargo() {
    }



    public Cargo(long userId, String vehicleType, Double weight, String loadAddress, String unloadAddress, Date loadDate, Date unloadDate, String status) {
        this.userId = userId;
        this.vehicleType = vehicleType;
        this.weight = weight;
        this.loadAddress = loadAddress;
        this.unloadAddress = unloadAddress;
        this.loadDate = loadDate;
        this.unloadDate = unloadDate;
        this.status = status;
    }



    public long getCargoId() {
        return cargoId;
    }

    public void setCargoId(long cargoId) {
        this.cargoId = cargoId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getLoadAddress() {
        return loadAddress;
    }

    public void setLoadAddress(String loadAddress) {
        this.loadAddress = loadAddress;
    }

    public String getUnloadAddress() {
        return unloadAddress;
    }

    public void setUnloadAddress(String unloadAddress) {
        this.unloadAddress = unloadAddress;
    }

    public Date getLoadDate() {
        return loadDate;
    }

    public void setLoadDate(Date loadDate) {
        this.loadDate = loadDate;
    }

    public Date getUnloadDate() {
        return unloadDate;
    }

    public void setUnloadDate(Date unloadDate) {
        this.unloadDate = unloadDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

package agh.soa.dziemich.krzeelzb.entities;


import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "parking_place")
@NamedQueries({
    @NamedQuery(
        name = "ParkingPlace.findOne",
        query = "SELECT pp FROM ParkingPlace pp WHERE id = :id"
    ),
    @NamedQuery(
        name = "ParkingPlace.findAll",
        query = "SELECT pp FROM ParkingPlace pp"
    ),
        @NamedQuery(
                name = "ParkingPlace.deleteOne",
                query = "DELETE ParkingPlace pp WHERE id = :id"
        ),

})

public class ParkingPlace implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;
  private String street;
  private Boolean taken;
  private Boolean expired;
  private LocalDateTime expirationTime;

  public ParkingPlace(long l, String nullo, boolean b, boolean b1, LocalDateTime of) {
  }

  public ParkingPlace() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public Boolean getTaken() {
    return taken;
  }

  public void setTaken(Boolean taken) {
    this.taken = taken;
  }

  public Boolean getExpired() {
    return expired;
  }

  public void setExpired(Boolean expired) {
    this.expired = expired;
  }

  public LocalDateTime getExpirationTime() {
    return expirationTime;
  }

  public void setExpirationTime(LocalDateTime expirationTime) {
    this.expirationTime = expirationTime;
  }

  public ParkingPlace(String street, Boolean taken, Boolean expired, LocalDateTime expirationTime) {
    this.street = street;
    this.taken = taken;
    this.expired = expired;
    this.expirationTime = expirationTime;
  }
}

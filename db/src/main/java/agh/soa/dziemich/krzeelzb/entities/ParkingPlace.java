package agh.soa.dziemich.krzeelzb.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Entity;
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
    )

})

public class ParkingPlace extends Model {

  private String street;
  private Boolean taken;
  private Boolean expired;
  private LocalDateTime expirationTime;

  public String getStreet() {
    return street;
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
}

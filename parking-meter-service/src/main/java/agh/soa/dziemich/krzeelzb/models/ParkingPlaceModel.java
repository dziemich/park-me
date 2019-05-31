package agh.soa.dziemich.krzeelzb.models;


import java.time.Duration;

public class ParkingPlaceModel {

  Long id;
  Long duration;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getDuration() {
    return duration;
  }

  public void setDuration(Long duration) {
    this.duration = duration;
  }
}

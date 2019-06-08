package com.github.adminfaces.showcase.mybeans;

import agh.soa.dziemich.krzeelzb.entities.ParkingPlace;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;


@Named
@ViewScoped
public class ParkingPlacesBean implements Serializable {

  public List<ParkingPlace> getParkingPlaces() {
    return List.of(
        new ParkingPlace( "nullo", true, true, LocalDateTime.of(2019, 7,1,1,1,1)),
        new ParkingPlace( "rodzinna", false, true, LocalDateTime.of(2019, 7,1,1,1,1)),
        new ParkingPlace( "rodzinna", true, false, LocalDateTime.of(2019, 7,1,1,1,1) )
    );
  }
}

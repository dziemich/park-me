package com.github.adminfaces.showcase.mybeans;

import agh.soa.dziemich.krzeelzb.entities.ParkingPlace;
import java.time.LocalDateTime;
import java.util.List;
import java.io.Serializable;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;


@Named
@ViewScoped
public class ParkingPlacesBean implements Serializable {
  public List<ParkingPlace> getParkingPlaces(){
    return List.of(
        new ParkingPlace(1L,"nullo", true, true,
            LocalDateTime.of(2020, 1, 2, 8, 20)),
    new ParkingPlace(3L, "rodzinna", false, true,
            LocalDateTime.of(2019, 9, 2, 8, 30)),
    new ParkingPlace(7L, "rodzinna", true, false,
            LocalDateTime.of(2019, 1, 2, 12, 20))
    );
  }
  public List<String> filtersTest(){
    return List.of("nullo", "rodzinna");
  }
}

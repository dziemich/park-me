package agh.soa.dziemich.krzeelzb.services;

import agh.soa.dziemich.krzeelzb.dao.ParkingPlaceDao;
import agh.soa.dziemich.krzeelzb.entities.ParkingPlace;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@Remote(IParkingMeterDatabaseOperationsService.class)
public class ParkingMeterDatabaseOperationsService implements
    IParkingMeterDatabaseOperationsService {

  @Inject
  ParkingPlaceDao parkingPlaceDao;

  @Override
  public void markPlaceAsTaken(Long id, Long duration) {
    List<ParkingPlace> pp = parkingPlaceDao.findOne(id);
    if (!pp.isEmpty()) {
      pp.get(0).setTaken(true);
      pp.get(0).setExpirationTime(pp.get(0).getExpirationTime().plusSeconds(duration));
    }
  }

  @Override
  public List<ParkingPlace> fetchExpiredParkingPlaces() {
    List<ParkingPlace> all = parkingPlaceDao.findAll();
    System.out.println("all size: " + all.size());
    all.forEach(a -> System.out.println(a));
    List<ParkingPlace> filtered = all
        .stream()
        .filter(pp -> pp.getExpirationTime().isBefore(LocalDateTime.now()))
        .collect(Collectors.toList());
    System.out.println("fsize: " + filtered.size());
    return filtered;
  }
}

package agh.soa.dziemich.krzeelzb.services;

import agh.soa.dziemich.krzeelzb.dao.ParkingPlaceDao;
import agh.soa.dziemich.krzeelzb.entities.ParkingPlace;
import java.util.List;
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
//    List<ParkingPlace> pp = parkingPlaceDao.findAll();
    pp.get(0).setTaken(true);
    pp.get(0).setExpirationTime(pp.get(0).getExpirationTime().plusSeconds(duration));
  }
}

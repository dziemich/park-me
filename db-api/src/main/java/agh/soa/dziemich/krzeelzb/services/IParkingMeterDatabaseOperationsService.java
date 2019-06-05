package agh.soa.dziemich.krzeelzb.services;

import agh.soa.dziemich.krzeelzb.entities.ParkingPlace;
import java.util.List;

public interface IParkingMeterDatabaseOperationsService {
  void markPlaceAsPaid(Long id, Long duration);
  List<ParkingPlace> fetchExpiredParkingPlaces();

  void markPlaceAsTaken(Long id);
  void markPlaceAsFree(Long id);

  List<ParkingPlace> findOne(Long id);
  List<ParkingPlace> findAll();
  void addParkingPlace(ParkingPlace pp);
  void deleteParkingPlace(Long id);


}

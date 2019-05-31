package agh.soa.dziemich.krzeelzb.services;

import agh.soa.dziemich.krzeelzb.entities.ParkingPlace;
import java.util.List;

public interface IParkingMeterDatabaseOperationsService {
  void markPlaceAsTaken(Long id, Long duration);
  List<ParkingPlace> fetchExpiredParkingPlaces();
}

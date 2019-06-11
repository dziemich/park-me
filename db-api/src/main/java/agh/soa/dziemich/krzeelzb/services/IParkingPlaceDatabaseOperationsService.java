package agh.soa.dziemich.krzeelzb.services;

import agh.soa.dziemich.krzeelzb.entities.ParkingPlace;
import java.util.List;

public interface IParkingPlaceDatabaseOperationsService {

  void markPlaceAsPaid(Long id, Long duration);
  List<ParkingPlace> fetchExpiredParkingPlaces();
  List<ParkingPlace> fetchTakenParkingPlaces();
  void markPlaceAsTaken(Long id);
  void markPlaceAsFree(Long id);
  List<ParkingPlace> findOne(Long id);
  List<ParkingPlace> findAll();
  void addParkingPlace(ParkingPlace pp);
  void deleteParkingPlace(Long id);
  List<ParkingPlace> fetchFreeParkingPlaces();
  List<ParkingPlace> fetchFreeParkingPlacesFromStreet(String street);
  List<ParkingPlace> fetchExpiredParkingPlacesFromStreet(String street);
}

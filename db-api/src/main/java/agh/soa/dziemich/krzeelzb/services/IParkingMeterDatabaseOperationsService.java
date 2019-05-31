package agh.soa.dziemich.krzeelzb.services;

import java.time.Duration;

public interface IParkingMeterDatabaseOperationsService {
  void markPlaceAsTaken(Long id, Long duration);
}

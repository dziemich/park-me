package agh.soa.dziemich.krzeelzb.services;

import agh.soa.dziemich.krzeelzb.entities.Parkometer;
import agh.soa.dziemich.krzeelzb.entities.SubZone;
import java.util.List;

public interface IZoneDatabaseOperetionsService {

  List<SubZone> getAll();

  List<Parkometer> getAllParkometers();

  List<Long> getAllIds();

  void addParkometer();

  void addSubZone(SubZone subZone);

  List<SubZone> getParkingPlacesSubZone(Long ppId);

  List<Parkometer> getOne(Long id);
}

package agh.soa.dziemich.krzeelzb.services;

import agh.soa.dziemich.krzeelzb.entities.Employee;
import agh.soa.dziemich.krzeelzb.entities.ParkingPlace;
import agh.soa.dziemich.krzeelzb.entities.Parkometer;
import agh.soa.dziemich.krzeelzb.entities.SubZone;

import java.util.List;

public interface IZoneDatabaseOperetionsService {

    List<SubZone> getAll();

    List<Parkometer> getAllParkometers();

    List<Long> getAllIds();
    void addSubZone(List<ParkingPlace> parkingPlaces, List<Parkometer> parkometers, List<Employee> employees);
    void addParkometer();
}

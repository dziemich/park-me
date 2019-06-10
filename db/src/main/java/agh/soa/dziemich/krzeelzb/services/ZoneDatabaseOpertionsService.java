package agh.soa.dziemich.krzeelzb.services;


import agh.soa.dziemich.krzeelzb.dao.ParkometerDao;
import agh.soa.dziemich.krzeelzb.dao.SubZoneDao;
import agh.soa.dziemich.krzeelzb.entities.Employee;
import agh.soa.dziemich.krzeelzb.entities.ParkingPlace;
import agh.soa.dziemich.krzeelzb.entities.Parkometer;
import agh.soa.dziemich.krzeelzb.entities.SubZone;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
@Remote(IZoneDatabaseOperetionsService.class)
public class ZoneDatabaseOpertionsService implements IZoneDatabaseOperetionsService {
    @Inject
    SubZoneDao subZoneDao;
    @Inject
    ParkometerDao parkometerDao;


    @Override
    public List<SubZone> getAll() {
        return subZoneDao.findAll();
    }

    @Override
    public List<Parkometer> getAllParkometers() {
        return parkometerDao.findAll();
    }

    @Override
    public List<Long> getAllIds() {
        return subZoneDao.getAllIds();
    }

    @Override
    public void addSubZone(List<ParkingPlace> parkingPlaces, List<Parkometer> parkometers, List<Employee> employees) {
        subZoneDao.addSubZone(parkingPlaces,parkometers,employees);
    }

    @Override
    public void addParkometer() {
        parkometerDao.addParkometer();
    }
}

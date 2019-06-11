package agh.soa.dziemich.krzeelzb.services;


import agh.soa.dziemich.krzeelzb.dao.ParkometerDao;
import agh.soa.dziemich.krzeelzb.dao.SubZoneDao;
import agh.soa.dziemich.krzeelzb.entities.ParkingPlace;
import agh.soa.dziemich.krzeelzb.entities.Parkometer;
import agh.soa.dziemich.krzeelzb.entities.SubZone;
import java.util.Collections;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

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
    public List<Long> getAllIds() {
        return subZoneDao.getAllIds();
    }

    @Override
    public void addSubZone(SubZone subZone) {
        subZoneDao.addSubZone(subZone);
    }

    @Override
    public List<SubZone> getParkingPlacesSubZone(Long ppId) {
        List<SubZone> zones = subZoneDao.findAll();
        for (SubZone z : zones) {
            List<ParkingPlace> parkingPlaces = z.getParkingPlaces();
            for (ParkingPlace pp : parkingPlaces) {
                if (pp.getId().equals(ppId)) {
                    return List.of(z);
                }
            }
        }
        return Collections.emptyList();
    }

    @Override
    public void addParkometer() {
        parkometerDao.addParkometer();
    }
    @Override
    public List<Parkometer> getAllParkometers() {
        return parkometerDao.findAll();
    }

    @Override
    public List<Parkometer> getOne(Long id){
        return parkometerDao.findOne(id);
    }

}

package agh.soa.dziemich.krzeelzb.services;


import agh.soa.dziemich.krzeelzb.dao.SubZoneDao;
import agh.soa.dziemich.krzeelzb.entities.Employee;
import agh.soa.dziemich.krzeelzb.entities.ParkingPlace;
import agh.soa.dziemich.krzeelzb.entities.Parkometer;
import agh.soa.dziemich.krzeelzb.entities.SubZone;

import java.util.Collections;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
@Remote(IZoneDatabaseOperetionsService.class)
public class ZoneDatabaseOpertionsService implements IZoneDatabaseOperetionsService {
    @Inject
    SubZoneDao subZoneDao;

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

}

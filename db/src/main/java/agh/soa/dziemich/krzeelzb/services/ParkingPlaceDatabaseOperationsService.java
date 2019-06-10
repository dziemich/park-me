package agh.soa.dziemich.krzeelzb.services;

import agh.soa.dziemich.krzeelzb.dao.ParkingPlaceDao;
import agh.soa.dziemich.krzeelzb.entities.ParkingPlace;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@Remote(IParkingPlaceDatabaseOperationsService.class)
public class ParkingPlaceDatabaseOperationsService implements
    IParkingPlaceDatabaseOperationsService {

    @Inject
    ParkingPlaceDao parkingPlaceDao;

    @Override
    public void markPlaceAsPaid(Long id, Long duration) {
        List<ParkingPlace> pp = parkingPlaceDao.findOne(id);
        if (!pp.isEmpty()) {
            pp.get(0).setExpirationTime(pp.get(0).getExpirationTime().plusSeconds(duration));
        }
    }

    @Override
    public List<ParkingPlace> fetchExpiredParkingPlaces() {
        return parkingPlaceDao.findAll()
            .stream()
            .filter(pp -> pp.getExpirationTime().isBefore(LocalDateTime.now()))
            .collect(Collectors.toList());
    }

    @Override
    public List<ParkingPlace> fetchFreeParkingPlaces(){
        return parkingPlaceDao.findAll()
            .stream()
            .filter(pp -> !pp.getTaken())
            .collect(Collectors.toList());
    }

    @Override
    public List<ParkingPlace> fetchFreeParkingPlacesFromStreet(String street) {
        return findAll().
            stream()
            .filter(pp->pp.getStreet().equals(street))
            .collect(Collectors.toList());
    }

    @Override
    public List<ParkingPlace> fetchExpiredParkingPlacesFromStreet(String street) {
        return  findAll()
            .stream()
            .filter(pp->pp.getStreet().equals(street))
            .collect(Collectors.toList());
    }

    @Override
    public List<ParkingPlace> fetchTakenParkingPlaces() {
        return findAll()
            .stream()
            .filter(pp -> pp.getTaken())
            .collect(Collectors.toList());
    }

    @Override
    public void markPlaceAsTaken(Long id) {
        List<ParkingPlace> pp = parkingPlaceDao.findOne(id);
        if (!pp.isEmpty()) {
            pp.get(0).setTaken(true);
        }
    }

    @Override
    public void markPlaceAsFree(Long id) {
        List<ParkingPlace> pp = parkingPlaceDao.findOne(id);
        if (!pp.isEmpty()) {
            pp.get(0).setTaken(false);
        }
    }

    @Override
    public List<ParkingPlace> findOne(Long id) {
        return parkingPlaceDao.findOne(id);
    }

    @Override
    public List<ParkingPlace> findAll() {
        return parkingPlaceDao.findAll();
    }

    @Override
    public void addParkingPlace(ParkingPlace pp) {
        parkingPlaceDao.addParkingPlace(pp);
    }

    @Override
    public void deleteParkingPlace(Long id) {
        parkingPlaceDao.deleteParkingPlace(id);

    }
}

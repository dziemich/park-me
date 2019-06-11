package agh.soa.dziemich.krzeelzb.dao;

import agh.soa.dziemich.krzeelzb.entities.ParkingPlace;
import java.util.List;

public class ParkingPlaceDao extends AbstractDao {

  public ParkingPlaceDao() {
    super();
  }

  public List<ParkingPlace> findOne(Long id) {
    return entityManager
        .createNamedQuery("ParkingPlace.findOne", ParkingPlace.class)
        .setParameter("id", id)
        .getResultList();
  }

  public List<ParkingPlace> findAll() {
    return entityManager
        .createNamedQuery("ParkingPlace.findAll", ParkingPlace.class)
        .getResultList();
  }

  public void updateParkingPlace(ParkingPlace parkingPlace) {
    entityManager.getTransaction().begin();
    entityManager.merge(parkingPlace);
    entityManager.getTransaction().commit();
    entityManager.clear();
  }

  public void addParkingPlace(ParkingPlace parkingPlace) {
    entityManager.getTransaction().begin();
    entityManager.persist(parkingPlace);
    entityManager.getTransaction().commit();
    entityManager.clear();
  }

  public void deleteParkingPlace(Long id) {
    entityManager.getTransaction().begin();
    entityManager.createNamedQuery("ParkingPlace.deleteOne").setParameter("id", id).executeUpdate();
    entityManager.getTransaction().commit();
  }
}

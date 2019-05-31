package agh.soa.dziemich.krzeelzb.dao;

import agh.soa.dziemich.krzeelzb.entities.ParkingPlace;
import java.util.List;

public class ParkingPlaceDao extends AbstractDao{

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

  public void update(ParkingPlace parkingPlace) {
    entityManager.getTransaction().begin();
    entityManager.persist(parkingPlace);
    entityManager.getTransaction().commit();
    entityManager.clear();
  }
}

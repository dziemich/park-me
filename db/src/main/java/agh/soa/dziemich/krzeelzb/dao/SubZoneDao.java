package agh.soa.dziemich.krzeelzb.dao;

import agh.soa.dziemich.krzeelzb.entities.Employee;
import agh.soa.dziemich.krzeelzb.entities.ParkingPlace;
import agh.soa.dziemich.krzeelzb.entities.Parkometer;
import agh.soa.dziemich.krzeelzb.entities.SubZone;

import java.util.LinkedList;
import java.util.List;

public class SubZoneDao extends AbstractDao {

  public List<SubZone> findAll() {
    return entityManager
        .createNamedQuery("SubZone.findAll", SubZone.class)
        .getResultList();
  }

  public List<SubZone> findOne(Long id) {
    return entityManager
        .createNamedQuery("SubZone.findOne", SubZone.class)
        .setParameter("id", id)
        .getResultList();
  }


  public void addSubZone(SubZone subZone) {
    entityManager.getTransaction().begin();
    entityManager.merge(subZone);
    entityManager.getTransaction().commit();
  }

  public List<Long> getAllIds() {
    List<Long> ids = new LinkedList<>();
    for (SubZone sz : findAll()) {
      ids.add(sz.getId());
    }
    return ids;
  }
}

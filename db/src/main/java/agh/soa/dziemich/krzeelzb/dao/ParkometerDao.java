package agh.soa.dziemich.krzeelzb.dao;

import agh.soa.dziemich.krzeelzb.entities.ParkingPlace;
import agh.soa.dziemich.krzeelzb.entities.Parkometer;

import java.util.List;

public class ParkometerDao extends AbstractDao{

    public void addParkometer(){
        entityManager.getTransaction().begin();
        entityManager.persist(new Parkometer());
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

    public List<Parkometer> findAll() {
        return entityManager
                .createNamedQuery("Parkometer.findAll", Parkometer.class)
                .getResultList();
    }
    public List<Parkometer> findOne(Long id) {
        return entityManager
                .createNamedQuery("Parkometer.findOne", Parkometer.class)
                .setParameter("id", id)
                .getResultList();
    }

}

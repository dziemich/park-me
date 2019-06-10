package agh.soa.dziemich.krzeelzb.dao;

import agh.soa.dziemich.krzeelzb.entities.ParkingPlace;
import agh.soa.dziemich.krzeelzb.entities.Parkometer;

public class ParkometerDao extends AbstractDao{

    public void addParkingPlace(Parkometer parkometer){
        entityManager.getTransaction().begin();
        entityManager.persist(parkometer);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

}

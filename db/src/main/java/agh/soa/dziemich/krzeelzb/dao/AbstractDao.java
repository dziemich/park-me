package agh.soa.dziemich.krzeelzb.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public abstract class AbstractDao {

  protected EntityManager entityManager;

  public AbstractDao() {
    entityManager = Persistence
        .createEntityManagerFactory("ParkingSystem")
        .createEntityManager();
  }

}

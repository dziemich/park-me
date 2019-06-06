package agh.soa.dziemich.krzeelzb.dao;

import agh.soa.dziemich.krzeelzb.entities.Employee;
import agh.soa.dziemich.krzeelzb.entities.SubZone;

import java.util.List;

public class SubZoneDao extends AbstractDao {
    public List<SubZone> findAll(){
        return entityManager
                .createNamedQuery("SubZone.findAll", SubZone.class)
                .getResultList();
    }


}

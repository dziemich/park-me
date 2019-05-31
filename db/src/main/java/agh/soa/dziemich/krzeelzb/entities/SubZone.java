package agh.soa.dziemich.krzeelzb.entities;

import java.util.List;
import javax.persistence.OneToMany;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "subzone")
public class SubZone extends Model {
  @OneToMany
  List<ParkingPlace> parkingPlaces;
  @OneToMany
  List<Parkometer> parkometers;
  @OneToMany
  List<Employee> employees;
}

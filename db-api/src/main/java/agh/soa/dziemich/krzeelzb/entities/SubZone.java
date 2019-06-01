package agh.soa.dziemich.krzeelzb.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "subzone")
public class SubZone implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;
  @OneToMany
  List<ParkingPlace> parkingPlaces;
  @OneToMany
  List<Parkometer> parkometers;
  @OneToMany
  List<Employee> employees;
}

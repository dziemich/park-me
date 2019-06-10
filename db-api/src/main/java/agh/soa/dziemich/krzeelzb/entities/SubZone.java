package agh.soa.dziemich.krzeelzb.entities;

import java.io.Serializable;
import java.util.List;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "subzone")
@NamedQueries({
    @NamedQuery(
        name = "SubZone.findAll",
        query = "SELECT sz FROM SubZone sz"
    ),
    @NamedQuery(
        name = "SubZone.selectEmployees",
        query = "SELECT employees FROM SubZone"),
    @NamedQuery(
        name = "SubZone.findOne",
        query = "SELECT sz FROM SubZone sz WHERE id = :id"
    ),
    @NamedQuery(
        name = "Employee.findPlaces",
        query = "SELECT sz.parkingPlaces FROM SubZone sz where :emp in employees"
    )
})
public class SubZone implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;
  @OneToMany
  @LazyCollection(LazyCollectionOption.FALSE)
  @JsonbTransient
  List<ParkingPlace> parkingPlaces;
  @OneToMany
  @LazyCollection(LazyCollectionOption.FALSE)
  @JsonbTransient
  List<Parkometer> parkometers;
  @OneToMany
  @LazyCollection(LazyCollectionOption.FALSE)
  @JsonbTransient
  List<Employee> employees;

  public SubZone() {
  }

  public void addEmployeeToSubZone(Employee emp) {
    if (!employees.contains(emp)) {
      employees.add(emp);
    }
  }

  public SubZone(List<ParkingPlace> parkingPlaces, List<Parkometer> parkometers,
      List<Employee> employees) {
    this.parkingPlaces = parkingPlaces;
    this.parkometers = parkometers;
    this.employees = employees;
  }

  public List<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(List<Employee> employees) {
    this.employees = employees;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<ParkingPlace> getParkingPlaces() {
    return parkingPlaces;
  }

  public void setParkingPlaces(List<ParkingPlace> parkingPlaces) {
    this.parkingPlaces = parkingPlaces;
  }

  public List<Parkometer> getParkometers() {
    return parkometers;
  }

  public void setParkometers(List<Parkometer> parkometers) {
    this.parkometers = parkometers;
  }

}

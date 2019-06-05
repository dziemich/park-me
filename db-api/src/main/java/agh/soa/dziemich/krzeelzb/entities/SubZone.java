package agh.soa.dziemich.krzeelzb.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "subzone")
@NamedQueries({
               @NamedQuery(
                name = "SubZone.findAll",
                query = "SELECT sz FROM SubZone sz"
        ),

        @NamedQuery(
                name = "SubZone.selectEmployees",
                query = "SELECT employees  FROM SubZone")
})
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

  public SubZone() {
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
}

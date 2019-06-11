package agh.soa.dziemich.krzeelzb.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "parkometer")
@NamedQueries({
    @NamedQuery(
        name = "Parkometer.findAll",
        query = "SELECT p FROM Parkometer p"
    ),
    @NamedQuery(
        name = "Parkometer.findOne",
        query = "SELECT sz FROM Parkometer sz WHERE id = :id"
    ),

})
public class Parkometer implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Parkometer() {
  }
}

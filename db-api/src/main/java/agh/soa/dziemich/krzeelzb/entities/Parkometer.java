package agh.soa.dziemich.krzeelzb.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "parkometer")
@NamedQueries({
       @NamedQuery(
                name = "Parkometer.findAll",
                query = "SELECT p FROM Parkometer p"
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

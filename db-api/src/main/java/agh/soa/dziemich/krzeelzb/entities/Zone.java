package agh.soa.dziemich.krzeelzb.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "zone")
public class Zone implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;
  @OneToMany
  List<SubZone> subzones;

}

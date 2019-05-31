package agh.soa.dziemich.krzeelzb.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.OneToMany;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "zone")
public class Zone extends Model implements Serializable {

  @OneToMany
  List<SubZone> subzones;

}

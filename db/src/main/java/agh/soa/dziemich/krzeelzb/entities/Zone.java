package agh.soa.dziemich.krzeelzb.entities;

import java.util.List;
import javax.persistence.OneToMany;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "zone")
public class Zone extends Model {

  @OneToMany
  List<SubZone> subzones;

}

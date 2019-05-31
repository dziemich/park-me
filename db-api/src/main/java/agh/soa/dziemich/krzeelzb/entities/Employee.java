package agh.soa.dziemich.krzeelzb.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee extends Model implements Serializable {
  String name;
}

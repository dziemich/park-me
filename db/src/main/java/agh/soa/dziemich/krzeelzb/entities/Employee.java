package agh.soa.dziemich.krzeelzb.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee extends Model{
  String name;
}

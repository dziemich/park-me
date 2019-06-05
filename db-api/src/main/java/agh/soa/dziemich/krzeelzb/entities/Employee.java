package agh.soa.dziemich.krzeelzb.entities;

import agh.soa.dziemich.krzeelzb.converter.PasswordConverter;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "employee")
@NamedQueries({
    @NamedQuery(
        name = "Employee.findOne",
        query = "SELECT emp FROM Employee emp WHERE id = :id"
    ),
        @NamedQuery(
                name = "Employee.deleteOne",
                query = "DELETE Employee emp WHERE id = :id"
        ),
        @NamedQuery(
                name = "Employee.findAll",
                query = "SELECT emp FROM Employee emp"
        ),
})
public class Employee implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;
  String name;
  String login;
  @Convert(converter = PasswordConverter.class)
  String password;
  Boolean isAdmin;

  public Employee() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Boolean getAdmin() {
    return isAdmin;
  }

  public void setAdmin(Boolean admin) {
    isAdmin = admin;
  }

  public Employee(String name, String login, String password, Boolean isAdmin) {
    this.name = name;
    this.login = login;
    this.password = password;
    this.isAdmin = isAdmin;
  }
}

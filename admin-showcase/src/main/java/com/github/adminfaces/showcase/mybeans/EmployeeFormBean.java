package com.github.adminfaces.showcase.mybeans;

import agh.soa.dziemich.krzeelzb.entities.Employee;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.json.JSONObject;


@ViewScoped
@Named
public class EmployeeFormBean implements Serializable {

  String name;
  String login;
  String password;
  Boolean admin;

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
    return admin;
  }

  public void setAdmin(Boolean admin) {
    this.admin = admin;
  }

  public List<Long> getIds() {
    return List.of(1L, 2L, 3L, 4L);
  }

  public void addEmployee() {
    Employee emp = new Employee(login, name, password, admin);
    System.out.println("jestem useless buttonem");
    ResteasyClient client = new ResteasyClientBuilder().build();
    ResteasyWebTarget target = client.target("http://127.0.0.1:8080/hr-management-service/hr/employees/post");
    JSONObject jsonObject = new JSONObject()
        .put("name", emp.getName())
        .put("login", emp.getLogin())
        .put("password", emp.getPassword())
        .put("isAdmin", emp.getAdmin());

    String jsonStringified = jsonObject.toString();
    Response response = target.request()
        .post(Entity.entity(jsonStringified, MediaType.APPLICATION_JSON));

    int status = response.getStatus();
    System.out.println(status);

  }
}

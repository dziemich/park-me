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


@ViewScoped
@Named
public class EmployeeFormBean implements Serializable {

  String name;
  String login;
  String password;
  Boolean isAdmin;

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

  public List<Long> getIds() {
    return List.of(1L, 2L, 3L, 4L);
  }

  public void addEmployee() {
    Employee emp = new Employee(login, name, password, isAdmin);
//    Client client = ClientBuilder.newClient();
//    WebTarget target = client.target("http://127.0.0.1:8080/hr-management-service/hr/employees/post");
//    Response response = target.request()
//        .post(Entity.entity("\"{\"name\":\"admin\",\"login\":\"admin\",\"password\":\"admin\",\"isAdmin\":true}\"",
//            MediaType.APPLICATION_JSON));
//    int status = response.getStatus();
//    ResteasyClient client = new ResteasyClientBuilder().build();
//    Response response = client.target("http://localhost:8080/rest/users")
//        .request("application/json").get();
//    int status = response.getStatus();
//    System.out.println("Status code: " + status);
    System.out.println("jestem useless buttonem");
    ResteasyClient client = new ResteasyClientBuilder().build();
    ResteasyWebTarget target = client.target("http://127.0.0.1:8080/hr-management-service/hr/employees/post");
    Response response = target.request()
        .post(Entity.entity(""
                + "{\n"
                + "\t\"name\": \"admin\",\n"
                + "\t\"login\": \"admin\",\n"
                + "\t\"password\": \"admin\",\n"
                + "\t\"isAdmin\": true\n"
                + "}",
            MediaType.APPLICATION_JSON));


    int status = response.getStatus();
    System.out.println(status);

  }
}

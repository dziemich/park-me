package agh.soa.dziemich.krzeelzb.bean;

import agh.soa.dziemich.krzeelzb.entities.Employee;
import agh.soa.dziemich.krzeelzb.entities.SubZone;
import agh.soa.dziemich.krzeelzb.services.IZoneDatabaseOperetionsService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Entity;
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

  Long id;
  String name;
  String login;
  String password;
  Boolean admin;
  Boolean zone;
  Integer selectedIdZone;


  @EJB(lookup = "java:global/db/ZoneDatabaseOpertionsService")
  IZoneDatabaseOperetionsService zoneDbOp;

  @Inject
  UserBean userBean;

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
    return admin;
  }

  public void setAdmin(Boolean admin) {
    this.admin = admin;
  }

  public Boolean getZone() {
    return zone;
  }

  public void setZone(Boolean zone) {
    this.zone = zone;
  }

  public Integer getSelectedIdZone() {
    return selectedIdZone;
  }

  public void setSelectedIdZone(Integer selectedIdZone) {
    this.selectedIdZone = selectedIdZone;
  }


  public List<Long> getIds() throws IOException {
    ResteasyClient client = new ResteasyClientBuilder().build();
    Response response = client.target("http://127.0.0.1:8080/hr-management-service/hr/subzones/ids")
        .request().get();
    String jsonData = response.readEntity(String.class);
    ObjectMapper mapper = new ObjectMapper();
    List<Long> myObjects = mapper.readValue(jsonData, new TypeReference<List<Long>>() {
    });
    return myObjects;
  }


  public List<String> getEmployeeIds() throws IOException {
    ResteasyClient client = new ResteasyClientBuilder().build();
    Response response = client.target("http://127.0.0.1:8080/hr-management-service/hr/employees")
        .request().get();
    String jsonData = response.readEntity(String.class);
    ObjectMapper mapper = new ObjectMapper();
    List<Employee> myObjects = mapper.readValue(jsonData, new TypeReference<List<Employee>>() {
    });

    List<String> empIds = new LinkedList<>();
    for (Employee emp : myObjects) {
      empIds.add(emp.getName());

    }
    return empIds;
  }

  public List<SubZone> getSubZones() {
    return zoneDbOp.getAll();
  }

  public void addEmployee() throws IOException {
    Employee emp = new Employee(login, name, password, admin);

    System.out.println("jestem useless buttonem");
    ResteasyClient client = new ResteasyClientBuilder().build();
    ResteasyWebTarget target = client
        .target("http://127.0.0.1:8080/hr-management-service/hr/employees/post");
    JSONObject jsonObject = new JSONObject()
        .put("name", emp.getLogin())
        .put("login", emp.getName())
        .put("password", emp.getPassword())
        .put("admin", emp.getAdmin());

    String jsonStringified = jsonObject.toString();

    Response response = target.request()
        .post(Entity.entity(jsonStringified, MediaType.APPLICATION_JSON));

    String idEmp = response.readEntity(String.class);

    if (!zone) {
      target = client
          .target("http://127.0.0.1:8080/hr-management-service/hr/employees/" + idEmp + "/zone");
      JSONObject jsonObjectZone = new JSONObject()
          .put("id", getSelectedIdZone());
      String jsonStringifiedZone = jsonObjectZone.toString();
      Response responseZone = target.request()
          .put(Entity.entity(jsonStringifiedZone, MediaType.APPLICATION_JSON));
    }
  }

  public void updatePassword() {
    ResteasyClient client = new ResteasyClientBuilder().build();
    Long userId = userBean.getUserId();
    String uri = "http://127.0.0.1:8080/hr-management-service/hr/employees/" + userId;
    if (userBean.getUserAdminPrivileges()) {
      uri = "http://127.0.0.1:8080/hr-management-service/hr/employees/" + id;
    }

    ResteasyWebTarget target = client.target(uri);
    JSONObject jsonObject = new JSONObject().put("password", password);

    String jsonStringified = jsonObject.toString();

    Response response = target.request()
        .put(Entity.entity(jsonStringified, MediaType.APPLICATION_JSON));
  }
}

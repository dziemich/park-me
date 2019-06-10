package com.github.adminfaces.showcase.mybeans;

import agh.soa.dziemich.krzeelzb.entities.Employee;
import agh.soa.dziemich.krzeelzb.entities.ParkingPlace;
import agh.soa.dziemich.krzeelzb.entities.SubZone;
import agh.soa.dziemich.krzeelzb.services.IParkingPlaceDatabaseOperationsService;
import agh.soa.dziemich.krzeelzb.services.IUserManagementDatabaseOperationsService;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.json.JSONArray;


@Named
@ViewScoped
public class ParkingPlacesBean implements Serializable {

  @EJB(lookup = "java:global/db/ParkingPlaceDatabaseOperationsService")
  IParkingPlaceDatabaseOperationsService parkingMeterDbOp;

  @EJB(lookup = "java:global/db/UserManagementDatabaseOperationService")
  IUserManagementDatabaseOperationsService userManagementDbOpService;

  @Inject
  UserBean userBean;

  public List<ParkingPlace> getParkingPlaces() {
    String userName = userBean.findUser();
    Employee employee = userManagementDbOpService.findAll().stream()
        .filter(emp -> emp.getName().equals(userName))
        .findFirst()
        .orElseThrow(IllegalStateException::new);
    if(!employee.getAdmin()) {
      SubZone employeeSubZone = userManagementDbOpService.getEmployeeSubZone(employee.getId())
          .get(0);
      List<ParkingPlace> parkingPlaces = employeeSubZone.getParkingPlaces();
      return parkingPlaces;
    } else {
      return parkingMeterDbOp.findAll();
    }
  }

  public int numberOfFreePlaces() {
    return mapToJsonArray(makeRequest("http://127.0.0.1:8080/hr-management-service/hr/parkingplaces/freePlaces")).length();
  }

  public int numberOfExpiredPlaces() {
    return mapToJsonArray(makeRequest("http://127.0.0.1:8080/hr-management-service/hr/parkingplaces/expiredPlaces")).length();
  }
  public int numberOfTakenPlaces() {
    return mapToJsonArray(makeRequest("http://127.0.0.1:8080/hr-management-service/hr/parkingplaces/takenPlaces")).length();
  }

  public int numberOfAllParkingPlaces() {
    return mapToJsonArray(makeRequest("http://127.0.0.1:8080/hr-management-service/hr/parkingplaces/")).length();
  }

  private String makeRequest(String url){
    ResteasyClient client = new ResteasyClientBuilder().build();
    Response response =  client.target(url).request().get();
    return response.readEntity(String.class);
  }
  private JSONArray mapToJsonArray(String json){
    return new JSONArray(json);
  }
}

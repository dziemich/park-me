package com.github.adminfaces.showcase.mybeans;

import agh.soa.dziemich.krzeelzb.entities.Employee;
import agh.soa.dziemich.krzeelzb.entities.ParkingPlace;
import java.io.IOException;
import agh.soa.dziemich.krzeelzb.entities.SubZone;
import agh.soa.dziemich.krzeelzb.services.IParkingPlaceDatabaseOperationsService;
import agh.soa.dziemich.krzeelzb.services.IUserManagementDatabaseOperationsService;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
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

  private Long id;
  private String street;
  private Boolean taken;
  private Boolean expired;

  private LocalDateTime expirationTime;
  private Date expirationTimeDate;


  public List<ParkingPlace> getParkingPlaces(){
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

  public List<Long> getParkingPlacesId() throws IOException {
    List<ParkingPlace> pp =getParkingPlaces();
    List<Long> ids=new LinkedList<>();
    for(ParkingPlace p: pp){
      ids.add(p.getId());
    }
    return ids;
  }

  public void onDateSelect(SelectEvent event) {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
    facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
  }


  public void click() {
    PrimeFaces.current().ajax().update("form:display");
    PrimeFaces.current().executeScript("PF('dlg').show()");
  }


  public LocalDateTime convertToLocalDateTime(Date dateToConvert) {
    return LocalDateTime.ofInstant(
            dateToConvert.toInstant(), ZoneId.systemDefault());
  }
  public void addParkingPlace(){
    expirationTime=convertToLocalDateTime(expirationTimeDate);
    parkingMeterDbOp.addParkingPlace(new ParkingPlace(street,taken,expired,expirationTime));
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

  public IParkingPlaceDatabaseOperationsService getParkingMeterDbOp() {
    return parkingMeterDbOp;
  }

  public void setParkingMeterDbOp(IParkingPlaceDatabaseOperationsService parkingMeterDbOp) {
    this.parkingMeterDbOp = parkingMeterDbOp;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public Boolean getTaken() {
    return taken;
  }

  public void setTaken(Boolean taken) {
    this.taken = taken;
  }

  public Boolean getExpired() {
    return expired;
  }

  public void setExpired(Boolean expired) {
    this.expired = expired;
  }

  public LocalDateTime getExpirationTime() {
    return expirationTime;
  }

  public void setExpirationTime(LocalDateTime expirationTime) {
    this.expirationTime = expirationTime;
  }

  public Date getExpirationTimeDate() {
    return expirationTimeDate;
  }

  public void setExpirationTimeDate(Date expirationTimeDate) {
    this.expirationTimeDate = expirationTimeDate;
  }
}

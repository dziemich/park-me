package agh.soa.dziemich.krzeelzb.bean;

import agh.soa.dziemich.krzeelzb.entities.Employee;
import agh.soa.dziemich.krzeelzb.entities.ParkingPlace;
import agh.soa.dziemich.krzeelzb.entities.SubZone;
import agh.soa.dziemich.krzeelzb.services.IParkingPlaceDatabaseOperationsService;
import agh.soa.dziemich.krzeelzb.services.IUserManagementDatabaseOperationsService;
import agh.soa.dziemich.krzeelzb.services.IZoneDatabaseOperetionsService;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;


@Named
@ViewScoped
public class ParkingPlacesBean implements Serializable {

  @EJB(lookup = "java:global/db/ParkingPlaceDatabaseOperationsService")
  IParkingPlaceDatabaseOperationsService parkingMeterDbOp;

  @EJB(lookup = "java:global/db/UserManagementDatabaseOperationService")
  IUserManagementDatabaseOperationsService userManagementDbOpService;

  @EJB(lookup = "java:global/db/ZoneDatabaseOpertionsService")
  IZoneDatabaseOperetionsService zoneDbOp;

  @Inject
  UserBean userBean;

  private Long id;
  private String street;
  private Boolean taken;
  private Boolean expired;
  private LocalDateTime expirationTime;
  private Date expirationTimeDate;

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

  public List<ParkingPlace> getParkingPlaces() {
    String userName = userBean.findUser();
    Employee employee = userManagementDbOpService
        .findAll()
        .stream()
        .filter(emp -> emp.getLogin().equals(userName))
        .findFirst()
        .orElseThrow(IllegalStateException::new);
    if (!employee.getAdmin()) {

      List<SubZone> empSubZone = userManagementDbOpService
          .getEmployeeSubZone(employee.getId());
      if (empSubZone.isEmpty()) {
        return Collections.emptyList();
      }

      return empSubZone.get(0).getParkingPlaces();
    } else {
      return parkingMeterDbOp.findAll();
    }
  }

  private boolean filterParkingPlaceByUser(Long empId, ParkingPlace pp) {
    List<SubZone> parkingPlacesSubZone = zoneDbOp.getParkingPlacesSubZone(pp.getId());
    if(parkingPlacesSubZone.isEmpty()) return false;
    List<Employee> subZoneEmployees = parkingPlacesSubZone.get(0).getEmployees();
    Optional<Employee> correctEmployee = subZoneEmployees
        .stream()
        .filter(emp -> emp.getId().equals(empId))
        .findFirst();
    return correctEmployee.isPresent();
  }

  private long getNumberOfPlaces(Supplier<List<ParkingPlace>> fetcher) {
    Long empId = userBean.getUserId();
    Boolean userAdminPrivileges = userBean.getUserAdminPrivileges();
    List<ParkingPlace> freeParkingPlaces = fetcher.get();
    return userAdminPrivileges ?
        (long) freeParkingPlaces.size()
        :
            freeParkingPlaces
                .stream()
                .filter(pp -> filterParkingPlaceByUser(empId, pp))
                .count();
  }

  public long numberOfFreePlaces() {
    return getNumberOfPlaces(() -> parkingMeterDbOp.fetchFreeParkingPlaces());
  }

  public long numberOfExpiredPlaces() {
    return getNumberOfPlaces(() -> parkingMeterDbOp.fetchExpiredParkingPlaces());
  }

  public long numberOfTakenPlaces() {
    return getNumberOfPlaces(() -> parkingMeterDbOp.fetchTakenParkingPlaces());
  }

  public long numberOfAllParkingPlaces() {
    return getNumberOfPlaces(() -> parkingMeterDbOp.findAll());
  }

  public List<Long> getParkingPlacesId() throws IOException {
    List<ParkingPlace> pp = getParkingPlaces();
    List<Long> ids = new LinkedList<>();
    for (ParkingPlace p : pp) {
      ids.add(p.getId());
    }
    return ids;
  }


  public LocalDateTime convertToLocalDateTime(Date dateToConvert) {
    return LocalDateTime.ofInstant(
        dateToConvert.toInstant(), ZoneId.systemDefault());
  }

  public void addParkingPlace() {
    expirationTime = convertToLocalDateTime(expirationTimeDate);
    parkingMeterDbOp.addParkingPlace(new ParkingPlace(street, taken, expired, expirationTime));
  }
}

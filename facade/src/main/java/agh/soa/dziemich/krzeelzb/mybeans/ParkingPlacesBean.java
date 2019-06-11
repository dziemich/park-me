package agh.soa.dziemich.krzeelzb.mybeans;

import agh.soa.dziemich.krzeelzb.entities.Employee;
import agh.soa.dziemich.krzeelzb.entities.ParkingPlace;
import agh.soa.dziemich.krzeelzb.entities.SubZone;
import agh.soa.dziemich.krzeelzb.services.IParkingPlaceDatabaseOperationsService;
import agh.soa.dziemich.krzeelzb.services.IUserManagementDatabaseOperationsService;
import agh.soa.dziemich.krzeelzb.services.IZoneDatabaseOperetionsService;
import java.io.Serializable;
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

  public List<ParkingPlace> getParkingPlaces() {
    String userName = userBean.findUser();
    Employee employee = userManagementDbOpService
        .findAll()
        .stream()
        .filter(emp -> emp.getLogin().equals(userName))
        .findFirst()
        .orElseThrow(IllegalStateException::new);
    if (!employee.getAdmin()) {
      SubZone employeeSubZone = userManagementDbOpService.getEmployeeSubZone(employee.getId())
          .get(0);
      List<ParkingPlace> parkingPlaces = employeeSubZone.getParkingPlaces();
      return parkingPlaces;
    } else {
      return parkingMeterDbOp.findAll();
    }
  }

  private boolean filterParkingPlaceByUser(Long empId, ParkingPlace pp) {
    List<SubZone> parkingPlacesSubZone = zoneDbOp.getParkingPlacesSubZone(pp.getId());
    List<Employee> subZoneEmployees = parkingPlacesSubZone.get(0)
        .getEmployees();
    Optional<Employee> correctEmployee = subZoneEmployees
        .stream()
        .filter(emp -> emp.getId().equals(empId))
        .findFirst();
    return correctEmployee.isPresent();
  }

  private long getNumberOfPlaces(Supplier<List<ParkingPlace>> fetcher){
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
}

package agh.soa.dziemich.krzeelzb;

import agh.soa.dziemich.krzeelzb.services.IParkingMeterDatabaseOperationsService;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class ParkingPlacesControllerImpl implements ParkingPlacesController {

  @EJB(lookup = "java:global/db/ParkingMeterDatabaseOperationsService")
  IParkingMeterDatabaseOperationsService parkingMeterDbOp;

  @WebMethod
  public void takePlace(Long id) {
    parkingMeterDbOp.markPlaceAsTaken(id);

  }

  @WebMethod
  public void freePlace(Long id) {
    parkingMeterDbOp.markPlaceAsFree(id);
  }
}

package agh.soa.dziemich.krzeelzb;

import agh.soa.dziemich.krzeelzb.queue.IQueueSender;
import agh.soa.dziemich.krzeelzb.services.IParkingPlaceDatabaseOperationsService;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class ParkingPlacesControllerImpl implements ParkingPlacesController {

  @EJB(lookup = "java:global/db/ParkingPlaceDatabaseOperationsService")
  IParkingPlaceDatabaseOperationsService parkingMeterDbOp;

  @EJB(lookup = "java:global/queue/QueueSender")
  IQueueSender queueSender;


  @WebMethod
  public void takePlace(Long id) {
    queueSender.sendNewPlaceOccupiedMessage(id.toString());
    parkingMeterDbOp.markPlaceAsTaken(id);

  }

  @WebMethod
  public void freePlace(Long id) {
    queueSender.sendNewPlaceFreeMessage(id.toString());
    parkingMeterDbOp.markPlaceAsFree(id);
  }
}

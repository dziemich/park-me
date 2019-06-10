package agh.soa.dziemich.krzeelzb;

import agh.soa.dziemich.krzeelzb.entities.ParkingPlace;
import agh.soa.dziemich.krzeelzb.queue.IQueueSender;
import agh.soa.dziemich.krzeelzb.services.IParkingPlaceDatabaseOperationsService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

@Singleton
public class Detector {

  @EJB(lookup = "java:global/db/ParkingPlaceDatabaseOperationsService")
  IParkingPlaceDatabaseOperationsService parkingMeterDbOp;

  @EJB(lookup = "java:global/queue/QueueSender")
  IQueueSender queueSender;

  @Schedule(second = "*/20", minute = "*/1", hour = "*", persistent = false)
  public void doWork() {
    System.out.println("lala");
    List<ParkingPlace> parkingPlaces = parkingMeterDbOp.fetchExpiredParkingPlaces();
    parkingPlaces.forEach(pp -> {
          queueSender.sendMessage(pp.getId().toString());
        }
    );
  }
}

package agh.soa.dziemich.krzeelzb;

//import ParkingPlace;
import agh.soa.dziemich.krzeelzb.entities.ParkingPlace;
import agh.soa.dziemich.krzeelzb.services.IParkingMeterDatabaseOperationsService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

@Singleton
public class Detector {

  @EJB(lookup = "java:global/db/ParkingMeterDatabaseOperationsService")
  IParkingMeterDatabaseOperationsService parkingMeterDbOp;

  @EJB
  QueueSender queueSender;

//  @Schedule(second = "*", minute = "*/1", hour = "*", persistent = false)
  public void doWork() {
    System.out.println("lala");
    List<ParkingPlace> parkingPlaces = parkingMeterDbOp.fetchExpiredParkingPlaces();
    parkingPlaces.forEach(abstractParkingPlace -> {
          System.out.println("kupakupa");
//          ParkingPlace parkingPlace = (ParkingPlace) abstractParkingPlace;
//          queueSender.sendMessage(parkingPlace.getId().toString());
        }
    );

  }
}

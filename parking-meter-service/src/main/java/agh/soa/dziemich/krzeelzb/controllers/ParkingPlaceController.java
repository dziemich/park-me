package agh.soa.dziemich.krzeelzb.controllers;

import agh.soa.dziemich.krzeelzb.models.ParkingPlaceModel;
import agh.soa.dziemich.krzeelzb.services.IParkingMeterDatabaseOperationsService;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Timer;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("pp")
public class ParkingPlaceController {

  @EJB(lookup = "java:global/db/ParkingMeterDatabaseOperationsService")
  IParkingMeterDatabaseOperationsService parkingMeterDbOp;

  @POST
  @Path("/post")
  public Response addOne(ParkingPlaceModel ppm){
    parkingMeterDbOp.markPlaceAsTaken(ppm.getId(), ppm.getDuration());
    System.out.println("sratatata");
    return Response.ok().build();
  }

}

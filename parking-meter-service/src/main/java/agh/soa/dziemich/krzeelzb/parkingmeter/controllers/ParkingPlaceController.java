package agh.soa.dziemich.krzeelzb.parkingmeter.controllers;

import agh.soa.dziemich.krzeelzb.parkingmeter.models.ParkingPlaceModel;
import agh.soa.dziemich.krzeelzb.services.IParkingMeterDatabaseOperationsService;
import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("places")
public class ParkingPlaceController {

  @EJB(lookup = "java:global/db/ParkingMeterDatabaseOperationsService")
  IParkingMeterDatabaseOperationsService parkingMeterDbOp;

  @POST
  @Path("/update")
  public Response addOne(ParkingPlaceModel ppm){
    parkingMeterDbOp.markPlaceAsTaken(ppm.getId(), ppm.getDuration());
    System.out.println("sratatata");
    return Response.ok().build();
  }

}

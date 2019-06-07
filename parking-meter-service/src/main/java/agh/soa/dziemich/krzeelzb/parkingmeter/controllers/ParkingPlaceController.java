package agh.soa.dziemich.krzeelzb.parkingmeter.controllers;

import agh.soa.dziemich.krzeelzb.parkingmeter.models.ParkingPlaceModel;
import agh.soa.dziemich.krzeelzb.services.IParkingPlaceDatabaseOperationsService;
import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("places")
public class ParkingPlaceController {

  @EJB(lookup = "java:global/db/ParkingMeterDatabaseOperationsService")
  IParkingPlaceDatabaseOperationsService parkingMeterDbOp;

  @POST
  @Path("/{id}")
  public Response addOne(@PathParam("id") Long id, ParkingPlaceModel ppm){
    parkingMeterDbOp.markPlaceAsPaid(id, ppm.getDuration());
    return Response.ok().build();
  }

}

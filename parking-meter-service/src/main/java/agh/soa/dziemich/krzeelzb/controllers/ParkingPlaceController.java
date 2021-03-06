package agh.soa.dziemich.krzeelzb.controllers;

import agh.soa.dziemich.krzeelzb.models.ParkingPlaceModel;
import agh.soa.dziemich.krzeelzb.queue.IQueueSender;
import agh.soa.dziemich.krzeelzb.services.IParkingPlaceDatabaseOperationsService;
import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("places")
public class ParkingPlaceController {

  @EJB(lookup = "java:global/db/ParkingPlaceDatabaseOperationsService")
  IParkingPlaceDatabaseOperationsService parkingMeterDbOp;

  @EJB(lookup = "java:global/queue/QueueSender")
  IQueueSender queueSender;

  @POST
  @Path("/{id}")
  public Response addOne(@PathParam("id") Long id, ParkingPlaceModel ppm) {
    parkingMeterDbOp.markPlaceAsPaid(id, ppm.getDuration());
    queueSender.sendNewTicketBoughtMessage(id.toString());
    return Response.ok().build();
  }
}

package agh.soa.dziemich.krzeelzb.facade.controllers;

import agh.soa.dziemich.krzeelzb.services.IUserManagementDatabaseOperationsService;
import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("hr")
public class UserManagementController {

//  @EJB(lookup = "java:global/db/ParkingMeterDatabaseOperationsService")
//  IUserManagementDatabaseOperationsService userManagementDbOpService;

  //TODO people management
  @POST
  @Path("/update")
  public Response addOne(){
//    parkingMeterDbOp.markPlaceAsTaken(ppm.getId(), ppm.getDuration());
//    System.out.println("sratatata");
    return Response.ok().build();
  }

}

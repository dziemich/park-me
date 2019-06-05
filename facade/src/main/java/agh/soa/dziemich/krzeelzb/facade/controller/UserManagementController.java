package agh.soa.dziemich.krzeelzb.facade.controller;

import agh.soa.dziemich.krzeelzb.facade.listener.QueueListener;
import agh.soa.dziemich.krzeelzb.services.IUserManagementDatabaseOperationsService;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;

@Path("employee")
public class UserManagementController {

  @EJB(lookup = "java:global/db/UserManagementDatabaseOperationService")
  IUserManagementDatabaseOperationsService userManagementDbOpService;


  //TODO people management
  @PUT
  @Path("/{id}/password/")
  public Response changePassword(@PathParam("id") Long id, String password){
    userManagementDbOpService.updatePassword(id, password);
    return Response.ok().build();
  }



}

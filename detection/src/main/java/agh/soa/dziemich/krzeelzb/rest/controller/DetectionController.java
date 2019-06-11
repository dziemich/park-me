package agh.soa.dziemich.krzeelzb.rest.controller;

import agh.soa.dziemich.krzeelzb.entities.Employee;
import agh.soa.dziemich.krzeelzb.entities.ParkingPlace;
import agh.soa.dziemich.krzeelzb.entities.SubZone;
import agh.soa.dziemich.krzeelzb.queue.IQueueListener;
import agh.soa.dziemich.krzeelzb.services.IParkingPlaceDatabaseOperationsService;
import agh.soa.dziemich.krzeelzb.services.IUserManagementDatabaseOperationsService;
import agh.soa.dziemich.krzeelzb.services.IZoneDatabaseOperetionsService;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;


@Path("detection")
public class DetectionController {

  @EJB(lookup = "java:global/queue/QueueListener")
  IQueueListener queueListener;


  @EJB(lookup = "java:global/db/ParkingPlaceDatabaseOperationsService")
  IParkingPlaceDatabaseOperationsService parkingMeterDbOp;

  @EJB(lookup = "java:global/db/ZoneDatabaseOpertionsService")
  IZoneDatabaseOperetionsService zoneDbOp;

  @EJB(lookup = "java:global/db/UserManagementDatabaseOperationService")
  IUserManagementDatabaseOperationsService userManagementDbOpService;

  @GET
  @Path("events/{id}")
  @Produces("text/event-stream")
  public void getMessage(@Context SseEventSink sseEventSink, @Context Sse sse,
      @PathParam("id") Long empId) {
    Pattern p = Pattern.compile("\\d+");
    while (true) {
      String s = queueListener.receiveMessage();

      Long parkingPlaceId = null;
      if (s != null) {
        Matcher m = p.matcher(s);
        if (m.find()) {
          parkingPlaceId = Long.parseLong(m.group());
        }

        System.out.println("find " + parkingPlaceId);
        System.out.println(empId);
        Employee employee = userManagementDbOpService.findOne(empId).get(0);
        List<ParkingPlace> pp = parkingMeterDbOp.findOne(parkingPlaceId);
        List<SubZone> parkingPlacesSubZone = zoneDbOp.getParkingPlacesSubZone(pp.get(0).getId());
        List<Employee> subZoneEmployees = parkingPlacesSubZone.get(0)
            .getEmployees();
        Optional<Employee> correctEmployee = subZoneEmployees
            .stream()
            .filter(emp -> emp.getId().equals(empId))
            .findFirst();
        System.out.println("ADMIN: " + employee.getAdmin());

        if (correctEmployee.isPresent() || employee.getAdmin()) {
          OutboundSseEvent sseEvent = sse.newEventBuilder()
              .mediaType(MediaType.APPLICATION_JSON_TYPE)
              .data(String.class, s)
              .reconnectDelay(1000)
              .build();
          System.out.println("publishing msg");
          System.out.println(pp.get(0).getId());
          sseEventSink.send(sseEvent);
          System.out.println("published msg");
        }
      }
    }
  }
}

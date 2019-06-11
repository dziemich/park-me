package agh.soa.dziemich.krzeelzb.controllers;

import agh.soa.dziemich.krzeelzb.entities.ParkingPlace;
import agh.soa.dziemich.krzeelzb.services.IParkingPlaceDatabaseOperationsService;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/parkingPlaces")
public class ApiParkingPlacesController {

  @EJB(lookup = "java:global/db/ParkingPlaceDatabaseOperationsService")
  IParkingPlaceDatabaseOperationsService parkingMeterDbOp;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<ParkingPlace> getAllParkingPlaces() {
    return parkingMeterDbOp.findAll();
  }

  @GET
  @Path("/expiredPlaces")
  @Produces(MediaType.APPLICATION_JSON)
  public List<ParkingPlace> getAllExpiredParkingPlaces() {
    return parkingMeterDbOp.fetchExpiredParkingPlaces();
  }

  @GET
  @Path("/expiredPlaces/{street}")
  @Produces(MediaType.APPLICATION_JSON)
  public List<ParkingPlace> getAllExpiredParkingPlacesFromStreet(
      @PathParam("street") String street) {
    return parkingMeterDbOp.fetchExpiredParkingPlacesFromStreet(street);
  }

  @GET
  @Path("/freePlaces")
  @Produces(MediaType.APPLICATION_JSON)
  public List<ParkingPlace> getAllFreeParkingPlaces() {
    return parkingMeterDbOp.fetchFreeParkingPlaces();
  }

  @GET
  @Path("/freePlaces/{street}")
  @Produces(MediaType.APPLICATION_JSON)
  public List<ParkingPlace> getAllFreeParkingPlacesFromStreet(@PathParam("street") String street) {
    return parkingMeterDbOp.fetchFreeParkingPlacesFromStreet(street);
  }
}

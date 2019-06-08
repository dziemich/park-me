package agh.soa.dziemich.krzeelzb.controllers;

import agh.soa.dziemich.krzeelzb.entities.ParkingPlace;
import agh.soa.dziemich.krzeelzb.services.IParkingPlaceDatabaseOperationsService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/parkingPlaces")
public class ApiParkingPlacesController {

    @EJB(lookup = "java:global/db/ParkingPlaceDatabaseOperationsService")
    IParkingPlaceDatabaseOperationsService parkingMeterDbOp;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ParkingPlace> getAllParkingPlaces(){ return parkingMeterDbOp.findAll();}

    @GET
    @Path("/expiredPlaces")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ParkingPlace> getAllExpiredParkingPlaces(){
        return parkingMeterDbOp.fetchExpiredParkingPlaces();
    }

    @GET
    @Path("/expiredPlaces/{street}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ParkingPlace> getAllExpiredParkingPlacesFromStreet(@PathParam("street") String street){
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

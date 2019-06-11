package agh.soa.dziemich.krzeelzb.controllers;

import agh.soa.dziemich.krzeelzb.entities.ParkingPlace;
import agh.soa.dziemich.krzeelzb.services.IParkingPlaceDatabaseOperationsService;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/parkingplaces")
public class HrParkingPlaceController {
    @EJB(lookup = "java:global/db/ParkingPlaceDatabaseOperationsService")
    IParkingPlaceDatabaseOperationsService parkingMeterDbOp;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ParkingPlace> getAll(){ return parkingMeterDbOp.findAll();}


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ParkingPlace> getOneParkingPlace(@PathParam("id") Long id){
        return parkingMeterDbOp.findOne(id);}

    @POST
    @Path("/post")
    public Response addOneParkingPlace(ParkingPlace ppm){
        parkingMeterDbOp.addParkingPlace(ppm);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteParkingPlace(@PathParam("id") Long id){
        parkingMeterDbOp.deleteParkingPlace(id);
        return Response.ok().build();
    }
    @GET
    @Path("/expiredPlaces")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ParkingPlace> getAllExpiredParkingPlaces(){
        return parkingMeterDbOp.fetchExpiredParkingPlaces();
    }

    @GET
    @Path("/freePlaces")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ParkingPlace> getAllFreeTakenPlaces() {
        return parkingMeterDbOp.fetchFreeParkingPlaces();
    }

    @GET
    @Path("/takenPlaces")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ParkingPlace> getAllTakenParkingPlaces() {
        return parkingMeterDbOp.fetchTakenParkingPlaces();
    }


}

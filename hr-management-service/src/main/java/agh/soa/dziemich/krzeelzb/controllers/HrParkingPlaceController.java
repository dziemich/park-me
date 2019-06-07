package agh.soa.dziemich.krzeelzb.controllers;

import agh.soa.dziemich.krzeelzb.entities.ParkingPlace;
import agh.soa.dziemich.krzeelzb.services.IParkingPlaceDatabaseOperationsService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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

}

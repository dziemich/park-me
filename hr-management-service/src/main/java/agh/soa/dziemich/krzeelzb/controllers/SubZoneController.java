package agh.soa.dziemich.krzeelzb.controllers;

import agh.soa.dziemich.krzeelzb.entities.Employee;
import agh.soa.dziemich.krzeelzb.entities.ParkingPlace;
import agh.soa.dziemich.krzeelzb.entities.Parkometer;
import agh.soa.dziemich.krzeelzb.entities.SubZone;
import agh.soa.dziemich.krzeelzb.services.IParkingPlaceDatabaseOperationsService;
import agh.soa.dziemich.krzeelzb.services.IZoneDatabaseOperetionsService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import java.io.IOException;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/subzones")
public class SubZoneController {
    @EJB(lookup = "java:global/db/ZoneDatabaseOpertionsService")
    IZoneDatabaseOperetionsService zoneDbOp;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SubZone> getAll(){ return zoneDbOp.getAll();}

    @GET
    @Path("/ids")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Long> getAllIds(){ return zoneDbOp.getAllIds();}


    @POST
    @Path("/post")
    public Response addOneParkingPlace(String json){
        ObjectMapper mapper = new ObjectMapper()
            .registerModule(new ParameterNamesModule())
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule());
        SubZone subZone = null;
        try {
            subZone = mapper.readValue(json, SubZone.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        zoneDbOp.addSubZone(subZone.getParkingPlaces(),subZone.getParkometers(),subZone.getEmployees());
        return Response.ok().build();
    }

}


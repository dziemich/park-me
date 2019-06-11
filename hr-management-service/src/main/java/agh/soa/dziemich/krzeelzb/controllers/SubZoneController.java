package agh.soa.dziemich.krzeelzb.controllers;

import agh.soa.dziemich.krzeelzb.entities.Parkometer;
import agh.soa.dziemich.krzeelzb.entities.SubZone;
import agh.soa.dziemich.krzeelzb.services.IZoneDatabaseOperetionsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
        zoneDbOp.addSubZone(subZone);
        return Response.ok().build();
    }

    @POST
    @Path("/parkometer")
    public Response addParkometer( ){
        zoneDbOp.addParkometer();
        return Response.ok().build();
    }
    @GET
    @Path("/parkometer")
    @Produces(MediaType.APPLICATION_JSON)
    public  List<Parkometer>  getParkometers( ){
        return zoneDbOp.getAllParkometers();
    }

}


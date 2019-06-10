package com.github.adminfaces.showcase.mybeans;

import agh.soa.dziemich.krzeelzb.entities.ParkingPlace;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.ws.rs.core.Response;

import agh.soa.dziemich.krzeelzb.entities.Parkometer;
import agh.soa.dziemich.krzeelzb.services.IParkingPlaceDatabaseOperationsService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.json.JSONArray;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import java.io.IOException;

@Named
@ViewScoped
public class ParkingPlacesBean implements Serializable {
  @EJB(lookup = "java:global/db/ParkingPlaceDatabaseOperationsService")
  IParkingPlaceDatabaseOperationsService parkingMeterDbOp;

  public List<ParkingPlace> getParkingPlaces() throws IOException {
//    return List.of(
//        new ParkingPlace( "nullo", true, true, LocalDateTime.of(2019, 7,1,1,1,1)),
//        new ParkingPlace( "rodzinna", false, true, LocalDateTime.of(2019, 7,1,1,1,1)),
//        new ParkingPlace( "rodzinna", true, false, LocalDateTime.of(2019, 7,1,1,1,1) )
//    );

   return parkingMeterDbOp.findAll();

  }

  public List<Long> getParkingPlacesId() throws IOException {
    List<ParkingPlace> pp =getParkingPlaces();
    List<Long> ids=new LinkedList<>();
    for(ParkingPlace p: pp){
      ids.add(p.getId());
    }
    return ids;
  }



  public int numberOfFreePlaces() {
    return mapToJsonArray(makeRequest("http://127.0.0.1:8080/hr-management-service/hr/parkingplaces/freePlaces")).length();
  }

  public int numberOfExpiredPlaces() {
    return mapToJsonArray(makeRequest("http://127.0.0.1:8080/hr-management-service/hr/parkingplaces/expiredPlaces")).length();
  }
  public int numberOfTakenPlaces() {
    return mapToJsonArray(makeRequest("http://127.0.0.1:8080/hr-management-service/hr/parkingplaces/takenPlaces")).length();
  }

  public int numberOfAllParkingPlaces() {
    return mapToJsonArray(makeRequest("http://127.0.0.1:8080/hr-management-service/hr/parkingplaces/")).length();
  }

  private String makeRequest(String url){
    ResteasyClient client = new ResteasyClientBuilder().build();
    Response response =  client.target(url).request().get();
    return response.readEntity(String.class);
  }
  private JSONArray mapToJsonArray(String json){
    return new JSONArray(json);
  }
}

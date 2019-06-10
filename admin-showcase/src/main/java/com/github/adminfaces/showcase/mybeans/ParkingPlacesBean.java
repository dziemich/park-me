package com.github.adminfaces.showcase.mybeans;

import agh.soa.dziemich.krzeelzb.entities.ParkingPlace;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.inject.Named;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.json.JSONArray;


@Named
@ViewScoped
public class ParkingPlacesBean implements Serializable {

  public List<ParkingPlace> getParkingPlaces() {
    return List.of(
        new ParkingPlace( "nullo", true, true, LocalDateTime.of(2019, 7,1,1,1,1)),
        new ParkingPlace( "rodzinna", false, true, LocalDateTime.of(2019, 7,1,1,1,1)),
        new ParkingPlace( "rodzinna", true, false, LocalDateTime.of(2019, 7,1,1,1,1) )
    );
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

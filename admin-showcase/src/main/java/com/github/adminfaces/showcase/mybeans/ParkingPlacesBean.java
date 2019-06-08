package com.github.adminfaces.showcase.mybeans;

import agh.soa.dziemich.krzeelzb.entities.ParkingPlace;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.io.Serializable;
import javax.inject.Named;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;


@Named
@ViewScoped
public class ParkingPlacesBean implements Serializable {
  public List<ParkingPlace> getParkingPlaces() {
    return List.of(
            new ParkingPlace(1L, "nullo", true, true,
                    LocalDateTime.of(2020, 1, 2, 8, 20)),
            new ParkingPlace(3L, "rodzinna", false, true,
                    LocalDateTime.of(2019, 9, 2, 8, 30)),
            new ParkingPlace(7L, "rodzinna", true, false,
                    LocalDateTime.of(2019, 1, 2, 12, 20))
    );
  }

  public List<String> filtersTest() {
    return List.of("nullo", "rodzinna");
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

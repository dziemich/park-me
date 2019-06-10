package com.github.adminfaces.showcase.mybeans;

import agh.soa.dziemich.krzeelzb.entities.Employee;
import agh.soa.dziemich.krzeelzb.entities.ParkingPlace;
import agh.soa.dziemich.krzeelzb.entities.Parkometer;
import agh.soa.dziemich.krzeelzb.entities.SubZone;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.omnifaces.cdi.ViewScoped;

import javax.inject.Named;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Named
@ViewScoped
public class ZoneFormBean implements Serializable {

    List<Employee> emps;
    List<ParkingPlace> parkingPlaces;
    List<Parkometer> parkometers;


    public List<Long> getParkometrsIds() throws IOException {
        ResteasyClient client = new ResteasyClientBuilder().build();
        Response response =  client.target("http://127.0.0.1:8080/hr-management-service/hr/subzones/parkometer").request().get();
        String jsonData = response.readEntity(String.class);
        ObjectMapper mapper = new ObjectMapper();
        List<Parkometer> myObjects = mapper.readValue(jsonData, new TypeReference<List<Parkometer>>(){});

        List<Long> parkometerIds=new LinkedList<>();
        for(Parkometer emp: myObjects){
            parkometerIds.add(emp.getId());
        }
        return parkometerIds;

    }

    public void addParkometer(){
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target =  client.target("http://127.0.0.1:8080/hr-management-service/hr/subzones/parkometer");
        Response response = target.request()
                .post(Entity.entity("", MediaType.APPLICATION_JSON));
    }


    


    public List<Employee> getEmps() {
        return emps;
    }

    public void setEmps(List<Employee> emps) {
        this.emps = emps;
    }


    public void addZone(){
        SubZone sz=new SubZone();
    }

    public List<ParkingPlace> getParkingPlaces() {
        return parkingPlaces;
    }

    public void setParkingPlaces(List<ParkingPlace> parkingPlaces) {
        this.parkingPlaces = parkingPlaces;
    }

    public List<Parkometer> getParkometers() {
        return parkometers;
    }

    public void setParkometers(List<Parkometer> parkometers) {
        this.parkometers = parkometers;
    }
}

package agh.soa.dziemich.krzeelzb.bean;

import agh.soa.dziemich.krzeelzb.entities.Employee;
import agh.soa.dziemich.krzeelzb.entities.ParkingPlace;
import agh.soa.dziemich.krzeelzb.entities.Parkometer;
import agh.soa.dziemich.krzeelzb.entities.SubZone;
import agh.soa.dziemich.krzeelzb.services.IParkingPlaceDatabaseOperationsService;
import agh.soa.dziemich.krzeelzb.services.IUserManagementDatabaseOperationsService;
import agh.soa.dziemich.krzeelzb.services.IZoneDatabaseOperetionsService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.json.JSONObject;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class ZoneFormBean implements Serializable {

    List<Employee> emps = new LinkedList<>();
    List<Long> empsId;
    List<ParkingPlace> parkingPlaces = new LinkedList<>();
    List<Long> parkingPlacesId;
    List<Parkometer> parkometers = new LinkedList<>();
    List<Long> parkometersId;

    @EJB(lookup = "java:global/db/ZoneDatabaseOpertionsService")
    IZoneDatabaseOperetionsService zoneDbOp;

    @EJB(lookup = "java:global/db/ParkingPlaceDatabaseOperationsService")
    IParkingPlaceDatabaseOperationsService parkingMeterDbOp;

    @EJB(lookup = "java:global/db/UserManagementDatabaseOperationService")
    IUserManagementDatabaseOperationsService userManagementDbOpService;

    public List<Long> getParkometrsIds() throws IOException {
        ResteasyClient client = new ResteasyClientBuilder().build();
        Response response = client.target("http://127.0.0.1:8080/hr-management-service/hr/subzones/parkometer").request().get();
        String jsonData = response.readEntity(String.class);
        ObjectMapper mapper = new ObjectMapper();
        List<Parkometer> myObjects = mapper.readValue(jsonData, new TypeReference<List<Parkometer>>() {
        });

        List<Long> parkometerIds = new LinkedList<>();
        for (Parkometer emp : myObjects) {
            parkometerIds.add(emp.getId());
        }
        return parkometerIds;

    }

    public List<Long> getParkometrsIdsWithoutZone() throws IOException {
        EmployeeFormBean empBean = new EmployeeFormBean();
        List<Long> szIds = empBean.getIds();
        return getParkometrsIds().stream()
                .filter(s -> !szIds.contains(s))
                .collect(Collectors.toList());
    }


    public void addParkometer() {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://127.0.0.1:8080/hr-management-service/hr/subzones/parkometer");
        Response response = target.request()
                .post(Entity.entity("", MediaType.APPLICATION_JSON));
    }

    public void addZone() {
        for (Long e : empsId) {
            System.out.println(userManagementDbOpService.findOne(e).get(0) + "ooooooooooooooo");
            emps.add(userManagementDbOpService.findOne(e).get(0));
        }
        for (Long p : parkingPlacesId) {
            System.out.println(parkingMeterDbOp.findOne(p).get(0));
            parkingPlaces.add(parkingMeterDbOp.findOne(p).get(0));
        }
        for (Long pm : parkometersId) {
            System.out.println(zoneDbOp.getOne(pm).get(0));
            parkometers.add(zoneDbOp.getOne(pm).get(0));
        }

        zoneDbOp.addSubZone(new SubZone(parkingPlaces, parkometers, emps)

        );
    }


    public List<Employee> getEmps() {
        return emps;
    }

    public void setEmps(List<Employee> emps) {
        this.emps = emps;
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

    public List<Long> getEmpsId() {
        return empsId;
    }

    public void setEmpsId(List<Long> empsId) {
        this.empsId = empsId;
    }

    public List<Long> getParkingPlacesId() {
        return parkingPlacesId;
    }

    public void setParkingPlacesId(List<Long> parkingPlacesId) {
        this.parkingPlacesId = parkingPlacesId;
    }

    public List<Long> getParkometersId() {
        return parkometersId;
    }

    public void setParkometersId(List<Long> parkometersId) {
        this.parkometersId = parkometersId;
    }

    public IZoneDatabaseOperetionsService getZoneDbOp() {
        return zoneDbOp;
    }

    public void setZoneDbOp(IZoneDatabaseOperetionsService zoneDbOp) {
        this.zoneDbOp = zoneDbOp;
    }
}

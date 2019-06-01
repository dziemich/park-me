package agh.soa.dziemich.krzeelzb;

import javax.xml.ws.Endpoint;

public class ParkingPlacePublisher {
  public static void main(String[] args) {
    Endpoint.publish("http://localhost:9000/ParkingPlace", new ParkingPlacesControllerImpl());
  }
}

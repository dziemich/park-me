package agh.soa.dziemich.krzeelzb;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface ParkingPlacesController {

  @WebMethod
  public void takePlace(Long id);

  @WebMethod
  public void freePlace(Long id);

}


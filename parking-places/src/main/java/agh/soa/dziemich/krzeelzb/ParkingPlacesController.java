package agh.soa.dziemich.krzeelzb;

import agh.soa.dziemich.krzeelzb.entities.ParkingPlace;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface ParkingPlacesController {

  @WebMethod
  public void takePlace(Long id);

  @WebMethod
  public void freePlace(Long id);

}


package agh.soa.dziemich.krzeelzb.theatre.Beans;

import agh.soa.dziemich.krzeelzb.theatre.Contracts.SeatAvailabilityServiceInterface;
import agh.soa.dziemich.krzeelzb.theatre.Exceptions.SeatDoesNotExistException;
import agh.soa.dziemich.krzeelzb.theatre.Contracts.LocalSeatsServiceInterface;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(SeatAvailabilityServiceInterface.class)
public class SeatAvailabilityService implements SeatAvailabilityServiceInterface {
    @EJB
    private LocalSeatsServiceInterface seatsService;

    @Override
    public boolean isAvailable(int number) throws SeatDoesNotExistException {
        return seatsService.getSeatByNumber(number).isAvailable();
    }
}

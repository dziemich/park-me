package agh.soa.dziemich.krzeelzb.theatre.Views;

import agh.soa.dziemich.krzeelzb.theatre.Contracts.SeatAvailabilityServiceInterface;
import agh.soa.dziemich.krzeelzb.theatre.Contracts.SeatsServiceInterface;
import agh.soa.dziemich.krzeelzb.theatre.Contracts.TicketsServiceInterface;
import agh.soa.dziemich.krzeelzb.theatre.Exceptions.NotEnoughFundsException;
import agh.soa.dziemich.krzeelzb.theatre.Exceptions.SeatDoesNotExistException;
import agh.soa.dziemich.krzeelzb.theatre.Exceptions.SeatUnavailableException;
import agh.soa.dziemich.krzeelzb.theatre.Models.Seat;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("Theatre")
@SessionScoped
public class Theatre implements Serializable {
    @EJB(lookup = "java:global/facade/TicketsService")
    private TicketsServiceInterface ticketsService;

    @EJB(lookup = "java:global/facade/SeatAvailabilityService")
    private SeatAvailabilityServiceInterface seatAvailabilityService;

    @EJB(lookup = "java:global/facade/SeatsService!agh.soa.dziemich.krzeelzb.theatre.Contracts.SeatsServiceInterface")
    private SeatsServiceInterface seatsService;

    private String error;

    public List<Seat> getSeats() {
        return seatsService.getSeatList();
    }

    public boolean isSeatAvailable(int number) throws SeatDoesNotExistException {
        return seatAvailabilityService.isAvailable(number);
    }

    public void buy(int number) {
        try {
            ticketsService.buyTicket(number);
            error = null;
        } catch (NotEnoughFundsException | SeatDoesNotExistException | SeatUnavailableException e) {
            error = e.getMessage();
        }
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getWallet() {
        return ticketsService.getWallet();
    }
}

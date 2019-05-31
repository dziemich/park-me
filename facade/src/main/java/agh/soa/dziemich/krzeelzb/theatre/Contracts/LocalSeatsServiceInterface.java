package agh.soa.dziemich.krzeelzb.theatre.Contracts;

import agh.soa.dziemich.krzeelzb.theatre.Exceptions.SeatDoesNotExistException;
import agh.soa.dziemich.krzeelzb.theatre.Exceptions.SeatUnavailableException;
import agh.soa.dziemich.krzeelzb.theatre.Models.Seat;

public interface LocalSeatsServiceInterface {
    Seat getSeatByNumber(int number) throws SeatDoesNotExistException;

    int getSeatPrice(int number) throws SeatDoesNotExistException;

    void buyTicket(int number) throws SeatUnavailableException, SeatDoesNotExistException;
}

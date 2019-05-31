package agh.soa.dziemich.krzeelzb.theatre.Contracts;

import agh.soa.dziemich.krzeelzb.theatre.Exceptions.NotEnoughFundsException;
import agh.soa.dziemich.krzeelzb.theatre.Exceptions.SeatDoesNotExistException;
import agh.soa.dziemich.krzeelzb.theatre.Exceptions.SeatUnavailableException;

public interface TicketsServiceInterface {
    void buyTicket(int number) throws SeatDoesNotExistException, NotEnoughFundsException, SeatUnavailableException;

    int getWallet();
}

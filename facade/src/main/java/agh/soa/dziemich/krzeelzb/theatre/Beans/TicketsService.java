package agh.soa.dziemich.krzeelzb.theatre.Beans;

import agh.soa.dziemich.krzeelzb.theatre.Contracts.TicketsServiceInterface;
import agh.soa.dziemich.krzeelzb.theatre.Exceptions.SeatDoesNotExistException;
import agh.soa.dziemich.krzeelzb.theatre.Exceptions.SeatUnavailableException;
import agh.soa.dziemich.krzeelzb.theatre.Contracts.LocalSeatsServiceInterface;
import agh.soa.dziemich.krzeelzb.theatre.Exceptions.NotEnoughFundsException;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;

@Stateful
@Remote(TicketsServiceInterface.class)
public class TicketsService implements TicketsServiceInterface {
    @EJB
    private LocalSeatsServiceInterface seatsService;

    private int wallet = 1000;

    @Override
    public void buyTicket(int number) throws SeatDoesNotExistException, NotEnoughFundsException, SeatUnavailableException {
        int price = seatsService.getSeatPrice(number);

        if (price > wallet) {
            throw new NotEnoughFundsException(number, price, wallet);
        }

        seatsService.buyTicket(number);
        wallet -= price;
    }

    @Override
    public int getWallet() {
        return wallet;
    }
}

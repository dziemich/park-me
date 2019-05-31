package agh.soa.dziemich.krzeelzb.theatre.Contracts;

import agh.soa.dziemich.krzeelzb.theatre.Exceptions.SeatDoesNotExistException;

public interface SeatAvailabilityServiceInterface {
    boolean isAvailable(int number) throws SeatDoesNotExistException;
}

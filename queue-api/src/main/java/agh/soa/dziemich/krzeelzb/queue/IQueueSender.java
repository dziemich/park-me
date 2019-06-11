package agh.soa.dziemich.krzeelzb.queue;

public interface IQueueSender {

  void sendExpirationMessage(String message);

  void sendNewPlaceOccupiedMessage(String message);

  void sendNewPlaceFreeMessage(String message);

  void sendNewTicketBoughtMessage(String message);
}

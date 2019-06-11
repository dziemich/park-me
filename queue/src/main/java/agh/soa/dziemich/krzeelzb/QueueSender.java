package agh.soa.dziemich.krzeelzb;

import agh.soa.dziemich.krzeelzb.queue.IQueueSender;
import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.TextMessage;

@Stateless
@Remote(IQueueSender.class)
public class QueueSender implements IQueueSender {

  @Inject
  private JMSContext context;

  @Resource(mappedName = "java:/jboss/exported/jms/queue/soa-queue")
  private Queue queue;

  @Override
  public void sendExpirationMessage(String message) {
    String placeExpirationMessage = String.format("The place with id: %s has expired", message);
    sendMessage(placeExpirationMessage);
    System.out.println("sent expiration message with id: " + message);
  }

  @Override
  public void sendNewPlaceOccupiedMessage(String message) {
    String newPlaceOccupiedMessage = String.format("The place with id: %s has been taken", message);
    sendMessage(newPlaceOccupiedMessage);
    System.out.println("sent new place occupied message with id: " + message);
  }

  @Override
  public void sendNewPlaceFreeMessage(String message) {
    String newPlaceFreeMessage = String.format("The place with id: %s has been freed", message);
    sendMessage(newPlaceFreeMessage);
    System.out.println("sent new place freed message with id: " + message);
  }

  @Override
  public void sendNewTicketBoughtMessage(String message) {
    String newTicketBought = String.format("The place with id: %s has been paid for", message);
    sendMessage(newTicketBought);
    System.out.println("sent new ticket bought message with id: " + message);
  }

  private void sendMessage(String expirationMessage) {
    TextMessage queueTextMessage = context.createTextMessage(expirationMessage);
    context.createProducer().send(queue, queueTextMessage);
  }
}

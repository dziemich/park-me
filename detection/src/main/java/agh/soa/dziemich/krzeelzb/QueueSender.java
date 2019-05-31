package agh.soa.dziemich.krzeelzb;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.TextMessage;

@Stateless
public class QueueSender {

  @Inject
  private JMSContext context;

  @Resource(mappedName = "java:/jboss/exported/jms/queue/soa-queue")
  private Queue queue;

  public void sendMessage(String message){
    System.out.println("sending id: " + message);
    TextMessage queueTextMessage = context.createTextMessage(message);
    context.createProducer().send(queue, "trututu");
    System.out.println("sent id: " + message);
  }

}

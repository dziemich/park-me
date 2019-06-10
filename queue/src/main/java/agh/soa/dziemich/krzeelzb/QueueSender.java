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

  public void sendMessage(String message){
    System.out.println("sending id: " + message);
    TextMessage queueTextMessage = context.createTextMessage(message);
    context.createProducer().send(queue, queueTextMessage);
    System.out.println("sent id: " + message);
  }

}

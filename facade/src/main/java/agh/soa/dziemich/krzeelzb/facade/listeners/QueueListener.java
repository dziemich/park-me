package agh.soa.dziemich.krzeelzb.facade.listeners;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.TextMessage;

@Stateless
public class QueueListener {

  @Inject
  private JMSContext context;

  @Resource(mappedName = "java:/jboss/exported/jms/queue/soa-queue")
  private Queue queue;

  @Schedule(second = "*", minute = "*/1", hour = "*", persistent = false)
  public String receiveMessage(){
    System.out.println("receiving id:");
    String body = context.createConsumer(queue).receiveBody(String.class);
    System.out.println("received id: " + body);
    return body;
  }
}

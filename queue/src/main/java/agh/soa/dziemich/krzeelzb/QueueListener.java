package agh.soa.dziemich.krzeelzb;

import agh.soa.dziemich.krzeelzb.queue.IQueueListener;
import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

@Stateless
@Remote(IQueueListener.class)
public class QueueListener implements IQueueListener {

  @Inject
  private JMSContext context;

  @Resource(mappedName = "java:/jboss/exported/jms/queue/soa-queue")
  private Queue queue;

  public String receiveMessage() {
    String body = context.createConsumer(queue).receiveBody(String.class);
    return body;
  }
}

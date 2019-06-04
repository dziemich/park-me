package agh.soa.dziemich.krzeelzb.facade.listener;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(
        propertyName = "destination",
        propertyValue = "java:/jboss/exported/jms/queue/soa-queue"
    )
})
public class QueueListener implements MessageListener {

  @Override
  public void onMessage(Message message) {
    TextMessage txt = (TextMessage) message;
    try {
      System.out.println("received message: " + txt.getText());
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }
}

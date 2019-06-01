package agh.soa.dziemich.krzeelzb.facade.listener;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

@Singleton
public class Listener {

  @EJB
  QueueListener queueListener;

  @Schedule(minute = "*/1", hour = "*", persistent = false)
  public void doWork() {
    String s = queueListener.receiveMessage();
  }
}

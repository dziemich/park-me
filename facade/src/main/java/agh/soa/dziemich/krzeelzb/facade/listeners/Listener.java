package agh.soa.dziemich.krzeelzb.facade.listeners;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Stateless;

@Singleton
public class Listener {

  @EJB
  QueueListener queueListener;

  @Schedule(second = "*", minute = "*/1", hour = "*", persistent = false)
  public void doWork() {
    System.out.println("pppppp");
    String s = queueListener.receiveMessage();
    System.out.println(s);
    System.out.println("XD");
  }
}

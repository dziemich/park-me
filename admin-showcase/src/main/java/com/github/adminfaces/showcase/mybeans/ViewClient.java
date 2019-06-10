package com.github.adminfaces.showcase.mybeans;


import java.io.Serializable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Consumer;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.sse.SseEventSource;

@ManagedBean(eager = true)
@ApplicationScoped
public class ViewClient implements Serializable {

  private static final String BASE_URI = "http://localhost:9090";
  private static final long serialVersionUID = 1974510449199395815L;

  @ManagedProperty(value = "#{messageHolder}")
  public MessageHolder messageHolder;
  private ScheduledExecutorService scheduler;


  public ViewClient() {

  }

  public void checkSSE() {
    System.out.println("Client start check");
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target("http://localhost:8080/facade/facade/detection/events");

    try (SseEventSource eventSource = SseEventSource.target(target).build()) {

      eventSource.register(
          inboundSseEvent -> {
            String msg = inboundSseEvent.readData();
            System.out.println("adding id");
            messageHolder.getResponseMessagesList().add(msg);
          },
          onError,
          onComplete
      );
      eventSource.open();

      //Consuming events for one hour
      Thread.sleep(60 * 60 * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    client.close();
    System.out.println("End");
  }

  //Error
  private static Consumer<Throwable> onError = Throwable::printStackTrace;

  //Connection close and there is nothing to receive
  private static Runnable onComplete = () -> {
    System.out.println("Done!");
  };

  public MessageHolder getMessageHolder() {
    return messageHolder;
  }

  public void setMessageHolder(MessageHolder messageHolder) {
    this.messageHolder = messageHolder;
  }
}

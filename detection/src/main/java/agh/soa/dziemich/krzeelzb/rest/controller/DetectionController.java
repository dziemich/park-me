package agh.soa.dziemich.krzeelzb.rest.controller;

import agh.soa.dziemich.krzeelzb.queue.IQueueListener;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;


@Path("detection")
public class DetectionController {

  @EJB(lookup = "java:global/queue/QueueListener")
  IQueueListener queueListener;

  @GET
  @Path("events")
  @Produces("text/event-stream")
  public void getMessage(@Context SseEventSink sseEventSink, @Context Sse sse) {
    while (true) {
      String s = queueListener.receiveMessage();
      if (s != null) {
        System.out.println("constructing msg");
        OutboundSseEvent sseEvent = sse.newEventBuilder()
            .mediaType(MediaType.APPLICATION_JSON_TYPE)
            .data(String.class, s)
            .reconnectDelay(1000)
            .build();
        System.out.println("publishing msg");
        sseEventSink.send(sseEvent);
        System.out.println("published msg");
      }
    }
  }
}

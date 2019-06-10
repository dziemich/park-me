package com.github.adminfaces.showcase.mybeans;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class MessageHolder implements Serializable {


  private List<String> responseMessagesList = new ArrayList<>();

  public MessageHolder() {

  }

  public List<String> getResponseMessagesList() {
    return responseMessagesList;
  }

  public void setResponseMessagesList(List<String> responseMessagesList) {
    this.responseMessagesList = responseMessagesList;
  }
}

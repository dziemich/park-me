package com.github.adminfaces.showcase.mybeans;

import java.io.Serializable;
import java.security.Principal;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;

@Named
@ViewScoped
public class UserBean implements Serializable {

  public String show(){
    Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
    return principal.getName();
  }
}

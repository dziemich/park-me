package agh.soa.dziemich.krzeelzb.bean;

import agh.soa.dziemich.krzeelzb.entities.Employee;
import agh.soa.dziemich.krzeelzb.services.IUserManagementDatabaseOperationsService;
import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;

@Named
@ViewScoped
@Local
public class UserBean implements Serializable {

  @EJB(lookup = "java:global/db/UserManagementDatabaseOperationService")
  IUserManagementDatabaseOperationsService userManagementDbOpService;


  public String findUser() {
    Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
    return principal.getName();
  }

  public Long getUserId(){
    String login = findUser();
    return userManagementDbOpService
        .findAll()
        .stream()
        .filter(emp -> emp.getLogin().equals(login))
        .map(Employee::getId)
        .findFirst()
        .orElseThrow(IllegalStateException::new);
  }

  public Boolean getUserAdminPrivileges(){
    String login = findUser();
    return userManagementDbOpService
        .findAll()
        .stream()
        .filter(emp -> emp.getLogin().equals(login))
        .map(Employee::getAdmin)
        .findFirst()
        .orElseThrow(IllegalStateException::new);
  }

  public String showName(){
    String login = findUser();
    return userManagementDbOpService
        .findAll()
        .stream()
        .filter(emp -> emp.getLogin().equals(login))
        .map(Employee::getName)
        .findFirst()
        .orElseThrow(IllegalStateException::new);
  }

  public void logout(){
    Faces.getSession().invalidate();
    ExternalContext ec = Faces.getExternalContext();
    try {
      ec.redirect("/showcase");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

package agh.soa.dziemich.krzeelzb.bean;

import agh.soa.dziemich.krzeelzb.model.User;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.event.FlowEvent;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class UserWizardMB implements Serializable {

    private User user = new User();

    private boolean skip;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void save() {
        FacesMessage msg = new FacesMessage("Successful", "Welcome :" + user.getFirstname());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }
}

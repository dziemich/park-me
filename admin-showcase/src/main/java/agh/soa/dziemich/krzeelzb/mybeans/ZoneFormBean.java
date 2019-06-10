package agh.soa.dziemich.krzeelzb.mybeans;

import agh.soa.dziemich.krzeelzb.entities.Employee;
import agh.soa.dziemich.krzeelzb.entities.SubZone;
import org.omnifaces.cdi.ViewScoped;

import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ZoneFormBean implements Serializable {

    List<Employee> emps;


    public List<Employee> getEmps() {
        return emps;
    }

    public void setEmps(List<Employee> emps) {
        this.emps = emps;
    }




    public void addZone(){
        SubZone sz=new SubZone();
    }



}

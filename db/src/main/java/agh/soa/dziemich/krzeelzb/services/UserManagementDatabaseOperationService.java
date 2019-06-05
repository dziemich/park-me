package agh.soa.dziemich.krzeelzb.services;

import agh.soa.dziemich.krzeelzb.dao.EmployeeDao;
import agh.soa.dziemich.krzeelzb.dao.SubZoneDao;
import agh.soa.dziemich.krzeelzb.entities.Employee;
import agh.soa.dziemich.krzeelzb.entities.SubZone;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

@Stateless
@Remote(IUserManagementDatabaseOperationsService.class)
public class UserManagementDatabaseOperationService implements
        IUserManagementDatabaseOperationsService {

    @Inject
    EmployeeDao employeeDao;

    @Inject
    SubZoneDao subZoneDao;


    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public List<Employee> findOne(Long id) {
        return employeeDao.findOne(id);
    }

    @Override
    public void addEmployee(Employee emp) {
        employeeDao.addEmployee(emp);
    }

    @Override
    public void updatePassword(Long id, String value) {
        employeeDao.updateEmployeesPassword(id, value);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeDao.deleteEmployee(id);
    }

    @Override
    public List<SubZone> getEmployeeSubZone(Long id) {
        List<SubZone> zonesEmployee = new LinkedList<>();
        List<SubZone> zones = subZoneDao.findAll();
        for(SubZone z : zones){
            List<Employee> emps = z.getEmployees();
            for (Employee e : emps) {
                if (e.getId().equals(id)) {
                    zonesEmployee.add(z);
                }
            }
        }
//        for (int i = 0; i < zones.size(); i++) {
//            System.out.println(zones.get(i));
//
//            List<Employee> emps = zones.get(i).getEmployees();
//            System.out.println(zones.get(i).getEmployees());
//            for (Employee e : emps) {
//                if (e.getId().equals(id)) {
//                    zonesEmployee.add(zones.get(i).getId());
//                }
//            }
//        }
        return zonesEmployee;
    }
}

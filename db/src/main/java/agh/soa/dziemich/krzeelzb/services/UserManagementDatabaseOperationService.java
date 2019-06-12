package agh.soa.dziemich.krzeelzb.services;

import agh.soa.dziemich.krzeelzb.dao.EmployeeDao;
import agh.soa.dziemich.krzeelzb.dao.SubZoneDao;
import agh.soa.dziemich.krzeelzb.entities.Employee;
import agh.soa.dziemich.krzeelzb.entities.SubZone;
import java.util.Collections;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

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
  public List<Employee> findOne(String login) {
    return employeeDao.findOne(login);
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
    List<SubZone> zones = subZoneDao.findAll();
    for (SubZone z : zones) {
      List<Employee> emps = z.getEmployees();
      for (Employee e : emps) {
        if (e.getId().equals(id)) {
          return List.of(z);
        }
      }
    }
    return Collections.emptyList();
  }

  @Override
  public void addEmployeesZone(Long idEmp, Long subzoneId) {
    List<Employee> employee = employeeDao.findOne(idEmp);
    List<SubZone> subZone = subZoneDao.findOne(subzoneId);
    if (!subZone.isEmpty()) {
      subZone.get(0).addEmployeeToSubZone(employee.get(0));
    }
  }
}

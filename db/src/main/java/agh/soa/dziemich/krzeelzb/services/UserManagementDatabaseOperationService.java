package agh.soa.dziemich.krzeelzb.services;

import agh.soa.dziemich.krzeelzb.dao.EmployeeDao;
import agh.soa.dziemich.krzeelzb.entities.Employee;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@Remote(IUserManagementDatabaseOperationsService.class)
public class UserManagementDatabaseOperationService implements
    IUserManagementDatabaseOperationsService {

  @Inject
  EmployeeDao employeeDao;


  @Override
  public void addEmployee(Employee emp) {
    employeeDao.addEmployee(emp);
  }

  @Override
  public void updatePassword(Long id, String value) {
    employeeDao.updateEmployeesPassword(id, value);
  }
}

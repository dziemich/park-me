package agh.soa.dziemich.krzeelzb.services;

import agh.soa.dziemich.krzeelzb.entities.Employee;
import agh.soa.dziemich.krzeelzb.entities.SubZone;
import java.util.List;

public interface IUserManagementDatabaseOperationsService {

  List<Employee> findAll();

  List<Employee> findOne(Long id);

  List<Employee> findOne(String login);

  void addEmployee(Employee emp);

  void updatePassword(Long id, String value);

  void deleteEmployee(Long id);

  List<SubZone> getEmployeeSubZone(Long id);

  void addEmployeesZone(Long idEmp, Long subzoneId);
}

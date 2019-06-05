package agh.soa.dziemich.krzeelzb.services;

import agh.soa.dziemich.krzeelzb.entities.Employee;

import java.util.List;

public interface IUserManagementDatabaseOperationsService {

  List<Employee> findAll();

  List<Employee> findOne(Long id);

  void addEmployee(Employee emp);

  void updatePassword(Long id, String value);

  void deleteEmployee(Long id);

  List<Long> getEmployeeSubZone(Long id);


}

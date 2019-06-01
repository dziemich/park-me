package agh.soa.dziemich.krzeelzb.services;

import agh.soa.dziemich.krzeelzb.entities.Employee;

public interface IUserManagementDatabaseOperationsService {
  void addEmployee(Employee emp);

  void updatePassword(Long id, String value);
}

package agh.soa.dziemich.krzeelzb.dao;

import agh.soa.dziemich.krzeelzb.entities.Employee;
import java.util.List;

public class EmployeeDao extends AbstractDao {

  public EmployeeDao() {
    super();
  }

  public void addEmployee(Employee emp) {
    System.out.println(emp.getAdmin());
    entityManager.getTransaction().begin();
    entityManager.persist(emp);
    entityManager.getTransaction().commit();
  }

  public List<Employee> findOne(Long id) {
    return entityManager
        .createNamedQuery("Employee.findOne", Employee.class)
        .setParameter("id", id)
        .getResultList();
  }

  public List<Employee> findAll() {
    return entityManager
            .createNamedQuery("Employee.findAll", Employee.class)
            .getResultList();
  }

  public void updateEmployeesPassword(Long id, String value) {
    List<Employee> emps = findOne(id);
    if (!emps.isEmpty()) {
      Employee emp = emps.get(0);
      emp.setPassword(value);
      entityManager.getTransaction().begin();
      entityManager.merge(emp);
      entityManager.getTransaction().commit();
    }
  }

  public void deleteEmployee(Long empId){
    entityManager.getTransaction().begin();
    entityManager.createNamedQuery("Employee.deleteOne").setParameter("id", empId).executeUpdate();
    entityManager.getTransaction().commit();
  }


}

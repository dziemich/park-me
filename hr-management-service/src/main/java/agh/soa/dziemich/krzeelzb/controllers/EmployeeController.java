package agh.soa.dziemich.krzeelzb.controllers;

import agh.soa.dziemich.krzeelzb.entities.Employee;
import agh.soa.dziemich.krzeelzb.entities.SubZone;
import agh.soa.dziemich.krzeelzb.services.IUserManagementDatabaseOperationsService;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/employees")
public class EmployeeController {

  @EJB(lookup = "java:global/db/UserManagementDatabaseOperationService")
  IUserManagementDatabaseOperationsService employeesDbOp;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Employee> getAll() {
    return employeesDbOp.findAll();
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Employee> getOne(@PathParam("id") Long id) {
    return employeesDbOp.findOne(id);
  }


  @GET
  @Path("/{id}/zones")
  @Produces(MediaType.APPLICATION_JSON)
  public List<SubZone> getEmployeeZone(@PathParam("id") Long id) {
    return employeesDbOp.getEmployeeSubZone(id);
  }

  @POST
  @Path("/post")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response addOne(Employee emp) {
    employeesDbOp.addEmployee(emp);
    System.out.println(emp.getId());
    return Response.ok(employeesDbOp.findAll().get(employeesDbOp.findAll().size() - 1).getId())
        .build();
  }

  @PUT
  @Path("/{id}/zone")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response addEmployeesZone(@PathParam("id") Long idEmp, SubZone zone) {
    employeesDbOp.addEmployeesZone(idEmp, zone.getId());
    return Response.ok(idEmp).build();
  }

  @POST
  @Path("/post")
  public Response addEmployee(Employee emp) {
    employeesDbOp.addEmployee(emp);
    return Response.ok().build();
  }

  @PUT
  @Path("/{id}")
  public Response updateEmployeePassword(@PathParam("id") Long idEmp, String pass) {
    employeesDbOp.updatePassword(idEmp, pass);
    return Response.ok().build();
  }

  @DELETE
  @Path("/{id}")
  public Response deleteUser(@PathParam("id") Long id) {
    employeesDbOp.deleteEmployee(id);
    return Response.ok().build();
  }
}

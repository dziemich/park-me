package agh.soa.dziemich.krzeelzb.controllers;

import agh.soa.dziemich.krzeelzb.entities.Employee;
import agh.soa.dziemich.krzeelzb.entities.SubZone;
import agh.soa.dziemich.krzeelzb.services.IUserManagementDatabaseOperationsService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/employees")
public class EmployeeController {

    @EJB(lookup = "java:global/db/UserManagementDatabaseOperationService")
    IUserManagementDatabaseOperationsService employeesDbOp;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> getAll(){ return employeesDbOp.findAll();}

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> getOne(@PathParam("id") Long id){
        return employeesDbOp.findOne(id);}


    @GET
    @Path("/{id}/zones")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SubZone> getEmployeeZone(@PathParam("id") Long id){
        return employeesDbOp.getEmployeeSubZone(id);}

    @POST
    @Path("/post")
    public Response addOne(Employee emp){
        employeesDbOp.addEmployee(emp);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Long id){
        employeesDbOp.deleteEmployee(id);
        return Response.ok().build();
    }


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.fi.muni.pa165.calorycounter.frontend.restserver;

import cz.fi.muni.pa165.calorycounter.serviceapi.ActivityRecordService;
import cz.fi.muni.pa165.calorycounter.serviceapi.UserActivityRecordsService;
import cz.fi.muni.pa165.calorycounter.serviceapi.UserService;
import cz.fi.muni.pa165.calorycounter.serviceapi.dto.ActivityRecordDto;
import cz.fi.muni.pa165.calorycounter.serviceapi.dto.AuthUserDto;
import cz.fi.muni.pa165.calorycounter.serviceapi.dto.UserActivityRecordsDto;
import java.util.List;
import javax.persistence.NoResultException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.RecoverableDataAccessException;

/**
 *
 * @author bryndza
 */
@Path("/record")
public class RecordRestResource {
    
    @Autowired
    private ActivityRecordService recordService;
    @Autowired
    private UserActivityRecordsService recordsService;
    @Autowired
    private UserService userService;
    final static Logger log = LoggerFactory.getLogger(ActivityRestResource.class);
    
    @GET
    public Response getText() {
        throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
    
    @GET
    @Path("/user/{username}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getRecordsOfUser(@PathParam("username") String username) {
        log.debug("Server: getRecordsOfUser(username) with username: " + username);
        if (username==null || username.isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        AuthUserDto user = null;
        try{
            user = userService.getByUsername(username);
        } catch (RecoverableDataAccessException ex) {
            if (ex.getCause().getClass().equals(NoResultException.class)) {
                return Response.status(Response.Status.NOT_FOUND).build();
            } else {
                throw new WebApplicationException(ex, Response.Status.INTERNAL_SERVER_ERROR);
            }
        }
        UserActivityRecordsDto records = null;
        try{
            records = recordsService.getAllActivityRecords(user);
        }catch (RecoverableDataAccessException ex) {
            if (ex.getCause().getClass().equals(NoResultException.class)) {
                records = new UserActivityRecordsDto();
                records.setNameOfUser(user.getName());
            } else {
                throw new WebApplicationException(ex, Response.Status.INTERNAL_SERVER_ERROR);
            }
        }
        return Response.status(Response.Status.OK).entity(records).build();
    }
    
    @GET
    @Path("/id/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getRecord(@PathParam("id") String id) {
        log.debug("Server: getRecord(id) with id: " + id);
        if (id==null || id.isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Long idL;
        try {
            idL = Long.parseLong(id);
        } catch (NumberFormatException ex){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        ActivityRecordDto record = null;
        try{
            record = recordService.get(idL);
        } catch (RecoverableDataAccessException ex) {
            if (ex.getCause().getClass().equals(NoResultException.class)) {
                return Response.status(Response.Status.NOT_FOUND).build();
            } else {
                throw new WebApplicationException(ex, Response.Status.INTERNAL_SERVER_ERROR);
            }
        }
        return Response.status(Response.Status.OK).entity(record).build();
    }
}

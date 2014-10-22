/**
 * 
 */
package com.learn.rest;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.web.bind.annotation.RequestBody;

import com.learn.model.Person;
import com.learn.services.PeopleService;


/**
 * @author HY
 *
 */
@Path( "/people" ) 
public class PeopleRestService {
    @Inject private PeopleService peopleService;
 
    @Produces( { "application/json" } )
    @GET
    public Collection< Person > getPeople( @QueryParam( "page") @DefaultValue( "1" ) final int page ) {
        return peopleService.getPeople( page, 10 );
    }
  
    @Produces( { "application/json" } )
    @PUT
    public Person addPerson( @RequestBody Person person ) {
        Person result = peopleService.addPerson( person );
    	return result;
    }
}
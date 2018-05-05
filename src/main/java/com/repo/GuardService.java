package com.repo;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/GuardService")
public class GuardService {
	GuardDao gd = new GuardDao();

//	private static final String SUCCESS_RESULT = "<result>success</result>";
//	private static final String FAILURE_RESULT = "<result>failure</result>";

	@GET
	@Path("guards")
	@Produces(MediaType.TEXT_PLAIN)
	public String getAllGuards() {
		String ausgabe = "";
		for(Guard g : gd.getAllGuards())		{
			ausgabe += "ID: "+g.getId() +" -- Name: "+g.getVorname() + " " + g.getNachname() + " -- Email:"+ g.getEmail() +"<br />";
		}
		return ausgabe;
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.TEXT_XML)
	public String getGuard(@PathParam("id") int id) {
		return gd.getGuardName(id);
	}

	@GET
	@Path("addnewguard")
	@Produces("application/json;charset=UTF-8")
	public Response createGuard(@QueryParam("vorname") String vorname, @QueryParam("nachname") String nachname,
			@QueryParam("email") String email, @QueryParam("password") String password, @QueryParam("id") int id)
					throws IOException {
		gd.addGuard(vorname,nachname,email,password,id);	
		return Response.seeOther(URI.create("../rest/GuardService/guards")).build();
	}
	
	@GET
	@Path("login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String login(@QueryParam("email") String email,@QueryParam("password") String password){
		return gd.login(email, password) + " --> "+email+" is logged.";
	}

	@GET
	@Path("logout")
	@Produces(MediaType.TEXT_PLAIN)
	public String logout(@QueryParam("email") String email){
		return gd.logout(email);
//		return Response.seeOther(URI.create("../rest/GuardService/guards")).build();
	}
	
	@OPTIONS
	@Path("/guards")
	@Produces(MediaType.APPLICATION_XML)
	public String getSupportedOperations() {
		return "<operations>GET, PUT, POST, DELETE</operations>";
	}
}
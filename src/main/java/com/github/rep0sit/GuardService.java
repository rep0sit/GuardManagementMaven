package com.github.rep0sit;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/GuardService")
public class GuardService {
	GuardDao gd = new GuardDao();

	private static final String SUCCESS_RESULT = "<result>success</result>";
	private static final String FAILURE_RESULT = "<result>failure</result>";

	@GET
	@Path("/guards")
	@Produces(MediaType.APPLICATION_XML)
	public Set<Guard> getAllGuards() {
		return gd.getAllGuards();
	}

	@GET
	@Path("/guards/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Guard getGuard(@PathParam("id") int id) {
		return gd.getGuard(id);
	}

	@POST
	@Path("/guards")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String createGuard(@FormParam("vorname") String vorname, @FormParam("nachname") String nachname,
			@FormParam("email") String email, @FormParam("password") String password, @FormParam("id") int id,
			@Context HttpServletResponse servletResponse) throws IOException {
		Guard guard = new Guard(vorname, nachname, email, password, id);
		int result = gd.addGuard(guard);
		if (result == 1) {
			return SUCCESS_RESULT;
		}
		return FAILURE_RESULT;
	}
	
//	@POST
//	@Path("/guards")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	public postLogin(String email, String password){
//		
//	}
	

	@OPTIONS
	@Path("/guards")
	@Produces(MediaType.APPLICATION_XML)
	public String getSupportedOperations() {
		return "<operations>GET, PUT, POST, DELETE</operations>";
	}
	
	
}

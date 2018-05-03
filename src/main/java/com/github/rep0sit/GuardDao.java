package com.github.rep0sit;

import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.Response;

public class GuardDao {

	private static Set<Guard> guards = new HashSet<>();
	private static String FILE_NAME = "guards.dat";
	
	private final Map<String,String> guardPWMap = new HashMap();
	private static final String SUCCESS_LOGIN = "<result>success_login</result>";
	private static final String FAILURE_LOGIN = "<result>failure_login</result>";
	
	public Set<Guard> getAllGuards(){
		return guards;
		
	}

	

	public Guard getGuard(int id) {
		return guards.stream().filter(g -> g.getId() == id).findAny().orElse(null);
	}
	


	public int addGuard(Guard guard) {
		if(guards.contains(guard)) {return 0;}
		guards.add(guard);
		guardPWMap.put(guard.getEmail(), guard.getPassword());

		return 1;
	}
	
	public String login(String email, String password){
		if(guardPWMap.containsKey(email)){
			String dbPW = guardPWMap.get(email);
			if(dbPW.equals(password)){
				return SUCCESS_LOGIN;
			}
		}
		return FAILURE_LOGIN;		
	}
	
	public void logout(){
		Response.seeOther( URI.create("../guards")).build();
	}
}

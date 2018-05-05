package com.github.rep0sit;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class GuardDao {

	private static Set<Guard> guards = new HashSet<>();
//	private static String FILE_NAME = "guards.dat";
	
	private final Map<String,String> guardPWMap = new HashMap<String, String>();
	private static final String SUCCESS_LOGIN = "<result>success_login</result>";
	private static final String FAILURE_LOGIN = "<result>failure_login</result>";
	
	public GuardDao(){
		addGuard("guard1","guard1","guard1@email","password1",1);
		addGuard("guard2","guard2","guard2@email","password2",2);
	}

	public Set<Guard> getAllGuards(){
		return guards;		
	}
	/**
	 * Returns the name of a guard.
	 * 
	 * @param id
	 * @return
	 */
	public String getGuardName(int id){
		for(Guard g : guards){
			if(g.getId() == id){
				return g.getVorname() + " " + g.getNachname();
			}
		}
		return "Guard nicht vorhanden";
	}
	/**
	 * Returns a guard.
	 * 
	 * @param id
	 * @return
	 */
	public Guard getGuard(int id) {
		return guards.stream().filter(g -> g.getId() == id).findAny().orElse(null);
	}
	/**
	 * Add guard to the guards-set.
	 * 
	 * @param vorname
	 * @param nachname
	 * @param email
	 * @param password
	 * @param id
	 */
	public void addGuard(String vorname, String nachname, String email, String password, int id) {
		Guard guard = new Guard(vorname,nachname,email,password,id);
		guards.add(guard);
		guardPWMap.put(guard.getEmail(), guard.getPassword());
	}
	/**
	 * Add a guard to the guards-set and return 1.
	 * 
	 * @param guard
	 * @return
	 */
	public int addGuard(Guard guard) {
		if(guards.contains(guard)) {return 0;}
		guards.add(guard);
		guardPWMap.put(guard.getEmail(), guard.getPassword());

		return 1;
	}
	/**
	 * Login if password is correct.
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	public String login(String email, String password){
		if(guardPWMap.containsKey(email)){
			String dbPW = guardPWMap.get(email);
			if(dbPW.equals(password)){
				return SUCCESS_LOGIN;
			}
		}
		return FAILURE_LOGIN;		
	}
	
	public String logout(String email){
//		Response.seeOther( URI.create("../guards")).build();
		return email + " is logged out.";
	}
}

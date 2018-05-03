package com.github.rep0sit;

import java.util.HashSet;
import java.util.Set;

public class GuardDao {
private static Set<Guard> guards = new HashSet<>();
	
	
	public Set<Guard> getAllGuards(){
		return guards;
		
	}

	

	public Guard getGuard(int id) {
		return guards.stream().filter(g -> g.getId() == id).findAny().orElse(null);
	}
	


	public int addGuard(Guard guard) {
		if(guards.contains(guard)) {return 0;}
		guards.add(guard);
		return 1;
	}
}

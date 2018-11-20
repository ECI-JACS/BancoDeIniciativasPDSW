package edu.eci.pdsw.generators;

import org.quicktheories.core.Gen;
import org.quicktheories.generators.Generate;


import static org.quicktheories.generators.SourceDSL.*;

import edu.eci.pdsw.samples.entities.Area;
import edu.eci.pdsw.samples.entities.Role;
import edu.eci.pdsw.samples.entities.User;
import edu.eci.pdsw.samples.entities.UserStatus;

public class UserGenerator {
	
	public static Gen<User> userGenerator(){
		
		return in -> new User(
				names().generate(in),
				lastNames().generate(in),
				email().generate(in) ,
			    code().generate(in),
			    UserStatus.ACTIVO,
			    Role.PUBLICO,
			    null
				);
	}

	private static Gen<String> names() {
		return strings().betweenCodePoints(65, 122).ofLengthBetween(6, 15);
	}
	
	private static Gen<String> lastNames() {
		return strings().betweenCodePoints(65, 122).ofLengthBetween(6, 15);
	}
	
	private static  Gen<String> email() {
		return strings().betweenCodePoints(65, 122).ofLengthBetween(8, 15);
	}
	
	private static Gen<Integer> code() {
		return integers().from(10000).upToAndIncluding(100000000);
	}
}
 
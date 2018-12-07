package edu.eci.pdsw.generators;

import static org.quicktheories.generators.SourceDSL.strings;


import org.quicktheories.core.Gen;

import edu.eci.pdsw.samples.entities.Initiative;
import edu.eci.pdsw.samples.entities.InitiativeStatus;
import edu.eci.pdsw.samples.entities.Role;
import edu.eci.pdsw.samples.entities.User;
import edu.eci.pdsw.samples.entities.UserStatus;


public class InitiativeGenerator {
	
	
	
	public static Gen<Initiative> initiativeGenerator(){
			
			return in -> new Initiative(
					1,
					description().generate(in),
					detail().generate(in),
					new java.sql.Date (2018-1900,10,2) ,
					new  java.sql.Date(2018-1900,10,2) ,
					keyWords().generate(in) ,
					user().generate(in) ,
					new InitiativeStatus(1, "En espera de revisión")
					);
	}
	
	private static Gen<String> description() {
		return strings().betweenCodePoints(65, 122).ofLengthBetween(6, 15);
	}
	
	private static Gen<String> detail() {
		return strings().betweenCodePoints(65, 122).ofLengthBetween(10, 30);
	}
	
	private static Gen<User> user() {
		return UserGenerator.userGenerator();
	}
	
	private static Gen<String> InitiativeStatus() {
		return strings().betweenCodePoints(65, 122).ofLengthBetween(6, 15);
	}
	
	private static Gen<String> keyWords() {
		return strings().betweenCodePoints(65, 122).ofLengthBetween(6, 15);
	}
	

}

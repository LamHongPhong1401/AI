package ai_lab1;

import java.util.Random;

import ai_lab1.Environment.LocationState;

public class AgentProgram {

	public Action execute(Percept p) {// location, status
		Random rd = new Random();
		int index = rd.nextInt(2);
		Action[] arrAction = {Environment.MOVE_UP, Environment.MOVE_RIGHT, Environment.MOVE_DOWN ,Environment.MOVE_LEFT};
		if(p.getLocationState() == LocationState.DIRTY) return Environment.SUCK_DIRT;
		if(p.getAgentLocation() == Environment.LOCATION_A ) {
			if(index == 0) {
				return arrAction[1];
			}else {
				return arrAction[2];
			}
		}
		if(p.getAgentLocation() == Environment.LOCATION_B ) {
			if(index == 0) {
				return arrAction[3];
			}else {
				return arrAction[2];
			}
		}
		if(p.getAgentLocation() == Environment.LOCATION_C) {
			if(index == 0) {
				return arrAction[0];
			}else {
				return arrAction[3];
			}
		}
		if( p.getAgentLocation() == Environment.LOCATION_D) {
			if(index == 0) {
				return arrAction[0];
			}else {
				return arrAction[1];
			}
		}
		return NoOpAction.NO_OP;
	}
	
}
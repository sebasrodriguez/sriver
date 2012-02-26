package logic.actions;

/*
 * Clase abstracta
 */
public abstract class Action {

	String actionType;
	/*
	 * Constructor
	 */
	public Action(String actionType){
		this.actionType = actionType;
		
	}	
	
	/*
	 * Getters
	 */
	public String getActionType(){
		return this.actionType;
	}
}

package logic.actions;

import java.io.Serializable;

/*
 * Clase abstracta
 */
public abstract class Action implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String actionType;
	/*
	 * Constructor
	 */
	public Action(String actionType){
		this.actionType = actionType;
		
	}	
	
	/*
	 * Devuelve el tipo del action
	 */
	public String getActionType(){
		return this.actionType;
	}
}

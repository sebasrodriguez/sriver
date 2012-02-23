package webservice;

import logic.Facade;
import entities.Coordinate;

public class SilverRiverService {	

	public int getTest(){
		return Facade.getInstance().GetTest();
	}
	
	public Coordinate getCoordinate(){
		return new Coordinate(2, 5);
		
	}
}

package Battleship;

public class Computer {
	private int whatToDo = 0;
	private int tempL = 0;
	String name = "DEFULT USER";
	void askComp(String s){
		whatToDo = orders(s);
	}
	String sendInput(){
		if(whatToDo == 1){
			return placeShip();
		}
		return "PANIC!";
	}
	String placeShip(){
		//TODO Create a more sophisticated Decision tree to place ship
		return "1 1 2 1";
	}
	
	int orders(String s){
		if(s.contains("place") && s.contains("length")){
			s = s.replaceAll("[^0-9]+", " ");
			s = s.trim();
			s = Character.toString(s.charAt(0));
			tempL = Integer.parseInt(s);
			return 1;
		}
		return 0;
	}
}

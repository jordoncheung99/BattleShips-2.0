package Battleship;

public class Ship {
	//[section], [0] = x [1] = y [2] = hit
	private int [][]shipArry;
	private int ID;
	private boolean sunk;
	Ship(){
		ID = -1;
		sunk = false;
	}
	void setShip(int id, int[][] s){
		ID = id;
		shipArry = s;
	}
	void checkSunk(){
		int counter = 0;
		for(int x = 0; x < shipArry.length; x++){
			if(shipArry[x][2] == 1){
				counter++;
			}
		}
		if (counter == shipArry.length){
			sunk = true;
		}
	}
	int getShipID(){
		return ID;
	}
}

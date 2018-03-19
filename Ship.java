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
	private void checkSunk(){
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
	void isHit(int x, int y){
		for(int i=0; i<shipArry.length;i++){
			if(shipArry[i][0] == x && shipArry[i][1] == y){
				shipArry[i][2] = 1;
			}
		}
		checkSunk();
	}
	boolean getSunk(){
		return sunk;
	}
	int getShipID(){
		return ID;
	}
}

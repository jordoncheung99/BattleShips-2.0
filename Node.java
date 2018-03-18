package Battleship;

public class Node {
	int xpos;
	int ypos;
	int shipID;
	boolean isShot;
	Node(int x,int y, int id, boolean s){
		xpos = x;
		ypos = y;
		shipID = id;
		isShot = s;
	}
	Node(){
		xpos = -1;
		ypos = -1;
		shipID = 0;
		isShot = false;
	}
	//Obsolete delete?
	void setCord(int x, int y){
		xpos = x;
		ypos = y;
	}
	void setShipID(int ID){
		shipID = ID;
	}
	
	void setIsShot(boolean S){
		isShot = S;
	}
	
	boolean GetIsShot(){
		return isShot;
	}
	int getShipID(){
		return shipID;
	}
	//not Used so far Delete?
	int[] getPos(){
		int[] a = {xpos,ypos};
		return a;
	}
}

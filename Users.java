package Battleship;

public class Users {
	boolean isHuman;
	Player P;
	Computer C;
	Users(boolean human){
		isHuman = human;
		if(isHuman){
			P = new Player();
		}else{
			C = new Computer();
		}
	}
	boolean getIsHuman(){
		return isHuman;
	}
	void askUser(String s){
		if(isHuman){
			P.askPlayer(s);
		}else{
			C.askComp(s);
		}
	}
	String getInput(){
		if(isHuman){
			return P.sendInput();
		}else{
			return C.sendInput();
		}
	}

}

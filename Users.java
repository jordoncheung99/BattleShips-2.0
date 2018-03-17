package BattleShip;

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
		P.askPlayer(s);
	}
	String getInput(){
		return P.sendInput();
	}

}

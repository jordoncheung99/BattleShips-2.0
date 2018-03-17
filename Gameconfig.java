package BattleShip;

public class Gameconfig {
	Board[] B;
	Users[] U;
	//defult 2 players
	Gameconfig(){
		U = new Users[2];
		U[0] = new Users(true);
		U[1] = new Users(true);
		 B = new Board[2];
		 B[0] = new Board(1);
		 B[1] = new Board(1);
	}
	//defult boards diffrent players
	Gameconfig(boolean P1Hum, boolean P2Hum){
		U = new Users[2];
		U[0] = new Users(P1Hum);
		U[1] = new Users(P2Hum);
		 B = new Board[2];
		 B[0] = new Board(1);
		 B[1] = new Board(1);
	}
	void setUp(){
		AskName();
	}
	
	void AskName(){
		for(int i = 0; i < 2; i++){
			if(U[i].getIsHuman()){
				U[i].askUser("What is your name?");
				U[i].P.setName(U[i].getInput());
				System.out.println("Name set to: " + U[i].P.name);
			}
		}
	}
}

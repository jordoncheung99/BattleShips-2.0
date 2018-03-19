package Battleship;

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
		//U is the play index = palyer number + 1(IE index 0 is player 1)
		U = new Users[2];
		U[0] = new Users(P1Hum);
		U[1] = new Users(P2Hum);
		//Board 0 is for first player
		B = new Board[2];
		B[0] = new Board(1);
		B[1] = new Board(1);
	}
	//setup of the game
	void setUp(){
		AskU2();
		AskName();
		AskBoard();
		AskShipPlace();
	}
	void AskShipPlace(){
		//asks players for ship placement
		for(int i = 0; i < 2; i++){
			for(int j = 0; j < B[i].Ships.length; j++){
				do{
					try{
						U[i].askUser("Where would you like to place " + (j+2) + " length ship? Give answer in (x1 y1 x2 y2)");
						int[][] Ships = AskShipPlaceConvert(U[i].getInput(), j+2);
						if(Ships == null){
							U[i].askUser("That was not a valid input try 1 1 2 2");
						}else{
							B[i].checkShipPlace(Ships);
							break;
						}
					}catch(NumberFormatException E){
						U[i].askUser("That was not a valid input try '1 1 2 2'");
					}catch(ArrayIndexOutOfBoundsException E){
						U[i].askUser("That was not a valid input try '1 1 2 2'");
					};
				}while(true);
			}
		}
	}
	int[][] AskShipPlaceConvert(String s, int L){
		int[] cord = AskFitToArray(s, 4);
		int[][] shipParts = new int[L][2];
		if((cord[0]-cord[2] == 0) && (cord[1]-cord[3] == 0)){
			return null;
		}
		if(cord[0] == cord[2]){
			if(Math.abs(cord[1] - cord[3])+1 != L){
				return null;
			}else{
				for(int i = 0; i < L; i++){
					shipParts[i][0] = cord[0];
					if(cord[1] > cord[3]){
						shipParts[i][1] = cord[3]++;
					}else{
						shipParts[i][1] = cord[1]++;
					}
				}
			}
		}else if(cord[1] == cord[3]){
			if(Math.abs(cord[0] - cord[2])+1 != L){
				return null;
			}else{
				for(int i = 0; i < L; i++){
					shipParts[i][1] = cord[1];
					if(cord[0] > cord[2]){
						shipParts[i][0] = cord[2]++;
					}else{
						shipParts[i][0] = cord[0]++;
					}
				}
			}
		}
		return shipParts;
	}
	void AskU2(){
		U[0].askUser("Is player 2 a human?");
		String s = U[0].getInput();
		if(s.equalsIgnoreCase("Yes")){
			U[1] = new Users(true);
		}else{
			U[1] = new Users(false);
		}
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
	void AskBoard(){
		int x =0;
		int y =0;
		//ask the user for the size of the board
		do{
		U[0].askUser("How big do you want your board to be, Enter X and Y ('0 0' for defult)");
			//if only x or y = 0 / Out of bounds / Not ints it will out put error message and ask user again
			try{
				String S = U[0].getInput();
				String[] s = S.split(" ");
				x = Integer.parseInt(s[0]);
				y = Integer.parseInt(s[1]);
				if(x == 0 && y == 0){
					B[0] = new Board();
					B[1] = new Board();
					break;
				}else if(x == 0 || y == 0 || (x < 2 && y < 2)){
					U[0].askUser("That is not a valid input!");
				}else{
					break;
				}
			}catch(NumberFormatException E){
				U[0].askUser("That was not a valid input try '10  10'");
			}catch(ArrayIndexOutOfBoundsException E){
				U[0].askUser("That was not a valid input try '10  10'");
			};
		}while(true);
		//ask users for number ammount of length of ships (ie 4 2 lengths)
		//TODO high level check: Can the ammount of ships they want be placed on the board?
		if(x != 0){
			int[] shipL = new int[4];
			for(int i = 0; i < 4; i++){
				do{
					try{
						U[0].askUser("How many " + (i+2)+ " length ships do you want?");
						shipL[i] = Integer.parseInt(U[0].getInput());
						break;
					}catch(NumberFormatException E){
						U[0].askUser("That's not a valid input, try (1)");
					}
				}while(true);
			}
			B[0] = new Board(x,y,shipL);
			B[1] = new Board(x,y,shipL);
		}
	}
	//Play Game
	void play(){
		do{
			for(int i =0; i < 2; i++){
				playDisplay(i);
				playGetCord(i);
			}
		}while(true);
	}
	void playDisplay(int i){
		int tempI;
		if(i == 0){
			tempI = 1;
		}else{
			tempI = 0;
		}
		U[i].askUser("This is your board");
		B[i].display(false);
		U[i].askUser("This is your oppent's board");
		B[tempI].display(true);
	}
	void playGetCord(int i){
		int tempI;
		if(i == 0){
			tempI = 1;
		}else{
			tempI = 0;
		}
		U[i].askUser("Where would you like to attack?");
		int x;
		int y;
		do{
			try{
				int [] s = AskFitToArray(U[i].getInput(), 2);
				x = s[0];
				y = s[1];
				if(B[tempI].beenShot(x, y)){
					U[i].askUser("You can not shoot there!");
				}else{
					U[i].askUser(B[tempI].shot(x, y));
					break;
				}
			}catch(NumberFormatException E){
				U[0].askUser("That was not a valid input try '10  10'");
			}catch(ArrayIndexOutOfBoundsException E){
				U[0].askUser("That was not a valid input try '10  10'");
			};
		}while(true);
		
	}
	//Create a function to -1 to nessary inputs
	int[] AskFitToArray(String s, int size){
		int[] a = new int[size];
		String[] parts = s.split(" ");
		for(int i = 0; i < parts.length; i++){
			a[i] = Integer.parseInt(parts[i]);
		}
		for(int i =0; i < a.length; i++){
			a[i] -= 1;
		}
		return a;
	}


}

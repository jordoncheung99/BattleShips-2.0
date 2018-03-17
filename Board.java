package BattleShip;

public class Board {
	Node[][] Grid;
	Ship[] Ships;
	//defult
	Board(){
		Grid = new Node[10][10];
		Ships = new Ship[5];
		initializeGrid();
	}
	Board(int i){
		//TEST CASE
		Grid = new Node[10][10];
		Ships = new Ship[1];
		initializeGrid();
		int[][] s = new int[2][3];
		s[0][0] = 4;
		s[0][1] = 4;
		s[0][2] = 0;
		s[1][0] = 5;
		s[1][1] = 4;
		s[1][2] = 1;
		placeShip(s);
		Grid[5][4].setIsShot(true);
	}
	
	//Variable Sized
	Board(int sizeX, int sizeY, int s){
		Grid = new Node[sizeX][sizeY];
		//s is number of ships
		Ships = new Ship[s];
		initializeGrid();
	}
	
	
	private void initializeGrid(){
		for(int x = 0; x < Grid.length; x++){
			for(int y = 0; y< Grid[1].length; y++){
				Grid[x][y] = new Node(x,y,0,false);
			}
		}
		for(int x = 0; x < Ships.length; x++){
			Ships[x] = new Ship();
		}
	}
	
	//prob change to a return type
	void checkShipPlace(int[][] sArry){
		boolean valid = true;
		for(int x = 0; x < sArry.length; x++){
			try{
			if(Grid[sArry[x][0]][sArry[x][1]].getShipID() != 0){
				valid = false;
			}
			}catch(IndexOutOfBoundsException e){
				valid = false;
			}
		}
		if(valid = true){
			placeShip(sArry);
		}
	}
	
	void placeShip(int[][] sArry){
		//finds then places the closes empty ship
		for(int x = 0; x < Ships.length; x++){
			if(Ships[x].getShipID() == -1){
				Ships[x].setShip(x+1,sArry);
				for(int i = 0; i < sArry.length; i++){
					Grid[sArry[i][0]][sArry[i][1]].setShipID(x+1);
				}
				x = Ships.length;
			}
		}
	}
	
	void display(boolean partial){
		for(int y = 0; y <Grid.length; y++){
			for(int x =0; x < Grid[1].length; x++){
				if(Grid[x][y].getShipID() == 0){
					if(Grid[x][y].GetIsShot()){
						System.out.print("M");
					}else{
						System.out.print("O");
					}
				}else{
					if(Grid[x][y].GetIsShot()){
						System.out.print("H");
					}else if(partial){
						System.out.print("O");
					}else{
						System.out.print(Grid[x][y].getShipID());
					}
				}
				System.out.print(" ");
			}
			System.out.println(" ");
		}
		System.out.println(" ");
	}
}

package Battleship;

import java.util.Scanner;
public class Player {
	Scanner scan = new Scanner(System.in);
	String name;
	Player(){
		name = "Defult_User";
	}
	void askPlayer(String s){
		System.out.println(s);
	}
	String sendInput(){
		return scan.nextLine();
	}
	void setName(String s){
		name = s;
	}
}

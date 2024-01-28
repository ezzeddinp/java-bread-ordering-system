package main;

import java.util.*;

public class Main {
	static Scanner sc = new Scanner(System.in); 
	static ArrayList<Bread> breadList = new ArrayList<>();
	
	public void sortBreads() {
		for(int i = 0; i < breadList.size(); i++) {
			for(int j = 1; j < breadList.size() - i; j++) {
				if(breadList.get(j-1).name.compareTo(breadList.get(j).name) > 0) {
					Bread temp = breadList.get(j-1);
					breadList.set(j-1, breadList.get(j));
					breadList.set(j, temp);
				}
			}
		}
	}
	
	public void viewBread() {
		for(Bread b : breadList) {
			System.out.println("\nid " + b.id);
			System.out.println("name " + b.name);
			System.out.println("type " + b.type);
			System.out.println("price " + b.price);
		}
	}
	
	public void deleteBread() {
		if(breadList.size() > 0) {
			String remCust;
			System.out.println("Delete by Name: ");
			remCust = sc.nextLine();
			
			boolean found = false;
			for(int i = 0; i < breadList.size(); i++) {
				if(remCust.equals(breadList.get(i).name)) {
					breadList.remove(i);
					found = true;
					System.out.println(remCust + " successfully deleted..");
					break;
				}
				
			}
			if(!found) {
				System.out.println(remCust + " not found in the list");
			}
			for(Bread b : breadList) {
				System.out.println("\nid " + b.id);
				System.out.println("name " + b.name);
				System.out.println("type " + b.type);
				System.out.println("price " + b.price);
			}
		} else {
			System.out.println("No data to be deleted!");
		}
	}

	public Main() {
		int input = 0;
		do {
			System.out.println("Bread Menu");
			System.out.println("==============");
			System.out.println("1. Order Bread");
			System.out.println("2. View Bread");
			System.out.println("3. Delete Bread");
			System.out.println("4. Exit");
			System.out.print(">>");
			input = sc.nextInt(); sc.nextLine();
			
			switch(input) {
			case 1:
				String name;
				do {
					System.out.println("Enter bread name [Shorter than 3 words]: ");
					name = sc.nextLine();
				} while(!(alpNum(name)));
				
				String type = null;
				do {
					System.out.println("Enter bread type [Regular|Croissant|Baguette]: ");
					type = sc.nextLine();
				} while(!(type.equals("Regular") || type.equals("Croissant") || type.equals("Baguette")));
				
				double price = 0;
				do {
					System.out.println("Enter bread price [1000 - 100000 inclusive]: ");
					price = sc.nextInt(); sc.nextLine();
				} while(!(price >= 1000 && price <= 100000));
				
				//ID
				int ranID = new Random().nextInt(1000);
				String id = String.format("BR%03d", ranID);
				
				breadList.add(new Bread(id, name, type, price));
				sortBreads();
				break;
				
			case 2:
				sortBreads();
				viewBread();
				sc.nextLine(); // agar berhenti dlu
				break;
				
			case 3:
				deleteBread();
				sortBreads();
				sc.nextLine();
				break;
			}
		} while(!(input == 4));
	}

	public static void main(String[] args) {
		new Main();
	}
	
	public static boolean alpNum(String input) {
		boolean containsLetter = false;
		boolean containsDigit = false;
		boolean containsUnique = true;
		
		if(input.equals(input.toUpperCase())) {
			return true;
		}
		
		for(char a : input.toCharArray()) {
			if(Character.isLetter(a)) {
				containsLetter = true;
			}
			else if(Character.isDigit(a)) {
				containsDigit =  true;
			}
			if(containsLetter && containsDigit) {
				return true;
			}
		}
		
		for(Bread names : breadList) {
			if(input.matches(names.name))
				System.out.println("the name was taken before..");
				containsUnique = false;
				break;
		}
		return containsLetter && containsDigit && containsUnique;
	}

}

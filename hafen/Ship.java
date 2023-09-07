package hafen;

import java.util.Arrays;
import gui.GUI;


public class Ship {
	private String name;
	private double maxWeight;	
	private Container[] loadedContainers;
	private String destination;
	private int crewMember;
	// Das folgende Attribut dient nur zu Testzwecken
	private Container testcontainer = new Container(1000, 1000.6);


	public Ship(String name, double maxWeight, String destination, int crewMember) {
		this.name = name;
		this.maxWeight = maxWeight;
		this.destination = destination;
		loadedContainers = new Container[6];
		testcontainer.setDestination("Hamburg");
		testcontainer.setLoadedProduct("LEGO Sets");
		this.crewMember = crewMember;

	}

	// TODO: automatisch getters und setters generieren lassen (Source -> Generate getters and setters)
	// Alle getters generieren und überlegen, welche setters man braucht


	public int getCrewMember() {
		return crewMember;
	}

	public void hireCrewMember(int numberCrewMate) {
		crewMember += numberCrewMate;

	}




	public void fireOneCrewMember() {		
		if(crewMember >0) {
			crewMember =-1;
		}else
			System.out.println("Du hast keine Crew members die du feuern kannst");
	}

	public boolean unloadContainer(int position) {
		if(position < loadedContainers.length && position >= 0 && loadedContainers[position] != null)  {
			loadedContainers[position] = null;
			return true;
		}

		return false;
	}


	public double calculateCurrentWeight() {

		double weight = 0;

		for(int i =0; i <loadedContainers.length; i++) {

			Container currenContainer = loadedContainers[i];
			if (currenContainer != null){
				weight += currenContainer.getLoadedWeight();
			}
		}
		return weight;
	}

	public int numberOfFirstEmptyPosition() {
		for(int i =0; i <loadedContainers.length; i++) {

			Container currenContainer = loadedContainers[i];
			if (currenContainer == null){

				return i;
			}
		}

		return -1;
	}

	public int numberOFEmptyPosition() {
		int result = 0;
		for(int i =0; i <loadedContainers.length; i++) {

			Container currenContainer = loadedContainers[i];
			if (currenContainer == null){

				result ++;
			}
		}
		return result;
	}

public boolean checkIfFits(Container newContainer) {

		double currentWeight = maxWeight -= calculateCurrentWeight();

		if(newContainer.getLoadedWeight()<= currentWeight && numberOfFirstEmptyPosition() != -1) {
			return true;
		}else
			return false;
	}

	public void load(Container newContainer) {
		if(checkIfFits(newContainer) == true) {


			int i1 = numberOfFirstEmptyPosition();
			loadedContainers[i1] = newContainer;

		}else {
			System.out.println("kein Platz mehr");
		}
	}


	public void tauschen(int index1, int index2 ) {
		Container x = loadedContainers[index1];
		Container y = loadedContainers[index2];

		loadedContainers[index2] = x;
		loadedContainers[index1]=y;


	}
	public void durchlaufen() {

		for (int i = 0; i<loadedContainers.length;i++) {
			Container currenContainer = loadedContainers[i];
			Container nextContainer = loadedContainers[i+1];

			int i2 =	numberOFEmptyPosition();

			for (int j = 0; i2>0; j++) {

				i2--;
				if(numberOfFirstEmptyPosition() !=-1) {
					tauschen(i, numberOfFirstEmptyPosition());
				}
			}

				if (currenContainer.getLoadedWeight() < nextContainer.getLoadedWeight()) {
					tauschen(i, i-1);
				

			}
		}
	}
	public String getDestination() {
		return destination;
	}

	public void sort() {
		for (int i = 0; i < loadedContainers.length-1; i++) {
			durchlaufen();
		}
	}


	@Override
	public String toString() {
		return name + ", maxWeight " + maxWeight + "t, Crew "
				// + numberOfCrewMembers
				+ ", goes to " + destination;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	 * Hilfsmethode zum Ausgeben der Ladung
	 */
	public void printLoad() {
		for(int i=0; i< loadedContainers.length; i++) {
			System.out.println(loadedContainers[i]);
		}
	}
	
	

	/*
	 * Hilfsmethode zum Erzeugen einer Beispielbeladung
	 */
	private void createExampleoad() {
		Container c1 = new Container(24.2, 12.3);
		c1.setDestination("Hamburg");
		c1.setLoadedProduct("Oil");
		Container c2 = new Container(23.2, 23.2);
		c2.setDestination("Lisbon");
		c2.setLoadedProduct("Computer chips");
		Container c3 = new Container(15.0, 4.2);
		c3.setDestination("Antwerpen");
		c3.setLoadedProduct("Hop");
		loadedContainers[0] = c1;
		loadedContainers[2] = c2;
		loadedContainers[5] = c3;
	}


	public static void main(String[] args) {
		Ship ship = new Ship("Floating Sibi", 111.2, "Lisbon",10);
		ship.createExampleoad();
		new GUI(ship);
	}

}

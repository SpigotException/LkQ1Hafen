package hafen;

import java.util.Random;

import linear.QueueWithViewer;
import linear.StackWithViewer;
import gui.GUI;



public class Harbour {
	private String country;
	private String city;
	private Ship[] shipsAtAnchor;
	private StackWithViewer<Container> containerStack;
	private StackWithViewer<Container> containerStack1;
	private StackWithViewer<Container> containerStackForSort;
	private QueueWithViewer<Ship> waitingline;

//Fff
	// das folgende Attribut ist nur zu Testzwecken vorhanden, es muss ansonsten nicht beachtet werden
	Ship testship = new Ship("Testship I", 187.45, "Hamburg",10);


	public Harbour(String country, String city, int size) {		
		this.country = country;
		this.city = city;
	
		this.shipsAtAnchor = new Ship[size];
		waitingline = new QueueWithViewer<Ship>();

		containerStack = new StackWithViewer<Container>();
		containerStack1 = new StackWithViewer<Container>();
		containerStackForSort = new StackWithViewer<Container>();
		fillCargoStack();
		testship.load(new Container(15.5, 13.3));
		testship.load(new Container(22.3, 0.2));	
		createline();

	}
	public void TestShips() {
		Ship s1 = new Ship("S1", 1000, "Lisbon", 1);
		shipsAtAnchor[00] = s1;
		Ship s2 = new Ship("S2", 1000, "Rotterdam", 1);
		shipsAtAnchor[01] = s2;
		Ship s3 = new Ship("S3", 1000, "Shanghai", 1);
		shipsAtAnchor[02] = s3;
		Ship s4 = new Ship("S4", 1000, "Antwerpen", 1);
		shipsAtAnchor[03] = s4;
		Ship s5 = new Ship("S5", 1000, "Antwerpen", 1);
		shipsAtAnchor[04] = s5;
		
		
	}
	public void createline() {
		Ship s1 = new Ship("S1", 1000, "Lisbon", 1);
		Ship s2 = new Ship("S2", 1000, "Rotterdam", 1);
		Ship s3 = new Ship("S3", 1000, "Shanghai", 1);
		Ship s4 = new Ship("S4", 1000, "Antwerpen", 1);
		Ship s5 = new Ship("S5", 1000, "Antwerpen", 1);
		
		
		waitingline.enqueue(s1);
		waitingline.enqueue(s2);
		waitingline.enqueue(s3);
		waitingline.enqueue(s4);
		waitingline.enqueue(s5);
	
		
	}


	public void fillCargoStack() {
		Container c1 = new Container(34, 1);
		c1.setDestination("test");
		containerStack.push(c1);
		Container c2 = new Container(100, 100);
		c2.setDestination("Rotterdam");
		containerStack.push(c2);
		Container c3 = new Container(13, 145);
		c3.setDestination("Shanghai");
		containerStack.push(c3);
		Container c4 = new Container(113, 123);
		c4.setDestination("Shanghai");
		containerStack.push(c4);


	}

	public int CountContainerTo(String pString) {

		int result = 0;
		while (!containerStack.isEmpty()) {

			Container currentContainer = containerStack.top();
			containerStack1.push(currentContainer);
			if ( currentContainer.getDestination().equals(pString) ) {
				result ++;


			}containerStack.pop();


		}while (!containerStack1.isEmpty()) {
			Container currentContainer1 = containerStack1.top();
			containerStack.push(currentContainer1);
			containerStack1.pop();
		}return result;
	}

	public double CalculateCurrentContainerWeight() {

		double result = 0;
		while (!containerStack.isEmpty()) {

			Container currentContainer = containerStack.top();

			containerStack1.push(currentContainer);

			result +=	currentContainer.getLoadedWeight();
			containerStack.pop();
		}





		while (!containerStack1.isEmpty()) {
			Container currentContainer1 = containerStack1.top();
			containerStack.push(currentContainer1);
			containerStack1.pop();
		}

		return result;
	}
	public boolean LoadFromStack(){


		Container currentContainer = containerStack.top();



		for (int i = 0; i < shipsAtAnchor.length; i++) {

			Ship ship = shipsAtAnchor[i];
			if (ship!= null) {


				if (ship.getDestination().equals( currentContainer.getDestination() )) {
					ship.printLoad();

					System.out.println(ship.getDestination());

					ship.load(currentContainer);
					ship.printLoad();

					containerStack.pop();
					return true;
				}

			}
		}

		return false;


	}


	public Container findContainer(String pString,double weightLimit) {

		Container result = null;

		while (!containerStack.isEmpty()) {
			boolean found = false;
			Container currentContainer = containerStack.top();

			containerStack1.push(currentContainer);
			if ( currentContainer.getDestination().equals(pString) && currentContainer.getLoadedWeight() <= weightLimit && found ==false) {

				found = true;
				System.out.println(currentContainer);


				result = currentContainer;

			}containerStack.pop();


		}while (!containerStack1.isEmpty()) {
			Container currentContainer1 = containerStack1.top();
			containerStack.push(currentContainer1);
			containerStack1.pop();
		}return result;


	}



	public Container findHeaviestContainer() {

		Container result =containerStack.top() ;

		while (!containerStack.isEmpty()) {

			Container currentContainer = containerStack.top();

			if(currentContainer.getLoadedWeight() > result.getLoadedWeight()){

				result = currentContainer;
			}
			containerStack1.push(currentContainer);
			containerStack.pop();
		}

		while (!containerStack1.isEmpty()) {
			Container currentContainer1 = containerStack1.top();
			containerStack.push(currentContainer1);
			containerStack1.pop();

		}
		return result;

	}



	public void sortContainerStackE() {
		while(!containerStack.isEmpty()) {

			Container current= containerStack.top();
			Container heaviestContainer = findHeaviestContainer();
			System.out.println(heaviestContainer);
			if(current.getLoadedWeight() == heaviestContainer.getLoadedWeight()) {
				containerStackForSort.push(current);
				containerStack.pop();

			} else {

				containerStack1.push(current);
				containerStack.pop();

				if (containerStack.isEmpty()) {
					while(!containerStack1.isEmpty()) {
						containerStack.push(containerStack1.top());
						containerStack1.pop();

					}

				}
			}

		}containerStack.pop();

		while(!containerStackForSort.isEmpty()) {
			containerStack.push(containerStackForSort.top());
			containerStackForSort.pop();

		}
	}


	public void optimalVerladen(){
		
		while(!containerStack.isEmpty()) {

		for (int i = 0; i < shipsAtAnchor.length; i++) {
			Ship cuShip = shipsAtAnchor[i];
				
			if(!containerStack.isEmpty()) {
			Container Cucontainer= containerStack.top();
			
			
			if (cuShip != null&& Cucontainer!= null ) {
								
				if (Cucontainer.getDestination().equals(cuShip.getDestination())){
					
					if (cuShip.checkIfFits(Cucontainer) == true) {
						cuShip.load(Cucontainer);
						containerStack.pop();
						
					}else System.out.println(" kein platz");
				}else System.out.println("Kein schiff");
				
			}
//

		}
		}
	}
 }
	
	public double approxWaitingLine() {
		double result = 0;
		QueueWithViewer<Ship> tempQue = new QueueWithViewer<Ship>();
		
		while(!waitingline.isEmpty()) {
			result = result+2.5;
			tempQue.enqueue(waitingline.front());
			waitingline.dequeue();
			
		}	while(!tempQue.isEmpty()) {
	
			waitingline.enqueue(tempQue.front());
			tempQue.dequeue();
			
		}
	
		return result;
	}
	
	
	public int numberOfFirstEmptyPositionAtAnchor() {
		for(int i =0; i <shipsAtAnchor.length; i++) {
			
			System.out.println(shipsAtAnchor);

			Ship currenShip = shipsAtAnchor[i];
			if (currenShip == null){

				return i;
			}
		}

		return -1;
	}
	
	public Ship findShipInLine() {
		Ship result  = null;
		QueueWithViewer<Ship> tempQue = new QueueWithViewer<Ship>();
		while(waitingline.isEmpty() == false) {
			if(	waitingline.front().getDestination().equals(containerStack.top().getDestination())) {
				result = waitingline.front();
			}
			tempQue.enqueue(waitingline.front());
			waitingline.dequeue();

		}
		while(!tempQue.isEmpty()) {

			waitingline.enqueue(tempQue.front());
			tempQue.dequeue();

		}
		return result;

	}
	public void deletShip(String pname) {
		QueueWithViewer<Ship> tempQue = new QueueWithViewer<Ship>();
		while (waitingline.isEmpty() == false) {
			Ship ship = waitingline.front();

			tempQue.enqueue(ship);

			waitingline.dequeue();
		}
		while(!tempQue.isEmpty()) {
			if (!tempQue.front().getName().equals(pname)) {
				waitingline.enqueue(tempQue.front());
			}

			tempQue.dequeue();

		}
	}
	
	public void allEnterHarbour(){
		
		printShips();
		System.out.println("____________________________________________");
		while (waitingline.isEmpty() == false) {
			Ship ship = waitingline.front();
		

				if (numberOfFirstEmptyPositionAtAnchor() != -1) {
					shipsAtAnchor[this.numberOfFirstEmptyPositionAtAnchor()]= ship;
					waitingline.dequeue();
					
				}else
					System.out.println("kein Platz");
			
			
			
		}
		System.out.println("____________________________________________");
		printShips();
		System.out.println("____________________________________________");
	}
	
	
	public boolean toFront(String pname) {

			QueueWithViewer<Ship> tempQue = new QueueWithViewer<Ship>();
			while (waitingline.isEmpty() == false) {
				Ship ship = waitingline.front();

				tempQue.enqueue(ship);

				waitingline.dequeue();
			}
			while(!tempQue.isEmpty()) {
				if (!tempQue.front().getName().equals(pname)) {
					waitingline.enqueue(tempQue.front());
			
				}
				
				if(!tempQue.front().getName().equals(pname)) {
					tempQue.dequeue();
				}
					
			

			}
		
		
		return false;
		
	}

	public int countShipsInHarbour() {
		// TODO
		return -1;
	}



	public boolean leave(String leavingShipName) {
		// TODO
		return false;
	}

	public boolean arrive(Ship arrivingShip) {
		// TODO
		return false;
	}


	/*
	 * Hilfsmethode zum Ausgaben aller vor Anker liegenden Schiffe auf der Konsole
	 */
	public void printShips() {
		for (Ship ship : shipsAtAnchor) {
			System.out.println(ship);
		}
	}

	/*
	 * getters & setters
	 */

	public String getCountry() {
		return country;
	}

	public String getCity() {
		return city;
	}

	public Ship[] getShipsAtAnchor() {
		return shipsAtAnchor;
	}

	// ------------------------------------------------------------------

	/*
	 * Hilfsmethode zum Erzeugen der zu Beginn im Hamburger Hafen liegenden Schiffe 
	 */
	private void createAndStoreShips() {
		String[] shipNames = {"Titanic", "Queen Mary", "Bismarck", "Yamato", "Santa Maria",
				"USS Enterprise", "HMS Victory", "Endeavour", "Mayflower", "Fitzgerald",
				"Black Pearl", "Cutty Sark", "Golden Hind", "Nautilus", "Lusitania",
				"Constitution", "Discovery", "Nina", "Pinta", "Santa Clara"};
		String[] destinationNames = {"Rotterdam", "Lisbon", "Piräus", "Shanghai", "Los Angeles", "Guangzhou", "Antwerpen", "Le Havre", "Singapur"};
		String[] countryNames = {"Netherlands", "Portugal", "Greece", "China", "USA", "China", "Belgium", "France", "Singapur"};
		double maxWeightLimit = 250.0;
		Random random = new Random();



		/* Create Harbours --> Has to be moved to administration class for the harbours later
        Harbour[] destinations = new Harbour[destinationNames.length];
        for(int i=0; i<destinationNames.length; i++) {
        	destinations[i] = new Harbour(destinationNames[i], countryNames[i], 25);
        } */       

		// Create 20 ships for Hamburg
		for (int i = 0; i < 20; i++) {
			String name = shipNames[i];            
			double maxWeight = (double)(Math.round(100 * random.nextDouble() * maxWeightLimit)+5000)/100;
			if( maxWeight > maxWeightLimit) maxWeight = maxWeightLimit;

			int harbourNumber = random.nextInt(destinationNames.length);
			Ship ship = new Ship(name, maxWeight, destinationNames[harbourNumber],random.nextInt(10));

			int randomIndex;
			do {
				randomIndex = random.nextInt(shipsAtAnchor.length);
			} while (shipsAtAnchor[randomIndex] != null);

			shipsAtAnchor[randomIndex] = ship;
		}
	}

	/*
	 * main-Methode
	 */
	public static void main(String[] args) {
		Harbour theHarbour = new Harbour("Germany", "Hamburg", 30);
		//theHarbour.createAndStoreShips();
		theHarbour.TestShips();
		new GUI(theHarbour);

	}
}

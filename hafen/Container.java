package hafen;

public class Container {
	private double maxWeight, currentWeight;
	private String loadedProduct;
	private String destination;
	
	

	
		
	public Container(double maxWeight, double currentWeight) {
		super();
		
		this.maxWeight = maxWeight;
		this.currentWeight = currentWeight;
		this.loadedProduct = null;
		this.destination = null;
		
	}
	
	// TODO: Methode zum kompletten Leeren des Containers
		
	
	// TODO: Hilfsmethode zum Überprüfen, ob eine gewünschte Zuladung noch passt 
	
	// TODO: Methode zum Zuladen einer bestimmten Menge
	
		
	public double getMaxWeight() {
		return maxWeight;
	}
	

	
	public double getLoadedWeight() {
		return currentWeight;
	}

	public String getLoadedProduct() {
		return loadedProduct;
	}

	public String getDestination() {
		return destination;
	}
	
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public void setLoadedProduct(String loadedProduct) {
		this.loadedProduct = loadedProduct;
	}

	@Override
	public String toString() {
		return "Container - Max: " + maxWeight + "t, current: " + currentWeight + "t,  "
				+ loadedProduct + ", goes to " + destination;
	}
	
	
}

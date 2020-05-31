package org.insa.graphs.algorithm.utils;


import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;

public class Label  implements Comparable<Label> {
	private Node sommet_courant;
	private boolean marque;
	protected double cout;
	private Arc pere;
	
	public Label(Node sommet_courant) {
		this.sommet_courant = sommet_courant;
		marque = false;
		cout = Double.POSITIVE_INFINITY;
		pere = null;
	}
	
	public double getCost() {
		return this.cout;
	}
	
	public double getTotalCost() {
		return this.getCost();
	}
	
	public Node getNode() {
		return this.sommet_courant;
	}
	
	public Arc getFather() {
		return this.pere;
	}
	
	public boolean isMarked() {
		return marque;
	}
	
	public void setFather(Arc pere) {
		
		this.pere = pere;
	}
	
	public void mark() {
		this.marque = true;
	}
	
	public void setCost(double cout) {
		this.cout = cout;
	}
	
	/* Compare les Labels selon leur coût */
	public int compareTo(Label autre) {
		int resultat;
		if (this.getTotalCost() < autre.getTotalCost()) {
			resultat = -1;
		}
		else if (this.getTotalCost() == autre.getTotalCost()) {
			resultat = 0;
		}
		else {
			resultat = 1;
		}
		return resultat;
	}
}


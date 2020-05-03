package org.insa.graphs.algorithm.utils;

import org.insa.graphs.model.Node;

public class Label {
	
	private boolean mark;
	private int cost;
	private Node father;
	private Node nodes;
	
	public Label(Node noeud) {
		this.nodes=noeud;
		this.mark=false;
		this.cost=100000;
		this.father=null;
	}
		
	public int getCost() {
		return this.cost;
	}
	
	public boolean getMark() {
		return this.mark;
	}
	
	public Node getfather() {
		return this.father;
	}
	
	public boolean setMark() {
		return this.mark=true;
	}
	
	
	
	
}

package org.insa.graphs.algorithm.utils;

import org.insa.graphs.model.Node;
import org.insa.graphs.model.Point;
import org.insa.graphs.algorithm.AbstractInputData;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;

public class LabelStar extends Label {
	private float inf;
	
	public LabelStar(Node sommet_courant, Node node_dest, ShortestPathData data) {
		super(sommet_courant);	

		if (data.getMode() == AbstractInputData.Mode.LENGTH) {
			this.inf = (float)Point.distance(sommet_courant.getPoint(),data.getDestination().getPoint());
		}
		else {
			int vitesse = Math.max(data.getMaximumSpeed(), data.getGraph().getGraphInformation().getMaximumSpeed());
			this.inf = (float)Point.distance(sommet_courant.getPoint(),data.getDestination().getPoint())/(vitesse*1000.0f/3600.0f);
		}
	}

	/* Renvoie le coût de l'origine jusqu'au noeud + coût à vol d'oiseau du noeud jusqu'à la destination */
	public double getTotalCost() {
		return this.inf+this.cout;
	}


}

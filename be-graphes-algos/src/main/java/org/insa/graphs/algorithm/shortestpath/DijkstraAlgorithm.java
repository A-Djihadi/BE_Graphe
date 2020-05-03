package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.algo.AbstractSolution.Status;
import org.insa.algo.utils.*;
import org.insa.graph.*;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        ShortestPathSolution solution = null;
        
        			// Initialisation
    	// du graphe
    	final ShortestPathData data = getInputData();
        Graph graph = data.getGraph();
        final int nbNodes = graph.size();
        // des couts 
        double[] distances = new double[nbNodes];
        Arrays.fill(distances, Double.POSITIVE_INFINITY);
        distances[data.getOrigin().getId()] = 0;
        
        // Notify observers about the first event (origin processed).
        notifyOriginProcessed(data.getOrigin());
        
        // Initialize array of predecessors.
        Arc[] predecessorArcs = new Arc[nbNodes];
        

        while (solution == null) { //Tant qu'il y a pas de solution 
        	
        }
        // TODO:
        return solution;
    }

}

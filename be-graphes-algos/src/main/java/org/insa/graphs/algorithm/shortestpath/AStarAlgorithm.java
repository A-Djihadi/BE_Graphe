package org.insa.graphs.algorithm.shortestpath;

public class AStarAlgorithm extends DijkstraAlgorithm {

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }
    /*Réécriture de la méthiode newLabel*/
    /*Afin d'utiliser LabelSatr au lieu de Label dans l'algo*/
    protected Label Label (Node node, ShortestpathData data) {
    	return new LabelSatr(node,data);
    }
    

}

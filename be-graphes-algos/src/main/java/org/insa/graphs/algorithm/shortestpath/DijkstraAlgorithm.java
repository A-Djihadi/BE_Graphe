package org.insa.graphs.algorithm.shortestpath;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import org.insa.graphs.algorithm.AbstractInputData;
import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.algorithm.utils.Label;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;


public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
    	
    	// Récupération des différentes données du graph
    	
        ShortestPathData data = getInputData();
        Graph graph = data.getGraph();
        List<Node> nodes = graph.getNodes();
        final int nbNodes = graph.size();
        
        //On récupère l'index du node origine du chemin à déterminer
        int index_origine = data.getOrigin().getId();
        //On récupère l'index du node destination
        int index_dest = data.getDestination().getId();
        
        notifyOriginProcessed(data.getOrigin());

// -----------------initialisation-------------------------------------------------

        BinaryHeap<Label> tas = new BinaryHeap<Label>();
        
        ArrayList<Label> labels = new ArrayList<Label>();
        
        //On initialise tous les labels à +infini, avec marque à false et pere à NULL
        for (int i = 0; i < nbNodes; i++) {
        	labels.add(new Label(nodes.get(i)));
        }
        //On actualise le cout du label correspondant au node d'origine
        labels.get(index_origine).setCost(0);
        //On insère le label actualisé dans le tas
        tas.insert(labels.get(index_origine));

//--------------------Boucle d'iteration-------------------------------------------
        
        //Définition d'une variable pour compter le nombre d'itérations pour le debogage
        int nbIterations = 0;
        
        while (!labels.get(index_dest).isMarked() && tas.size() != 0) {
        	//On récupère le label minimal dans le tas
        	Label label_min = tas.deleteMin();
        	//On marque le label minimal
        	labels.get(label_min.getNode().getId()).mark();
        	
        	//Vérification du coût croissant des labels marqués
        	System.out.println("Coût du label marqué : " + label_min.getCost());
        	//Vérification de la taille du tas
        	System.out.println("Taille du tas : " + tas.size());
        	
        	//Sortie debogueur 
        	
        	//Incrémentation du nombre d'itérations
        	nbIterations++;
        	//Verification du tas
        	if (tas.isValid()) {
        		System.out.println("Tas valide");
        	}
        	else {
        		System.out.println("Tas non valide");
        	}
        	
        	//On récupère les arcs successeurs du label minimal
        	List<Arc> arcs = label_min.getNode().getSuccessors();
        	
        	//Sortie debogueur
        	System.out.println("Nb successeurs du label : " + arcs.size());
        	
        	for (int i = 0; i < arcs.size(); i++) {
        		//On vérifie que le chemin est autorisé
                if (!data.isAllowed(arcs.get(i))) {
                    continue;
                }
                //On récupère l'index de la destination de l'arc actuel
        		int index_suiv = arcs.get(i).getDestination().getId();
        		
        		if (!labels.get(index_suiv).isMarked())
        		{
        			double oldDistance = labels.get(index_suiv).getCost();
        			double newDistance = label_min.getCost() + data.getCost(arcs.get(i));
        			
        			//Coloration des chemins au fur et à mesure
        			if (Double.isInfinite(oldDistance) && Double.isFinite(newDistance)) {
                        notifyNodeReached(arcs.get(i).getDestination());
                    }
        			
        			if (newDistance < oldDistance) {
        				labels.get(index_suiv).setCost(newDistance);
        				labels.get(index_suiv).setFather(arcs.get(i));
        				if (Double.isFinite(oldDistance)) {
        					tas.remove(labels.get(index_suiv));
        				}
        				tas.insert(labels.get(index_suiv));
        			}
        		}
        	}
        }
        
//-------------------On crée notre solution -------------------------------------
        ShortestPathSolution solution = null;
        
        //La destination n'a pas de prédécesseur, le chemin est infaisable
        if (!labels.get(index_dest).isMarked()) {
            solution = new ShortestPathSolution(data, Status.INFEASIBLE);
        }
        else {

            //La destination a été trouvée. On en informe l'utilisateur.
            notifyDestinationReached(data.getDestination());

            //On crée un nouveau chemin à partir des prédécesseurs
            ArrayList<Arc> chemin = new ArrayList<>();
            Arc arc = labels.get(index_dest).getFather();
            while (arc != null) {
                chemin.add(arc);
                arc = labels.get(arc.getOrigin().getId()).getFather();
            }
            
            //Affichages pour le debogage
            System.out.println("Nombre d'itérations : " + nbIterations);
            System.out.println("Nombre d'arcs dans le plus court chemin : " + chemin.size());
            
            //On inverse ce chemin
            Collections.reverse(chemin);

            //On crée la solution finale
            solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, chemin));
            
            //Debogage
            if (!solution.getPath().isValid()) {
            	System.out.println("Chemin trouvé non valide.");
            }
            else {
            	System.out.println("Chemin trouvé valide.");
            }
            if (data.getMode() == AbstractInputData.Mode.TIME) {
            	System.out.println("Durée chemin Path : " + solution.getPath().getMinimumTravelTime() + ", Dijkstra : " + labels.get(index_dest).getCost());
            }
            else {
            	System.out.println("Longueur chemin Path : " + solution.getPath().getLength() + ", Dijkstra : " + labels.get(index_dest).getCost());
            }
            
        }
        return solution;
    }
   

}

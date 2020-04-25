package it.polito.tdp.seriea.model;

import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.seriea.db.SerieADAO;

public class Model {
private List<Team> listaT ;
private Map <Integer, Annata> mappaA;
private List <Annata> listaA;
private SerieADAO dao;
private Graph<Annata, DefaultWeightedEdge> grafo;
public Model() {
	listaT= new LinkedList <>();
	dao = new SerieADAO();
}
public List<Team> getTeam(){
	return dao.listTeams();
}
public List <Annata> getAnnate (Team team){
	mappaA = new HashMap <Integer, Annata>();
	mappaA = dao.getAnnate(team, mappaA);
	listaA = new LinkedList <Annata>(mappaA.values());
	Collections.sort(listaA);
	return listaA;
}
public void creaGrafo(Team team) {
	grafo = new DefaultDirectedWeightedGraph<Annata, DefaultWeightedEdge>(DefaultWeightedEdge.class);
    Graphs.addAllVertices(grafo, listaA);
    for (Annata a1: listaA) {
    	for (Annata a2 : listaA) {
    		if ( a1.getPunteggio()<a2.getPunteggio()) {
    			Graphs.addEdgeWithVertices(grafo, a1, a2, a2.getPunteggio()-a1.getPunteggio());
    		}
    		
    	}
    }
    System.out.println("vertici : " + grafo.vertexSet().size()+";");
    System.out.println("edge : " + grafo.edgeSet().size()+";");
//    System.out.println(grafo.edgeSet().toString());
}
public String annataDoro() {
	int Max=0;
	Annata annata = null;
	for (Annata a : grafo.vertexSet()) {
		int ent =0;
		for (Annata p : Graphs.predecessorListOf(grafo, a)) {
			ent+=p.getPunteggio();
		}
		int usc=0;
		for (Annata u : Graphs.successorListOf(grafo, a)) {
			usc+=u.getPunteggio();
		}
		int diff = ent-usc;
		if (diff>Max) {
			Max=diff;
			annata = a;
		}
		
	}
	if (annata!= null)
	return "l' annata d'oro è : "+annata.getAnnata()+" e il valore è : "+ Max;
	return "ha partecipato solo ad una stagione";
}







}

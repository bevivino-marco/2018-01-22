package it.polito.tdp.seriea.model;

import java.util.LinkedList;
import java.util.List;

public class TestModel {

	public static void main(String[] args) {
        Model m = new Model();
        Team team = m.getTeam().get(23);
        List <Annata> l = new LinkedList <Annata>(m.getAnnate(team));
        System.out.println("le annate della squadra :"+ team.getTeam()+" sono :\n"+ l.toString());
	    m.creaGrafo(team);
	    System.out.println(m.annataDoro());
	    System.out.println(m.camminoV(team));
	}

}

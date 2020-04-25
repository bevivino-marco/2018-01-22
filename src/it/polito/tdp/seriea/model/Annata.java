package it.polito.tdp.seriea.model;

public class Annata implements Comparable <Annata>{
private int annata;
private int punteggio;
public Annata(int annata) {
	super();
	this.annata = annata;
	this.punteggio = 0;
}
public int getAnnata() {
	return annata;
}
public void setAnnata(int annata) {
	this.annata = annata;
}
public int getPunteggio() {
	return punteggio;
}
public void setPunteggio(int punteggio) {
	this.punteggio += punteggio;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + annata;
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Annata other = (Annata) obj;
	if (annata != other.annata)
		return false;
	return true;
}
@Override
public String toString() {
	return String.format("\nanno=%s , punteggio=%s", annata, punteggio);
}
@Override
public int compareTo(Annata a) {
	// TODO Auto-generated method stub
	return this.annata-a.getAnnata();
}

}

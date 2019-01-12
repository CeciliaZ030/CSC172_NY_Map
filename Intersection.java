/*
Project 4
Junkang Gu
Class ID:72
Net ID:jgu8
Email:jgu8@u.rochester.edu

Partner:
Name:Jiajiang Yang
Class ID:63
Net ID:jyang80
Email:jyang80@u.rochester.edu
 */
public class Intersection {
	protected String id;
	protected double latitude;
	protected double longitude;
	protected boolean visited;
	protected double distance;
	protected Intersection previous;
	
	public Intersection(String id, double latitude, double longitude) {
		this.id=id;
		this.latitude=latitude;
		this.longitude=longitude;
	}
}



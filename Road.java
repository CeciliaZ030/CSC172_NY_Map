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
public class Road {
	protected String roadID;
	protected Intersection node1;
	protected Intersection node2;
	protected double distance;
	
	public Road(String a, Intersection node1, Intersection node2) {
		this.roadID=a;
		this.node1=node1;
		this.node2=node2;
	}
	

}



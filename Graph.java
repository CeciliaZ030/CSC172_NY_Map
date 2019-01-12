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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Graph {
	protected ArrayList<Intersection> nodes;
	protected HashMap<Intersection,ArrayList<Intersection>> adjList;
	protected HashMap<String,Intersection> a;
	protected double totalDistance;
	public Graph(){
		adjList=new HashMap<Intersection,ArrayList<Intersection>>();
		nodes=new ArrayList<Intersection>();
		a=new HashMap<String,Intersection>();
	}
	
	public void addIntersection(Intersection x) {
		if(!nodes.contains(x)) {
		nodes.add(x);
		adjList.put(x, new ArrayList<Intersection>());}
	}
	
	public void addRoad(Road x) {
		x.distance=findDistance(x.node1,x.node2);
		if(!adjList.get(x.node1).contains(x.node2)) 
			adjList.get(x.node1).add(x.node2);
		if(!adjList.get(x.node2).contains(x.node1))
		adjList.get(x.node2).add(x.node1);
	}
	
	public double findDistance(Intersection a, Intersection b) {
		return calculateDistance(a.latitude,a.longitude,b.latitude,b.longitude);
	}
	
	public static double calculateDistance(double ax,double ay, double bx, double by) {
		
		ax=(ax*Math.PI/180);
		bx=(bx*Math.PI/180);
		ay= (ay*Math.PI/180);
		by=(by*Math.PI/180);
		double x= 6356.752*2*Math.asin(Math.sqrt(       Math.pow( Math.sin((by-ay)/2)  , 2)    +   Math.cos(ay)*Math.cos(by)*Math.pow(   Math.sin((bx-ax)/2)   , 2)          ));
		//Haversine formula, Wikipedia, https://en.wikipedia.org/wiki/Haversine_formula
		return x;
	}
	
	public class IntersectionComparator implements Comparator<Intersection>
	{
	    @Override
	    public int compare(Intersection x, Intersection y)
	    {
	        if (x.distance < y.distance)
	        {
	            return -1;
	        }
	        if (x.distance > y.distance)
	        {
	            return 1;
	        }
	        return 0;
	    }
	}
	
	public ArrayList<Intersection> findShortestPath(String a, String b) {
		Comparator<Intersection> comparator = new IntersectionComparator();
		PriorityQueue<Intersection> pq=new PriorityQueue<Intersection>(10, comparator);
		Intersection start = null;
		
		for(Intersection node:nodes) {
			if(node.id.equals(a)) {
				node.distance=0;
				start=node;
			}
			else
			node.distance=Double.MAX_VALUE;
			node.previous=null;
			node.visited=false;
		}
		pq.add(start);
		Intersection end=null;
		while(!pq.isEmpty()) {

			Intersection minNode=pq.poll();

			if(minNode.id.equals(b)){
				end=minNode;
				break;
			}
			minNode.visited=true;
			for(Intersection n:adjList.get(minNode)) {
				if(!n.visited) {
					//System.out.println(n.id);
					double distance=findDistance(minNode,n)+minNode.distance;
					if(distance<n.distance) {
						n.distance=distance;
						n.previous=minNode;
						pq.remove(n);
						pq.add(n);
					}
				}
			}

		}
		if(end==null) {
			return new ArrayList<Intersection>();
		}

		ArrayList<Intersection> path=new ArrayList<Intersection>();
		Intersection ptr=end;
		totalDistance=end.distance;
		while(ptr!=null) {
			if(ptr.id.equals(a))
				break;
			path.add(0, ptr);
			ptr=ptr.previous;
		}
		path.add(0,ptr);
		return path;
		
		
	}
}


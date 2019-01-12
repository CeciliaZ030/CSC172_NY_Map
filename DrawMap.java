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

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JFrame;
public class DrawMap extends JPanel{
	ArrayList<Intersection> path=new ArrayList<>();
	Graph g1=new Graph();
	double x1=0,x2=0,y1=0,y2=0;
	double x11=0,x22=0,y11=0,y22=0;
	double max1=0,max2=0,min1=0,min2=0;
	@Override
	public void paintComponent(Graphics g) {

		int width=getWidth();
		int height=getHeight();
		max1=g1.nodes.get(0).longitude;
		min1=g1.nodes.get(0).longitude;
		max2=g1.nodes.get(0).latitude;
		min2=g1.nodes.get(0).latitude;
		for(int i=1;i<g1.nodes.size();i++) {
			if(g1.nodes.get(i).longitude>max1) {
				max1=g1.nodes.get(i).longitude;
			} 
			if(g1.nodes.get(i).latitude>max2) {
				max2=g1.nodes.get(i).latitude;
			}
			if(g1.nodes.get(i).longitude<min1) {
				min1=g1.nodes.get(i).longitude;
			}
			if(g1.nodes.get(i).latitude<min2) {
				min2=g1.nodes.get(i).latitude;
			}
		}
		for(Map.Entry<Intersection,ArrayList<Intersection>> entry: g1.adjList.entrySet()) {
			x1=width/((max1-min1))*(entry.getKey().longitude-min1);
			y1=height/((max2-min2))*(entry.getKey().latitude-min2);
			for(int i=0;i<entry.getValue().size();i++) {
				x2=width/((max1-min1))*(entry.getValue().get(i).longitude-min1);
				y2=height/((max2-min2))*(entry.getValue().get(i).latitude-min2);
				g.setColor(Color.BLACK);
				g.drawLine((int)x1, (int)(height-y1), (int)x2, (int)(height-y2));

			}
		}
		if(path.size()>0) {
			g.setColor(Color.RED);
			for(int j=0;j<path.size()-1;j++) {
				int startX=(int) (width/((max1-min1))*(path.get(j).longitude-min1));
				int startY=(int) (height/((max2-min2))*(path.get(j).latitude-min2));
				int endX=(int) (width/((max1-min1))*(path.get(j+1).longitude-min1));
				int endY=(int) (height/((max2-min2))*(path.get(j+1).latitude-min2));
				g.drawLine(startX, height-startY, endX, height-endY);
			}
		}
		

	}

}

	


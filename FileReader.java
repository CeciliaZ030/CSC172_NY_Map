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
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
	public void readfile(String name, Graph g) {
		File f=new File(name);
		try {
			Scanner sc=new Scanner(f);
			while(sc.hasNextLine()) {
				String data=sc.nextLine();
				String[] values=data.split("\t");
				if(values[0].equals("i")) {
						Intersection in=new Intersection(" ",0,0);
						in.id=values[1];
						in.latitude=Double.parseDouble(values[2]);
						in.longitude=Double.parseDouble(values[3]);
						g.a.put(values[1],in);
						g.addIntersection(in);
					}else if(values[0].equals("r")){
						Road r=new Road(" ",null,null);
						r.roadID=values[1];
						r.node1=g.a.get(values[2]);
						r.node2=g.a.get(values[3]);
						g.addRoad(r);
					}
			}
			sc.close();
		}	catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}

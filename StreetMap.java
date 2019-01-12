
import java.util.ArrayList;
import javax.swing.JFrame;

public class StreetMap {

	public static void main(String[] args) {
		
		ArrayList<String>command=new ArrayList<String>();
		for (String s: args) {
            command.add(s);
        }
		if(command.size()==0) {
			System.out.println("Empty argument");
			System.exit(0);
		}
		command.remove("java");
		command.remove("StreetMap");
		DrawMap d=new DrawMap();
		FileReader f=new FileReader();
		JFrame frame=new JFrame("Street Map");
		String fileName=command.get(0);command.remove(0);
		f.readfile(fileName, d.g1);
		
		while(command.contains("--"))
			command.remove("--");
		for(int i=0;i<command.size();i++) {
			String x=command.get(i);
			if(x.equals("--"))
				command.remove("--");
			if(x.startsWith("--")) {
				command.set(i, x.substring(2));}
		}

		if(command.contains("show")) {
			command.remove("show");
			frame.add(d);
			frame.setSize(1000, 1000);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		}
		if(command.contains("directions")) {
			command.remove("directions");
			d.path=d.g1.findShortestPath(command.get(0), command.get(1));
			if(d.path.isEmpty())
				System.out.println("Not connected.");
			else{
				for(int i=0;i<d.path.size();i++) {
					if(d.path.get(i)!=null)
						System.out.print(d.path.get(i).id);
					if(i!=d.path.size()-1)
						System.out.print(" -> ");
				}
				System.out.println("\nDistance:"+(d.g1.totalDistance));}
		}
		
	}
}

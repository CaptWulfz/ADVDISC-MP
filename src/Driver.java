import java.util.ArrayList;
import java.util.List;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Vector> vectors = new ArrayList<Vector>(); 
		double[] d1 = {1, 2, 3, 4};
		vectors.add(new Vector(d1, 4));
		double[] d2 = {0, 0, 3, 4};
		vectors.add(new Vector(d2, 4));
		double[] d3 = {0, 2, 3, 4};
		vectors.add(new Vector(d3, 4));
		double[] d4 = {1, 2, 3, 4};
		vectors.add(new Vector(d4, 4));
		
		double[] d5 = {1, 2, 3, 4};
		Vector constants = new Vector(d5, 4);
		
		Vector.Gauss_Jordan(vectors, 4, constants);
	}

}

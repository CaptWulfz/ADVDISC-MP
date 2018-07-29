import java.util.ArrayList;
import java.util.List;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Vector> vectors = new ArrayList<Vector>(); 
		double[] d1 = {1, 1, 1, 2};
		vectors.add(new Vector(d1, 4));
		double[] d2 = {2, 3, 5, 5};
		vectors.add(new Vector(d2, 4));
		double[] d3 = {4, 0, 5, 6};
		vectors.add(new Vector(d3, 4));
		//double[] d4 = {1, 2, 3, 4};
		//vectors.add(new Vector(d4, 4));
		
		double[] d5 = {5, 8, 2, 4};
		Vector constants = new Vector(d5, 4);
		
		System.out.println(Vector.Gauss_Jordan(vectors, constants.getDimension(), constants));
		//Vector.Gauss_Jordan(vectors, 3, constants);
	}

}

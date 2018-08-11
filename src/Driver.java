import java.util.ArrayList;
import java.util.List;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Vector> vectors = new ArrayList<Vector>(); 
		double[] d1 = {1, 2, 3, 5};
		vectors.add(new Vector(d1, 4));
		double[] d2 = {1, 2, 9, 11};
		vectors.add(new Vector(d2, 4));
		double[] d3 = {4, 10, 21, 29};
		vectors.add(new Vector(d3, 4));
		double[] d4 = {1, 6, 17, 23};
		vectors.add(new Vector(d4, 4));
		
		double[] d5 = {5, 8, 2, 1};
		Vector constants = new Vector(d5, 4);

		//Vector sample = Vector.Gauss_Jordan(vectors, constants.getDimension(), constants);
		//System.out.println(sample);
		//Vector.Gauss_Jordan(vectors, 3, constants);

		//System.out.println("Span: " + sample.span(vectors, constants.getDimension()));
		// System.out.println("Span: " + sample.getSpan());
		Matrix matrix = new Matrix(vectors, 4);
		
		for (int i = 0; i < matrix.getMatrix().size(); i++) {
			Vector vector = matrix.getMatrix().get(i);
			for (int j = 0; j < vector.getDimensions().length; j++)
				System.out.print(vector.getDimensions()[j] + " ");
			System.out.println();
		}
		
		System.out.println(matrix.det());
	}

	

}

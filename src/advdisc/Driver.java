package advdisc;
import java.util.ArrayList;
import java.util.List;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Vector> vectors = new ArrayList<Vector>(); 
		double[] d1 = {1, 1, 0};
		vectors.add(new Vector(d1, 3));
		double[] d2 = {2, 2, 1};
		vectors.add(new Vector(d2, 3));
		double[] d3 = {4, 6, 2};
		vectors.add(new Vector(d3, 3));
		//double[] d4 = {1, 2, 3, 4};
		//vectors.add(new Vector(d4, 4));
		
		double[] d5 = {5, 8, 2};
		Vector constants = new Vector(d5, 3);

		Vector sample = Vector.Gauss_Jordan(vectors, constants.getDimension(), constants);
		System.out.println(sample);
		//Vector.Gauss_Jordan(vectors, 3, constants);

		System.out.println("Span: " + sample.span(vectors, constants.getDimension()));
		// System.out.println("Span: " + sample.getSpan());
		
		Matrix matrix = new Matrix(4);
		
		for (int i = 0; i < matrix.getMatrix().size(); i++) {
			Vector vector = matrix.getMatrix().get(i);
			for (int j = 0; j < vector.getDimensions().length; j++)
				System.out.print(vector.getDimensions()[j] + " ");
			System.out.println();
		}
		
		System.out.println();
		
		List<Vector> vectors2 = new ArrayList<Vector>();
		double[] v1 = {0, 1};
		vectors2.add(new Vector(v1, 2));
		double[] v2 = {1, -1};
		vectors2.add(new Vector(v2, 2));
		double[] v3 = {2, 3};
		vectors2.add(new Vector(v3, 2));
		
		Matrix matrix2 = new Matrix(vectors2, 2);
		
		for (int i = 0; i < matrix2.getMatrix().size(); i++) {
			Vector vector = matrix2.getMatrix().get(i);
			for (int j = 0; j < vector.getDimensions().length; j++)
				System.out.print(vector.getDimensions()[j] + " ");
			System.out.println();
		}
		
		List<Vector> vectors3 = new ArrayList<Vector>();
		double[] v11 = {0, 4, -2};
		vectors3.add(new Vector(v11, 2));
		double[] v12 = {-4, -3, 0};
		vectors3.add(new Vector(v12, 2));
		
		Matrix matrix3 = new Matrix(vectors3, 3);
		
		for (int i = 0; i < matrix3.getMatrix().size(); i++) {
			Vector vector = matrix3.getMatrix().get(i);
			for (int j = 0; j < vector.getDimensions().length; j++)
				System.out.print(vector.getDimensions()[j] + " ");
			System.out.println();
		}
		
		System.out.println();
		System.out.println("THE NEW MATRIX:");
		Matrix matrix4 = matrix3.times(matrix2);
		
		for (int i = 0; i < matrix4.getMatrix().size(); i++) {
			Vector vector = matrix4.getMatrix().get(i);
			for (int j = 0; j < vector.getDimensions().length; j++)
				System.out.print(vector.getDimensions()[j] + " ");
			System.out.println();
		}
		
	}
}

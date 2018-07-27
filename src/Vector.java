import java.util.List;

public class Vector {
	
	private double[] dimensions;
	private int dimension;
	
	public Vector(int dimension) {
		this.dimension = dimension;
		dimensions = new double[this.dimension];
	}
	
	public Vector(double[] array, int dimension) {
		this.dimension = dimension;
		dimensions = array;
	}
	
	public Vector scale(double scalar) {
		double[] newArray = new double[this.dimension];
		
		for (int i = 0; i < dimensions.length; i++) 
			newArray[i] = dimensions[i] * scalar;
		
		return new Vector(newArray, newArray.length);
	}
	
	public Vector add(Vector addend) {
		double[] newArray = new double[this.dimension];
		
		for(int i = 0; i < dimensions.length; i++)
			newArray[i] = dimensions[i] + addend.getDimensions()[i];

		return new Vector(newArray, newArray.length);
	}

	public double[] getDimensions() {
		return this.dimensions;
	}
	
	public Vector Gauss_Jordan(List<Vector> vectyors, int dimension, Vector constants) {
		Vector vector = new Vector(dimension);
		
		return vector;
	}
}

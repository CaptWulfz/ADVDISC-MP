import java.util.Collections;
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
	
	public int getDimension() {
		return this.dimension;
	}
	
	public static Vector Gauss_Jordan(List<Vector> vectors, int dimension, Vector constants) {
		if(vectors.size() == dimension){
			Vector vector = new Vector(dimension);
			//Step 1, sort based on position of 1st non zero element
			int[] rank = new int[vectors.size()];
			int listDem =  vectors.get(0).getDimension();
			
			for(int i = 0; i < vectors.size(); i++){
				for(int j = 0; j < listDem; j++){
					if(vectors.get(i).getDimensions()[j] != 0){
						rank[i] = j;
						j = listDem;
					} else rank[i] = listDem; 
				}
			}
			
			for(int i = 0; i < rank.length; i++){
				for(int j = i+1; j < rank.length; j++){
					if(rank[i] > rank[j]){
						//swap rank
						rank[i] += rank[j];
						rank[j] = rank[i] - rank[j];
						rank[i] -= rank[j];
						//swap list
						Collections.swap(vectors, i, j);
						//swap constraint
						constants.getDimensions()[i] += constants.getDimensions()[j];
						constants.getDimensions()[j] = constants.getDimensions()[i] - constants.getDimensions()[j];
						constants.getDimensions()[i] -= constants.getDimensions()[j];
					}
				}
			}
			
			for(int i = 0; i < rank.length; i++){
				System.out.println(rank[i]);
			}
			
			for(int i = 0; i < rank.length; i++){
				System.out.println(vectors.get(i).getDimensions()[0] + " "
								   + vectors.get(i).getDimensions()[1] + " "
								   + vectors.get(i).getDimensions()[2] + " "
								   + vectors.get(i).getDimensions()[3] + " "
								   + constants.getDimensions()[i]);
			}
			
			//Step 2, reduce to row echelon form up to bottom
			
			//Step 3, perform row echelon from bottom up
			return vector;
		}
		else return null;
	}
}

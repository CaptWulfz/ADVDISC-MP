import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Matrix {
	private List<Vector> matrix;
	private int dimension;
	private int sign;
	
	public Matrix(int dimension) {
		matrix = new ArrayList<Vector>();
		this.dimension = dimension;
		for (int i = 0; i < dimension; i++) {
			double[] vector = new double[dimension];
			for (int j = 0; j < dimension; j++) {
				if (j == i)
					vector[j] = 1;
				else
					vector[j] = 0;
			}
			matrix.add(new Vector(vector, dimension));
		}
	}
	
	public Matrix (List<Vector> list, int dimension) {
		list = Vector.inputConverter(list);
		this.matrix = list;
		this.dimension = dimension;
	}
	
	public Matrix times(Matrix other){
        if (this.dimension == other.getMatrix().size() || this.matrix.size() == other.getDimension()) {
        	Matrix newMatrix = null;
        	List<Vector> list = new ArrayList<Vector>();
        	for (int i = 0; i < this.matrix.size(); i++) {
        		
        		double[] vector = matrix.get(i).getDimensions();
        		
        		double[] array = new double[this.matrix.size()];
        		int index = 0;
        		for (int k = 0; k < other.getDimension(); k++) {
        			double value = 0;
	        		for (int j = 0; j < this.dimension; j++) {
	        			value += vector[j] * other.getMatrix().get(j).getDimensions()[k];
	        		}
	        		array[index] = value;
	        		index++;
        		}
        		list.add(new Vector(array, this.matrix.size()));
        	}
        	
        	newMatrix = new Matrix(list, this.matrix.size());
        	
        	return newMatrix;
        } else {
        	System.out.println("The Matrices are of different Sizes!");
        	return null;
        }
    }
	
	public double det(){
		Matrix temp = new Matrix( matrix, dimension);
		List<Vector> tMatrix = temp.getMatrix();
		tMatrix = Vector.inputConverter(tMatrix);
		Vector tVector = null;
		double det = 1;
		sign = 1;
		
		//Step 1 sort list
		tMatrix = sortMatrixList(tMatrix);
		//Step 2, reduce to row echelon form up to bottom, while preserving value
		int crrntTop = 0;
		for(int i = 0; i < dimension; i ++){
			crrntTop = i;
			for(int j = i; j < dimension; j++){
				if(i == j && tMatrix.get(j).getDimensions()[i] != 0){
					tVector = tMatrix.get(j).scale(1/tMatrix.get(crrntTop).getDimensions()[i]);
					det*=tMatrix.get(crrntTop).getDimensions()[i];
				}
				else if(i != j && tMatrix.get(j).getDimensions()[i] != 0){
					Vector scaledTemp = new Vector(dimension);
					scaledTemp = tVector.scale(-tMatrix.get(j).getDimensions()[i]);
					tMatrix.set(j,tMatrix.get(j).add(scaledTemp));
				}
			}
			tMatrix = sortMatrixList(tMatrix);
			
		}
		
        return det*sign; 
    }

    public Matrix inverse(){
        return new Matrix(0); // dummy
    }
	
	public List<Vector> getMatrix() {
		return matrix;
	}

	public void setMatrix(List<Vector> matrix) {
		this.matrix = matrix;
	}

	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}
	
	public List<Vector> sortMatrixList(List<Vector> vectors){
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
					sign*=-1;
					//swap rank
					rank[i] += rank[j];
					rank[j] = rank[i] - rank[j];
					rank[i] -= rank[j];
					//swap list
					Collections.swap(vectors, i, j);
				}
			}
		}
		
		return vectors;
	}
}

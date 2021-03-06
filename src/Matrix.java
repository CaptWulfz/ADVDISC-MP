import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Matrix {
	/* ADVDISC MP Part 2 - S18
	 * Non, Gary
	 * Gana, Jordan
	 * Martinez, Jarod
	 * */
	
	private Matrix iMatrix = null;
	private List<Vector> matrix;
	private List<Vector> inverse;
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
        	
        	for (int i = 0; i < other.getDimension(); i++) {
        		
        		double[] array = new double[other.getMatrix().size()];
        		int index = 0;
        		for (int j = 0; j < this.matrix.size(); j++) {
        			double value = 0;
        			for (int k = 0; k < this.getDimension(); k++) {
        				value += other.getMatrix().get(k).getDimensions()[i] * this.getMatrix().get(j).getDimensions()[k];
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
		Vector oVector = null;
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
					oVector = tMatrix.get(j);
					tVector = tMatrix.get(j).scale(1/tMatrix.get(crrntTop).getDimensions()[i]);
					det*=tMatrix.get(crrntTop).getDimensions()[i];
				}
				else if(i != j && tMatrix.get(j).getDimensions()[i] != 0){
					Vector scaledTemp = new Vector(dimension);;
					if(tMatrix.get(j).getDimensions()[i] != oVector.getDimensions()[i])
						scaledTemp = tVector.scale(-tMatrix.get(j).getDimensions()[i]);
					else scaledTemp = oVector.scale(-1);
					tMatrix.set(j,tMatrix.get(j).add(scaledTemp));
				}
			}
			tMatrix = sortMatrixList(tMatrix);
			
		}
		
		tMatrix = null;
		iMatrix = null;
		
        return det*sign; 
    }

    public Matrix inverse(){
    	Matrix temp = new Matrix( matrix, dimension);
		List<Vector> tMatrix = temp.getMatrix();
		tMatrix = Vector.inputConverter(tMatrix);
		Vector orgVector = null;
		Vector orgiVector = null;
		
    	if(det() != 0){
    		if(iMatrix == null){
	    		//Step 1, Sort
	    		tMatrix = sortMatrixList(tMatrix);
	    		
	    		//Step 2, reduce to row echelon form up to bottom
	    		int crrntTop = 0;
				for(int i = 0; i < dimension; i ++){
					crrntTop = i;
					for(int j = i; j < dimension; j++){
						if(i == j && tMatrix.get(j).getDimensions()[i] != 0){
							//DIVIDES TOP NON ECHELON ROW BY THE FIRST NON ZERO INT, TO PRODUCES 1
							orgiVector = inverse.get(j);
							orgVector = tMatrix.get(j);
							inverse.set(j, inverse.get(j).scale(1/tMatrix.get(crrntTop).getDimensions()[i]));
							tMatrix.set(j, tMatrix.get(j).scale(1/tMatrix.get(crrntTop).getDimensions()[i]));
							
						}else if(i != j && tMatrix.get(j).getDimensions()[i] != 0){
							//ALL SUBSEQUENT ROWS USE TOP ROW WITH 1, MULTIPLY 1 WITH THE NON 0 INT'S NEGATIVE, AND ADD DOWN  
							
							double scaleBy = -tMatrix.get(j).getDimensions()[i];
							Vector scaledTemp = new Vector(dimension);
							
							if(tMatrix.get(j).getDimensions()[i] != orgVector.getDimensions()[i]){
								scaledTemp = tMatrix.get(crrntTop).scale(scaleBy);
								tMatrix.set(j,tMatrix.get(j).add(scaledTemp));
								scaledTemp = inverse.get(crrntTop).scale(scaleBy);
								inverse.set(j,inverse.get(j).add(scaledTemp));
							}else {
								scaledTemp = orgVector.scale(-1);
								tMatrix.set(j,tMatrix.get(j).add(scaledTemp));
								scaledTemp = orgiVector.scale(-1);
								inverse.set(j,inverse.get(j).add(scaledTemp));
							}
							
							
						}
					}
					tMatrix = sortMatrixList(tMatrix);
				}
				
				//checks for all zero rows, to return null if found
				for(int i = 0; i < tMatrix.size(); i ++){
					for(int j = 0; j < dimension; j++){
						if(tMatrix.get(i).getDimensions()[j]!=0){
							j = dimension;
						}
						if(j == dimension-1 && tMatrix.get(i).getDimensions()[j]==0){
							return null;
						}
							
					}
				}
				
				//Step 3, perform row echelon from bottom up
				int crrntBottom = 0;
				for(int i = dimension -1; i >= 0; i--){
					crrntBottom = i;
					for(int j = i; j >= 0; j--){
						if(i == j && tMatrix.get(j).getDimensions()[i] != 0){
							//DIVIDES BOTTOM NON ECHELON ROW BY THE FIRST NON ZERO INT, TO PRODUCES 1
							orgiVector = inverse.get(j);
							orgVector = tMatrix.get(j);
							inverse.set(j, inverse.get(j).scale(1/tMatrix.get(crrntBottom).getDimensions()[i]));
							tMatrix.set(j, tMatrix.get(j).scale(1/tMatrix.get(crrntBottom).getDimensions()[i]));
							
						}else if(i != j && tMatrix.get(j).getDimensions()[i] != 0){
							//ALL SUBSEQUENT ROWS USE Bottom ROW WITH 1, MULTIPLY 1 WITH THE NON 0 INT'S NEGATIVE, AND ADD DOWN  
							double scaleBy = -tMatrix.get(j).getDimensions()[i];
							Vector scaledTemp = new Vector(dimension);
							
							if(tMatrix.get(j).getDimensions()[i] != orgVector.getDimensions()[i]){
								scaledTemp = tMatrix.get(crrntBottom).scale(scaleBy);
								tMatrix.set(j,tMatrix.get(j).add(scaledTemp));
								scaledTemp = inverse.get(crrntBottom).scale(scaleBy);
								inverse.set(j,inverse.get(j).add(scaledTemp));
							}else {
								scaledTemp = orgVector.scale(-1);
								tMatrix.set(j,tMatrix.get(j).add(scaledTemp));
								scaledTemp = orgiVector.scale(-1);
								inverse.set(j,inverse.get(j).add(scaledTemp));
							}
						}
					}
					tMatrix = sortMatrixList(tMatrix);
				}
				
				//checks for all zero rows, to return null if found
				for(int i = 0; i < tMatrix.size(); i ++){
					for(int j = 0; j < dimension; j++){
						if(tMatrix.get(i).getDimensions()[j]!=0){
							j = dimension;
						}
						if(j == dimension-1 && tMatrix.get(i).getDimensions()[j]==0){
							return null;
						}
							
					}
				}
    		}
    		return iMatrix;
    	}
    	else return null; // dummy
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
		
		if(inverse == null || iMatrix == null){
			iMatrix = new Matrix(dimension);
			inverse = iMatrix.getMatrix();
		}
			
		
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
					Collections.swap(inverse, i, j);
				}
			}
		}
		
		return vectors;
	}
	
	public Matrix transpose(){
		return new Matrix(matrix, dimension);
	}
}

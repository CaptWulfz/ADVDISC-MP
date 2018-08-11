package advdisc;
import java.util.ArrayList;
import java.util.List;
import advdisc.Vector;

public class Matrix {
	private List<Vector> matrix;
	private int dimension;
	
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
        return 0; //dummy
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
}

import java.util.ArrayList;
import java.util.List;

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
        list = Vector.inputConverter(list);
		this.matrix = list;
		this.dimension = dimension;
	}
	
	public Matrix times(Matrix other){
        return other;
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

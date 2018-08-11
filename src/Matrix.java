import java.util.ArrayList;
import java.util.List;

public class Matrix {
	private List<Vector> matrix;
	private int dimension;
	
	public Matrix(int dimension) {
		matrix = new ArrayList<Vector>();
		this.dimension = dimension;
	}
	
	public Matrix (List<Vector> list, int dimension) {
		this.matrix = list;
		this.dimension = dimension;
	}
	
	public Matrix times(Matrix other) {
		
	}
}

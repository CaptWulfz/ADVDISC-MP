import java.util.*;

public class Matrix{

    public Matrix(int dimension){

    }

    public Matrix(List<Vector> list, int dimension){

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

}
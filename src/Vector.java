import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Vector {
	
	private double[] dimensions;
	private int dimension;
	private static int span;
	
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
		
		vectors = inputConverter(vectors);
		
		/*
		System.out.println("Start");
		for(int i = 0; i < vectors.size(); i++){
			System.out.println(vectors.get(i).getDimensions()[0] + " "
							   + vectors.get(i).getDimensions()[1] + " "
							   + vectors.get(i).getDimensions()[2] + " "
							   //+ vectors.get(i).getDimensions()[3] + " "
							   + constants.getDimensions()[i]);
		}
		System.out.println();*/

		if(vectors.size() == constants.getDimension()){
			//Step 1, sort based on position of 1st non zero element
			int[] rank = new int[vectors.size()];
			int listDem =  vectors.get(0).getDimension();
			constants = sortConstants(vectors, constants);
			vectors = sortVectorList(vectors, constants);
			
			//Step 2, reduce to row echelon form up to bottom
			int crrntTop = 0;
			for(int i = 0; i < listDem; i ++){
				crrntTop = i;
				for(int j = i; j < vectors.size(); j++){
					if(i == j && vectors.get(j).getDimensions()[i] != 1 && vectors.get(j).getDimensions()[i] != 0){
						//DIVIDES TOP NON ECHELON ROW BY THE FIRST NON ZERO INT, TO PRODUCES 1
						constants.getDimensions()[i] *= 1/vectors.get(crrntTop).getDimensions()[i];
						vectors.set(j, vectors.get(j).scale(1/vectors.get(crrntTop).getDimensions()[i]));
						
					}else if(i != j && vectors.get(j).getDimensions()[i] != 0){
						//ALL SUBSEQUENT ROWS USE TOP ROW WITH 1, MULTIPLY 1 WITH THE NON 0 INT'S NEGATIVE, AND ADD DOWN  
						Vector scaledTemp = new Vector(listDem);
						scaledTemp = vectors.get(crrntTop).scale(-vectors.get(j).getDimensions()[i]);
						double scaledConst = constants.getDimensions()[crrntTop] * -vectors.get(j).getDimensions()[i];
						vectors.set(j,vectors.get(j).add(scaledTemp));
						constants.getDimensions()[j] = scaledConst + constants.getDimensions()[j];
						constants = sortConstants(vectors, constants);
						vectors = sortVectorList(vectors, constants);
					}
				}
			}
			
			/*
			System.out.println("2nd");
			for(int i = 0; i < vectors.size(); i++){
				System.out.println(vectors.get(i).getDimensions()[0] + " "
								   + vectors.get(i).getDimensions()[1] + " "
								   + vectors.get(i).getDimensions()[2] + " "
								   //+ vectors.get(i).getDimensions()[3] + " "
								   + constants.getDimensions()[i]);
			}
			System.out.println();*/
			
			//checks for all zero rows, to return null if found
			for(int i = 0; i < vectors.size(); i ++){
				for(int j = 0; j < listDem; j++){
					if(vectors.get(i).getDimensions()[j]!=0){
						j = listDem;
					}
					if(j == listDem-1 && vectors.get(i).getDimensions()[j]==0){
						generateSpan(vectors);
						return null;
					}
						
				}
			}
			
			//Step 3, perform row echelon from bottom up
			int crrntBottom = 0;
			for(int i = listDem -1; i >= 0; i--){
				crrntBottom = i;
				for(int j = i; j >= 0; j--){
					if(i == j && vectors.get(j).getDimensions()[i] != 1 && vectors.get(j).getDimensions()[i] != 0){
						//DIVIDES BOTTOM NON ECHELON ROW BY THE FIRST NON ZERO INT, TO PRODUCES 1
						constants.getDimensions()[i] *= 1/vectors.get(crrntBottom).getDimensions()[i];
						vectors.set(j, vectors.get(j).scale(1/vectors.get(crrntBottom).getDimensions()[i]));
						
					}else if(i != j && vectors.get(j).getDimensions()[i] != 0){
						//ALL SUBSEQUENT ROWS USE Bottom ROW WITH 1, MULTIPLY 1 WITH THE NON 0 INT'S NEGATIVE, AND ADD DOWN  
						Vector scaledTemp = new Vector(listDem);
						scaledTemp = vectors.get(crrntBottom).scale(-vectors.get(j).getDimensions()[i]);
						double scaledConst = constants.getDimensions()[crrntBottom] * -vectors.get(j).getDimensions()[i];
						vectors.set(j,vectors.get(j).add(scaledTemp));
						constants.getDimensions()[j] = scaledConst + constants.getDimensions()[j];
						constants = sortConstants(vectors, constants);
						vectors = sortVectorList(vectors, constants);
					}
				}
			}
			
			//checks for all zero rows, to return null if found
			for(int i = 0; i < vectors.size(); i ++){
				for(int j = 0; j < listDem; j++){
					if(vectors.get(i).getDimensions()[j]!=0){
						j = listDem;
					}
					if(j == listDem-1 && vectors.get(i).getDimensions()[j]==0){
						generateSpan(vectors);
						return null;
					}
						
				}
			}
			/*
			System.out.println("Third");
			for(int i = 0; i < vectors.size(); i++){
				System.out.println(vectors.get(i).getDimensions()[0] + " "
								   + vectors.get(i).getDimensions()[1] + " "
								   + vectors.get(i).getDimensions()[2] + " "
								   //+ vectors.get(i).getDimensions()[3] + " "
								   + constants.getDimensions()[i]);
			}*/
			
			generateSpan(vectors);
			return constants;
		}
		else {
			generateSpan(vectors);
			return null;
		}
	}
	
	private static List<Vector> sortVectorsList() {
		// TODO Auto-generated method stub
		return null;
	}

	public static List<Vector> sortVectorList(List<Vector> vectors, Vector constants){
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
		
		return vectors;
	}
	
	public static Vector sortConstants(List<Vector> vectors, Vector constants){
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
		
		return constants;
	}

	public static int span(List<Vector> vectors, int dimension){
		double[] d = new double[dimension];
		for(int i = 0; i< dimension; i++)
			d[i] = 1;
		Vector constants = new Vector(d, dimension);
		
		Gauss_Jordan(vectors, dimension, constants);
		return span;
	}

	public static void generateSpan(List<Vector> vectors){
		int listDem = vectors.get(0).getDimension();
		span = 0;
		int ctr;
		for (int i = 0; i < vectors.size(); i++) {
			ctr = 0;
			for(int j = 0; j < listDem; j++){
				if (vectors.get(i).getDimensions()[j] > 0)
					ctr++;
			}

			if (ctr > 0){
				span++;
			}
		}
	}
	
	public static List<Vector> inputConverter(List<Vector> vectors){
		List<Vector> newVectors = new ArrayList<Vector>();
		
		for(int i = 0; i < vectors.get(0).getDimension(); i++){
			newVectors.add(new Vector(vectors.size()));
		}
		for(int i = 0; i < vectors.get(0).getDimension(); i++){
			for(int j = 0; j < vectors.size(); j++){
				newVectors.get(i).getDimensions()[j] = vectors.get(j).getDimensions()[i];
			}
		}
		
		
		return newVectors;
	}
}

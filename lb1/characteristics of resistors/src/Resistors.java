import java.io.File;
import java.util.Scanner;
import java.util.Vector;

public class Resistors {
	
	private int n; //кол-во строк
	private int m; //кол-во столбцов
	//таблица со значениями 44-резисторов 9 раз
	private Vector<Vector<Integer>> resistirs2D;
	///Ri
	private double resistorI;
	//R(serii)
	private double resistorSeries;
	//СКО серии
	private double resistorSeriesError;
	//СКО(i) приближенное
	private double approximResistorSeriesErrorI;
	//СКО приближенное
	private double approximResistorSeriesError;
	//промах
	private double misse; 
	
	//конструктор
	public void createResistirs2D() {
		resistirs2D = new Vector<Vector<Integer>>();
		for (int i = 0; i < n; i++) 
			resistirs2D.add(new Vector<Integer>());
		
		File file = null;
		Scanner scanner = null;
		
		try {
			file = new File("dataResisters.txt");
			scanner = new Scanner(file);
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (file.canRead()) {
						resistirs2D.get(i).add(scanner.nextInt());
						//System.out.print(resistirs2D.get(i).get(j) + " ");
					}
				}
				//System.out.print("\n");
			} 
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			scanner.close();
		}
	}
	
	public Resistors(int n, int m) {
		this.n = n;
		this.m = m;
		
		createResistirs2D();
	}
	
	//calculation 
	public void calcResistorI(int row) {
		double sum = 0;
		for (int j = 0; j < m; j++) {
			sum += resistirs2D.get(row).get(j);
		}
		
		resistorI = sum / m;
	}
	
	public void calcResistorSeries() {
		double sumRi = 0;
		
		for (int i = 0; i < n; i++) {
			calcResistorI(i);
			sumRi += resistorI;
		}
		
		resistorSeries = sumRi / n;
	}

	public void calcResistorSeriesError() {
		double sum = 0;
		for (int i = 0; i < n; i++) {
			calcResistorI(i);
			sum += Math.pow(resistorI - getResistorSeries(), 2);
 		}
		
		resistorSeriesError = Math.sqrt( sum / (n-1) );
	}

	public void calcApproximResistorSeriesErrorI(int row) {
		double sum = 0;
		calcResistorI(row);
		
		for (int j = 0; j < m; j++) {
			sum += Math.pow(resistirs2D.get(row).get(j) - resistorI, 2);
		}
		
		approximResistorSeriesErrorI = Math.sqrt( sum / (m-1) );
	}

	public void calcApproximResistorSeriesError() {
		double sum = 0;
		
		for (int i = 0; i < n; i++) {
			calcApproximResistorSeriesErrorI(i);
			sum += Math.pow(approximResistorSeriesErrorI, 2);
		}
		
		approximResistorSeriesError = Math.sqrt( sum / n );
	}	
	
	//удаление промаха из Ri
	public void removeMissesI(int row) {
		for (int j = 0; j < m; j++) {
			if (resistirs2D.get(row).get(j) <= resistorI - 3*approximResistorSeriesErrorI ||
					resistirs2D.get(row).get(j) >= resistorI + 3*approximResistorSeriesErrorI) {
				resistirs2D.get(row).set(j, 0);
				calcResistorI(row);
				calcApproximResistorSeriesErrorI(row);
			}
		}
	}
	
	//удаление промахов из R
	public void removeMisses() {
		for (int i = 0; i < n; i++) {
			removeMissesI(i);
		}
	}
	
	//getter
	public double getResistorI() {
		return resistorI;
	}
	
	public double getResistorSeries() {
		return resistorSeries;
	}

	public double getResistorSeriesError() {
		return resistorSeriesError;
	}

	public double getApproximResistorSeriesErrorI() {
		return approximResistorSeriesErrorI;
	}

	public double getApproximResistorSeriesError() {
		return approximResistorSeriesError;
	}
	public double getMisse() {
		return misse;
	}
	
}

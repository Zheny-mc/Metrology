import java.io.File;
import java.util.Scanner;
import java.util.Vector;

public class Resistors {
	
	private int n; //���-�� �����
	private int m; //���-�� ��������
	//������� �� ���������� 44-���������� 9 ���
	private Vector<Vector<Integer>> resistirs2D;
	///Ri
	private double resistorI;
	//R(serii)
	private double resistorSeries;
	//��� �����
	private double resistorSeriesError;
	//���(i) ������������
	private double approximResistorSeriesErrorI;
	//��� ������������
	private double approximResistorSeriesError;
	//������
	private double misse; 
	
	//�����������
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
	
	//�������� ������� �� Ri
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
	
	//�������� �������� �� R
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

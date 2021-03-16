
public class Main {

	public static void main(String[] args) throws Exception {
		
		final Integer N = Integer.parseInt(args[0]);
		final Integer M = Integer.parseInt(args[1]);
		Resistors resistors = new Resistors(N, M);
		
		
		// 1 stage
		resistors.calcResistorSeries();
		System.out.printf("ResistorSeries = %.3f\n", resistors.getResistorSeries());
		
		// 2 stage
		//-a
		resistors.calcResistorSeriesError();
		System.out.printf("a)ResistorSeriesError = %.3f\n", resistors.getResistorSeriesError());
		//-b
		resistors.calcApproximResistorSeriesError();
		System.out.printf("a)ApproximResistorSeriesError = %.3f\n", resistors.getApproximResistorSeriesError());
	
		// 3 stage
		resistors.removeMisses();
		
		System.out.println("********************************************************************************");
		// 1 stage
		resistors.calcResistorSeries();
		System.out.printf("ResistorSeries = %.3f\n", resistors.getResistorSeries());
		
		// 2 stage
		//-a
		resistors.calcResistorSeriesError();
		System.out.printf("a)ResistorSeriesError = %.3f\n", resistors.getResistorSeriesError());
		//-b
		resistors.calcApproximResistorSeriesError();
		System.out.printf("a)ApproximResistorSeriesError = %.3f\n", resistors.getApproximResistorSeriesError());
	}

}

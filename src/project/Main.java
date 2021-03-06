package project;

import gd.gui.GeneticDrawingApp;

public class Main {

	public static final int NUMBER_OF_TRIANGLES = 100;

	public static final int NUMBER_OF_RUNS = 3;
	public static final int NUMBER_OF_GENERATIONS = 2000;
	public static final int POPULATION_SIZE = 25;
	public static final double MUTATION_PROBABILIY = 0.25;
	public static final double CROSSOVER_PROBABILIY = .9;
	public static final int TOURNAMENT_SIZE = 3;

	public static boolean KEEP_WINDOWS_OPEN = false;

	public static Solution[] bestSolutions = new Solution[NUMBER_OF_RUNS];
	public static double[] bestFitness = new double[NUMBER_OF_RUNS];
	public static int currentRun = 0;

	public static void main(String[] args) {
		run();
	}

	public static void addBestSolution(Solution bestSolution) {
		bestSolutions[currentRun] = bestSolution;
		bestFitness[currentRun] = bestSolution.getFitness();
		System.out.printf("Got %.2f as a result for run %d\n", bestFitness[currentRun], currentRun + 1);
		System.out.print("All runs:");
		for (int i = 0; i <= currentRun; i++) {
			System.out.printf("\t%.2f", bestFitness[i]);
		}
		System.out.println();
		currentRun++;
		if (KEEP_WINDOWS_OPEN == false) {
			Problem.view.getFittestDrawingView().dispose();
			Problem.view.getFrame().dispose();
		}
		if (currentRun < NUMBER_OF_RUNS) {
			run();
		} else {
			presentResults();
		}
	}

	public static void presentResults() {
		double mean = Statistics.mean(bestFitness);
		double stdDev = Statistics.standardDeviation(bestFitness);
		double best = Statistics.min(bestFitness);
		double worst = Statistics.max(bestFitness);
		System.out.printf("\n\t\tMean +- std dev\t\tBest\t\tWorst\n\n");
		System.out.printf("Results\t\t%.2f +- %.2f\t%.2f\t%.2f\n", mean, stdDev, best, worst);
	}

	public static void run() {
		GeneticDrawingApp.main(null);
	}
}

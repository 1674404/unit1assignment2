/* ContestantDriver.java 
 - This ccode simulates the contests rounds using one ArrayList, records contestant's position, and measures movement time.
 - Abraham Atencio Park ID: 1674404
 - Oct 25, 2026
 */

import java.util.ArrayList;
import java.util.Random;

public class ContestDriver {

	public static void main(String[] args) {
		System.out.println("Starting Simulation");

		/*  Change these two values for the fairness run and timing experiments.*/
        int numberOfContestants = 8;
        int numberOfRounds = 12;

        ArrayList<Contestant> line =
                new ArrayList<>(numberOfContestants);

        /* A fixed seed makes the same configuration repeatable during testing.*/
        Random random = new Random(352);

        /* Generate unique names so large simulations need no manual entries.*/
        for (int i = 0; i < numberOfContestants; i++) {
            line.add(new Contestant("Contestant " + (i + 1), 22));
        }

        long simulationNanos = 0;

        for (int round = 0; round < numberOfRounds; round++) {
            /* Time only movement; statistics and output remain outside.*/
            long start = System.nanoTime();

            playRound(line, random);

            simulationNanos += System.nanoTime() - start;

            /* Record every contestant's position after the entire round ends.*/
            for (int position = 0; position < line.size(); position++) {
                line.get(position).recordPosition(position);
            }
        }

        double simulationSeconds =
				simulationNanos / 1_000_000_000.0;

        System.out.printf(
                "Contestants: %d, rounds: %d, seconds: %.6f%n",
                numberOfContestants,
                numberOfRounds,
                simulationSeconds
        );

        /* Show individual statistics for the required fairness experiment.*/
        /* Large timing experiments print only the timing summary above.*/
        if (numberOfContestants == 8 && numberOfRounds == 12) {
            System.out.println();
            System.out.println("Average positions after all rounds:");

            for (Contestant contestant : line) {
                System.out.printf(
                        "%s | Rounds: %d | Average position: %.3f%n",
                        contestant.getName(),
                        contestant.getRoundsRecorded(),
                        contestant.getAveragePosition()
                );
            }
        }



		System.out.println("Finished");
	}


    /* Processes each contestant once in the round's starting order*/
    private static void playRound(ArrayList<Contestant> line,
                                  Random random) {
        int contestantCount = line.size();
        int nextIndex = 0;

        /* Use the starting count so every contestant receives exactly one turn.*/
        for (int turn = 0; turn < contestantCount; turn++) {
            Contestant current = line.remove(nextIndex);
            boolean correct = random.nextBoolean();

            /* A front insertion shifts the next unprocessed contestant right.*/
            /* A back insertion leaves that contestant at the same index.*/
            if (correct) {
                line.add(0, current);
                nextIndex++;
            } else {
                line.add(current);
            }
        }
    }

}

/* Contestant.java 
 - This class extends Person with added attributes and methods.
 - Abraham Atencio Park ID: 1674404
 - Oct 25, 2026
 */
public class Contestant extends Person {
	/* 
    - Keep a running total and count. 
    - A long total supports large numbers of contestants and rounds. 
    */
    private long positionTotal;
    private int roundsRecorded;

    /*
     - Creates a contestant using the name and age stored by Person.
     */
    public Contestant(String name, int age) {
        super(name, age);
    }

    /*
     - Records one end-of-round position for this contestant.
     - Updates the running total and round count; returns nothing.
     */

    public void recordPosition(int position) {
        positionTotal += position;
        roundsRecorded++;
    }

    /*
     - Calculates the mean of the recorded end-of-round positions.
    */
    public double getAveragePosition() {
        if (roundsRecorded == 0) {
            return 0.0;
        }

        /*Convert before division so the average keeps the fractional part.*/
        return (double) positionTotal / roundsRecorded;
    }

    public int getRoundsRecorded() {
        return roundsRecorded;
    }
}

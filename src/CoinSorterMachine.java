import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * CoinSorterMachine Lab
 * @author Ryfi
 * @version 03.30.2023
 */
public class CoinSorterMachine {
    public ArrayList<Coin> coins;
    public int[] coinCount = {0,0,0,0,0,0};
    DecimalFormat df;

    /**
     * initializes coins ArrayList
     */
    public CoinSorterMachine(){
        coins = new ArrayList<>();
        df = new DecimalFormat("0.00");
    }

    /**
     *  use one or two Scanner objects, prompting user for the appropriate file
     *  name and importing the data from filename – MUST handle diabolic values!
     */
    public void depositCoins(){
        String file;
        try{
            System.out.print("Enter the name of the data file for coin deposit: ");
            Scanner in = new Scanner(System.in);
            file = in.nextLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Depositing coins...");
        try{
            Scanner in = new Scanner(new File(file));
            String temp;
            while(in.hasNext()){
                temp = in.nextLine();
                switch (Integer.parseInt(temp)) {
                    case 1 -> {
                        coins.add(new Penny());
                        coinCount[0]++;
                    }
                    case 5 -> {
                        coins.add(new Nickel());
                        coinCount[1]++;
                    }
                    case 10 -> {
                        coins.add(new Dime());
                        coinCount[2]++;
                    }
                    case 25 -> {
                        coins.add(new Quarter());
                        coinCount[3]++;
                    }
                    case 50 -> {
                        coins.add(new HalfDollar());
                        coinCount[4]++;
                    }
                    case 100 -> {
                        coins.add(new Dollar());
                        coinCount[5]++;
                    }
                    default -> System.err.println("Coin value " + temp + " not recognized");
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Prints deposit summary using a DecimalFormat object (see output section)
     */
    public void printDepositSummary(){
        System.out.println("Summary of deposit: ");
        System.out.println("\t" + coinCount[0] + (coinCount[0] == 1 ? " penny " : " pennies $") + df.format(.01 * coinCount[0]));
        System.out.println("\t" + coinCount[1] + (coinCount[1] == 1 ? " nickel " : " nickels $") + df.format(.05 * coinCount[1]));
        System.out.println("\t" + coinCount[2] + (coinCount[2] == 1 ? " dime " : " dimes $") + df.format(.1 * coinCount[2]));
        System.out.println("\t" + coinCount[3] + (coinCount[3] == 1 ? " quarter " : " quarters $") + df.format(.25 * coinCount[3]));
        System.out.println("\t" + coinCount[4] + (coinCount[4] == 1 ? " half dollar " : " half dollars $") + df.format(.5 * coinCount[4]));
        System.out.println("\t" + coinCount[5] + (coinCount[5] == 1 ? " dollar " : " dollars $") + df.format(1.0 * coinCount[5]));
        System.out.println("TOTAL DEPOSIT: $" + df.format(getTotalValue()));
    }

    /**
     * @return the total value of all Coin objects stored in coins as a double
     */
    public double getTotalValue(){
        return coinCount[0] * .01 + coinCount[1] * .05 + coinCount[2] * .1 + coinCount[3] * .25 + coinCount[4] * .5 + coinCount[5] * 1.0;
    }
    public static void main(String[] args){
        CoinSorterMachine app = new CoinSorterMachine();
        app.depositCoins();
        app.printDepositSummary();
    }
}

/**
 * Coin machine project
 * @author Ryfi
 * @version 03.30.2023
 */

public abstract class Coin {
    public abstract double getValue();
    public abstract String getName();
    public String getPluralName(){
        return getName().equals("penny") ? "pennies": getName() + "s";
    }
// returns .01, .05, ..., .5, 1.0 based on
// implementing class object's value
// returns "penny", "nickel", ...,
// "half dollar", "dollar"
// define in the class: pennies, dimes...
}

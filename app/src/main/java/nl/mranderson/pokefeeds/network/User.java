package nl.mranderson.pokefeeds.network;


public class User {

    private int age;

    int calculateRemainingLifespan(LifespanCalculator lifespanCalculator) {
        return lifespanCalculator.calculate(this.age);
    }

    void setAge(int age) {
        this.age = age;
    }
}






class LifespanCalculator {
    public int calculate(int age) {
        return 3;
    }
}


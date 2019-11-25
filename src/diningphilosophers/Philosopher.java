package diningphilosophers;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Philosopher extends Thread {

    private static int seed = 1;
    private final Random myRandom = new Random(System.currentTimeMillis() + seed++);
    private final static int DELAY = 1000;
    private final ChopStick myLeftStick;
    private final ChopStick myRightStick;
    private boolean jeContinue = true;

    public Philosopher(String name, ChopStick left, ChopStick right) {
        super(name);
        myLeftStick = left;
        myRightStick = right;
    }

    @Override
    public void run() {
        while (jeContinue) {
            try {
                think();
                myLeftStick.take();
                think();
                myRightStick.take();
                eat();
              
                myLeftStick.release();
                myRightStick.release();
        
            } catch (InterruptedException ex) {
                Logger.getLogger("Table").log(Level.SEVERE, "{0} Interrupted", this.getName());
            }
        }
    }

    public void arreteToi() {
        jeContinue = false;
    }

    private void think() throws InterruptedException {
        int delay = myRandom.nextInt(500 + DELAY);
        System.out.println(this.getName() + " commence à penser pour: " + delay + " ms");
        sleep(delay);
        System.out.println(this.getName() + " Arrete de penser");
    }

    private void eat() throws InterruptedException {
        int delay = myRandom.nextInt(100 + DELAY);
        System.out.println(this.getName() + " commence à manger pour: " + delay + " ms");
        sleep(delay); 
        System.out.println(this.getName() + " Arrete de manger");
    }
}

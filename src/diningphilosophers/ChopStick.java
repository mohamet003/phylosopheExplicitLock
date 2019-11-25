package diningphilosophers;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChopStick {

	private static int stickCount = 0;
	private final Lock verrou = new ReentrantLock();
	private final int myNumber;

	public ChopStick() {
		myNumber = ++stickCount;
	}

	synchronized public boolean tryTake(int delay) throws InterruptedException {
            
                if (verrou.tryLock(delay,TimeUnit.MILLISECONDS)) {
                    try{
                        System.out.println("Baguette N°: " + myNumber + " prise");
                        return true;
                    }finally{
                         verrou.unlock();
                    }
                }else{
                    System.out.println("Baguette N° " + myNumber + " non disponible");
                    return false;
                }
            
	}

	synchronized public void release() {
          if (verrou.tryLock()) {
                System.out.println("Baguette N° " + myNumber + " relachée");
                verrou.unlock();
            }
	}

	@Override
	public String toString() {
		return "Baguette#" + myNumber;
	}
}

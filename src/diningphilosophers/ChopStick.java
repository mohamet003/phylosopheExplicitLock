package diningphilosophers;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChopStick {
	private static int stickCount = 0;
        
        	private final Lock verrou = new ReentrantLock();
       
	private final int myNumber;

	public ChopStick() {
		myNumber = ++ stickCount;
	}

	public void take() throws InterruptedException {
                if (verrou.tryLock(1000,TimeUnit.MILLISECONDS)) {
                    try{
                        System.out.println("Baguette N°: " + myNumber + " prise");
                    }finally{
                         verrou.unlock();
                    }
                }else{
                    System.out.println("Baguette N° " + myNumber + " non disponible");
                  
                }
	}

	
        public void release() throws InterruptedException {     
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

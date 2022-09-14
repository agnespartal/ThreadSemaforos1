package view;

import java.util.concurrent.Semaphore;

import controller.ThreadController;

public class Principal {

	public static void main(String[] args) {
		
		Semaphore semaphore1 = new Semaphore(1);
		Semaphore semaphore2 = new Semaphore(1);
		Semaphore semaphore3 = new Semaphore(1);
		
		for (int id = 1; id < 22; id++) {
			Thread threadC = new ThreadController(id, semaphore1, semaphore2, semaphore3);
			threadC.start();
		}
	}

}

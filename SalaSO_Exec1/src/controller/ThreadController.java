package controller;

import java.util.concurrent.Semaphore;

public class ThreadController extends Thread {
	
	private int id;
	private static int cont1=0;
	private static int cont2=0;
	private static int cont3=0;
	private Semaphore semaforo1;
	private Semaphore semaforo2;
	private Semaphore semaforo3;
	
	public ThreadController(int id, Semaphore semaforo1, Semaphore semaforo2, Semaphore semaforo3) {
		super();
		this.id = id;
		this.semaforo1 = semaforo1;
		this.semaforo2 = semaforo2;
		this.semaforo3 = semaforo3;
	}

	@Override
	public void run() {
		calculos();
		bancoDados();
	}

	private void calculos() {
		int tempo1 = (int)((Math.random()* 801) + 200);
		int tempo2 = (int)((Math.random()* 1001) + 500);
		int tempo3 = (int)((Math.random()* 1001) + 1000);
		
		if (id%3 == 1) {
			System.out.println("#"+id+ " esta calculando");
			try {
				sleep(tempo1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (cont1 == 0) {
				cont1++;
				bancoDados();
			}
		}
		if (id%3 == 2) {
			System.out.println("#"+id+ " esta calculando");
			try {
				sleep(tempo2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (cont2 == 0 || cont2 == 1) {
				cont2++;
				bancoDados();
			}
		}
		if (id%3 == 0) {
			System.out.println("#"+id+ " esta calculando");
			try {
				sleep(tempo3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (cont3 == 0 || cont3 == 1) {
				cont3++;
				bancoDados();
			}
		}
	}

	private void bancoDados() {
		
		if (id%3 == 1) {
			System.out.println("#"+id+ " esta no banco de dados");
			try {
				semaforo1.acquire();
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo1.release();
			}
			if(cont1 == 1) {
				calculos();
			}
			
		}
		if (id%3 == 2) {
			System.out.println("#"+id+ " esta no banco de dados");
			try {
				semaforo2.acquire();
				sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo2.release();
			}
			if(cont2 == 1 || cont2 == 2) {
				calculos();
			}
		}
		if (id%3 == 0) {
			System.out.println("#"+id+ " esta no banco de dados");
			try {
				semaforo3.acquire();
				sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo3.release();
			}
			if(cont3 == 1 || cont3 == 2) {
				calculos();
			}
		}
	}
	
}

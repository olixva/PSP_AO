
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Parking {
    private int maxparking;
    private Semaphore estacionados;
	private Semaphore accesoContador;
	private Lock miLock = new ReentrantLock();
	private int contadorEstacionamientos;
	private long tiempoEstacionamientos;
    
	public Parking(int maxparking) {
		this.maxparking = maxparking;
		this.estacionados = new Semaphore(maxparking);
		this.accesoContador = new Semaphore(1);
		this.contadorEstacionamientos = 0;
	}

	public int getContadorEstacionamientos() { //Obtiene el contador de coches totales estacionados
		int contador = 0;
		try {
			accesoContador.acquire();
			contador = contadorEstacionamientos;
			accesoContador.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return contador;
	}

	public void entrarParking() { //Metodo en desuso
		try {
			estacionados.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	public void salirParking() { //Metodo que hace salir al coche del parking y aumenta el contador de estacionados
		estacionados.release();
		try {
			accesoContador.acquire();
			contadorEstacionamientos++;
			accesoContador.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean esDentroParking() { //Comprueba si puede entrar al parking, si puede entra y si no devuelve false
		return estacionados.tryAcquire();
	}

	public void addTiempoEstacionado(long tiempoCoche) { //Agrega el tiempo estacionado de un coche a la variable total
		miLock.lock();
		tiempoEstacionamientos += tiempoCoche;
		miLock.unlock();
	}

	public long getTiempoEstacionamientos() { 
		long tiempo;
		
		miLock.lock();
		tiempo = tiempoEstacionamientos;
		miLock.unlock();
		return tiempo;
	}
}
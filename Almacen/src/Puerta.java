
import java.util.concurrent.Semaphore;

// Clase semaforo
public class Puerta {
	
	private Semaphore puertaAbreCierra;
	
	public Puerta() {
		this.puertaAbreCierra = new Semaphore(1);
	}
	
	public boolean tryOcuparPuerta() {
		return this.puertaAbreCierra.tryAcquire();
	}

	public void liberarPuerta() {
		this.puertaAbreCierra.release();
	}
	
}


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Sincro {

	private CyclicBarrier clientesListos; // Inicio concurrente de clientes
	private CountDownLatch esperaClientes; // Para que el main espere a que los clientes finalicen
	
	public Sincro(int clientes) {
		this.clientesListos = new CyclicBarrier(clientes);
		this.esperaClientes = new CountDownLatch(clientes);
	}

	public void notificarClienteListo() {
		try {
			this.clientesListos.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

	public void esperarClientesTerminen() {
		try {
			this.esperaClientes.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void finalizarCliente() {
		this.esperaClientes.countDown();
	}

}

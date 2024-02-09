
public class Cliente extends Thread {

	private int id;
	private Almacen almacen;
	private Puerta puerta;
	private Sincro sincro;
	private int paciencia;

	public Cliente(int id, Almacen almacen, Puerta puerta, Sincro sincro) {
		this.id = id;
		this.almacen = almacen;
		this.puerta = puerta;
		this.sincro = sincro;
		this.paciencia = 10;
		this.start();
	}

	@Override
	public void run() {
		// Notificamos que el cliente esta listo para entrar en el almacen
		sincro.notificarClienteListo();

		boolean fin = false;
		while (!fin) {

			if (puerta.tryOcuparPuerta()) {

				System.out.println("El cliente " + id + " ha entrado en el almacen.");
				if (almacen.getCantidadProductos() != 0) {

					System.out.println("El cliente " + id + " ha comprado un producto y se ha marchado.");
					almacen.venderProducto();
				} else {
					System.out.println("El cliente " + id + " no ha podido comprar un producto y se ha marchado.");
				}
				
				fin = true;
				puerta.liberarPuerta();

			} else {

				System.out.println(
						"El cliente " + id + " no ha podido entrar en el almacen, su paciencia es de " + --paciencia);

				if (paciencia == 0) {
					System.out.println("El cliente " + id + " se ha marchado sin conseguir producto.");
					fin = true;
				}
			}
		}
		sincro.finalizarCliente();
	}
}

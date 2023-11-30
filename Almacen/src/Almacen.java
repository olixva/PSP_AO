
public class Almacen {

	private int productos;
	
	public Almacen(int productos) {
		this.productos = productos;
	}
	
	public void venderProducto() {
		this.productos--;
	}
	
	public int getCantidadProductos() {
		return this.productos;
	}
	
}

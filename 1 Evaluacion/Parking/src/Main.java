public class Main {
	static final int SIMULACOCHES = 100;
	static final int MAX_PARKING = 25;
	
	public static void main(String[] args) {
		Coche[] coche = new Coche[SIMULACOCHES];
        Parking parking = new Parking(MAX_PARKING);
        Sincro sincro = new Sincro(MAX_PARKING, SIMULACOCHES);
        
        System.out.println("Iniciando simulación ...");
        long inicioSimulacion = System.currentTimeMillis();

        for (int i = 0; i < coche.length; i++) {
			coche[i] = new Coche(i,sincro,parking);
		}
        sincro.esperarFinCoches();

        long finSimulacion = System.currentTimeMillis();
        long tiempoSimulacion = finSimulacion - inicioSimulacion;
        
        System.out.println("Tiempo simulación " + tiempoSimulacion);
        System.out.println("Han entrado " + parking.getContadorEstacionamientos());
        System.out.println("El tiempo medio de estancia es " + parking.getTiempoEstacionamientos());
	}

}
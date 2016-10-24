import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class HiloCliente extends Thread{

Socket paquete;
	
	public HiloCliente(Socket paquete){
		this.paquete = paquete;
	}
	
	@Override
	public void run() {
		InputStream entrada = null;
		PrintStream salida = null;
		
		try {
			
			salida = new PrintStream(paquete.getOutputStream());
			
			
			String cadenaJson = generarJsonAMano();

			salida.println(cadenaJson);
			System.out.println("Enviando: " + cadenaJson);
			
			salida.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public String generarJsonAMano(){
		String s = null;
		StringBuilder sb = new StringBuilder();
		sb.append("{\"latitud\":");
		Random random = new Random();
		double resultadoLatitud = ThreadLocalRandom.current().nextDouble(-90,90);
		sb.append(resultadoLatitud);
		sb.append(",\"longitud\":");
		double resultadolongitud = ThreadLocalRandom.current().nextDouble(-180,180);
		sb.append(resultadolongitud);
		sb.append("}");
		
		/*Esto era para probar que no se repitieran los puntos
		 * 
		double resultadoLatitud = 33.;
		sb.append(resultadoLatitud);
		sb.append(",\"longitud\":");
		double resultadolongitud = 33.;
		sb.append(resultadolongitud);
		sb.append("}");*/
		
		
		s = sb.toString();
		
		return s;
	}
}

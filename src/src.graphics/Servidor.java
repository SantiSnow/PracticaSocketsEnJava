package src.graphics;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;
public class Servidor  {
	//metodo main
	public static void main(String[] args) {
		MarcoServidor mimarco=new MarcoServidor();
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	
}
//---------------------------------fin de la clase
class MarcoServidor extends JFrame implements Runnable{
	//atributos
	private	JTextArea areatexto;
	//constructor
	public MarcoServidor(){
		
		setBounds(200,300,480,550);				
			
		JPanel milamina= new JPanel();
		
		milamina.setLayout(new BorderLayout());
		
		areatexto=new JTextArea();
		
		milamina.add(areatexto,BorderLayout.CENTER);
		
		add(milamina);
		
		setVisible(true);
		
		Thread hilo1 = new Thread(this);
		
		hilo1.start();
		
		}
	//metodos
	@Override
	public void run() {
		
		try {
			//creamos un serverSocket ponemos el socket a la escucha
			ServerSocket socketServidor = new ServerSocket(9000);
			//creamos un bucle para que no se cierre y podamos reenviar mensajes
			while(true) {
				//que acepte las conexiones del puerto
				Socket miSocket = socketServidor.accept();
				//creamos un flujo de datos de entrada
				DataInputStream flujoEntrada = new DataInputStream(miSocket.getInputStream());
				//leemos lo que entra por ese flujo
				String informacionEntrada = flujoEntrada.readUTF();
				//colocamos la info en pantalla
				areatexto.append("\n" + informacionEntrada);
				//cerramos el socket
				miSocket.close();
			}
		} catch (IOException e) {
			System.out.println("Se ha producido una excepcion del tipo IOException en el servidor: "+e.getMessage());
		}
	}
}

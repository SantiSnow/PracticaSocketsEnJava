package src.graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.swing.*;
import java.net.*;

public class Cliente {
	//metodo main
	public static void main(String[] args) {
		MarcoCliente mimarco=new MarcoCliente();
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
//-----------------------fin de la clase
class MarcoCliente extends JFrame{
	//constructor
	public MarcoCliente(){
		
		setBounds(600,300,280,350);
				
		LaminaMarcoCliente milamina=new LaminaMarcoCliente();
		
		add(milamina);
		
		setVisible(true);
	}	
	
}
//-----------------------fin de la clase
class LaminaMarcoCliente extends JPanel{
	//atributos
	private JTextField campo1;
	private JButton miboton;
	
	//clase interna
	private class EnviaTexto implements ActionListener{

		//dentro de la clase creamos todo el socket
		@Override
		public void actionPerformed(ActionEvent e) {
			//para crear un socket necesitamos la direccion ip y el puerto abierto
			try {
				//mi socket
				Socket miSocket = new Socket("192.168.57.1", 9000);
				
				//flujo de datos de salida que saldra por el socket
				DataOutputStream flujoSalida = new DataOutputStream(miSocket.getOutputStream());
				
				//indicamos que informacion saldra
				flujoSalida.writeUTF(campo1.getText());
				
				//cerramos el stream
				flujoSalida.close();
				
			} catch (UnknownHostException e1) {
				System.out.println("Se ha producido una excepcion del tipo UnknownHostException en el cliente: "+e1.getMessage());
			} catch (IOException e1) {
				System.out.println("Se ha producido una excepcion del tipo IOException en el cliente: "+e1.getMessage());
			}
		}//fin del metodo action performed
		
	}//fin de la clase actionListener
	
	//constructor
	public LaminaMarcoCliente(){
	
		JLabel texto=new JLabel("Cliente");
		
		add(texto);
	
		campo1=new JTextField(20);
	
		add(campo1);		
	
		miboton=new JButton("Enviar");
		
		EnviaTexto enviaTexto = new EnviaTexto();
		
		miboton.addActionListener(enviaTexto);
		
		add(miboton);
	}
//--------------------------fin de la clase
}

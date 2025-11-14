package UserLayer;

import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import LogicLayer.Admin;
import LogicLayer.Banco;
import LogicLayer.Cliente;
import LogicLayer.Cuenta;
import LogicLayer.Usuario;
import LogicLayer.Validaciones;

public class Main {
public static void main(String[] args) {
	
	
	Banco mercadoPago=new Banco("Banco virtual Keith");
	       
	Cliente pepe = new Cliente("pepe","0000",false,
		    "Pepe", "Av.Belgrano 1121", 12345678, 98764321,
		    "pepe@gmail.com",
		    new Cuenta(1000,500,"pepe.mp",mercadoPago)
		);

		Usuario.getListusuarios().add(pepe);
		Admin.getListasClientes().add(pepe);

		Cliente pepa = new Cliente("pepa","1111",false,
			    "Pepa", "Av.Calchaqui 1311", 87654321, 45483644,
			    "pepa@gmail.com",
			    new Cuenta(10500,10,"pepapig.mp",mercadoPago)
			);

			Usuario.getListusuarios().add(pepa);
			Admin.getListasClientes().add(pepa);

	
       
     Admin admin = new Admin("admin", "1234", true,"IT");
     Usuario.getListusuarios().add(admin);
	
	JOptionPane.showMessageDialog(null, "Bienvenidos a la billetera virtual Keith","Banco Keith", JOptionPane.DEFAULT_OPTION,
			new ImageIcon(Main.class.getResource("../img/banco.png")));

	String[] inicio = {"Login", "Registrar","Salir"};
	int eleccion;
	
	do {
		 eleccion = JOptionPane.showOptionDialog(null, "Elija alguna opción",
                 "Banco Enith", 0,0, null, inicio, inicio[0]);

		//por si se apreta el boton de la cruz o cancelar aparece eso
		if (eleccion==-1 || eleccion==2) {
			JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
			break;
		}
		switch (eleccion) {
		case 0: // LOGIN
			String usuario=Validaciones.IngresarString("Ingrese su nombre de usuario");
			String contra=Validaciones.IngresarString("Ingrese su contraseña");
			Usuario logueado = Usuario.Login(usuario,contra);
			if (logueado==null) {
				
				 JOptionPane.showMessageDialog(null, "Volviendo al menú principal...","Cargando",JOptionPane.DEFAULT_OPTION,new ImageIcon(Main.class.getResource("../img/cargando.png")));
	                break; 
			}else {
				logueado.Menu();
			}
			
			break;
		case 1: // REGISTRAR
			Usuario.Registrar();
			
			
			break;
			
		}
	} while (eleccion!=2);
	JOptionPane.showMessageDialog(null, "Programa finalizado.");

	
}//fin de main
}//fin de clase

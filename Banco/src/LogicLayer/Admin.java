package LogicLayer;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Admin extends Usuario {
	private String departamento;
	private static LinkedList<Cliente> listasClientes = new LinkedList<Cliente>();
	private static LinkedList<Movimiento> listasMovimientos = new LinkedList<Movimiento>();

	public Admin(String usuario, String contrasenia, boolean esAdmin, String departamento) {
		super(usuario, contrasenia, esAdmin);
		this.departamento = departamento;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public static LinkedList<Cliente> getListasClientes() {
		return listasClientes;
	}

	public static void setListasClientes(LinkedList<Cliente> listasClientes) {
		Admin.listasClientes = listasClientes;
	}

	public static LinkedList<Movimiento> getListasMovimientos() {
		return listasMovimientos;
	}

	public static void setListasMovimientos(LinkedList<Movimiento> listasMovimientos) {
		Admin.listasMovimientos = listasMovimientos;
	}

	@Override
	public String toString() {
		return "Admin [listasClientes=" + listasClientes + ", listasMovimientos=" + listasMovimientos + "]";
	}
	

	@Override
	public void Menu() {
		String opciones[]= {"Mostrar clientes","Eliminar cliente","Ver movimientos generales","Salir"};
		int opcion;
	     do {
	           opcion = JOptionPane.showOptionDialog(null, "Elija alguna opción",
	                   "Menú admin", 0,0, null, opciones, opciones[0]);
	          switch (opcion) {
			case 0:		//mostrar clientes	
				JOptionPane.showMessageDialog(null, this.listasClientes);
				break;
			case 1:		//eliminar clientes	
				
				break;
			case 2:// ver movimientos generales
				JOptionPane.showMessageDialog(null, this.listasMovimientos);
               break;
			
			default:
				break;
			}
	       } while (opcion !=3 );
		
	}
}

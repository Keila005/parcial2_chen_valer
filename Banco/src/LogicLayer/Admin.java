package LogicLayer;

import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Admin extends Usuario {
	private String departamento;
	private static LinkedList<Cliente> listasClientes = new LinkedList<Cliente>();
	private static LinkedList<Movimiento> listasMovimientos = new LinkedList<Movimiento>();
	

public Admin(String usuario, String contrasenia, String departamento) {
		super(usuario, contrasenia);
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
		return "Admin [departamento=" + departamento + "]";
	}

	@Override
	public void Menu() {
		String opciones[]= {"Mostrar clientes","Eliminar cliente","Ver movimientos generales","Salir"};
		int opcion;
	     do {
	           opcion = JOptionPane.showOptionDialog(null, "Elija alguna opción",
	                   "Menú admin", 0,JOptionPane.DEFAULT_OPTION, 
	                   new ImageIcon(Admin.class.getResource("/img/admin.png")), opciones, opciones[0]);
	          switch (opcion) {
	          
			case 0:		//mostrar clientes	
				if (listasClientes.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No hay clientes","Vacio",JOptionPane.DEFAULT_OPTION,
							new ImageIcon(Admin.class.getResource("/img/nohay.png")));
				}else {
					String[] gente = new String[listasClientes.size()];
					for (int i = 0; i < gente.length; i++) {
						gente[i] = listasClientes.get(i).getNombre_completo();
					}
					
					int persona =JOptionPane.showOptionDialog(null,
							"Elija los datos del cliente que deseas ver","Ver datos", 0,
							JOptionPane.DEFAULT_OPTION,
							new ImageIcon(Admin.class.getResource("/img/contacto.png")), gente, gente[0]);
					
				JOptionPane.showMessageDialog(null, listasClientes.get(persona));
				}
					
				break;
			case 1:		//eliminar clientes	
				if (listasClientes.isEmpty()) {
					JOptionPane.showMessageDialog(null, "La lista no tiene clientes","No existe",JOptionPane.DEFAULT_OPTION,
							new ImageIcon(Admin.class.getResource("/img/nohay.png")));
				} else {
					String[] elegible = new String[listasClientes.size()];
					
					// este es el arrays de menu
					for (int i = 0; i < elegible.length; i++) {
						elegible[i] = listasClientes.get(i).getNombre_completo();
					}
					int elec =JOptionPane.showOptionDialog(null,
							"Elija el cliente que deseas eliminar","Eliminar", 0,
							JOptionPane.DEFAULT_OPTION,new ImageIcon(Admin.class.getResource("/img/eliminar.png")), elegible, elegible[0]);
					
					int confirmar = JOptionPane.showConfirmDialog(null, "Seguro de eliminar a " + listasClientes.get(elec));
					
				
					if (confirmar==JOptionPane.YES_OPTION) {
						Cliente clienteSeleccionado = listasClientes.get(elec);
						listasClientes.remove(clienteSeleccionado);
						JOptionPane.showMessageDialog(null, "Se eliminó a :"+ clienteSeleccionado.getNombre_completo(),"Eliminado correctamente",
								JOptionPane.DEFAULT_OPTION,new ImageIcon(Admin.class.getResource("/img/correcto.png")));
					}else {
						JOptionPane.showMessageDialog(null, "No se eliminó a :"+ listasClientes.get(elec).getNombre_completo(),
								"Error en la eliminacion",JOptionPane.DEFAULT_OPTION,new ImageIcon(Admin.class.getResource("/img/nohay.png")));
					}
				}

				break;
			case 2:// ver movimientos generales
				if (this.listasMovimientos.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No hay ningun movimiento","Vacio",JOptionPane.DEFAULT_OPTION,
							new ImageIcon(Admin.class.getResource("/img/nohay.png")));
				}else {
					JOptionPane.showMessageDialog(null, this.listasMovimientos,"Movimientos generales",JOptionPane.INFORMATION_MESSAGE);
				}
				
               break;
			
			default:
				break;
			}
	       } while (opcion !=3 );
		
	}
}

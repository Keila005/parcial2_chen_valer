package LogicLayer;

import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public abstract class Usuario {
	
	private static LinkedList<Usuario> listusuarios = new LinkedList<Usuario>();
	private String usuario;
	private String contrasenia;
	
	
	public Usuario(String usuario, String contrasenia) {
		super();
		this.usuario = usuario;
		this.contrasenia = contrasenia;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	

	public static LinkedList<Usuario> getListusuarios() {
		return listusuarios;
	}

	public static void setListusuarios(LinkedList<Usuario> listusuarios) {
		Usuario.listusuarios = listusuarios;
	}

	@Override
	public String toString() {
		return "Login usuario=" + usuario + ", contraseña=" + contrasenia;
	} 
	
	public abstract void Menu();
	
	public static Usuario Login(String usuario, String contr) {
		
		  for (Usuario user : listusuarios) {
		        if (user.getUsuario().equalsIgnoreCase(usuario) && user.getContrasenia().equals(contr)) {
		            return user; // entra al menu
		        }
		    }

		    boolean usuarioExiste = false;
		    for (Usuario user : listusuarios) {
		        if (user.getUsuario().equalsIgnoreCase(usuario)) { // si se ingresa el mismo nombre de usuario, 
		        	//pero la contra esta mal
		            usuarioExiste = true;
		            break;
		        }
		    }

		    if (usuarioExiste) {
		        JOptionPane.showMessageDialog(null, "La contraseña es incorrecta","Incorrecto",JOptionPane.DEFAULT_OPTION,
		        		new ImageIcon(Usuario.class.getResource("/img/nohay.png")));
		    } else {
		        JOptionPane.showMessageDialog(null, "El usuario no está registrado","No registrado",JOptionPane.DEFAULT_OPTION,
		        		new ImageIcon(Usuario.class.getResource("/img/nohay.png")));
		    }
		    return null;
	
		
	}  //fin de login
	
	public static void Registrar(){
		
		String nuevoUser=Validaciones.IngresarString("Ingrese el nombre del usuario que deseas crear");
		
		for (Usuario user : listusuarios) {
			if (user.getUsuario().equalsIgnoreCase(nuevoUser)) {
	JOptionPane.showMessageDialog(null, "El usuario " +nuevoUser+" ya esta registrado, debes ir al Login","Ya registrado",JOptionPane.DEFAULT_OPTION,
			new ImageIcon(Usuario.class.getResource("/img/nohay.png")));
	return; //salir del metodo if
				
			}// fin del if, si ya existe		
			}// fin del for
		 String nuevaContra = Validaciones.IngresarString("Ingrese una contraseña:");
		 String nombre=Validaciones.IngresarString("Ingrese su nombre completo (nombre y apellido):");
		 String direccion=Validaciones.IngresarString("Ingrese su direccion");
		 int doc=Validaciones.IngresarInt("Ingrese su dni");
		 int tel=Validaciones.IngresarInt("Ingrese su numero de telefono");
		 String mail=Validaciones.IngresarMail("Ingrese su gmail");
		
		 String alias;
		    boolean aliasExiste;
		    do {
		        alias = Validaciones.IngresarString("Ingrese el alias que desea crear (ej: nombre.mp)");
		        aliasExiste = false;
		        for (Cliente client : Admin.getListasClientes()) {
		            if (client.getCuenta() != null && client.getCuenta().getAlias().equalsIgnoreCase(alias)) {
		                aliasExiste = true;
		                JOptionPane.showMessageDialog(null, "El alias ya existe, ingrese otro.","Alias existente",
		                		JOptionPane.DEFAULT_OPTION,	new ImageIcon(Usuario.class.getResource("/img/nohay.png")));
		                break;
		            }
		        }
		    } while (aliasExiste);
		    	
		    Cuenta nuevaCuenta = new Cuenta( 0, 0, alias, new Banco("Banco Virtual Keith"));
		    Cliente nuevoCliente = new Cliente(nuevoUser, nuevaContra, nombre, direccion, doc, tel, mail, nuevaCuenta);
		    Usuario.getListusuarios().add(nuevoCliente); //guardar en la lista de usuarios
		    Admin.getListasClientes().add(nuevoCliente); //guardar en la lista de clientes
		    
		    JOptionPane.showMessageDialog(null, "Usuario : "+nuevoUser+" se registro correctamente.\nYa puede iniciar sesión.","Registrado con éxito",
		    		JOptionPane.DEFAULT_OPTION,	new ImageIcon(Usuario.class.getResource("/img/correcto.png")));
		  
		    
		    return;// luego de registar y que se guarde el dato(en la lista de usuarios), debe ir al login 


	}// fin de registrar
}

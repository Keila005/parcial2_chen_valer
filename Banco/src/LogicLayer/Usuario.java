package LogicLayer;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public abstract class Usuario {
	
	private static LinkedList<Usuario> listusuarios = new LinkedList<Usuario>();
	private String usuario;
	private String contrasenia;
	private boolean esAdmin;
	
	public Usuario(String usuario, String contrasenia, boolean esAdmin) {
		super();
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.esAdmin = esAdmin;
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
	
	public boolean isEsAdmin() {
		if (usuario.equals("admin") && contrasenia.equals("1234")) {
			return this.esAdmin=true;
		}else {
			return this.esAdmin=false;
		}	
	}

	public void setEsAdmin(boolean esAdmin) {
		this.esAdmin = esAdmin;
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
			if (user.getUsuario().equals(usuario) && user.getContrasenia().equals(contr)) {
				return user;
			}
			if (!user.getUsuario().equals(usuario) && user.getContrasenia().equals(contr)) {
				JOptionPane.showMessageDialog(null, "La contraseña o el nombre del usuario es incorrecto"); //el usuario es incorrecto
				return null;
			}else if (user.getUsuario().equals(usuario) && !user.getContrasenia().equals(contr)) { //la contraseña es incorrecto
				JOptionPane.showMessageDialog(null, "La contraseña o el nombre del usuario es incorrecto");
				return null;
			}
		}
		JOptionPane.showMessageDialog(null, "El usuario no está registrado");
		return null;
		
	}  //fin de login
	
	public static void Registrar(){
		
		String nuevoUser=Validaciones.IngresarString("Ingrese el nombre del usurio que deseas crear");
		for (Usuario user : listusuarios) {
			if (user.getUsuario().equalsIgnoreCase(nuevoUser)) {
	JOptionPane.showMessageDialog(null, "El usuario ya esta registrado, debes ir al Login");
	return; //salir del metodo if
				
			}// fin del if, si ya existe
		
				 String nuevaContra = Validaciones.IngresarString("Ingrese una contraseña:");
				 String nombre=Validaciones.IngresarString("Ingrese su nombre");
				 String direccion=Validaciones.IngresarString("Ingrese su direccion");
				 int doc=Validaciones.IngresarInt("Ingrese su dni");
				 int tel=Validaciones.IngresarInt("Ingrese su numero de telefono");
				 String mail=Validaciones.IngresarMail("Ingrese su gmail");
				
				 Usuario.getListusuarios().add(new Cliente(nuevoUser, nuevaContra, false, nombre, direccion, doc,tel,mail,new Cuenta()));
				
				    JOptionPane.showMessageDialog(null, "Usuario registrado correctamente.\nYa puede iniciar sesión.");
				    Admin.getListasClientes().add(new Cliente(nuevoUser,nuevaContra,false,nombre,direccion,doc,tel,mail));
				    
				    return;// luego de registar y que se guarde el dato(en la lista de usuarios), debe ir al login 
	
		
			}
	}// fin de registrar
}

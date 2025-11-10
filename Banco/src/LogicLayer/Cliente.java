package LogicLayer;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Cliente extends Usuario {
	private String nombre_completo;
	private String direccion;
	private int dni;
	private int tel;
	private String email;
	private Cuenta cuenta;
	private static LinkedList<Contacto> cuentasGuardadas = new LinkedList<Contacto>();
	

	public Cliente(String usuario, String contrasenia, boolean esAdmin, String nombre_completo, String direccion,
			int dni, int tel, String email, Cuenta cuenta) {
		
		super(usuario, contrasenia, esAdmin);
		this.nombre_completo = nombre_completo;
		this.direccion = direccion;
		this.dni = dni;
		this.tel = tel;
		this.email = email;
		this.cuenta = cuenta;
	}


	public Cliente(String usuario, String contrasenia, boolean esAdmin, String nombre_completo, String direccion,
			int dni, int tel, String email) {
		super(usuario, contrasenia, esAdmin);
		this.nombre_completo = nombre_completo;
		this.direccion = direccion;
		this.dni = dni;
		this.tel = tel;
		this.email = email;
	}


	public String getNombre_completo() {
		return nombre_completo;
	}

	public void setNombre_completo(String nombre_completo) {
		this.nombre_completo = nombre_completo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public int getTel() {
		return tel;
	}

	public void setTel(int tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public static LinkedList<Contacto> getCuentasGuardadas() {
		return cuentasGuardadas;
	}

	public static void setCuentasGuardadas(LinkedList<Contacto> cuentasGuardadas) {
		Cliente.cuentasGuardadas = cuentasGuardadas;
	}

	
	@Override
	public String toString() {
		return "Cliente:\nNombre_completo=" + nombre_completo + ", direccion=" + direccion + ", dni=" + dni + ", tel="
				+ tel + ", email=" + email + "\n";
	}

	@Override
	public void Menu() {

		    Tipo_operacion[] operaciones = Tipo_operacion.values();
		    
		 String[] nombres = new String[operaciones.length];
		    for (int i = 0; i < operaciones.length; i++) {
		        nombres[i] = operaciones[i].name(); 
		    }
		int opcion;
		do {
	
			opcion= JOptionPane.showOptionDialog(null, "Elija alguna operacion",
	                   "Menú cliente", 0,0, null, nombres, nombres[0]);
			double monto;
			switch (opcion) {
			case 0:// retirar dinero
				
				  Tipo_operacion retirar = Tipo_operacion.Retirar_dinero; // obtenemos el enum
				    String[] subopciones = retirar.getOpciones(); // obtenemos las opciones: En locales / agencias
				    
				    int opcionRetiro = JOptionPane.showOptionDialog(null, 
				        "Seleccione cómo desea retirar dinero:", 
				        "Retirar dinero", 
				        JOptionPane.DEFAULT_OPTION, 
				        JOptionPane.INFORMATION_MESSAGE, 
				        null, subopciones, subopciones[0]);
				    String lugarSeleccionado="";
						    switch (opcionRetiro) {
						   
							case 0:// en locales
								String []locales= {"Rapipago","Pago Fácil","Carrefour","Coto"};
								String seleccion=(String)JOptionPane.showInputDialog(null,"Seleccione el lugar que deseas retirar dinero","Locales",0,null,locales,locales[0]);
	
								if (seleccion != null) { 
								    lugarSeleccionado = seleccion; // guardar el nombre
								}
							 
								break;// fin de caso 0
							case 1: // en agencias
								String []agencias= {"Banco Nación", "Banco Provincia", "Santander", "Galicia"};
								String eleccion=(String)JOptionPane.showInputDialog(null,"Seleccione el lugar que deseas retirar dinero","Locales",0,null,agencias,agencias[0]);
								if (eleccion != null) { 
								    lugarSeleccionado = eleccion; // guardar el nombre
								}
								
								break; // fin de caso 1
										
					}// fin de elegir los lugares
						    
						    // Se realiza la accion:
						    if (!lugarSeleccionado.isEmpty()) {
						         monto = Validaciones.IngresarDouble("Ingrese la cantidad de dinero que deseas retirar en " + lugarSeleccionado);
						        this.getCuenta().retirarDinero(monto, this.nombre_completo);
						        JOptionPane.showMessageDialog(null, "Ingreso realizado en: " + lugarSeleccionado);
						    } 
				
				break; // fin del caso 0
				
			case 1://ingresar dinero
				
				 Tipo_operacion ingresar = Tipo_operacion.Ingresar_dinero; // obtenemos el enum
				    String[] subopciones2 = ingresar.getOpciones(); // obtenemos las opciones
				    
				    int opcionIngresar = JOptionPane.showOptionDialog(null, 
				        "Seleccione cómo deseas ingresar dinero:", 
				        "Ingresar dinero", 
				        JOptionPane.DEFAULT_OPTION, 
				        JOptionPane.INFORMATION_MESSAGE, 
				        null, subopciones2, subopciones2[0]);
				 
						    switch (opcionIngresar) {
							case 0:// en efectivo
								
								String []locales= {"Rapipago","Carrefour","Coto","Banco Nación","Galicia"};
								String seleccion=(String)JOptionPane.showInputDialog(null,"Seleccione el lugar que deseas retirar dinero","Locales",0,null,locales,locales[0]);
	
								if (seleccion != null) { 
							monto = Validaciones.IngresarDouble("Ingrese la cantidad de dinero que deseas ingresar desde " + seleccion);
								        this.getCuenta().ingresarDinero(monto, this.getNombre_completo());
								        JOptionPane.showMessageDialog(null, "Ingreso realizado en: " + seleccion);
								}
							 
								break;// fin de caso 0
								
							case 1: // transferencia
								if (this.cuenta.getContactos().isEmpty()) {
									JOptionPane.showMessageDialog(null, "No hay ningun contacto disponible");
								}else {
									String[] persona = new String[this.cuenta.getContactos().size()];
									
								for (int i = 0; i < persona.length; i++) {
									persona[i] = this.cuenta.getContactos().get(i).getNombre();
								}
								
								String eleccion=(String)JOptionPane.showInputDialog(null,"Seleccione a quien deseas transferir","Transferencia",0,null,persona,persona[0]);
								
								if (eleccion != null) { 
			 monto = Validaciones.IngresarDouble("Ingrese la cantidad de dinero que necesitas pedir a "+ eleccion);
						  this.getCuenta().ingresarDinero(monto, this.nombre_completo);
						JOptionPane.showMessageDialog(null, eleccion+" te transfirio $"+ monto);
									}
								} 
								
								break; // fin de caso 1
										
					}// fin de elegir
						    
				break;  // fin del caso 1
				
			case 2://transferir dinero
				 Tipo_operacion transferir = Tipo_operacion.Transferir_dinero; // obtenemos el enum
				    String[] subopciones3 = transferir.getOpciones(); // obtenemos las opciones
				    
				    int opcionTransferir = JOptionPane.showOptionDialog(null, 
				        "Seleccione cómo deseas trasnferir dinero:", 
				        "Transferir dinero", 
				        JOptionPane.DEFAULT_OPTION, 
				        JOptionPane.INFORMATION_MESSAGE, 
				        null, subopciones3, subopciones3[0]);
				    
				    switch (opcionTransferir) {
					case 0: // por alias o cbu
						String alias=Validaciones.IngresarString("Ingrese el alias o cbu/cvu");
						monto = Validaciones.IngresarDouble("Ingrese la cantidad de dinero que deseas transferir al "+alias);
						this.cuenta.transferirDinero(monto, this.nombre_completo);
						break;
						
					case 1: //por contactos
						
						if (this.cuenta.getContactos().isEmpty()) {
							JOptionPane.showMessageDialog(null, "No hay ningun contacto disponible\nSi deseas agregar contacto ir a: Otras_Funciones->Agregar Contactos");
						}else {
							String[] persona = new String[this.cuenta.getContactos().size()];
							
						for (int i = 0; i < persona.length; i++) {
							persona[i] = this.cuenta.getContactos().get(i).getNombre();
						}
						
						String destino=(String)JOptionPane.showInputDialog(null,"Seleccione a quien deseas transferir","Transferencia",0,null,persona,persona[0]);
						
						if (destino != null) { 
							monto=Validaciones.IngresarDouble("Ingrese la cantidad de dinero que necesitas transferir a "+ destino);
							this.getCuenta().transferirDinero(monto,this.nombre_completo);
				
				JOptionPane.showMessageDialog(null, "Transferiste a "+destino+" $"+ monto+" exitosamente");
							}
						}  // fin de else
						break; // fin contacto
						
					}
				break;
				
			case 3://dolares
				Tipo_operacion dolares=Tipo_operacion.Dolares;
				String [] subopciones4= dolares.getOpciones();
				
				 int opcionDolares= JOptionPane.showOptionDialog(null, 
					        "Seleccione qué deseas realizar:", 
					        "Comprar/Verder dinero", 
					        JOptionPane.DEFAULT_OPTION, 
					        JOptionPane.INFORMATION_MESSAGE, 
					        null, subopciones4, subopciones4[0]);
				 switch (opcionDolares) {
				case 0: // comprar
					
					monto=Validaciones.IngresarDouble("Ingrese la cantidad de dinero en ARS que se quiere vender para obtener USD");
					this.cuenta.comprarDolares(monto, this.nombre_completo);
					break;
				case 1: // vender
					
					monto=Validaciones.IngresarDouble("Ingrese la cantidad de dinero en USD que se quiere vender para obtener ARS");
					this.cuenta.venderDolares(monto, this.nombre_completo);
					
					break;

				}
				break; // fin del caso 2
				
			case 4://otras funciones
				Tipo_operacion otras=Tipo_operacion.Otras_Funciones;
				String [] subopciones5= otras.getOpciones();
				
				 int opcionOtras= JOptionPane.showOptionDialog(null, 
					        "Seleccione qué deseas realizar:", 
					        "Comprar/Verder dinero", 
					        JOptionPane.DEFAULT_OPTION, 
					        JOptionPane.INFORMATION_MESSAGE, 
					        null, subopciones5, subopciones5[0]);
				 switch (opcionOtras) {
				case 0:  // agregar contactos
					String contacto=Validaciones.IngresarString("Ingrese el nombre del contacto");
					String alias=Validaciones.IngresarString("Ingrese el alias del contacto");
					int cbu=Validaciones.IngresarInt("Ingrese el cbu/cvu del contacto");
					
					this.cuenta.getContactos().add(new Contacto(contacto,alias,cbu));
					
					break;
				case 1: // ver movimientos
					JOptionPane.showMessageDialog(null, this.cuenta.getListamov());
					
					break;
				case 2: // ver saldo
					JOptionPane.showMessageDialog(null, "Saldo actual en Pesos: "+this.cuenta.getSaldoPesos()+"\nSaldo Actual en Dólares: "+this.cuenta.getSaldoDolar());
					
					break;
				
				}
				
				break; // fin del caso 4

				// agregar caso 5 invertir dinero
				}
		} while (opcion!=5); // agregar la opcion seria 6
		
	}
	
	
	

}

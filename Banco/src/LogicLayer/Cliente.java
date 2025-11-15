package LogicLayer;

import java.util.LinkedList;

import javax.swing.ImageIcon;
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
		return "Cliente :\nNombre completo=" + nombre_completo + "\nDireccion=" + direccion + "\nDni=" + dni + "\nTel="
				+ tel + "\nEmail=" + email + "\nDatos de la cuenta=\nCbu/cvu:"+ getCuenta().getCbu_cvu()+"\nAlias:"+ getCuenta().getAlias() + "\n";
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
	
			opcion= JOptionPane.showOptionDialog(null, "Elija alguna operacion\n"+"Saldo actual en Pesos: "+this.cuenta.getSaldoPesos()+"\nSaldo actual en Dólares: "+this.cuenta.getSaldoDolar(),
	                   "Menú cliente", 0,JOptionPane.DEFAULT_OPTION, 
	                   new ImageIcon(Cliente.class.getResource("/img/cliente.png")), nombres, nombres[0]);
			double monto;
			switch (opcion) {
			case 0:// retirar dinero
				
				  Tipo_operacion retirar = Tipo_operacion.Retirar; // obtenemos el enum
				    String[] subopciones = retirar.getOpciones(); // obtenemos las opciones: En locales / agencias
				    
				    int opcionRetiro = JOptionPane.showOptionDialog(null, 
				        "Seleccione cómo desea retirar dinero:", 
				        "Retirar dinero", 
				        0, 
				        JOptionPane.DEFAULT_OPTION, 
				        new ImageIcon(Cliente.class.getResource("/img/retirar.jpg")), subopciones, subopciones[0]);
				    String lugarSeleccionado="";
						    switch (opcionRetiro) {
						   
							case 0:// en locales
								String []locales= {"Rapipago","Pago Fácil","Carrefour","Coto"};
								String seleccion=(String)JOptionPane.showInputDialog(null,"Seleccione el lugar que deseas retirar dinero","Locales",0,
										new ImageIcon(Cliente.class.getResource("/img/locales.png")),locales,locales[0]);
	
								if (seleccion != null) { 
								    lugarSeleccionado = seleccion; // guardar el nombre
								}
							 
								break;// fin de caso 0
							case 1: // en agencias
								String []agencias= {"Banco Nación", "Banco Provincia", "Santander", "Galicia"};
								String eleccion=(String)JOptionPane.showInputDialog(null,"Seleccione el lugar que deseas retirar dinero","Locales",0,
										new ImageIcon(Cliente.class.getResource("/img/agencias.png")),agencias,agencias[0]);
								if (eleccion != null) { 
								    lugarSeleccionado = eleccion; // guardar el nombre
								}
								
								break; // fin de caso 1
										
					}// fin de elegir los lugares
						    
						    // Se realiza la accion:
						    if (!lugarSeleccionado.isEmpty()) {
						         monto = Validaciones.IngresarDouble("Ingrese la cantidad de dinero que deseas retirar en " + lugarSeleccionado);
						        this.getCuenta().retirarDinero(monto, this.nombre_completo,lugarSeleccionado);
						    } 
				
				break; // fin del caso 0 RETIRAR DINERO
				
			case 1://ingresar dinero
				
				 Tipo_operacion ingresar = Tipo_operacion.Ingresar; // obtenemos el enum
				    String[] subopciones2 = ingresar.getOpciones(); // obtenemos las opciones
				    
				    int opcionIngresar = JOptionPane.showOptionDialog(null, 
				        "Seleccione cómo deseas ingresar dinero:", 
				        "Ingresar dinero",0,
				        JOptionPane.DEFAULT_OPTION, 
				        new ImageIcon(Cliente.class.getResource("/img/ingresar.png")), 
				        subopciones2, subopciones2[0]);
				 
						    switch (opcionIngresar) {
							case 0:// en efectivo
								
								String []locales= {"Rapipago","Carrefour","Coto","Banco Nación","Galicia"};
								String seleccion=(String)JOptionPane.showInputDialog(null,"Seleccione el lugar que deseas retirar dinero","Locales",
										0, new ImageIcon(Cliente.class.getResource("/img/efectivo.png")),locales,locales[0]);
	
								if (seleccion != null) { 
							monto = Validaciones.IngresarDouble("Ingrese la cantidad de dinero que deseas ingresar desde " + seleccion);
								        this.getCuenta().ingresarDinero(monto, this.getNombre_completo(),seleccion);
								}
							 
								break;// fin de caso caso0/caso 0
								
							case 1: // transferencia, solicitar que la persona te transfiera a vos
								String alias=Validaciones.IngresarString("Ingrese el alias");
								Cliente destinos=null;
								
								for (Cliente client : Admin.getListasClientes()) {
								if (client.getCuenta()!= null && client.getCuenta().getAlias().equalsIgnoreCase(alias)) {
								            destinos = client;
								            break;
								        }
								}// fin del FOR
								
								  if (destinos == null) {
					       JOptionPane.showMessageDialog(null, "No existe ningún usuario con ese alias.","Error alias",JOptionPane.DEFAULT_OPTION,
					    		   new ImageIcon(Cliente.class.getResource("/img/nohay.png")));
					     
								        break; // salir del caso
								    }else {
								    	monto = Validaciones.IngresarDouble("Ingrese la cantidad de dinero que deseas pedir al \nAlias: "+alias +"\nNombre:"+ destinos.getNombre_completo());
								    	
							this.cuenta.enviarDinero(monto, this.nombre_completo,destinos.getCuenta());
								
									}
								
								break;// fin del caso 0/caso1
										
					}// fin de elegir
						    
				break;  // fin del caso 1
				
			case 2://transferir dinero
				 Tipo_operacion transferir = Tipo_operacion.Transferir; // obtenemos el enum
				    String[] subopciones3 = transferir.getOpciones(); // obtenemos las opciones
				    
				    int opcionTransferir = JOptionPane.showOptionDialog(null, 
				        "Seleccione cómo deseas trasnferir dinero:", 
				        "Transferir dinero",0, 
				        JOptionPane.DEFAULT_OPTION, 
				        new ImageIcon(Cliente.class.getResource("/img/transferir.png")), 
				        subopciones3, subopciones3[0]);
				    
				    switch (opcionTransferir) {
					case 0: // por alias o cbu
						String alias=Validaciones.IngresarString("Ingrese el alias:");
						Cliente destinos=null;
						
						for (Cliente client : Admin.getListasClientes()) {
						if (client.getCuenta()!= null && client.getCuenta().getAlias().equalsIgnoreCase(alias)) {
						            destinos = client;
						            break;
						        }
						}// fin del FOR
						
						  if (destinos == null) {
						        JOptionPane.showMessageDialog(null, "No existe ningún usuario con ese alias.","Error alias",
						        		JOptionPane.DEFAULT_OPTION,
							    		   new ImageIcon(Cliente.class.getResource("/img/nohay.png")));
						        break; // salir del caso
						    }else {
						    	monto = Validaciones.IngresarDouble("Ingrese la cantidad de dinero que deseas transferir al \nAlias: "+alias +"\nNombre:"+ destinos.getNombre_completo());
								this.cuenta.transferirDinero(monto, this.nombre_completo,destinos.getCuenta());	
							}
						
						break;
						
					case 1: //por contactos
						
						if (this.cuenta.getContactos().isEmpty()) {
							JOptionPane.showMessageDialog(null, "No hay ningun contacto disponible\nSi deseas agregar contacto ir a: Otras_Funciones->Agregar Contactos");
						}else {
							String[] persona = new String[this.cuenta.getContactos().size()];
							
						for (int i = 0; i < persona.length; i++) {
							persona[i] = this.cuenta.getContactos().get(i).getNombre();
						}
						
						String destino=(String)JOptionPane.showInputDialog(null,"Seleccione a quien deseas transferir","Transferencia",0,
								new ImageIcon(Cliente.class.getResource("/img/contacto.png")),persona,persona[0]);
	
//						if (destino != null) { 
//							
//							 Cuenta enviado=null;
//								
//							 for (Contacto contac : this.cuenta.getContactos()) {
//								 if (contac.getNombre().equals(destino)) {
//									enviado=contac.getCuenta();
//									break;
//								}
//								
//							}// fin del FOR
//							 
//							 if (enviado!=null) { // si se recibio una cuenta destino
//								
//								 monto=Validaciones.IngresarDouble("Ingrese la cantidad de dinero que necesitas transferir a "+ destino);
//											this.getCuenta().transferirDinero(monto,this.nombre_completo,enviado);
//								
//								JOptionPane.showMessageDialog(null, "Transferiste exitosamente a "+destino+" $"+ monto,"Existosamente",
//										JOptionPane.DEFAULT_OPTION, new ImageIcon(Cliente.class.getResource("/img/correcto.png")));
//									}
//							
//							}// si eligio alguna persona
						Cuenta enviado = null;
						boolean encontrado = false;

						// 1. Busco en tus contactos cuál se eligió
						for (Contacto cont : this.cuenta.getContactos()) {
						    if (cont.getNombre().equals(destino)) {
						        // guardo su alias o CBU
						        String aliasContacto = cont.getAlias();
						        
						        // 2. busco la cuenta REAL de ese contacto entre todos los clientes
						        for (Cliente cli : Admin.getListasClientes()) {
						            if (cli.getCuenta() != null &&
						                cli.getCuenta().getAlias().equalsIgnoreCase(aliasContacto)) {
						                
						                enviado = cli.getCuenta();  
						                encontrado = true;
						                break;
						            }
						        }
						        break;
						    }
						}

						// 3. Si lo encontré → pedir monto
						if (encontrado && enviado != null) {
						     monto = Validaciones.IngresarDouble("¿Cuánto desea transferir a " + destino + "?");
						    this.getCuenta().transferirDinero(monto, this.nombre_completo, enviado);

						    JOptionPane.showMessageDialog(null,
						        "Transferiste exitosamente a " + destino + " $" + monto,
						        "Éxito",
						        JOptionPane.DEFAULT_OPTION,
						        new ImageIcon(Cliente.class.getResource("/img/correcto.png")));
						} else {
						    JOptionPane.showMessageDialog(null,
						        "No se encuentra la cuenta del contacto.",
						        "Error",
						        JOptionPane.DEFAULT_OPTION,
						        new ImageIcon(Cliente.class.getResource("/img/nohay.png")));
						}

						}  // fin de else
						break; // fin contacto
						
					}
				break;
				
			case 3://dolares
				Tipo_operacion dolares=Tipo_operacion.Dolares;
				String [] subopciones4= dolares.getOpciones();
				
				 int opcionDolares= JOptionPane.showOptionDialog(null, 
					        "Seleccione qué deseas realizar:\nComprar: $1450\nVender:$1400", 
					        "Comprar/Verder dinero", 0,
					        JOptionPane.DEFAULT_OPTION, 
					        new ImageIcon(Cliente.class.getResource("/img/dolares .png")), 
					       subopciones4, subopciones4[0]);
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
					        "Comprar/Verder dinero", 0,
					        JOptionPane.DEFAULT_OPTION, 
					        new ImageIcon(Cliente.class.getResource("/img/otro.png")), 
					      subopciones5, subopciones5[0]);
				 switch (opcionOtras) {
				case 0:  // agregar contactos
					
					String contacto=Validaciones.IngresarString("Ingrese el nombre del contacto");
					String alias=Validaciones.IngresarString("Ingrese el alias del contacto");
					boolean existe=false;
					
					  Cliente clienteEncontrado = null;
					    for (Cliente cli : Admin.getListasClientes()) {
					        if (cli.getCuenta() != null && cli.getCuenta().getAlias().equalsIgnoreCase(alias) 
					        		&& cli.getNombre_completo().equalsIgnoreCase(contacto) ) {
					            clienteEncontrado = cli;
					            existe=true;
					            break;
					        }
					    }
					    
					    if (clienteEncontrado == null) {
					        JOptionPane.showMessageDialog(null, "No existe ningún usuario con ese alias.","Error alias",JOptionPane.DEFAULT_OPTION,
					        		new ImageIcon(Cliente.class.getResource("/img/nohay.png")));
					        break;
					    }
					    
					    for (Contacto c : this.getCuenta().getContactos()) {
					        if (c.getAlias().equalsIgnoreCase(alias)) {
					           JOptionPane.showMessageDialog(null, "Ya tienes agregado un contacto con ese alias.","Alias existente",JOptionPane.DEFAULT_OPTION,
					        		   new ImageIcon(Cliente.class.getResource("/img/nohay.png")));
					           existe=false;
					           break;
					        }
					    }
					    
					    if (existe) {
					    	  int cbu = Validaciones.IngresarInt("Ingrese el CBU/CVU del contacto");
					    		this.cuenta.getContactos().add(new Contacto(contacto,alias,cbu));

							    JOptionPane.showMessageDialog(null, "Contacto agregado correctamente.","Agregado",JOptionPane.DEFAULT_OPTION,
							    		new ImageIcon(Cliente.class.getResource("/img/correcto.png")));
							
						}
					  
					
					break;
				case 1: // ver movimientos
					if (this.cuenta.getListamov().isEmpty()) {
						JOptionPane.showMessageDialog(null, "No hay ningun movimiento","Vacio",JOptionPane.DEFAULT_OPTION,
								new ImageIcon(Cliente.class.getResource("/img/nohay.png")));
					}else {
						JOptionPane.showMessageDialog(null, this.cuenta.getListamov(),"Movimientos",JOptionPane.DEFAULT_OPTION,
								new ImageIcon(Cliente.class.getResource("/img/mostrar.png")));
					}
					
					break;
				case 2: //ver datos	
					
						JOptionPane.showMessageDialog(null, "Alias:"+this.cuenta.getAlias()+"\nCBU/CVU:"+this.cuenta.getCbu_cvu()+"\nSaldo pesos: "+this.cuenta.getSaldoPesos()+
								"\nSaldo dolar: "+this.cuenta.getSaldoDolar()+"\nBanco: "+this.cuenta.getBanco());
				
				}
				
				break; // fin del caso 4

				// agregar caso 5 invertir dinero
				}
		} while (opcion!=5); // agregar la opcion seria 6
		
	}

	
	
	
	

}

package LogicLayer;

import javax.swing.JOptionPane;

public enum Tipo_operacion {
	
	Retirar(new String[]{"En locales","Agencias"}),
	Ingresar(new String[]{"En efectivo","Transferencia"}),
	Transferir(new String[]{"Alias/Cbu_cvu","Contacto"}),
	Dolares(new String[]{"Comprar","Vender"}),
	//agregar INVERTIR DINERO(new String[]{"Invertir","Ver interes"})
	Otras_Funciones(new String[] {"Agregar contactos","Ver Movimientos","Ver Saldo"}),
	Salir(new String[] {"Cerrar seccion"});
	
	private String[] opciones;
	
	private Tipo_operacion(String[] opciones) {
		this.opciones = opciones;
	}
	public String[] getOpciones() {
		return opciones;
	}
	public void setOpciones(String[] opciones) {
		this.opciones = opciones;
	}


	
}

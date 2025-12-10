package LogicLayer;


public enum Tipo_operacion {
	
	Retirar(new String[]{"En locales","Agencias"}),
	Ingresar(new String[]{"En efectivo","Transferencia"}),
	Transferir(new String[]{"Alias/Cbu_cvu","Contacto"}),
	Dolares(new String[]{"Comprar","Vender"}),
	Invertir(new String[]{"Invertir", "Simular Día", "Ver interés", "Retirar inversión"}),
	Otras_Funciones(new String[] {"Agregar contactos","Ver Movimientos","Ver datos"}),
	Cerrar(new String[] {""});
	
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

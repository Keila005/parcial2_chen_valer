package LogicLayer;

public class Banco {
	private String nombre_banco;

	
	public Banco(String nombre_banco) {
		super();
		this.nombre_banco = nombre_banco;
	}

	public String getNombre_banco() {
		return nombre_banco;
	}

	public void setNombre_banco(String nombre_banco) {
		this.nombre_banco = nombre_banco;
	}

	@Override
	public String toString() {
		return "Banco: " + nombre_banco ;
	}
	
}

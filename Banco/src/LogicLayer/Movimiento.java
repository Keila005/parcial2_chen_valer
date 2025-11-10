package LogicLayer;

import java.time.LocalDateTime;

public class Movimiento {
	private String nombreCliente;
	private Tipo_operacion operacion;
	private double monto_operacion;
	private LocalDateTime fechaRealizada;
	
	
	
	public Movimiento(String nombreCliente,Tipo_operacion operacion, double monto_operacion) {
		super();
		this.nombreCliente=nombreCliente;
		this.operacion = operacion;
		this.monto_operacion = monto_operacion;
		this.fechaRealizada = LocalDateTime.now();
		
	}
	

	public Tipo_operacion getOperacion() {
		return operacion;
	}
	
	public void setOperacion(Tipo_operacion operacion) {
		this.operacion = operacion;
	}
	public double getMonto_operacion() {
		return monto_operacion;
	}
	public void setMonto_operacion(double monto_operacion) {
		this.monto_operacion = monto_operacion;
	}
	public LocalDateTime getFechaRealizada() {
		return fechaRealizada;
	}
	public void setFechaRealizada(LocalDateTime fechaRealizada) {
		this.fechaRealizada = fechaRealizada;
	}
	
	public String getNombreCliente() {
		return nombreCliente;
	}


	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}


	@Override
	public String toString() {
		return "Movimiento \nNombreCliente=" + nombreCliente + ", operacion=" + operacion + ", monto_operacion="
				+ monto_operacion + ", fechaRealizada=" + fechaRealizada + "\n--------------\n";
	}






	

}

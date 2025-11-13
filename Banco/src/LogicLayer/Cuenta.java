package LogicLayer;

import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Cuenta {
	private int cbu_cvu;
	private static int num=00000100;
	private  double saldoPesos;
	private double saldoDolar;
	private String alias;
	private Banco banco;
	
	private LinkedList<Movimiento> listamov = new LinkedList<Movimiento>();
	private LinkedList<Contacto> contactos = new LinkedList<Contacto>();
	
	public Cuenta( double saldoPesos, double saldoDolar, String alias,Banco banco) {
		super();
		num++;
		this.cbu_cvu = num;
		this.saldoPesos = saldoPesos;
		this.saldoDolar = saldoDolar;
		this.alias = alias;
		this.banco = banco;
		this.listamov = new LinkedList<Movimiento>();
		this.contactos = new LinkedList<Contacto>();
	
	}

	public Cuenta(int cbu_cvu, double saldoPesos, double saldoDolar, String alias, Banco banco) {
		super();
		this.cbu_cvu = cbu_cvu;
		this.saldoPesos = saldoPesos;
		this.saldoDolar = saldoDolar;
		this.alias = alias;
		this.banco = banco;
		this.listamov = new LinkedList<Movimiento>();
		this.contactos = new LinkedList<Contacto>();
	}

	public Cuenta() {
		super();
	}

	public int getCbu_cvu() {
		return cbu_cvu;
	}

	public void setCbu_cvu(int cbu_cvu) {
		this.cbu_cvu = cbu_cvu;
	}

	public double getSaldoPesos() {
		return saldoPesos;
	}

	public void setSaldoPesos(double saldoPesos) {
		this.saldoPesos = saldoPesos;
	}

	public double getSaldoDolar() {
		return saldoDolar;
	}

	public void setSaldoDolar(double saldoDolar) {
		this.saldoDolar = saldoDolar;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public LinkedList<Movimiento> getListamov() {
		return listamov;
	}

	public void setListamov(LinkedList<Movimiento> listamov) {
		this.listamov = listamov;
	}
	

	public LinkedList<Contacto> getContactos() {
		return contactos;
	}

	public void setContactos(LinkedList<Contacto> contactos) {
		this.contactos = contactos;
	}

	@Override
	public String toString() {
		return "Cuenta:\nCbu_cvu=" + cbu_cvu +"\nSaldoPesos=" + saldoPesos + "\\nSaldoDolar=" + saldoDolar + "\nAlias="+ alias + "\nBanco=" + banco + "\nListamov="+ listamov + "----------\n";
	}
		
	public void transferirDinero(double monto,String nombreCliente, Cuenta destino) {
		if (this.saldoPesos>=monto) {
			this.saldoPesos-=monto;
			
			destino.setSaldoPesos(destino.getSaldoPesos()+monto);
			//movimiento para el que transfiere
			JOptionPane.showMessageDialog(null, "Saldo actual: "+this.saldoPesos);
			this.listamov.add(new Movimiento(nombreCliente+" transfirió a "+destino.getAlias(), Tipo_operacion.Transferir,monto));
			
			Admin.getListasMovimientos().add(new Movimiento(nombreCliente+" transfirio a "+destino.getAlias(), Tipo_operacion.Transferir,monto));
			//mov para el que recibe
			
		destino.listamov.add(new Movimiento("Usted con alias: "+destino.getAlias()+" recibió de "+nombreCliente,Tipo_operacion.Ingresar,monto));
		 Admin.getListasMovimientos().add(new Movimiento("El alias: "+destino.getAlias()+" recibió de "+nombreCliente,Tipo_operacion.Ingresar,monto));
		}else {
			JOptionPane.showMessageDialog(null, "No tenes el saldo suficiente para transferir");
		}
	}// fin de transferir
	
	public void retirarDinero(double monto,String nombreCliente,String lugarSeleccionado) {
		if (this.saldoPesos>=monto) {
			this.saldoPesos-=monto;
			JOptionPane.showMessageDialog(null, "Se retiro el dinero correctamente en:"+lugarSeleccionado+".\nSaldo actual: "+this.saldoPesos);
			this.listamov.add(new Movimiento(nombreCliente, Tipo_operacion.Retirar,monto));
			Admin.getListasMovimientos().add(new Movimiento(nombreCliente,Tipo_operacion.Retirar,monto));
		}else {
			JOptionPane.showMessageDialog(null, "No tenes el saldo suficiente para retira dinero");
		}
	} // fin de retirar
	
	public void ingresarDinero(double monto,String nombreCliente,String lugarSeleccionado) {
		this.saldoPesos+=monto;
		   
		this.listamov.add(new Movimiento(nombreCliente, Tipo_operacion.Ingresar,monto));
		Admin.getListasMovimientos().add(new Movimiento(nombreCliente,Tipo_operacion.Ingresar,monto));
		
			JOptionPane.showMessageDialog(null, "Se retiro el dinero correctamente por: "+lugarSeleccionado+".\nSaldo actual: "+this.saldoPesos);
		
	} // fin de ingresar
	
	public void enviarDinero(double monto,String nombreCliente,Cuenta destino) {
		if (destino.getSaldoPesos()>=monto) {
			double suPlata=destino.getSaldoPesos();
		double plataMenos=suPlata-monto;
		this.saldoPesos+=monto;
		
		// el que recibio dinero
		this.listamov.add(new Movimiento(nombreCliente, Tipo_operacion.Ingresar,monto));
		Admin.getListasMovimientos().add(new Movimiento(nombreCliente,Tipo_operacion.Ingresar,monto));
		
		// el que envia el dinero( le transfirio)
		destino.listamov.add(new Movimiento("Usted con alias: "+destino.getAlias()+" ingreso plata a  "+nombreCliente,Tipo_operacion.Ingresar,monto));
		 Admin.getListasMovimientos().add(new Movimiento("El alias: "+destino.getAlias()+" ingreso plata a "+nombreCliente,Tipo_operacion.Ingresar,monto));
		
			JOptionPane.showMessageDialog(null, "Se retiro el dinero correctamente por: "+destino.getAlias()+".\nSaldo actual: "+this.saldoPesos);
		}else {
			JOptionPane.showMessageDialog(null, "El alias ingresado no tiene el saldo suficiente para transferirte");
		}
		
	}
	
	
	public void comprarDolares(double monto,String nombreCliente) { //ingresar la plata en pesos que se quiere vender
		  double valorDolarCompra = 1450; 
		if (this.saldoPesos>=monto) {
			this.saldoPesos-=monto;
			double compro=monto/valorDolarCompra;
			this.saldoDolar+=compro;

			JOptionPane.showMessageDialog(null, "Se compro correctamente "+compro+" dolares\nSaldo actual en pesos: "+this.saldoPesos+"\nSaldo actual en dolares:"+this.saldoDolar);
			
			this.listamov.add(new Movimiento(nombreCliente, Tipo_operacion.Dolares,monto));
			Admin.getListasMovimientos().add(new Movimiento(nombreCliente,Tipo_operacion.Dolares,monto));
		}else {
			JOptionPane.showMessageDialog(null, "No tenes el saldo suficiente para comprar dolares");
		}
	} // fin de comprar dolares
	
	public void venderDolares(double monto, String nombreCliente) { //ingresar la plata en dolares que se quiere vender
		  double valorDolarVender = 1400; 
		if (this.saldoDolar>=monto) {
			this.saldoDolar-=monto;
			double vender=monto*valorDolarVender;
			this.saldoPesos+=vender;
			
			JOptionPane.showMessageDialog(null, "Se vendio correctamente "+monto+" dolares\nRecibiste en Pesos"+vender+"\nSaldo actual en pesos: "+this.saldoPesos+"\nSaldo actual en dolar:"+this.saldoDolar);
			this.listamov.add(new Movimiento(nombreCliente, Tipo_operacion.Dolares,monto));
			Admin.getListasMovimientos().add(new Movimiento(nombreCliente,Tipo_operacion.Dolares,monto));
		}else {
			JOptionPane.showMessageDialog(null, "No tenes el saldo suficiente para vender dolares");
		}
	} // fin de vender dolares
}// fin de la clase

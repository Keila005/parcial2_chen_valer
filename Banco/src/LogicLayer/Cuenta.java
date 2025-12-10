package LogicLayer;

import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Cuenta {
	private int cbu_cvu;
	private static int num=1000;
	private  double saldoPesos;
	private double saldoDolar;
	private String alias;
	private Banco banco;
	private double montoInvertido = 0;
	private int diaInversion = 0;
	private LinkedList<String> historialInversion = new LinkedList<>();

	private LinkedList<Movimiento> listamov = new LinkedList<Movimiento>();
	private LinkedList<Contacto> contactos = new LinkedList<Contacto>();
	
	public Cuenta(double saldoPesos, double saldoDolar, String alias,Banco banco) {
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
	

	public double getMontoInvertido() {
		return montoInvertido;
	}

	public void setMontoInvertido(double montoInvertido) {
		this.montoInvertido = montoInvertido;
	}

	public LinkedList<String> getHistorialInversion() {
		return historialInversion;
	}

	public void setHistorialInversion(LinkedList<String> historialInversion) {
		this.historialInversion = historialInversion;
	}

	@Override
	public String toString() {
		return "\nCbu_cvu=" + cbu_cvu +"\nSaldoPesos=" + saldoPesos + "\nSaldoDolar=" + saldoDolar + "\nAlias="+ alias + "\nBanco=" + banco + "\nListamov="+ listamov + "----------\n";
	}
		

	public void transferirDinero(double monto,String nombreCliente, Cuenta destino) {
		if (this.saldoPesos>=monto) {
			this.saldoPesos-=monto;
			
			destino.setSaldoPesos(destino.getSaldoPesos()+monto);
			
			//movimiento para el que transfiere
			this.listamov.add(new Movimiento(nombreCliente+" transfirió a "+destino.getAlias(), Tipo_operacion.Transferir,monto));
			
			Admin.getListasMovimientos().add(new Movimiento(nombreCliente+" transfirio a "+destino.getAlias(), Tipo_operacion.Transferir,monto));
			//mov para el que recibe
			
		destino.listamov.add(new Movimiento("Usted con alias: "+destino.getAlias()+" recibió de "+nombreCliente,Tipo_operacion.Transferir,monto));
		 Admin.getListasMovimientos().add(new Movimiento("El alias: "+destino.getAlias()+" recibió de "+nombreCliente,Tipo_operacion.Transferir,monto));
		}else {
			JOptionPane.showMessageDialog(null, "No tenes el saldo suficiente para transferir","Saldo insuficiente",JOptionPane.DEFAULT_OPTION,new ImageIcon(Cuenta.class.getResource("/img/nohay.png")));
		}
	}// fin de transferir
	
	public void retirarDinero(double monto,String nombreCliente,String lugarSeleccionado) {
		if (this.saldoPesos>=monto) {
			this.saldoPesos-=monto;
			JOptionPane.showMessageDialog(null, "Se retiro el dinero correctamente en: "+lugarSeleccionado+".\nSaldo actual: "+this.saldoPesos,"Retirar",JOptionPane.DEFAULT_OPTION,new ImageIcon(Cuenta.class.getResource("/img/correcto.png")));
			this.listamov.add(new Movimiento(nombreCliente, Tipo_operacion.Retirar,monto));
			Admin.getListasMovimientos().add(new Movimiento(nombreCliente,Tipo_operacion.Retirar,monto));
		}else {
			JOptionPane.showMessageDialog(null, "No tenes el saldo suficiente para retirar esa cantidad de dinero","Saldo insuficiente",JOptionPane.DEFAULT_OPTION,new ImageIcon(Cuenta.class.getResource("/img/nohay.png")));
		}
	} // fin de retirar
	
	public void ingresarDinero(double monto,String nombreCliente,String lugarSeleccionado) {
		this.saldoPesos+=monto;
		   
		this.listamov.add(new Movimiento(nombreCliente, Tipo_operacion.Ingresar,monto));
		Admin.getListasMovimientos().add(new Movimiento(nombreCliente,Tipo_operacion.Ingresar,monto));
		
			JOptionPane.showMessageDialog(null, "Se ingresó el dinero correctamente por: "+lugarSeleccionado+".\nSaldo actual: "+this.saldoPesos,
					"Correcto",JOptionPane.DEFAULT_OPTION,new ImageIcon(Cuenta.class.getResource("/img/correcto.png")));
		
	} // fin de ingresar
	
	public void comprarDolares(double monto,String nombreCliente) { //ingresar la plata en pesos que se quiere vender
		  double valorDolarCompra = 1450; 
		if (this.saldoPesos>=monto) {
			this.saldoPesos-=monto;
			double compro=monto/valorDolarCompra;
			this.saldoDolar+=compro;

			JOptionPane.showMessageDialog(null, "Se compro correctamente "+compro+" dolares\nSaldo actual en pesos: "+this.saldoPesos+"\nSaldo actual en dolares:"+this.saldoDolar,
					"Comprar dolares",JOptionPane.DEFAULT_OPTION,new ImageIcon(Cuenta.class.getResource("/img/correcto.png")));
			
			this.listamov.add(new Movimiento(nombreCliente, Tipo_operacion.Dolares,monto));
			Admin.getListasMovimientos().add(new Movimiento(nombreCliente,Tipo_operacion.Dolares,monto));
		}else {
			JOptionPane.showMessageDialog(null, "No tenes el saldo suficiente para comprar dolares","Saldo insuficiente",
					JOptionPane.DEFAULT_OPTION,new ImageIcon(Cuenta.class.getResource("/img/nohay.png")));
		}
	} // fin de comprar dolares
	
	public void venderDolares(double monto, String nombreCliente) { //ingresar la plata en dolares que se quiere vender
		  double valorDolarVender = 1400; 
		if (this.saldoDolar>=monto) {
			this.saldoDolar-=monto;
			double vender=monto*valorDolarVender;
			this.saldoPesos+=vender;
			
			JOptionPane.showMessageDialog(null, "Se vendio correctamente "+monto+" dolares\nRecibiste en Pesos: "+vender+
					"\nSaldo actual en pesos: "+this.saldoPesos+"\nSaldo actual en dolar:"+this.saldoDolar,"Correcto",
					JOptionPane.DEFAULT_OPTION,new ImageIcon(Cuenta.class.getResource("/img/correcto.png")));
			this.listamov.add(new Movimiento(nombreCliente, Tipo_operacion.Dolares,monto));
			Admin.getListasMovimientos().add(new Movimiento(nombreCliente,Tipo_operacion.Dolares,monto));
		}else {
			JOptionPane.showMessageDialog(null, "No tenes el saldo suficiente para vender dolares","Saldo insuficiente",
					JOptionPane.DEFAULT_OPTION,new ImageIcon(Cuenta.class.getResource("/img/nohay.png")));
		}
	} // fin de vender dolares

	public void invertirDinero(double monto, String nombreCliente) {
	    if (saldoPesos >= monto) {
	        saldoPesos -= monto;
	        montoInvertido += monto;
	        
	        JOptionPane.showMessageDialog(null, 
	            "Invertiste $" + monto + "\nMonto de inversión total: $" + montoInvertido);

	        historialInversion.add("Inversión: $" + monto);
	        listamov.add(new Movimiento(nombreCliente, Tipo_operacion.Invertir, monto));
	        Admin.getListasMovimientos().add(new Movimiento(nombreCliente, Tipo_operacion.Invertir, monto));
	 
	    } else {
	        JOptionPane.showMessageDialog(null, "Saldo insuficiente para invertir");
	    }
	}//fin de invertir
	
	public void retirarInversion(String nombreCliente) {
	    saldoPesos += montoInvertido;
	    JOptionPane.showMessageDialog(null, "Retiraste correctamente todo el dinero de la inversión($"+montoInvertido+")","Retirar inversión",JOptionPane.DEFAULT_OPTION,new ImageIcon(Cuenta.class.getResource("/img/correcto.png")));
	    this.historialInversion.add("Retiraste toda la inversión: $" + montoInvertido);
	    
        listamov.add(new Movimiento(nombreCliente, Tipo_operacion.Ingresar, montoInvertido));
        
        Admin.getListasMovimientos().add(new Movimiento(nombreCliente, Tipo_operacion.Ingresar, montoInvertido));
        
	    montoInvertido = 0;
	}
	
	public void simularInversion() {
	    if (this.montoInvertido <= 0) {
	        JOptionPane.showMessageDialog(null, "No tenes dinero invertido");
	    }else {
	    int porcentaje =(int)(Math.random() * 12) - 6; 
	    // (0 y 1)*12= 0 y 12 -6= -6 y 6%
	    diaInversion++;
	    double resultado = this.montoInvertido*porcentaje/100;
	   
	   this.montoInvertido += resultado;
	    JOptionPane.showMessageDialog(null,"Tasa de interés del día: "+porcentaje+" % : "+resultado);
	    
	    this.historialInversion.add("Día "+diaInversion+":--------"+"\nTasa de interés: "+porcentaje+" %\nMonto de inversión total: $"+montoInvertido);
	 
	    }
	}
	
	public void verMovimientosInversion() {
	    if (historialInversion.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "No hay movimientos de inversión.");
	        return;
	    }

	    String historial = "Historial de inversión:\n";
	    for (String mov : historialInversion) {
	        historial += mov + "\n";
	    }

	    JOptionPane.showMessageDialog(null, historial, "Historial de Inversión", JOptionPane.INFORMATION_MESSAGE);
	}


	
}// fin de la clase

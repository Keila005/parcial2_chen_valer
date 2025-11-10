package LogicLayer;

import java.util.LinkedList;

public class Contacto {
		private String nombre; 
	    private String alias; 
	    private int cbu;
	   
	    public Contacto(String nombre, String alias, int cbu) {
	        this.nombre = nombre;
	        this.alias = alias;
	        this.cbu = cbu;
	    }
	    

		public String getNombre() {
			return nombre;
		}


		public void setNombre(String nombre) {
			this.nombre = nombre;
		}


		public String getAlias() {
			return alias;
		}


		public void setAlias(String alias) {
			this.alias = alias;
		}


		public int getCbu() {
			return cbu;
		}


		public void setCbu(int cbu) {
			this.cbu = cbu;
		}


		@Override
		public String toString() {
			return "Contacto:\nNombre=" + nombre + ", alias=" + alias + ", cbu=" + cbu + "\n--------\n";
		}



	


		

		
	    
	    
}

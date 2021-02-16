package mx.ipn.cic.controlescolar.demo.models;

public class Mensajes {
	
	String mensaje;

	
	public Mensajes() {
		super();
	}

	public Mensajes(String mensaje) {
		super();
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		return "Mensajes [mensaje=" + mensaje + "]";
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	

}

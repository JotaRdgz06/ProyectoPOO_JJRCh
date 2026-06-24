package Logica;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Alerta implements Serializable {
	
	private TipoAlerta tipo;
    private LocalDateTime fechaActivacion;
    private String mensaje;
    private boolean yaActivada;

    public enum TipoAlerta {
		SIN_ALERTA, UNA_VEZ, RECURRENTE
	}
    
    public Alerta(TipoAlerta tipo, LocalDateTime fechaActivacion, String mensaje) {
        this.tipo = tipo;
        this.fechaActivacion = fechaActivacion;
        this.mensaje = mensaje;
        yaActivada = false;
    }
	
    public TipoAlerta getTipo() {
    	return tipo;
    }
    
    public void setTipo(TipoAlerta tipo) {
    	this.tipo = tipo;
    }
    
    public LocalDateTime getFechaActivacion() {
    	return fechaActivacion;
    }
    
    public void setFechaActivacion(LocalDateTime fechaActivacion) {
        this.fechaActivacion = fechaActivacion;
    }
    
    public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public boolean debeActivarse() {
        if (LocalDateTime.now().isAfter(fechaActivacion))
        	return true;
        if (tipo == Alerta.TipoAlerta.UNA_VEZ && yaActivada == true)
        	return false;
    }
}

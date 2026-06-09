package Logica;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Alerta implements Serializable {
	
	private TipoAlerta tipo;
    private LocalDateTime fechaActivacion;

    public enum TipoAlerta {
		UNA_VEZ, RECURRENTE
	}
    
    public Alerta(TipoAlerta tipo, LocalDateTime fechaActivacion) {
        this.tipo = tipo;
        this.fechaActivacion = fechaActivacion;
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
}

package edu.eci.arep.modelo;

public class Log {

    private String mensaje; 
    private String fecha; 
	
	/**
     * Constructor del usuario 
     * @param mensaje del log
     * @param fecha del log
     */
    public Log(String mensaje, String fecha){
        this.mensaje = mensaje;
        this.fecha = fecha;
    }

	/**
     * Constructor del log
     */
    public Log(){

    }
	
	/**
     * Retorna el mensaje
     * @return mensaje del log
     */
    public String getMensaje(){
        return this.mensaje;
    }
	
	/**
     * Retorna la fecha
     * @return fecha del log
     */
    public String getFecha(){
        return this.fecha;
    }
	
	/**
     * Cambia el mensaje del log
     * @param mensaje: nuevo mensaje del log
     */
    public void setMensaje(String mensaje){
        this.mensaje = mensaje;
    }
	
	/**
     * Cambia la fecha del log
     * @param fecha: nuevo fecha del log
     */
    public void setFecha(String fecha){
        this.fecha = fecha;
    }   

     /**
     * Define como el log va a ser transformado en cadena 
     * @return String con la representacion del log como cadena
     */
    @Override
	public String toString() {
		return "[" + this.mensaje + " " + this.fecha + "]";
	}
}

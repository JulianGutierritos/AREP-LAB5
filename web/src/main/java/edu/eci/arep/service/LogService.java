package edu.eci.arep.service;

import java.util.List;

import edu.eci.arep.modelo.Log;

/**
 * Interfaz que define los metodos necesarios para recuperar los usuarios de algun almacenamiento externo 
 * @author Julián Gutiérrez
 * @version 1.0
 */

public interface LogService {


	/**
	* Adicionar un log al sistema 
	* @param log a ingresar 
	* @return los ultimos 10 logs ingresados
	*/
	public List<Log> addLog(Log log);





    
}
package edu.eci.arep;

import static spark.Spark.*;
import edu.eci.arep.modelo.Log;
import edu.eci.arep.service.LogService;
import edu.eci.arep.service.impl.LogServiceMongo;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Clase principal cuya tarea es arrancar un servidor HTTP 
 * @author Julián Gutiérrez
 * @version 1.0
 */
public class Main {
	
	/**
	*inicializa spark 
	*/
    public static void main(String... args){
		staticFiles.location("/static");
        LogService ls = new LogServiceMongo();
        port(getPort());
        get("hello", (req,res) -> "Hello Docker! " + getPort());
		post("/addMensaje",(req, res) -> {
			String s = req.body().replace("\"", "");
			Date fecha = new Date();
			Log l = new Log(s, fecha.toString());
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(ls.addLog(l));
			res.type("application/json");
			return json;
        });
	}
	
	/**
	* Cambia el puerto 
	* @return puerto de la aplicacion
	*/
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8001;
    }
    
}
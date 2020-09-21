package edu.eci.arep;

import static spark.Spark.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Clase principal cuya tarea es arrancar un servidor HTTP 
 * @author Julián Gutiérrez
 * @version 1.0
 */
public class Main {
	
	static String[] apps = {"100.14.0.2:6000", "100.14.0.3:6000", "100.14.0.4:6000"};
	static int actual = 0;
	
	/**
	*inicializa spark 
	*/
    public static void main(String... args){
		staticFiles.location("/static");
        port(getPort());
        get("hello", (req,res) -> "Hello Docker! " + getPort());
		post("/addMensaje", (req, res) -> {
			HttpPost post = new HttpPost("http://" + apps[actual] + "/addMensaje");
			StringEntity requestEntity = new StringEntity(req.body(), ContentType.APPLICATION_JSON); 
			post.setEntity(requestEntity);
			String respuesta = "";
			try (CloseableHttpClient httpClient = HttpClients.createDefault();
             	CloseableHttpResponse response = httpClient.execute(post)) {
            	respuesta = EntityUtils.toString(response.getEntity());
       		} catch (Exception e) {}
			cambiarPuerto();
			return respuesta;
        });
	}
	
	/**
	* Cambia de aplicacion 
	*/
	private static void cambiarPuerto(){
		if (actual < 2){
			actual+= 1;
		}
		else{
			actual = 0;
		}
	}

	/**
	* Cambia el puerto 
	* @return puerto de la aplicacion
	*/
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
    
}
package edu.eci.arep.service.impl;

import edu.eci.arep.modelo.Log;
import edu.eci.arep.service.LogService;
import java.util.List;
import java.util.ArrayList;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Clase que recupera datos de una base de datos
 * 
 * @author Julián Gutiérrez
 * @version 1.0
 */

public class LogServiceMongo implements LogService {

    MongoDatabase db;

    /**
     * Constructor de la clase
     */
    public LogServiceMongo() {

        MongoClient mc = new MongoClient("100.14.0.5", 27017);
        db = mc.getDatabase("arep");
    }
	
	/**
	* Adicionar un log al sistema 
	* @param log a ingresar 
	* @return los ultimos 10 logs ingresados
	*/
	public List<Log> addLog(Log log){
		Document doc = new Document("Mensaje", log.getMensaje())
                .append("Fecha", log.getFecha());
		MongoCollection dc = db.getCollection("logs");
        dc.insertOne(doc);
        MongoCursor<Document> cursor = dc.find().cursor();
		List<Log> logs = new ArrayList<Log>();
		Log l;
        while (cursor.hasNext()){
			doc = cursor.next();
			l = new Log(doc.get("Mensaje").toString(), doc.get("Fecha").toString());
            logs.add(l);
        }
        if (logs.size() > 10){
            logs = logs.subList(logs.size() - 10, logs.size());
        }
        return logs;
	}
    
}

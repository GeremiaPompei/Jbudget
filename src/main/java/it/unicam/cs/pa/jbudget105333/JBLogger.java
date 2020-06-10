package it.unicam.cs.pa.jbudget105333;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Classe responsabile della creazione di un logger personalizzato.
 */
public class JBLogger {

    /**
     * Logger del JBLogger.
     */
    private static Logger logger;

    /**
     * Path della directory principale.
     */
    private static final String dirPath = "src/file/logging/log_"+LocalDateTime.now();

    /**
     * Blocco statico che ha la responsabilità di creare la directory su cui vengono salvati
     * i nuovi file di log
     */
    static {
        new File((dirPath)).mkdirs();
    }

    /**
     * Metodo che ha la responsabilità di generare un logger.
     * @param aClass Classe su cui viene generato il logger.
     * @return Logger generato.
     */
    public static Logger generateLogger(Class aClass) {
        logger = Logger.getLogger(aClass.getName());
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.INFO);
        logger.addHandler(fileHandler(aClass.getSimpleName()));
        return logger;
    }

    /**
     * Metodo che ha la responsabilità di gestire il fileHandler del logger.
     * @param name Nome della classe che ha generato il logger.
     * @return FileHandler gastito.
     */
    private static FileHandler fileHandler(String name){
        FileHandler fileHandler = null;
        try {
            String path = dirPath+"/log_"+name;
            new File(path).mkdirs();
            fileHandler = new FileHandler(path+"/"+LocalDateTime.now()+".txt");
            fileHandler.setFormatter(new SimpleFormatter());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileHandler;
    }
}

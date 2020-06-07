package it.unicam.cs.pa.jbudget105333;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class JBLogger {

    private static Logger logger;

    public static Logger generateLogger(Class aClass) {
        logger = Logger.getLogger(aClass.getName());
        logger.setUseParentHandlers(false);
        //logger.addHandler(fileHandler(aClass.getSimpleName()));
        logger.setLevel(Level.INFO);
        return logger;
    }

    private static FileHandler fileHandler(String name){
        FileHandler fileHandler = null;
        try {
            String path = "src/file/logging/log_"+name;
            new File(path).mkdirs();
            fileHandler = new FileHandler(path+"/"+LocalDateTime.now());
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileHandler.setFormatter(new SimpleFormatter());
        return fileHandler;
    }
}

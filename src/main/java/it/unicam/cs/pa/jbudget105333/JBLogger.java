package it.unicam.cs.pa.jbudget105333;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class JBLogger {

    private final Logger logger;

    public JBLogger(Class aClass) {
        this.logger = Logger.getLogger(aClass.getName());
        this.logger.setUseParentHandlers(false);
        this.logger.addHandler(fileHandler(aClass.getSimpleName()));
    }

    private FileHandler fileHandler(String name){
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler("src/file/logging/log_"+name+".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileHandler.setFormatter(new SimpleFormatter());
        return fileHandler;
    }

    public Logger getLogger() {
        return logger;
    }
}

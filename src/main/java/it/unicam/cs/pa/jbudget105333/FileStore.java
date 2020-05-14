package it.unicam.cs.pa.jbudget105333;

import java.io.*;

public class FileStore<B extends Bilancio> implements Store<B> {

    private static final String path = "src/file/";
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private BilancioPrinter<B> bilancioPrinter;
    private File file;

    public FileStore(BilancioPrinter<B> bilancioPrinter) throws IOException {
        this.bilancioPrinter = bilancioPrinter;
        this.file = new File(path+bilancioPrinter.getClass().getSimpleName()+".txt");
        if(!this.file.exists())
            this.file.createNewFile();
    }

    @Override
    public GestoreMovimenti<B> read() throws IOException{
        try{
            in = new ObjectInputStream(new FileInputStream(this.file));
            return (GestoreMovimenti<B>)in.readObject();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public void write(GestoreMovimenti<B> gestoreMovimenti) throws IOException {
        out = new ObjectOutputStream(new FileOutputStream(this.file));
        out.writeObject(gestoreMovimenti);
        out.flush();
        out.close();
    }

    @Override
    public void close() {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

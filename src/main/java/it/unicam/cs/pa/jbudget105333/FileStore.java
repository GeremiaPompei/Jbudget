package it.unicam.cs.pa.jbudget105333;

import java.io.*;
import java.util.ArrayList;

public class FileStore<B extends Bilancio> implements Store<B> {

    private static final String path = "/Users/geremiapompei/Desktop/JetBrains/IntelliJ/JBudget/";
    private InputStreamReader reader;
    private OutputStreamWriter writer;
    private BilancioPrinter<B> bilancioPrinter;

    public FileStore(BilancioPrinter<B> bilancioPrinter) {
        this.bilancioPrinter = bilancioPrinter;
    }

    @Override
    public void read(GestoreMovimenti gestoreMovimenti) throws IOException {
        reader = new InputStreamReader(new FileInputStream
                (new File(path+gestoreMovimenti.getBilancio().getClass().toString()+".txt")));
        int in = 0;
        ArrayList<String> al = new ArrayList<>();
        String s = "";
        while((in=reader.read())>-1){
            if ((char)in!='\n'){
                s+=(char)in;
            }else{
                al.add(s);
                s="";
            }
        }
        gestoreMovimenti.addMovimenti(new GestoreMovimentiScanner().scanOf(s).getMovimenti());
    }

    @Override
    public void write(GestoreMovimenti<B> gestoreMovimenti) throws IOException {
        writer = new OutputStreamWriter(new FileOutputStream
                (new File(path+gestoreMovimenti.getBilancio().getClass().toString()+".txt")));
        writer.write(bilancioPrinter.stringOf(gestoreMovimenti.getBilancio())+"\n");
        writer.write(new GestoreMovimentiPrinter<B,Tag>().stringOf(gestoreMovimenti));
        writer.flush();
    }

    @Override
    public void close() {
        try {
            reader.close();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

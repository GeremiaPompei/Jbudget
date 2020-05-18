package it.unicam.cs.pa.jbudget105333;

import java.io.*;

public class FileStore<B extends BudgetReport> implements Store<B>{

    private static final String path = "src/file/";
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private B budgetReport;
    private File file;

    public FileStore(B budgetReport) throws IOException {
        this.budgetReport = budgetReport;
        this.file = new File(path+budgetReport.getClass().getSimpleName()+".txt");
        if(!this.file.exists())
            this.file.createNewFile();
    }

    @Override
    public B read() throws IOException{
        try{
            in = new ObjectInputStream(new FileInputStream(this.file));
            return (B)in.readObject();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public void write(B budgetReport) throws IOException {
        out = new ObjectOutputStream(new FileOutputStream(this.file));
        out.writeObject(budgetReport);
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

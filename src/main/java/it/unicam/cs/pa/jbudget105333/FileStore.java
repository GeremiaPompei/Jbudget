package it.unicam.cs.pa.jbudget105333;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

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
        TreeSet<Movimento<Tag>> ts = new TreeSet<>();
        StringTokenizer st = null;
        if(s.length()!=0){
        for(int i = 1;i<al.size();i++){
            st = new StringTokenizer(al.get(i),":");
            String dateS = st.nextToken();
            dateS = dateS.substring(dateS.indexOf('[')+1,dateS.indexOf(']')).trim();
            LocalDate date = LocalDate.parse(dateS);
            String valueS = st.nextToken();
            valueS = valueS.substring(valueS.indexOf('[')+1,valueS.indexOf(']')).trim();
            double value = Double.parseDouble(valueS);
            ArrayList<Tag> tag = new ArrayList<>();
            StringTokenizer st2 = new StringTokenizer(st.nextToken(),",");
            for(int j = 0;j<st2.countTokens();j++){
                String tagS = "";
                tagS = st2.nextToken();
                tagS = tagS.substring(tagS.indexOf('(')+1,tagS.indexOf(')'));
                try{
                    tag.add(TagIn.valueOf(tagS));
                }catch(Exception e){
                    tag.add(TagOut.valueOf(tagS));
                }
            }
            ts.add(new Movimento<Tag>(value,tag, LocalDateTime.of(date, LocalTime.MIN)));
        }
        gestoreMovimenti.getBilancio().setValue
                (Double.parseDouble(al.get(0).substring(9,al.get(0).indexOf(',')).trim()));
        gestoreMovimenti.addMovimenti(ts);}
    }

    @Override
    public void write(GestoreMovimenti<B, Tag> gestoreMovimenti) throws IOException {
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

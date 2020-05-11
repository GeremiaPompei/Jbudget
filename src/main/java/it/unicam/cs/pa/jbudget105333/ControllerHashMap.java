package it.unicam.cs.pa.jbudget105333;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class ControllerHashMap<B extends Bilancio> implements Controller<B> {

    private HashMap<String, Consumer<B>> comandi = null;
    private B stato = null;
    private GestoreMovimenti<B,Tag> gestoreMovimenti = null;
    private Store<B> store = null;

    public ControllerHashMap(HashMap<String, Consumer<B>> comandi, B stato, Store<B> store) {
        this.comandi = comandi;
        this.stato = stato;
        this.gestoreMovimenti = new GestoreMovimenti<>(stato);
        this.store = store;
    }

    @Override
    public void processCommand(String s) throws IOException{
        try{
            String intro = s.substring(0,3);
            String coda = s.substring(3).trim();
            switch (intro){
                case "pi " : new PianoIstantaneo<B,Tag>
                        (processoTags(coda),processValue(coda),gestoreMovimenti).move();
                break;
                case "pp " : new PianoProgrammato<B,Tag>
                        (processoTags(coda),(processValue(coda)),gestoreMovimenti,processStopLocalDate(coda)).move();
                break;
                case "pm " : new PianoMensile<B,Tag>
                        (processoTags(coda),(processValue(coda)),processStartLocalDate(coda)
                                ,processStopLocalDate(coda),gestoreMovimenti).move();
                break;
                case "gd " : print(gestoreMovimenti.getMovimenti().stream()
                        .filter(d->d.getLocalDateTime().toLocalDate().compareTo(processStartLocalDate(coda))>=0)
                        .filter(d->d.getLocalDateTime().toLocalDate().compareTo(processStopLocalDate(coda))<=0));
                break;
                case "gt " : print(gestoreMovimenti.getMovimenti().stream()
                        .filter(t->t.getTags().equals(processoTags(coda))));
                    break;
                case "sho" : System.out.println(new GestoreMovimentiPrinter<B,Tag>().stringOf(gestoreMovimenti));
                break;
                default : throw new Exception();
            }
        }catch (Exception e){
            Consumer action = this.comandi.get(s);
            if(action==null)
                System.err.println("Comando non riconosciuto: "+s);
            else
                action.accept(stato);
        }finally{
            this.store.write(gestoreMovimenti);
        }
    }

    @Override
    public void addComando(String s, Consumer<B> comando) {
        this.comandi.put(s,comando);
    }

    @Override
    public void addComandi(HashMap hm) {
        this.comandi.putAll(hm);
    }

    @Override
    public boolean isOn(){
        return this.stato.isOn();
    }

    @Override
    public void shutdown(){
        this.stato.shutdown();
    }

    @Override
    public B getStato() {
        return stato;
    }

    @Override
    public HashMap<String, Consumer<B>> getComandi() {
        return comandi;
    }

    @Override
    public GestoreMovimenti<B, Tag> getGestoreMovimenti() {
        return gestoreMovimenti;
    }

    private ArrayList<Tag> processoTags(String s){
        ArrayList<Tag> tags = new ArrayList<>();
        for(Tag t : TagIn.values())
            if(s.toUpperCase().contains(t.toString()))
                tags.add(t);
        for(Tag t : TagOut.values())
            if(s.toUpperCase().contains(t.toString()))
                tags.add(t);
        return tags;
    }

    private double processValue(String s){
        Double value = 0.0;
        String tmp = "";
        boolean flag = true;
        for(char c : s.toCharArray()){
            if(c>='0'&&c<='9')
                tmp += c;
            else
                if(c=='.'&&flag){
                    tmp += c;
                    flag = false;
                }else
                    break;
            }
        if(tmp!="")
            value = Double.parseDouble(tmp);
        return value;
    }

    private LocalDate processStartLocalDate(String s){
        LocalDate localDate = null;
        String tmp = "";
        boolean flag1 = true,flag2 = true;
        int in = 0;
        for(int i = s.length()-1;i>=0;i--)
            if(s.charAt(i)=='-'){
                in = i;
                break;
            }
        for(int i = in-1;i>=0;i--){
            char c = s.charAt(i);
            if(c>='0'&&c<='9')
                tmp = c+tmp;
            else
            if(c==':'&&flag1){
                tmp = c+tmp;
                flag1 = false;
            }else {
                if (c == ':' && flag2) {
                    tmp = c + tmp;
                    flag2 = false;
                } else
                    break;
            }
        }
        String date[]= new String[3];
        String tmp2 = "";
        int i = 0;
        if(tmp!="")
            for (char c : tmp.toCharArray()){
                if(c==':'){
                    date[i] = tmp2;
                    tmp2="";
                    i++;
                }else
                    tmp2+=c;

            }
        date[i]=tmp2;
        localDate = LocalDate.of(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]));
        return localDate;
    }

    private LocalDate processStopLocalDate(String s){
        LocalDate localDate = null;
        String tmp = "";
        boolean flag1 = true,flag2 = true;
        for(int i = s.length()-1;i>=0;i--){
            char c = s.charAt(i);
            if(c>='0'&&c<='9')
                tmp = c+tmp;
            else
            if(c==':'&&flag1){
                tmp = c+tmp;
                flag1 = false;
            }else {
                if (c == ':' && flag2) {
                    tmp = c + tmp;
                    flag2 = false;
                } else
                    break;
            }
        }
        String date[]= new String[3];
        String tmp2 = "";
        int i = 0;
        if(tmp!="")
            for (char c : tmp.toCharArray()){
                if(c==':'){
                    date[i] = tmp2;
                    tmp2="";
                    i++;
                }else
                    tmp2+=c;

            }
        date[i]=tmp2;
        localDate = LocalDate.of(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]));
        return localDate;
    }

    private void print(Stream<Movimento<Tag>> movimentoStream){
        movimentoStream.forEach(s->System.out.println(new MovimentoPrinter<Tag>().stringOf(s)));
    }
}

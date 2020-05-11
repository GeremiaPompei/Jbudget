package it.unicam.cs.pa.jbudget105333;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MovimentoScanner implements Scanner<Movimento<Tag>> {
    @Override
    public Movimento<Tag> scanOf(String stringa) {
        StringTokenizer st = new StringTokenizer(stringa,":");
        String dateS = st.nextToken();
        dateS = dateS.substring(dateS.indexOf('[')+1,dateS.indexOf(']')).trim();
        LocalDate date = LocalDate.parse(dateS);
        String valueS = st.nextToken();
        valueS = valueS.substring(valueS.indexOf('[')+1,valueS.indexOf(']')).trim();
        double value = Double.parseDouble(valueS);
        TagsScanner tags = new TagsScanner();
        ArrayList<Tag> tag = tags.scanOf(stringa);
        return new Movimento<Tag>(value,tag, LocalDateTime.of(date, LocalTime.MIN));

    }
}

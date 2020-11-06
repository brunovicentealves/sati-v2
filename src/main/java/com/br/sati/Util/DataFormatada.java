package com.br.sati.Util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataFormatada {

    public static Date FormatandoData() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        return  formato.parse(dateFormat.format(date));
    }

    public static Date FormatandoPadrao(Date data) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return  formato.parse(dateFormat.format(data));
    }
}

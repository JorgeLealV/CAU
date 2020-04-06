/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.lib01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author jleal
 */
public class Libra01 {
    static public boolean isInteger(String numero){
    try{
        Integer.parseInt(numero);
        return true;
    }catch(NumberFormatException e){
        return false;
    }
}
    
// String newDate = dateToString(myDate, "dd.MM.yyyy HH:mm:ss");    
public static String dateToString(Date date, String format) throws ParseException {
    SimpleDateFormat sdf = new java.text.SimpleDateFormat(format);
    return sdf.format(date);
}
}

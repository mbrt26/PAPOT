package co.plasticosunion;


import co.linxsi.modelo.touch.principal.Touch_Principal_DAO;
import java.util.Calendar;
import java.util.Date;
import org.junit.Assert;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Edgar J García L
 */
public class Touch_Principal_DAO_Test1 {

    @Test
    public void isToday() {
        Touch_Principal_DAO daoTouch = new Touch_Principal_DAO();
        boolean isYesterday = daoTouch.isYesterday(new Date());
        Assert.assertEquals(false, isYesterday);
    }

    @Test
    public void isYesterday() {
        Date d1 = new Date(122, 04, 04);
        Touch_Principal_DAO daoTouch = new Touch_Principal_DAO();
        boolean isYesterday = daoTouch.isYesterday(d1);
        Assert.assertEquals(true, isYesterday);
    }
   @Test
    public void isValidDateFromBBDD() {
//        Date d1 = new Date(122, 04, 06);
        Touch_Principal_DAO daoTouch = new Touch_Principal_DAO();
        Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.HOUR_OF_DAY, 02);
        c1.set(Calendar.MINUTE, 35);
        Date d2 = daoTouch.getFechaInicio("2022-05-04  03:08:25.62",c1.getTime());
        System.out.println("Fecha Inicio: -> "+d2);
        Assert.assertNotNull("FechaInicio valida -> "+d2,d2);
        
    }
   @Test
    public void isNullFromBBDD() {
//        Date d1 = new Date(122, 04, 06);
        Touch_Principal_DAO daoTouch = new Touch_Principal_DAO();
        Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.HOUR_OF_DAY, 02);
        c1.set(Calendar.MINUTE, 9);
        Date d2 = daoTouch.getFechaInicio(null,c1.getTime());
        System.out.println("Fecha Inicio: -> "+d2);
        Assert.assertNotNull("FechaInicio valida -> "+d2,d2);
        
    }

}

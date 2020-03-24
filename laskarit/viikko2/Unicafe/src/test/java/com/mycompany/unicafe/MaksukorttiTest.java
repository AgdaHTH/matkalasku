package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoMetodiToimii(){
        assertEquals(1000, kortti.saldo());
    }
    
    /*
    kortin saldo alussa oikein 
    rahan lataaminen kasvattaa saldoa oikein
    rahan ottaminen toimii
        saldo vähenee oikein, jos rahaa on tarpeeksi
        saldo ei muutu, jos rahaa ei ole tarpeeksi
        metodi palauttaa true, jos rahat riittivät ja muuten false
    */
    
    @Test
    public void saldoAlussaOikein(){
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void saldoKasvaaOikein(){
        kortti.lataaRahaa(2000);
        assertEquals("saldo: 30.0", kortti.toString());
    }
    
    @Test
    public void otaRahaaPalauttaaTrue(){
        boolean vastaus = kortti.otaRahaa(400);
        assertEquals(true, vastaus);
    }
    
    @Test
    public void otaRahaaPalauttaaFalse(){
        boolean vastaus = kortti.otaRahaa(1100);
        assertEquals(false, vastaus);
    }
    
    @Test
    public void otaRahaaToimiiOikein(){
        kortti.otaRahaa(400);
        assertEquals("saldo: 6.0", kortti.toString());
    }
    
    @Test
    public void saldoEiMuutuJosRahaEiRiitä(){
        kortti.otaRahaa(1100);
        assertEquals("saldo: 10.0", kortti.toString());
    }

}

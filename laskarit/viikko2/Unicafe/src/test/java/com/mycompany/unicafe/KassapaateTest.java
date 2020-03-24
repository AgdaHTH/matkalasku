/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hilla
 */
public class KassapaateTest {
    
    Kassapaate kassa;
    Maksukortti kortti;
    
    public KassapaateTest() {
    }
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(1000);
    }
    
    @Test 
    public void saldoAlussaOikein(){
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void maukkaitaAluksiNolla(){
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullisiaAluksiNolla(){
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kateismaksuEdullinenKasvattaaKassaa(){ 
        kassa.syoEdullisesti(500);
        assertEquals(100240, kassa.kassassaRahaa());
    }
    
    @Test
    public void kateismaksuMaukasKasvattaaKassaa(){ 
        kassa.syoMaukkaasti(500);
        assertEquals(100400, kassa.kassassaRahaa());
    }
    
    @Test 
    public void kateismaksustaTuleeOikeaVaihtorahaEdullinen(){
        int takaisin = kassa.syoEdullisesti(500);
        assertEquals(260, takaisin);    
    }
    
    @Test 
    public void kateismaksustaTuleeOikeaVaihtorahaMaukas(){
        int takaisin = kassa.syoMaukkaasti(500);
        assertEquals(100, takaisin);    
    }
    
    @Test 
    public void kateismaksuLisaaMaukkkaita(){
        kassa.syoMaukkaasti(500);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test 
    public void kateismaksuLisaaEdullisia(){
        kassa.syoEdullisesti(500);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void rahaEiRiitaKassaEiMuutuEdullinen(){
        kassa.syoEdullisesti(200);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void rahaEiRiitaKassaEiMuutuMaukas(){
        kassa.syoMaukkaasti(300);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void rahaEiRiitaKaikkiTakaisinEdullinen(){
        int takaisin = kassa.syoEdullisesti(200);
        assertEquals(200, takaisin);
    }
    
    @Test
    public void rahaEiRiitaKaikkiTakaisinMaukas(){
        int takaisin = kassa.syoMaukkaasti(300);
        assertEquals(300, takaisin);
    }
    
    @Test
    public void rahaEiRiitaEdullistenMaaraEiMuutu(){
        kassa.syoEdullisesti(200);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void rahaEiRiitaMaukkaittenMaaraEiMaara(){
        kassa.syoMaukkaasti(300);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    //MAKSUKORTTITESTIT
    /*
    sekä edullisten että maukkaiden lounaiden osalta
jos kortilla on tarpeeksi rahaa, veloitetaan summa kortilta ja palautetaan true
jos kortilla on tarpeeksi rahaa, myytyjen lounaiden määrä kasvaa
jos kortilla ei ole tarpeeksi rahaa, kortin rahamäärä ei muutu, myytyjen lounaiden määrä muuttumaton ja palautetaan false
kassassa oleva rahamäärä ei muutu kortilla ostettaessa
kortille rahaa ladattaessa kortin saldo muuttuu ja kassassa oleva rahamäärä kasvaa ladatulla summalla
    */
    @Test
    public void maksuKortillaOnnistuuKunRahaRiittääEdulliset(){
        boolean myynti = kassa.syoEdullisesti(kortti);
        assertEquals(true, myynti);
    }
    
    @Test
    public void maksuKortillaOnnistuuKunRahaRiittääMaukkaat(){
        boolean myynti = kassa.syoMaukkaasti(kortti);
        assertEquals(true, myynti);
    }
    
    @Test
    public void kortinSaldoVaheneeEdulliset(){
        kassa.syoEdullisesti(kortti);
        assertEquals(760, kortti.saldo());
    }
    
    @Test
    public void kortinSaldoVaheneeMaukkaat(){
        kassa.syoMaukkaasti(kortti);
        assertEquals(600, kortti.saldo());
    }
    
    @Test
    public void edullisetLisaantyvat(){
        kassa.syoEdullisesti(kortti);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaatLisaantyvät(){
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void rahaEiRiitaKortinSaldoEiMuutuEdulliset(){
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        //jäljellä 200
        kassa.syoEdullisesti(kortti);
        assertEquals(200, kortti.saldo());
    }
    
    @Test
    public void rahaEiRiitaKortinSaldoEiMuutuMaukkaat(){
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        //jäljellä 200
        kassa.syoMaukkaasti(kortti);
        assertEquals(200, kortti.saldo());
    }
    
    
    @Test
    public void rahaEiRiitaMyyntiEiOnnistuEdulliset(){
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        //jäljellä 200
        boolean myynti = kassa.syoEdullisesti(kortti);
        assertEquals(false, myynti);
    }
    
    @Test
    public void rahaEiRiitaMyyntiEiOnnistuMaukkaat(){
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        //jäljellä 200
        boolean myynti = kassa.syoMaukkaasti(kortti);
        assertEquals(false, myynti);
    }
    
    @Test
    public void rahaEiRiitaEdullisetEivatLisaanny(){
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        kassa.syoEdullisesti(kortti);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }

@Test
    public void rahaEiRiitaMaukkaatEivatLisaanny(){
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        //jäljellä 750
        kassa.syoMaukkaasti(kortti);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }    
    
    @Test
    public void korttiostosEiVaikutaKassaanEdulliset(){
        kassa.syoEdullisesti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void korttiostosEiVaikutaKassaanMaukkaat(){
        kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    //kortille rahaa ladattaessa kortin saldo muuttuu 
    //ja kassassa oleva rahamäärä kasvaa ladatulla summalla
    
    @Test
    public void lataaminenToimiiKortinPaassa(){
        kassa.lataaRahaaKortille(kortti, 500);
        assertEquals(1500, kortti.saldo());
    }
    
    @Test
    public void lataaminenToimiiKassanPaassa(){
        kassa.lataaRahaaKortille(kortti, 500);
        assertEquals(100500, kassa.kassassaRahaa());
    }
    
    @Test
    public void kortilleEiVoiLadataNegatiivistaSummaaKortti(){
        kassa.lataaRahaaKortille(kortti, -5);
        assertEquals(1000, kortti.saldo());
    }
    
    public void kortilleEiVoiLadataNegatiivistaSummaaKassa(){
        kassa.lataaRahaaKortille(kortti, -5);
        assertEquals(100000, kassa.kassassaRahaa());
    }
                
}

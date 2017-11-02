package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest { // testaa Varasto-luokkaa

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }
    
    @Test
    public void virheellinenTilavuusLuoTyhjanVaraston() {
        varasto = new Varasto(-100);
        
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaVarastollaOikeaTilavuusKunAsetetaanAlkusaldo() {
        varasto = new Varasto(8, 3);
        
        assertEquals(8, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void virheellinenTilavuusLuoTyhjanVarastonKunAsetetaanAlkusaldo() {
        varasto = new Varasto(-100, 3);
        
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void VarastonLuontiAlkuSaldollaAsettaaSaldonOikein(){
        varasto = new Varasto(10, 3);
        
        assertEquals(3, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void VarastonAlkusaldoEiOleNegatiivinen() {
        varasto = new Varasto(10, -3);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void VarastonAlkusaldoEiYlitaTilavuutta() {
        varasto = new Varasto(10, 13);
        
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiLisataYliTilavuuden() {
        varasto.lisaaVarastoon(1000);
        
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiLisataNegatiivistaMaaraa() {
        varasto.lisaaVarastoon(10);
        varasto.lisaaVarastoon(-7);
        
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiOttaaEnemmänKuinSaldo() {
        varasto.lisaaVarastoon(8);
        
        double saatuMaara = varasto.otaVarastosta(10);
        
        assertEquals(8, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiOttaaNegatiivistaMaaraa() {
        varasto.lisaaVarastoon(8);
        
        double saatuMaara = varasto.otaVarastosta(-1);
        // ei ota huomioon negatiivista arvoa ja korvaa sen nollalla
        
        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void toStringToimiiOikein() {
        varasto.lisaaVarastoon(8);
        
        assertEquals("saldo = 8.0, vielä tilaa 2.0", varasto.toString());
    }
}
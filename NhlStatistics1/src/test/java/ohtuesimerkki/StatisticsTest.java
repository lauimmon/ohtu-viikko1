/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Perus
 */
public class StatisticsTest {
    
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12)); // 16
            players.add(new Player("Lemieux", "PIT", 45, 54)); // 99
            players.add(new Player("Kurri",   "EDM", 37, 53)); // 90
            players.add(new Player("Yzerman", "DET", 42, 56)); // 98
            players.add(new Player("Gretzky", "EDM", 35, 89)); // 124
 
            return players;
        }
    };
 
    Statistics stats;
    
    public StatisticsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void searchLoytaaListallaOlevanPelaajan() {
        Player player = stats.search("Kurri");
        
        assertEquals(player, new Player("Kurri",   "EDM", 37, 53));
    }
    
    @Test
    public void searchPalauttaaNullJosPelaajaEiListalla() {
        Player player = stats.search("Selänne");
        
        assertEquals(player, null);
    }
    
    @Test
    public void teamPalauttaaTiiminOikein() {
        List<Player> tiimi = stats.team("EDM");
        List<Player> oikea = new ArrayList<Player>();
        oikea.add(new Player("Semenko", "EDM", 4, 12));
        oikea.add(new Player("Kurri",   "EDM", 37, 53));
        oikea.add(new Player("Gretzky", "EDM", 35, 89));
        
        assertEquals(tiimi, oikea);
    }
    
    @Test
    public void teamPalauttaaTyhjanKunTiimiEiOleListalla() {
        List<Player> tiimi = stats.team("IFK");
        
        assertEquals(tiimi.size(), 0);
    }
    
    @Test
    public void topScorersPalauttaaOikein() {
        List<Player> tiimi = stats.topScorers(1);
        List<Player> oikea = new ArrayList<Player>();
        oikea.add(new Player("Gretzky", "EDM", 35, 89));
        oikea.add(new Player("Lemieux", "PIT", 45, 54));
        
        assertEquals(tiimi, oikea);
    }
}

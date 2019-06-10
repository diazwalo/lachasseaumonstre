package src.config;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import config.Config;
import config.GameMode;
import config.Map;

public class ConfigTest
{
	private Config config;
	
    @Before
    public void beforeTest()
    {   
    	this.config = new Config();
    }
	
	@Test
    public void testWidth()
    {
    	this.config.setWidth(42);
    	assertEquals(42, this.config.getWidth());
    }
	
	@Test
    public void testHeight()
    {
    	this.config.setHeight(42);
    	assertEquals(42, this.config.getHeight());
    }
	
	@Test
    public void testGameMode()
    {
    	this.config.setGameMode(GameMode.AIvsAI);
    	assertEquals(GameMode.AIvsAI, this.config.getGameMode());
    }

    @Test
	public void testNbTeleportation()
	{
		this.config.setNbTeleportation(42);
		assertEquals(42, this.config.getNbTeleportation());
	}
    
    @Test
	public void testMap()
	{
		this.config.setMap(Map.CIRCULAR);
		assertEquals(Map.CIRCULAR, this.config.getMap());
	}
	
	@Test
    public void testTrap()
    {
    	this.config.setTrap(true);
    	assertTrue(this.config.isTrap());
    }
	
	@Test
    public void testCamouflage()
    {
    	this.config.setCamouflage(true);
    	assertTrue(this.config.isCamouflage());
    }
	
	@Test
    public void testWard()
    {
    	this.config.setWard(true);
    	assertTrue(this.config.isWard());
    }
	
	@Test
    public void testBait()
    {
    	this.config.setBait(true);
    	assertTrue(this.config.isBait());
    }
	
	@Test
    public void testResetConfig()
    {
    	this.config.setDefaultConfig();
    	
    	assertEquals(5, this.config.getWidth());
    	assertEquals(5, this.config.getHeight());
    	assertEquals(GameMode.BEASTvsAI, this.config.getGameMode());
    	assertEquals(2, this.config.getNbTeleportation());
    	assertEquals(Map.SQUARE, this.config.getMap());
    	
    	assertFalse(this.config.isTrap());
    	assertFalse(this.config.isCamouflage());
    	assertFalse(this.config.isWard());
    	assertFalse(this.config.isBait());
    }
}

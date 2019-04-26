package src.config;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import config.Config;
import config.GameMode;

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
    	assertEquals(this.config.getWidth(), 42);
    }
	
	@Test
    public void testHeight()
    {
    	this.config.setHeight(42);
    	assertEquals(this.config.getHeight(), 42);
    }
	
	@Test
    public void testGameMode()
    {
    	this.config.setGameMode(GameMode.AIvsAI);
    	assertEquals(this.config.getGameMode(), GameMode.AIvsAI);
    }

    @Test
	public void testNbTeleportation()
	{
		this.config.setNbTeleportation(42);
		assertEquals(this.config.getNbTeleportation(), 42);
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
    	
    	assertEquals(this.config.getWidth(), 11);
    	assertEquals(this.config.getHeight(), 11);
    	assertEquals(this.config.getGameMode(), GameMode.BEASTvsAI);
    	assertEquals(this.config.getNbTeleportation(), 3);
    	assertFalse(this.config.isTrap());
    	assertFalse(this.config.isCamouflage());
    	assertFalse(this.config.isWard());
    	assertFalse(this.config.isBait());
    }
}

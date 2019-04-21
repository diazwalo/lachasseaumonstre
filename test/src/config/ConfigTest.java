package src.config;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import config.Config;

public class ConfigTest {
	private Config config;
	
	@Before
	public void initConf() {
		this.config=new Config();
		this.config.setConfig(Config.getConfig());
	}
	
	@Test
	public void testAttributDeClass() {
		this.config.setConfig(new boolean[] {true, false, false, false});
		assertTrue(Config.getConfig()[0]);
		
		this.config=new Config();
		assertTrue(Config.getConfig()[0]);
	}
	
	@Test
	public void testSetConfig() {
		this.config.setConfig(Config.getConfig());
		for (int i = 0; i < Config.getConfig().length; i++) {
			assertFalse(Config.getConfig()[0]);
		}
		
		this.config.setConfig(new boolean [] {true, false, true, false});
		assertTrue(Config.getConfig()[0]);
		assertTrue(Config.getConfig()[2]);
	}
}

package src.core;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import config.GameMode;
import config.Map;
import core.CommandParser;

public class CommandParserTest
{

	@Test
	public void testArgsWidth()
	{
		String[] cmd0 = {};
		String[] cmd1 = {"--width=42"};
		String[] cmd2 = {"--width=20", "--width=42"};
		
		CommandParser commandParser0 = new CommandParser(cmd0);
		CommandParser commandParser1 = new CommandParser(cmd1);
		CommandParser commandParser2 = new CommandParser(cmd2);
		
		assertEquals(5, commandParser0.getConfig().getWidth());
		assertEquals(42, commandParser1.getConfig().getWidth());
		assertEquals(42, commandParser2.getConfig().getWidth());
	}
	
	@Test
	public void testArgsHeight()
	{
		String[] cmd0 = {};
		String[] cmd1 = {"--height=42"};
		String[] cmd2 = {"--height=20", "--height=42"};
		
		CommandParser commandParser0 = new CommandParser(cmd0);
		CommandParser commandParser1 = new CommandParser(cmd1);
		CommandParser commandParser2 = new CommandParser(cmd2);
		
		assertEquals(5, commandParser0.getConfig().getHeight());
		assertEquals(42, commandParser1.getConfig().getHeight());
		assertEquals(42, commandParser2.getConfig().getHeight());
	}
	
	@Test
	public void testArgsGameMode()
	{
		String[] cmd0 = {};
		String[] cmd1 = {"--gamemode=ai"};
		String[] cmd2 = {"--gamemode=beast"};
		String[] cmd3 = {"--gamemode=hunter"};
		String[] cmd4 = {"--gamemode=multi"};
		String[] cmd5 = {"--gamemode=ai", "--gamemode=multi"};
		
		CommandParser commandParser0 = new CommandParser(cmd0);
		CommandParser commandParser1 = new CommandParser(cmd1);
		CommandParser commandParser2 = new CommandParser(cmd2);
		CommandParser commandParser3 = new CommandParser(cmd3);
		CommandParser commandParser4 = new CommandParser(cmd4);
		CommandParser commandParser5 = new CommandParser(cmd5);
		
		assertEquals(GameMode.BEASTvsAI, commandParser0.getConfig().getGameMode());
		assertEquals(GameMode.AIvsAI, commandParser1.getConfig().getGameMode());
		assertEquals(GameMode.BEASTvsAI, commandParser2.getConfig().getGameMode());
		assertEquals(GameMode.HUNTERvsAI, commandParser3.getConfig().getGameMode());
		assertEquals(GameMode.BEASTvsHUNTER, commandParser4.getConfig().getGameMode());
		assertEquals(GameMode.BEASTvsHUNTER, commandParser5.getConfig().getGameMode());
	}

	@Test
	public void testArgsNbTeleportation()
	{
		String[] cmd0 = {};
		String[] cmd1 = {"--tp=42"};
		String[] cmd2 = {"--tp=20", "--tp=42"};

		CommandParser commandParser0 = new CommandParser(cmd0);
		CommandParser commandParser1 = new CommandParser(cmd1);
		CommandParser commandParser2 = new CommandParser(cmd2);

		assertEquals(2, commandParser0.getConfig().getNbTeleportation());
		assertEquals(42, commandParser1.getConfig().getNbTeleportation());
		assertEquals(42, commandParser2.getConfig().getNbTeleportation());
	}
	
	@Test
	public void testArgsMap()
	{
		String[] cmd0 = {};
		String[] cmd1 = {"--map=circular"};
		String[] cmd2 = {"--map=circular", "--map=carre"};

		CommandParser commandParser0 = new CommandParser(cmd0);
		CommandParser commandParser1 = new CommandParser(cmd1);
		CommandParser commandParser2 = new CommandParser(cmd2);

		assertEquals(Map.SQUARE, commandParser0.getConfig().getMap());
		assertEquals(Map.CIRCULAR, commandParser1.getConfig().getMap());
		assertEquals(Map.SQUARE, commandParser2.getConfig().getMap());
	}
	
	@Test
	public void testArgsTrap()
	{
		String[] cmd0 = {};
		String[] cmd1 = {"--trap"};
		
		CommandParser commandParser0 = new CommandParser(cmd0);
		CommandParser commandParser1 = new CommandParser(cmd1);
		
		assertFalse(commandParser0.getConfig().isTrap());
		
		assertTrue(commandParser1.getConfig().isTrap());
		assertFalse(commandParser1.getConfig().isCamouflage());
		assertFalse(commandParser1.getConfig().isBait());
		assertFalse(commandParser1.getConfig().isWard());
	}
	
	@Test
	public void testArgsBait()
	{
		String[] cmd0 = {};
		String[] cmd1 = {"--bait"};
		
		CommandParser commandParser0 = new CommandParser(cmd0);
		CommandParser commandParser1 = new CommandParser(cmd1);
		
		assertFalse(commandParser0.getConfig().isBait());
		
		assertTrue(commandParser1.getConfig().isBait());
		assertFalse(commandParser1.getConfig().isCamouflage());
		assertFalse(commandParser1.getConfig().isTrap());
		assertFalse(commandParser1.getConfig().isWard());
	}
	
	@Test
	public void testArgsCamouflage()
	{
		String[] cmd0 = {};
		String[] cmd1 = {"--camouflage"};
		
		CommandParser commandParser0 = new CommandParser(cmd0);
		CommandParser commandParser1 = new CommandParser(cmd1);
		
		assertFalse(commandParser0.getConfig().isCamouflage());
		
		assertTrue(commandParser1.getConfig().isCamouflage());
		assertFalse(commandParser1.getConfig().isBait());
		assertFalse(commandParser1.getConfig().isWard());
		assertFalse(commandParser1.getConfig().isTrap());
	}
	
	@Test
	public void testArgsWard()
	{
		String[] cmd0 = {};
		String[] cmd1 = {"--ward"};
		
		CommandParser commandParser0 = new CommandParser(cmd0);
		CommandParser commandParser1 = new CommandParser(cmd1);
		
		assertFalse(commandParser0.getConfig().isWard());
		
		assertTrue(commandParser1.getConfig().isWard());
		assertFalse(commandParser1.getConfig().isBait());
		assertFalse(commandParser1.getConfig().isCamouflage());
		assertFalse(commandParser1.getConfig().isTrap());
	}
	
	@Test
	public void testArgsFull()
	{
		String[] cmd = {"--width=42", "--height=42", "--gamemode=ai", "--map=circular", "--ward", "--trap", "--camouflage", "--bait"};
		
		CommandParser commandParser0 = new CommandParser(cmd);
		
		assertEquals(42, commandParser0.getConfig().getWidth());
		assertEquals(42, commandParser0.getConfig().getHeight());
		assertEquals(GameMode.AIvsAI, commandParser0.getConfig().getGameMode());
		assertEquals(2, commandParser0.getConfig().getNbTeleportation());
		assertEquals(Map.CIRCULAR, commandParser0.getConfig().getMap());
		assertTrue(commandParser0.getConfig().isWard());
		assertTrue(commandParser0.getConfig().isTrap());
		assertTrue(commandParser0.getConfig().isCamouflage());
		assertTrue(commandParser0.getConfig().isBait());
	}	

}

package src.core;

import static org.junit.Assert.*;

import org.junit.Test;

import config.GameMode;
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
		
		assertEquals(commandParser0.getConfig().getWidth(), 15);
		assertEquals(commandParser1.getConfig().getWidth(), 42);
		assertEquals(commandParser2.getConfig().getWidth(), 42);
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
		
		assertEquals(commandParser0.getConfig().getHeight(), 15);
		assertEquals(commandParser1.getConfig().getHeight(), 42);
		assertEquals(commandParser2.getConfig().getHeight(), 42);
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
		
		assertEquals(commandParser0.getConfig().getGameMode(), GameMode.BEASTvsAI);
		assertEquals(commandParser1.getConfig().getGameMode(), GameMode.AIvsAI);
		assertEquals(commandParser2.getConfig().getGameMode(), GameMode.BEASTvsAI);
		assertEquals(commandParser3.getConfig().getGameMode(), GameMode.HUNTERvsAI);
		assertEquals(commandParser4.getConfig().getGameMode(), GameMode.BEASTvsHUNTER);
		assertEquals(commandParser5.getConfig().getGameMode(), GameMode.BEASTvsHUNTER);
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
		String[] cmd = {"--width=42", "--height=42", "--gamemode=ai", "--ward", "--trap", "--camouflage", "--bait"};
		
		CommandParser commandParser0 = new CommandParser(cmd);
		
		assertEquals(42, commandParser0.getConfig().getWidth());
		assertEquals(42, commandParser0.getConfig().getHeight());
		assertEquals(GameMode.AIvsAI, commandParser0.getConfig().getGameMode());
		assertTrue(commandParser0.getConfig().isWard());
		assertTrue(commandParser0.getConfig().isTrap());
		assertTrue(commandParser0.getConfig().isCamouflage());
		assertTrue(commandParser0.getConfig().isBait());
	}	

}

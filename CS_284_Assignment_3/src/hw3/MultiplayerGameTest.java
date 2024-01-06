package hw3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MultiplayerGameTest { //doing one test case per method (from SSW-315)

	MultiplayerGame test = new MultiplayerGame(5);
	
	//MultiplayerGame(int)
	@Test
	void testMultiplayerGame() {
		MultiplayerGame hey = new MultiplayerGame(4);
		assertEquals("Player0, Player1, Player2, Player3.", hey.toString());
	}
	
	@Test
	void testMultiplayerGameInvalidInput() {
		assertThrows(IllegalArgumentException.class, () -> new MultiplayerGame(0));
	}
	
	@Test
	void testMultiplayerGameOne() {
		MultiplayerGame hey = new MultiplayerGame(1);
		assertEquals("Player0.", hey.toString());
	}
	
	//size()
	@Test
	void testSize() {
		test.addGamePiece(1, "hey", 2);
		test.addGamePiece(1, "car", 15);
		test.addGamePiece(2, "shrimp", -178);
		assertEquals(3, test.size());
	}
	
	@Test
	void testSizeOne() {
		test.addGamePiece(1, "hey", 4);
		assertEquals(1, test.size());
	}
	
	@Test
	void testSizeNoPieces() {
		assertEquals(0, test.size());
	}
	
	//addGamePiece()
	@Test
	void testAdd() {
		test.addGamePiece(3, "testing", 4);
		assertTrue(test.hasGamePiece(3, "testing"));
	}
	
	@Test
	void testAddNoPlayer() {
		assertThrows(IllegalArgumentException.class, () -> new MultiplayerGame(0));
	}
	
	@Test
	void testAddDuplicatePiece() {
		test.addGamePiece(3, "tests", 4);
		assertThrows(IllegalArgumentException.class, () -> test.addGamePiece(3, "tests", 4));
	}
	
	//removeGamePiece()
	@Test
	void testRemove() {
		test.addGamePiece(1, "hey", 2);
		test.addGamePiece(1, "car", 15);
		test.addGamePiece(2, "shrimp", -178);
		test.removeGamePiece(1, "hey");
		assertFalse(test.hasGamePiece(1,  "hey"));
	}
	
	@Test
	void testRemoveNoPlayer() {
		assertThrows(IllegalArgumentException.class, () -> test.removeGamePiece(15, "tests")); //test is only 5 players
	}
	
	@Test
	void testRemoveNoPiece() {
		assertThrows(IllegalArgumentException.class, () -> test.removeGamePiece(1, "tests"));
	}
	
	@Test
	void testRemoveEndOfList() { //Because I split into two separate conditions (when the list wraps around and when it doesn't)
		test.addGamePiece(4, "hey", 2);
		test.removeGamePiece(4, "hey");
		assertFalse(test.hasGamePiece(4,  "hey"));
	}
	
	//hasGamePiece()
	@Test
	void testHasPieceTrue() {
		test.addGamePiece(1, "hey", 2);
		assertTrue(test.hasGamePiece(1,  "hey"));
	}
	
	@Test
	void testHasPieceFalse() {
		assertFalse(test.hasGamePiece(1,  "hey"));
	}
	
	@Test
	void testHasPieceNoPlayer() {
		assertThrows(IllegalArgumentException.class, () -> test.hasGamePiece(5, "tests"));
	}
	
	@Test
	void testHasPieceEndOfList() {
		test.addGamePiece(4, "hello", 2);
		assertTrue(test.hasGamePiece(4,  "hello"));
	}
	
	//removeAllGamePieces()
	@Test
	void testRemoveAll() {
		test.addGamePiece(1, "hey", 2);
		test.addGamePiece(1, "car", 15);
		test.addGamePiece(1, "shrimp", -178);
		test.addGamePiece(4, "hello", 15);
		test.removeAllGamePieces(1);
		assertFalse(test.hasGamePiece(1, "hey") && test.hasGamePiece(1, "car") && test.hasGamePiece(1, "shrimp"));
	}
	
	@Test
	void testRemoveKeepsOtherPieces() {
		test.addGamePiece(1, "hey", 2);
		test.addGamePiece(1, "car", 15);
		test.addGamePiece(1, "shrimp", -178);
		test.addGamePiece(4, "hello", 15);
		test.removeAllGamePieces(1);
		assertTrue(test.hasGamePiece(4, "hello"));
	}
	
	@Test
	void testRemoveAllNoPlayer() {
		assertThrows(IllegalArgumentException.class, () -> test.removeAllGamePieces(-1));
	}
	
	@Test
	void testRemoveAllEndOfList() {
		test.addGamePiece(4, "hello", 15);
		test.removeAllGamePieces(4);
		assertFalse(test.hasGamePiece(4, "hello"));
	}
	
	//increaseStrength()
	@Test
	void testIncrease() {
		test.addGamePiece(1, "hey", 2);
		test.addGamePiece(1, "car", 15);
		test.increaseStrength(1, 15);
		assertEquals("Player0, Player1, GamePiece: car strength: 30, GamePiece: hey strength: 17, Player2, Player3, Player4.", test.toString());
	}
	
	@Test
	void testIncreaseNegative() {
		test.addGamePiece(1, "hey", 10);
		test.addGamePiece(1, "car", 24);
		test.increaseStrength(1, -50);
		assertEquals("Player0, Player1, GamePiece: car strength: -26, GamePiece: hey strength: -40, Player2, Player3, Player4.", test.toString());
	}
	
	@Test
	void testIncreaseNoPieces() {
		test.addGamePiece(1, "hey", 10);
		test.increaseStrength(2, 50);
		assertEquals("Player0, Player1, GamePiece: hey strength: 10, Player2, Player3, Player4.", test.toString());
	}
	
	@Test
	void testIncreaseNoPlayer() {
		assertThrows(IllegalArgumentException.class, () -> test.increaseStrength(-16, 20));
	}
	
	@Test
	void testIncreaseEndOfList() {
		test.addGamePiece(4, "hello", 15);
		test.addGamePiece(4, "peanutButter", 37);
		test.addGamePiece(4, "jelly", 29);
		test.increaseStrength(4, 36);
		assertEquals("Player0, Player1, Player2, Player3, Player4, GamePiece: jelly strength: 65, GamePiece: peanutButter strength: 73, GamePiece: hello strength: 51.", test.toString());
	}
	
	//toString()
	@Test
	void testToString() {
		test.addGamePiece(1, "hey", 2);
		test.addGamePiece(1, "car", 15);
		test.addGamePiece(2, "hello", 15);
		test.addGamePiece(3, "peanutButter", 37);
		test.addGamePiece(4, "jelly", 29);
		assertEquals("Player0, Player1, GamePiece: car strength: 15, GamePiece: hey strength: 2, Player2, GamePiece: hello strength: 15, Player3, GamePiece: peanutButter strength: 37, Player4, GamePiece: jelly strength: 29.", test.toString());
	}
	
	@Test
	void testToStringOneEntity() {
		MultiplayerGame testOne = new MultiplayerGame(1);
		assertEquals("Player0.", testOne.toString());
	}
	
	//initializeTurnTracker()
	@Test
	void testInitialize() {
		test.initializeTurnTracker();
		assertEquals("Player0", test.currentEntityToString());
	}
	
	//nextPlayer()
	@Test
	void testNextPlayer() {
		test.addGamePiece(1, "hey", 2);
		test.addGamePiece(1, "car", 15);
		test.addGamePiece(2, "hello", 15);
		test.initializeTurnTracker();
		test.nextPlayer();
		assertEquals("Player1", test.currentEntityToString());
	}
	
	@Test
	void testNextPlayerNoPieces() {
		test.initializeTurnTracker();
		test.nextPlayer();
		assertEquals("Player1", test.currentEntityToString());
	}
	
	//nextEntity()
	@Test
	void testNextEntity() {
		test.addGamePiece(0, "hey", 2);
		test.initializeTurnTracker();
		test.nextEntity();
		assertEquals("GamePiece: hey strength: 2", test.currentEntityToString());
	}
	
	@Test
	void testNextEntityNoPieces() {
		test.initializeTurnTracker();
		test.nextEntity();
		assertEquals("Player1", test.currentEntityToString());
	}
	
	//prevPlayer()
	@Test
	void testPrevPlayer() {
		test.addGamePiece(1, "hey", 2);
		test.addGamePiece(1, "car", 15);
		test.addGamePiece(1, "hello", 15);
		test.initializeTurnTracker();
		test.nextPlayer();
		test.nextPlayer();
		test.prevPlayer();
		assertEquals("Player1", test.currentEntityToString());
	}
	
	@Test
	void testPrevPlayerNoPieces() {
		test.initializeTurnTracker();
		test.nextPlayer();
		test.nextPlayer();
		test.nextPlayer();
		test.prevPlayer();
		assertEquals("Player2", test.currentEntityToString());
	}
	
	//currentEntityToString()
	@Test
	void testEntityToStringPlayer() {
		test.initializeTurnTracker();
		test.nextPlayer();
		assertEquals("Player1", test.currentEntityToString());
	}
	
	@Test
	void testEntityToStringPiece() {
		test.addGamePiece(1, "thisParticularOneIsCool", 2);
		test.initializeTurnTracker();
		test.nextPlayer();
		test.nextEntity();
		assertEquals("GamePiece: thisParticularOneIsCool strength: 2", test.currentEntityToString());
	}

}

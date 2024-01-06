package hw3;

public class MultiplayerGameUpdated {
	//data fields
	/** The reference to the entity whose turn it is */
	private GameEntity turnTracker;
	/** The reference to the array of GamePlayers */
	private GameEntity[] index;
	
	/** Creates a new MultiplayerGame with n amount of GamePlayers, also connected through a circular doubly linked list.
	 * @param n The number of GamePlayers in the MultiplayerGame
	 * @throws IllegalArgumentException if n is 0 or less (can't have a game with 0 or negative players)
	 */
	public MultiplayerGameUpdated(int n) {
		if (n <= 0) throw new IllegalArgumentException("MultiplayerGame: You can't have less than one player in the game!");
		
		this.index = new GameEntity[n]; //just for GamePlayers, not GamePieces
			index[0] = new GamePlayer(null, null, 0); //initializes "head" of the list
			
		if (n == 1) {
			index[0].prev = index[0].next = index[0];
			return;
		}
		
		for (int i = 1; i < n-1; i++) {
			index[i] = new GamePlayer(index[i-1], null, i); //initializes each successor based on what it follows
		}
		index[n-1] = new GamePlayer(index[n-2], index[0], n-1); //wraps last GamePlayer around to the front
	
		index[0].prev = index[n-1];
	
		for (int i = 0; i < n-1; i++) {
			index[i].next = index[i+1];
		}
	}
	
	/** Gives the amount of GamePieces present in the MultiplayerGame.
	 * @return size (number of GamePieces)
	 */
	public int size() {
		if (index[0].next == index[0]) return 1;
		
		GameEntity currentEntity = index[0].next; //starts currentEntity past index[0] so for loop can run
		int size;
		
		if (currentEntity.isGamePlayer() == false) size = 1; //accounts for skipping index[0] initially
		
		else size = 0;
		
		while (currentEntity != index[0]) {
			if (currentEntity.isGamePlayer() == false) {
				size++;
			}
			currentEntity = currentEntity.next;
		}
		
		return size;
	}
	
	/** Adds a GamePiece of specified name and strength to a given player. Also adds the GamePiece to the linked list.
	 * @param playerId The player that will get a GamePiece added to
	 * @param name The name of the GamePiece being added
	 * @param strength The strength value of the GamePiece being added
	 * @throws IllegalArgumentException if player doesn't exist or if GamePiece already exists
	 */
	public void addGamePiece(int playerId, String name, int strength) {
		if (playerId < 0 || playerId > this.index.length - 1) throw new IllegalArgumentException("addGamePiece: no such player"); //checks if player exists in index
		
		if (this.hasGamePiece(playerId, name) == true) throw new IllegalArgumentException("addGamePiece: duplicate entity"); //checks if the GamePiece is there already
		
		GameEntity currentEntity = index[playerId];
		GameEntity piece = new GamePiece(currentEntity, currentEntity.next, name, strength);
		currentEntity.next = piece;
		currentEntity = piece.next;
		currentEntity.prev = piece;
	}
	
	/** Removes a specified GamePiece from a given player.
	 * @param playerId The player that the GamePiece will be removed from
	 * @param name The name of the GamePiece being removed
	 * @throws IllegalArgumentException if player doesn't exist or GamePiece doesn't exist
	 */
	public void removeGamePiece(int playerId, String name) {
		if (playerId < 0 || playerId > this.index.length - 1) throw new IllegalArgumentException("removeGamePiece: no such player"); //checks if player exists in index
		
		if (this.hasGamePiece(playerId, name) == false) throw new IllegalArgumentException("removeGamePiece: entity does not exist"); //checks if the GamePiece is there already
		
		GameEntity currentEntity = index[playerId]; //functions as "head"
		int place = 0; //keeps track of where the GamePiece is

		
		while(currentEntity.next.isGamePlayer() == false) { //used to find the placement of the GamePiece in the list
			currentEntity = currentEntity.next;
			if (currentEntity.getName() == name) {
				place++;
				break;
			}
			place++;
		}
		
		currentEntity = index[playerId];
		
		for (int i = 0; i < place; i++) { //normally place - 1 but updated logic goes better this way
			currentEntity = currentEntity.next;
		}
		
		currentEntity.next.prev = currentEntity.prev;
		currentEntity.prev.next = currentEntity.next;		
	}
	
	/** Shows if a given player has a specified GamePiece.
	 * @param playerId The player that will be checked for the GamePiece
	 * @param name The name of the GamePiece that will be searched for
	 * @return If the GamePiece exists or not
	 * @throws IllegalArgumentException if the player doesn't exist
	 */
	public boolean hasGamePiece(int playerId, String name) {
		if (playerId < 0 || playerId > this.index.length - 1) throw new IllegalArgumentException("hasGamePiece: no such player"); //checks if player exists in index
		
		GameEntity currentEntity = index[playerId]; //functions as "head"

		while(currentEntity.next.isGamePlayer() == false) { //checks if piece already exists in player
			currentEntity = currentEntity.next;
			if (currentEntity.getName() == name) {
				return true;
			}
		}
		
		return false;
	}
	
	/** Removes all GamePieces from a specified player
	 * @param playerId The player that will have all their GamePieces removed
	 * @throws IllegalArgumentException if the player doesn't exist
	 */
	public void removeAllGamePieces(int playerId) {
		if (playerId < 0 || playerId > this.index.length - 1) throw new IllegalArgumentException("removeAllGamePieces: no such player"); //checks if player exists in index

		if (playerId == this.index.length - 1) { //if the next player is player0 (wraps around)
			index[playerId].next = index[0]; //removes everything between the two players in the .next
			index[0].prev = index[playerId]; //does the same for .prev
		}
		
		else {
			index[playerId].next = index[playerId + 1]; //same as above
			index[playerId + 1].prev = index[playerId];
		}
		
	}
	
	/** Increases the strength value of each GamePiece owned by a specified player
	 * @param playerId The player that will have all their GamePieces increase in strength
	 * @param n The strength value that will be added to each GamePiece
	 * @throws IllegalArgumentException if the player doesn't exist
	 */
	public void increaseStrength(int playerId, int n) {
		if (playerId < 0 || playerId > this.index.length - 1) throw new IllegalArgumentException("increaseStrength: no such player"); //checks if player exists in index
		
		GameEntity currentEntity = index[playerId];
		currentEntity = index[playerId].next;
		
		while(currentEntity.isGamePlayer() == false) {
			((GamePiece)currentEntity).updateStrength(n);
			currentEntity = (GamePiece) currentEntity.next;
		}		
	}
	
	/**	Returns the string representation of the MultiplayerGame. */
	public String toString() {
		if (this.index[0].next == index[0]) { //case for one player with no pieces
			return "Player0.";
		}
		
		StringBuilder sb = new StringBuilder();
		
		GameEntity currentEntity = this.index[0];
		sb.append(currentEntity + ", ");
		currentEntity = currentEntity.next;
		
		while (currentEntity.next != this.index[0]) {
			sb.append(currentEntity + ", ");
			currentEntity = currentEntity.next;
		}
		
		sb.append(currentEntity + ".");
		
		return sb.toString();
	}
	
	/**	Initializes a turnTracker to keep track of which entity's turn it is. */
	public void initializeTurnTracker() {
		turnTracker = index[0];
	}
	
	/** Moves the turnTracker to the next player in the MultiplayerGame, skipping over the GamePieces. */
	public void nextPlayer() {
		turnTracker = turnTracker.next;
		while(turnTracker.isGamePlayer() == false) turnTracker = turnTracker.next;
	}
	
	/** Moves the turnTracker to the next entity, regardless of type. */
	public void nextEntity() {
		turnTracker = turnTracker.next;
	}
	
	/** Moves the turnTracker to the previous player, skipping over the GamePieces. */
	public void prevPlayer() {
		turnTracker = turnTracker.prev;
		while(turnTracker.isGamePlayer() == false) turnTracker = turnTracker.prev;
	}
	
	/** Returns the string representation of the entity being pointed at by turnTracker. */
	public String currentEntityToString() {
		return turnTracker.toString();
	}
	
	public static void main(String[] args) {
		MultiplayerGameUpdated n = new MultiplayerGameUpdated(5);
		n.addGamePiece(1, "soup", 0);
		n.addGamePiece(1, "hey", 0);
		n.removeGamePiece(1, "hey");
	}
}

package edu.isu.cs.cs3308;

/**
 * Decrypts text
 *
 * @author Dylan Lasher
 */
public class SolitaireDecrypt
{
	SolitaireAlgorithm theDeck;

	public SolitaireDecrypt(String deckPath)
	{
		System.out.println("\n" + "Initiating Decryption:\n");

		theDeck = new SolitaireAlgorithm(deckPath);
	}

	/**
	 * Encrypt string input
	 */
	String execute(String encString)
	{
		return theDeck.encode(false, encString);
	}
}

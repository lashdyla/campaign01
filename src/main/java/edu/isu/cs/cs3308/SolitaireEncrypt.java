package edu.isu.cs.cs3308;

/**
 * Text encryption
 *
 * @author Dylan Lasher
 */
public class SolitaireEncrypt
{
	SolitaireAlgorithm theDeck;

	public SolitaireEncrypt(String deckPath)
	{
		System.out.println("\n" + "Initiating Encryption:\n");

		theDeck = new SolitaireAlgorithm(deckPath);
	}

	/**
	 * Decrypt string input
	 */
	String execute(String decString)
	{
		return theDeck.encode(true, decString);
	}
}

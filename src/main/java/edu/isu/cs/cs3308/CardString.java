package edu.isu.cs.cs3308;

import edu.isu.cs.cs3308.structures.impl.CircularlyLinkedList;

/**
 * Convert string input into acceptable form for encryption
 *
 * @author Dylan Lasher
 */
public class CardString
{
	private CircularlyLinkedList<Integer> cardList = new CircularlyLinkedList<>();

	public CircularlyLinkedList<Integer> getCardList() {
		return cardList;
	}

	private boolean debugPrint = false;

	// Constructor to get the string
	public CardString(String inputString)
	{
		cleanString(inputString);
	}

	/**
	 * Convert string input into simplistic 1 - 26 characters,
	 * no extras.
	 *
	 * @param stringForList string to convert to number list
	 */
	private void cleanString(String stringForList)
	{
		char[] charString = stringForList.toCharArray();

		// loop through string to add to list, letters only
		for (int i = 0; i < charString.length; i++)
		{
			// temp for insertion
			int letterNum = -1;

			//Check ascii letter status
			if (charString[i] >= 65 && charString[i] <= 90)
			{
				letterNum = charString[i] - 64;
				cardList.addLast(letterNum);
			}
			else if (charString[i] >= 97 && charString[i] <= 122)
			{
				letterNum = charString[i] - 96;
				cardList.addLast(letterNum);
			}
		}

		for (int i = 0; i < cardList.size() % 5; i++)
		{
			cardList.addLast(24);
		}

		if (debugPrint)
		{
			System.out.println("Start: " + stringForList);
			System.out.print("Ready: ");
			cardList.printList();
		}
	}
}

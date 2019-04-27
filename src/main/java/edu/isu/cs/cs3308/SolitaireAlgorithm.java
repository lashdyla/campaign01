package edu.isu.cs.cs3308;

import edu.isu.cs.cs3308.structures.impl.CircularlyLinkedList;

/**
 * Process Solitaire Algorithmic implementation
 *
 * @author Dylan Lasher
 */
public class SolitaireAlgorithm
{
	// key generator
	private KeyGeneration theKey;

	private CircularlyLinkedList<Integer> codeMessage = new CircularlyLinkedList<>();
	private CircularlyLinkedList<Integer> codeKey = new CircularlyLinkedList<>();
	private CircularlyLinkedList<Integer> finalCode = new CircularlyLinkedList<>();

	private boolean debugPrint = true;

	/**
	 * Constructor for creating generator
	 **/
	public SolitaireAlgorithm(String deckPath)
	{
		// generate the key
		theKey = new KeyGeneration(deckPath);
	}

	private void prepareCode(String codeString)
	{
		finalCode = new CircularlyLinkedList<>();

		CardString theMessage = new CardString(codeString);

		codeMessage = theMessage.getCardList();

		theKey.generateCodeKey(codeMessage.size());

		codeKey = theKey.getCodeKey();

		if (debugPrint)
		{
			System.out.println("Message:  " + numList2String(codeMessage));
			System.out.print("Coded Message: ");
			codeMessage.printList();
			System.out.print("Key:     ");
			codeKey.printList();
		}
	}

	/**
	 * Encryption
	 */
	public String encode(boolean doEncode, String codeString)
	{
		// prepare lists/values
		prepareCode(codeString);

		int tempNum = -1;
		int listSize = codeKey.size();

		// iterate until everything coded
		for (int i = 0; i < listSize; i++)
		{
			if (doEncode)
			{
				tempNum = codeMessage.removeFirst() + codeKey.removeFirst();
				if (tempNum > 26)
				{
					tempNum -= 26;
				}
			}
			else {
				// Compare two numbers and subtract
				int tempUpper = codeMessage.removeFirst();
				int tempLower = codeKey.removeFirst();

				if (tempUpper <= tempLower)
				{
					tempUpper += 26;
				}
				tempNum = tempUpper - tempLower;
			}

			finalCode.addLast(tempNum);
		}

		String strCode = numList2String(finalCode);

		if (debugPrint)
		{
			System.out.print("Finally:   ");
			finalCode.printList();
			System.out.println("Code:     " + strCode + "\n");
		}

		return strCode;
	}

	/**
	 * Convert number list into letters
	 */
	public String numList2String(CircularlyLinkedList<Integer> numList)
	{
		String converted = "";
		int listSize = numList.size();

		for (int i = 0; i < listSize; i++)
		{
			char numLetter = (char)(numList.get(i) + 64);
			converted += numLetter;
		}

		return converted;
	}
}

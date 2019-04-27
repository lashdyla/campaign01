package edu.isu.cs.cs3308;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import edu.isu.cs.cs3308.structures.impl.CircularlyLinkedList;

/**
 * Key generation for encryption functionality
 *
 * @author Dylan Lasher
 */
public class KeyGeneration
{
	// Create CLL for deck values, and functionality
	private CircularlyLinkedList<Integer> deckKey = new CircularlyLinkedList<>();
	private CircularlyLinkedList<Integer> codeKey = new CircularlyLinkedList<>();
	public CircularlyLinkedList<Integer> getCodeKey() {
		return codeKey;
	}
	private boolean debugPrint = false;


	public KeyGeneration(String deckLocation)
	{
		// Split file for list functionality
		deckSplit(deckLocation);

		if (debugPrint)
		{
			System.out.println("Input: " + deckLocation);
			System.out.print("Before: ");
			deckKey.printList();
		}
	}

	/**
	 * Scan and split into CLL
	 * Will read in the Deck list provided and split it into
	 * a Circularly Linked List for use later.
	 */
	private void deckSplit(String deckPath)
	{
		// Read from file
		try
		{
			// Get string
			List<String> deckList = Files.readAllLines(Paths.get(deckPath));

			// Split into CLL
			for (String deckNum: deckList.get(0).split(" "))
			{
				deckKey.addLast(Integer.parseInt(deckNum));
			}
		}
		catch (IOException ex)
		{
			System.out.println("Please note: " + ex);
		}
	}

	/**
	 * Trade element with following ref in list
	 **/
	private void swapWithNext(int check)
	{
		if (check >= 0 && check <= 28) {
			int indexLookup = deckKey.indexOf(check);

			if (indexLookup+1 == deckKey.size()) {
				int dataBegin = deckKey.removeFirst();
				int dataLookup = deckKey.removeLast();

				deckKey.addLast(dataBegin);
				deckKey.addFirst(dataLookup);
			}
			else {
				int dataLookup = deckKey.remove(indexLookup);
				deckKey.insert(dataLookup, indexLookup+1);
			}
		}
	}

	/**
	 * Move 27 to 1 in list
	 */
	private void step1Swap27()
	{
		swapWithNext(27);

		if (debugPrint)
		{
			System.out.print("First: ");
			deckKey.printList();
		}
	}

	/**
	 * Move 28 to 2 in list
	 */
	private void step2Move28()
	{
		swapWithNext(28);
		swapWithNext(28);

		if (debugPrint)
		{
			System.out.print("Second: ");
			deckKey.printList();
		}
	}

	/**
	 * Handle jokers
	 */
	private void step3TripleCut()
	{
		// locate jokers
		int index27 = deckKey.indexOf(27);
		int index28 = deckKey.indexOf(28);

		int joker1, joker2;

		// Determine which is first in list
		if (index27 < index28)
		{
			joker1 = index27;
			joker2 = index28;
		}
		else {
			joker1 = index28;
			joker2 = index27;
		}

		// Store if either on end
		boolean joker2end = false;
		boolean joker1top = false;

		// Determine if either on end
		if (joker2 == deckKey.size()-1)
		{
			joker2end = true;
		}
		if (joker1 == 0)
		{
			joker1top = true;
		}

		CircularlyLinkedList<Integer> cut2End = new CircularlyLinkedList<>();
		CircularlyLinkedList<Integer> cut1Top = new CircularlyLinkedList<>();

		// if joker2 not at end
		if (joker2end == false)
		{
			int deckSize = deckKey.size();
			for (int i = joker2 + 1; i < deckSize; i++)
			{
				cut2End.addFirst(deckKey.removeLast());
			}
		}

		// if joker1 is not on top
		if (joker1top == false)
		{
			for (int i = 0; i < joker1; i++)
			{
				cut1Top.addLast(deckKey.removeFirst());
			}
		}

		if (joker2end == false)
		{
			// Get size
			int cut2Size = cut2End.size();

			for (int i = 0; i < cut2Size; i++)
			{
				deckKey.addFirst(cut2End.removeLast());
			}
		}
		// Top to end
		if (joker1top == false)
		{
			// Get size
			int cut1Size = cut1Top.size();

			for (int i = 0; i < cut1Size; i++)
			{
				deckKey.addLast(cut1Top.removeFirst());
			}
		}

		if (debugPrint)
		{
			System.out.print("Third: ");
			deckKey.printList();
		}
	}

	/**
	 * Remove bottom, shift top to bottom (end-to-end)
	 */
	private void step4Bottom()
	{
		int deckLast = deckKey.removeLast();

		int deckCount = deckLast;

		if (deckCount == 28)
		{
			deckCount = 27;
		}

		for (int i = 0; i < deckCount; i++)
		{
			deckKey.addLast(deckKey.removeFirst());
		}

		deckKey.addLast(deckLast);

		if (debugPrint)
		{
			System.out.print("Fourth: ");
			deckKey.printList();
		}
	}

	int step5Num = -1;

	/**
	 * Find list number with first count.
	 */
	private void step5Card()
	{
		int deckCount = deckKey.first();

		if (deckCount == 28)
		{
			deckCount = 27;
		}

		int countValue = deckKey.get(deckCount);

		if (countValue == 27 || countValue == 28)
		{
			step1Swap27();
			step2Move28();
			step3TripleCut();
			step4Bottom();
			step5Card();
		}
		else {
			step5Num = countValue;
		}
	}

	/**
	 * Create list for message
	 */
	public void generateCodeKey(int codeSize)
	{
		for (int i = 0; i < codeSize; i++)
		{
			step1Swap27();
			step2Move28();
			step3TripleCut();
			step4Bottom();
			step5Card();
			codeKey.addLast(step5Num);

			if (debugPrint) {
				System.out.print("Fifth: ");
				codeKey.printList();
			}
		}
	}
}

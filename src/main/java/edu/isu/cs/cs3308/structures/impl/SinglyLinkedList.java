package edu.isu.cs.cs3308.structures.impl;

import edu.isu.cs.cs3308.structures.List;
import edu.isu.cs.cs3308.structures.Node;

/**
 * A class to implement a singly linked list based off the List class
 *
 * @author Dylan Lasher
 * @param <E> any list type
 */
public class SinglyLinkedList<E> implements List<E> {

	protected Node<E> head = null;
	protected Node<E> tail = null;
	protected int size = 0;

	/**
	 * Check if null element
	 */
	protected boolean checkElement(E element)
	{
		return (element != null) ? true : false;
	}

	/**
	 * Checks if index is within list range
	 */
	protected boolean checkIndex(int index) {
		return (index < size && index >= 0) ? true : false;
	}

	protected void singleHeadTail(Node<E> theNode)
	{
		if (size == 1)
		{
			head = theNode;
			tail = theNode;
		}
		else if (size == 0)
		{
			head = null;
			tail = null;
		}

		if (size > 0)
		{
			verifyTail();
		}
	}

	/**
	 * Get list node at index
	 */
	protected Node<E> getNode(int index)
	{
		Node<E> seekNode = head;

		for (int i = 0; i < index; i++)
		{
			seekNode = seekNode.getNext();
		}

		return seekNode;
	}

	/**
	 * Increment size value
	 */
	protected void addSize() {
		size++;
	}

	/**
	 * Decrement size value
	 * */
	protected void subSize()
	{
		size--;

		if (size < 0) // Can't drop below 0
		{
			size = 0;
		}
	}

	/**
	 * For circular functionality - verify SLL tail points at null
	 */
	protected void verifyTail() {
		tail.setNext(null);
	}

	/**
	 * Get data of first node
	 */
	@Override
	public E first() {
		return (head != null) ? head.getData() : null;
	}

	/**
	 * Get data of last node
	 */
	@Override
	public E last() {
		return (tail != null) ? tail.getData() : null;
	}

	/**
	 * Create node at end of list
	 */
	@Override
	public void addLast(E element)
	{
		if (checkElement(element))
		{
			if (!isEmpty())
			{
				Node<E> lastNode = new Node<>(element);
				lastNode.setNext(null);

				tail.setNext(lastNode);
				tail = lastNode;

				addSize();

				singleHeadTail(lastNode);
			}
			else {
				addFirst(element);
			}
		}
	}

	/**
	 * Create node with given data at list start
	 */
	@Override
	public void addFirst(E element)
	{
		if (checkElement(element))
		{
			Node<E> firstNode = new Node<>(element);

			if (size > 0)
			{
				firstNode.setNext(head);
			}
			else {
				firstNode.setNext(null);
			}

			head = firstNode;

			addSize();

			singleHeadTail(firstNode);
		}
	}

	/**
	 * Remove first list node
	 */
	@Override
	public E removeFirst()
	{
		if (head != null)
		{
			if (size > 1)
			{
				Node<E> removeNode = head;
				head = removeNode.getNext();
				removeNode.setNext(null);

				subSize();

				singleHeadTail(head);

				return removeNode.getData();
			}
			else {
				Node<E> removeNode = head;
				head.setNext(null);
				head = null;
				tail = null;

				subSize();

				singleHeadTail(head);

				return removeNode.getData();
			}
		}
		else {
			return null;
		}
	}

	/**
	 * Remove last list node
	 */
	@Override
	public E removeLast()
	{
		if (size > 1)
		{
			return remove(size - 1);
		}

		else {
			return removeFirst();
		}
	}

	/**
	 * Create node with given data at specified index
	 */
	@Override
	public void insert(E element, int index)
	{
		if (checkElement(element))
		{
			if (index == 0)
			{
				addFirst(element);
			}
			else if (index >= size)
			{
				addLast(element);
			}
			else {
				if (checkIndex(index))
				{
					Node<E> prevNode = getNode(index-1);
					Node<E> insertNode = new Node<>(element);
					insertNode.setNext(prevNode.getNext());
					prevNode.setNext(insertNode);

					addSize();
				}
			}
		}
	}

	/**
	 * Remove specified node in list, given index
	 */
	@Override
	public E remove(int index)
	{
		if (checkIndex(index))
		{
			if (index == 0)
			{
				return removeFirst();
			}
			else {
				Node<E> prevNode = getNode(index-1);
				Node<E> removeNode = prevNode.getNext();
				prevNode.setNext(removeNode.getNext());
				removeNode.setNext(null);

				if (index == size-1)
				{
					tail = prevNode;
				}

				subSize();
				singleHeadTail(prevNode);

				return removeNode.getData();
			}
		}
		else {
			return null;
		}
	}

	/**
	 * Get data from node at specified index
	 */
	@Override
	public E get(int index)
	{
		if (checkIndex(index))
		{
			if (index == 0)
			{
				return head.getData();
			}
			else if (index == size-1)
			{
				return tail.getData();
			}
			else {
				return getNode(index).getData();
			}
		}
		else {
			return null;
		}
	}

	/**
	 * Return list size
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Check if empty list
	 */
	@Override
	public boolean isEmpty() {
		return (size == 0) ? true : false;
	}

	/**
	 * Print list contents
	 */
	@Override
	public void printList()
	{
		if (!isEmpty())
		{
			Node<E> printNode = head;

			for (int i = 0; i < size; i++)
			{
				if (i < size-1)
				{
					System.out.print(printNode.getData() + " ");
				}
				else {
					System.out.println(printNode.getData());
				}

				printNode = printNode.getNext();
			}
		}
		else {
			System.out.println("List is empty");
		}
	}

	/**
	 * Find given list item
	 */
	@Override
	public int indexOf(E item)
	{
		if (!isEmpty() && item != null)
		{
			Node<E> findNode = head;

			for (int i = 0; i < size; i++)
			{
				if (findNode.getData() == item)
				{
					System.out.println("Index: " + i + "\n");
					System.out.println("Data: " + findNode.getData() + "\n");
					return i;
				}
				findNode = findNode.getNext();
			}
		}
		return -1;
	}
}

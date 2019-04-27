package edu.isu.cs.cs3308.structures;

/**
 * Node class for list implementation
 *
 * @author Dylan Lasher
 * @param <E> any node type
 */
public class Node<E>
{
	// Data stored in node.
	private E data;

	// Stores next node status
	private Node<E> next;

	public Node(E data) {
		this.data = data;
	}

	/**
	 * Get stored data
	 */
	public E getData() {
		return data;
	}

	/**
	 * Set stored data
	 */
	public void setData(E data) {
		this.data = data;
	}

	/**
	 * Get next Node
	 */
	public Node<E> getNext() {
		return next;
	}

	/**
	 * Set next Node
	 */
	public void setNext(Node<E> next) {
		this.next = next;
	}
}

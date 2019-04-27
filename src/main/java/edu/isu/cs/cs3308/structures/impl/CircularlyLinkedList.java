package edu.isu.cs.cs3308.structures.impl;

/**
 * CLL implementation with imported functionality
 *
 * @author Dylan Lasher
 * @param <E> any list type
 */
public class CircularlyLinkedList<E> extends SinglyLinkedList<E>
{
	@Override
	protected void verifyTail() { tail.setNext(head); }
}

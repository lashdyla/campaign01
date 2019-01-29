/**Author: Dylan Lasher
 * Class: CS 3308
 * Campaign 01
 * Purpose: To implement the modified Solitaire algorithm described in the
 * assignment specifications.
 */

package test.java.edu.isu.cs.cs3308.structures.impl;

import java.io.IOException;
import java.util.Scanner;

public class SolitaireEncDec
{

    SolitaireNodes deckBack;

    //Create circularly linked list - derived from scan.
    public void makeDeck(Scanner scanner)
            throws IOException
    {
        SolitaireNodes cNode = null;
        if (scanner.hasNextInt())
        {
            cNode = new SolitaireNodes();
            cNode.cardValue = scanner.nextInt();
            cNode.next = cNode;
            deckBack = cNode;
        }
        while (scanner.hasNextInt())
        {
            cNode = new SolitaireNodes();
            cNode.cardValue = scanner.nextInt();
            cNode.next = deckBack.next;
            deckBack.next = cNode;
            deckBack = cNode;
        }
    }

    //Joker A functionality
    void jokerA()
    {
        SolitaireNodes pre = deckBack;
        for(SolitaireNodes place = deckBack.next; place != deckBack; place = place.next)
        {
            if(place.cardValue == 27)
            {
                SolitaireNodes a = place;
                SolitaireNodes b = place.next.next;
                pre.next = place.next;
                pre.next.next = a;
                a.next = b;
                return;
            }
            else if(place.next == deckBack && place.cardValue == 27)
            {
                SolitaireNodes a = place;
                SolitaireNodes head = deckBack.next;
                SolitaireNodes tail = deckBack;
                pre.next = place.next;
                tail.next = a;
                deckBack = a;
                a.next = head;
                return;
            }
            else if(place.next == deckBack && place.next.cardValue == 27)
            {
                SolitaireNodes a = deckBack;
                place.next = deckBack.next;
                deckBack = place.next;
                SolitaireNodes b = deckBack.next;
                deckBack.next = a;
                a.next = b;
                return;
            }
            pre = pre.next;
        }
    }

    //Joker B functionality.
    void jokerB()
    {
        SolitaireNodes pre = deckBack;
        for(SolitaireNodes place = deckBack.next; place != deckBack; place = place.next)
        {
            if(place.next == deckBack && place.cardValue == 28)
            {
                SolitaireNodes a = place;
                SolitaireNodes b = deckBack.next;
                pre.next = place.next;
                deckBack = a;
                deckBack.next = b;
                pre.next.next = deckBack.next;
                deckBack = pre.next.next;
                b = deckBack.next;
                deckBack.next = a;
                a.next = b;
                return;
            }
            if(place.next == deckBack && place.next.cardValue == 28)
            {
                SolitaireNodes a = deckBack;
                place.next = deckBack.next;
                deckBack = place.next;
                SolitaireNodes b = deckBack.next;
                a.next = b;
                SolitaireNodes c = b.next;
                b.next = a;
                a.next = c;
                return;
            }
            if ( place.next.next == deckBack && place.cardValue == 28)
            {
                SolitaireNodes tail = deckBack;
                SolitaireNodes head = deckBack.next;
                tail.next = place;
                deckBack = place;
                pre.next = place.next;
                place.next = head;
                return;
            }
            if (place.cardValue == 28)
            {
                SolitaireNodes a = place;
                SolitaireNodes b = place.next.next.next;
                pre.next = place.next;
                pre.next.next.next = a;
                a.next = b;
                return;
            }
            else
                pre = pre.next;
        }
    }

    //Triple cut functionality.
    void tripleCut()
    {
        SolitaireNodes pre = deckBack.next;
        SolitaireNodes place = deckBack.next.next;
        if(deckBack.next.cardValue == 27 || deckBack.next.cardValue == 28)
        {
            for(place = deckBack.next.next; place != deckBack; place = place.next)
            {
                if(deckBack.cardValue == 27 || deckBack.cardValue == 28)
                    return;
                else if(place.cardValue == 27 || place.cardValue == 28)
                {
                    deckBack = place;
                    deckBack.next = place.next;
                    return;
                }
                else
                    pre = pre.next;
            }
        }
        else if (deckBack.cardValue == 27 || deckBack.cardValue == 28)
        {
            pre = deckBack;
            place = deckBack.next;
            for(place = deckBack.next; place != deckBack; place = place.next)
            {
                if(deckBack.next.cardValue == 27 || deckBack.next.cardValue == 28)
                    return;
                else if(place.cardValue == 27 || place.cardValue == 28)
                {
                    deckBack = pre;
                    deckBack.next = place;
                    return;
                }
                else
                    pre = pre.next;
            }
        }
        else
        {
            pre = deckBack;
            place = deckBack.next;
            while(place != deckBack)
            {
                if(place.cardValue == 27 || place.cardValue == 28)
                {
                    SolitaireNodes nextplace = place.next;
                    while(nextplace != deckBack.next)
                    {
                        if(nextplace.cardValue == 27 || nextplace.cardValue == 28)
                        {
                            SolitaireNodes joker = nextplace;
                            SolitaireNodes folplace = nextplace.next;
                            SolitaireNodes head = deckBack.next;
                            deckBack.next = place;
                            joker.next = head;
                            deckBack = pre;
                            deckBack.next = folplace;
                            return;
                        }
                        else
                            nextplace = nextplace.next;
                    }
                }
                else
                {
                    pre = pre.next;
                    place = place.next;
                }
            }
        }
    }

    //Count cut functionality.
    void countCut()
    {
        int cnt = 0;
        int numFinal;
        if(deckBack.cardValue == 28)
            numFinal = 27;
        else
            numFinal = deckBack.cardValue;
        SolitaireNodes pre = deckBack;
        SolitaireNodes head = deckBack.next;
        SolitaireNodes place = deckBack.next;
        while (cnt <= numFinal)
        {
            if(cnt == (numFinal - 1))
            {
                if(place.next == deckBack)
                    return;
                SolitaireNodes placeFin = place;
                SolitaireNodes mid = place.next;
                for(SolitaireNodes nextplace = place.next; nextplace != deckBack; nextplace = nextplace.next)
                {
                    if(nextplace.next == deckBack)
                    {
                        SolitaireNodes cent = nextplace;
                        cent.next = null;
                        nextplace.next = head;
                        placeFin.next = deckBack;
                        deckBack.next = cent;
                        return;
                    }
                }
            }
            pre = pre.next;
            place = place.next;
            cnt++;
        }
    }

    //Obtain convert-key.
    //Make call to previous steps.
    int obtKey()
    {
        int convKey = -1;
        int cnt = 1;
        int firstVal = deckBack.next.cardValue;
        if(firstVal == 28)
            firstVal = 27;
        SolitaireNodes place = deckBack.next;
        while(place != deckBack)
        {
            if (cnt == firstVal)
            {
                if(place.next.cardValue == 27 || place.next.cardValue == 28)
                {
                    jokerA();
                    jokerB();
                    tripleCut();
                    countCut();
                    place = deckBack;
                    cnt = 0;
                    firstVal = deckBack.next.cardValue;
                    if(firstVal == 28)
                        firstVal = 27;
                }
                else
                {
                    convKey = place.next.cardValue;
                    return convKey;
                }
            }
            place = place.next;
            cnt++;
        }
        return convKey;
    }

    //Encrypt input
    public String encrypt(String input)
    {
        String message = "";
        for(int i = 0 ; i < input.length(); i++)
        {
            if(!Character.isLetter(input.charAt(i)))
                continue; //Don't worry about previous methods.
            else
            {
                //Engage previous methods.
                jokerA();
                jokerB();
                tripleCut();
                countCut();
                char letter = Character.toUpperCase(input.charAt(i));
                int c = letter - 'A'+1;
                int key = obtKey();
                int code = c + key;
                if (code > 26)
                    code -= 26;
                letter = (char)(code-1+'A');
                message += letter;
            }
        }
        return message;
    }

    //Decrypt input.
    public String decrypt(String message)
    {
        String input = "";
        for(int i = 0 ; i < message.length(); i++)
        {
            if(!Character.isLetter(message.charAt(i)))
                continue; //Don't worry about the previous methods.
            else
            {
                //Engage previous methods.
                jokerA();
                jokerB();
                tripleCut();
                countCut();
                char letter = Character.toUpperCase(message.charAt(i));
                int c = letter - 'A'+1;
                int convKey = obtKey();
                int code = c - convKey;
                if (code <= 0)
                    code += 26;
                letter = (char)(code-1+'A');
                input += letter;
            }
        }
        return input;
    }
}

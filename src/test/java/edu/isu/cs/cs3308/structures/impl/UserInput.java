package test.java.edu.isu.cs.cs3308.structures.impl;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInput
{
    public static void main(String[] args)
            throws IOException //Exception handling
    {

        SolitaireEncDec solitaire = new SolitaireEncDec();

        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Input deck file: ");

        Scanner scan = new Scanner(new File(bufferReader.readLine()));
        solitaire.makeDeck(scan);
        System.out.print("Would you like to \"encrypt\" or \"decrypt\"?");
        String answer = bufferReader.readLine();

        String input = answer;
        System.out.print("Enter your message: ");
        String message = bufferReader.readLine();
        if (input == "encrypt")
        {
            System.out.println("The encrypted message: " + solitaire.encrypt(message));
        }
        else
        {
            System.out.println("The decrypted message: " + solitaire.decrypt(message));
        }
    }
}

package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //változók deklarálása
        Scanner sc = new Scanner(System.in);
        String ans;
        GameField gameField;

        //Mező generálása vagy importálása
        System.out.println("Új játékot szeretne(1), vagy be szeretne tölteni egy már meglévőt?(2)");
        ans = sc.nextLine();
        while(ans != "1" || ans != "2")
        {
            System.out.println("Kérem a két lehetőség közül válasszon");
            ans = sc.nextLine();
        }
        switch (ans) {
            case "1":
                gameField = new GameField();
                break;
            case "2":
                System.out.println("Kérem adja meg az importálni kívánt file nevét(csak a nevét)");
                ans = sc.nextLine();
                gameField = new GameField(ans);
                break;
            default:
                gameField = new GameField();
        }

        //játék futtatása
        int nextStep;
        do
        {
            System.out.println("Hova szeretne lépni?(számként adja meg kérem)");
            nextStep = Integer.parseInt((sc.nextLine()));
        } while(gameField.Step(nextStep) != 0);
    }
}
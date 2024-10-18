package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class GameField {
    //változók deklarálása
    int board[][] = new int[7][6];

    //tábla generálása
    public GameField() {
        for(int i = 0; i < 7; i++)
        {
            for(int j = 0; j < 6; j++)
            {
                board[i][j] = 0;
            }
        }
        Visual();
    }

    //Tábla importálása
    public GameField(String nev){
        nev.concat(".txt");
        try{
            File file = new File(nev);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            int counter = 6;
            while ((st = br.readLine()) != null)
            {
                for(int i = 0; i < 7; i ++)
                {
                    board[i][counter] = st.charAt(i);
                }
                counter--;
            }
        } catch (Exception e)
        {

        }
        Visual();
    }

    //Játékos lépései
    public int Step(int column)
    {
        int i = 0;
        while(i < 6 && board[column][i] != 0)
        {
            i++;
        }
        if(i == 6)
        {
            System.out.println("Az oszlop megtelt, kérem válasszon egy másikat!");
        } else{
            board[column][i] = 1;
            if(IsEnd(column, i, true))
            {
                return 0;
            }
            else{
                return StepAI();
            }
        }
        return 3;
    }

    //AI lépései
    private int StepAI()
    {
        int genNumber = (int)(Math.random() * 7) + 1;
        int i = 0;
        while(i < 6 && board[genNumber][i] != 0)
        {
            i++;
        }
        if(i == 6)
        {
            StepAI();
        } else{
            board[genNumber][i] = 2;
        }
        Visual();
        if(IsEnd(genNumber, i, false))
        {
            return 0;
        } else{
            return 1;
        }
    }

    //Vége-e a játéknak
    public boolean IsEnd(int column, int row, boolean isPlayer)
    {
        //változók létrehozása
        int counter = 0;
        int eCounter = 0;
        int preferedChacacter = 2;
        if(isPlayer)
        {
            preferedChacacter = 1;
        }
        //oszlopok leellenőrzése
        while(board[column][row-counter] == preferedChacacter)
        {
            eCounter++;
            counter--;
        }
        counter=0;
        while(board[column][row+counter] == preferedChacacter)
        {
            eCounter++;
            counter++;
        }
        if(eCounter >= 4)
        {
            return true;
        }
        counter= 0;
        eCounter = 0;
        //sorok leellenőrzése
        while(board[column-counter][row] == preferedChacacter)
        {
            eCounter++;
            counter--;
        }
        counter=0;
        while(board[column+counter][row] == preferedChacacter)
        {
            eCounter++;
            counter++;
        }
        if(eCounter >= 4)
        {
            return true;
        }
        counter= 0;
        eCounter = 0;
        //bal fent, jobb le átló
        while(board[column-counter][row+counter] == preferedChacacter)
        {
            eCounter++;
            counter--;
        }
        counter=0;
        while(board[column+counter][row-counter] == preferedChacacter)
        {
            eCounter++;
            counter++;
        }
        if(eCounter >= 4)
        {
            return true;
        }
        counter= 0;
        eCounter = 0;
        //bal lent jobb fel
        while(board[column-counter][row-counter] == preferedChacacter)
        {
            eCounter++;
            counter--;
        }
        counter=0;
        while(board[column+counter][row+counter] == preferedChacacter)
        {
            eCounter++;
            counter++;
        }
        if(eCounter >= 4)
        {
            return true;
        }
        return false;
    }

    //Pálya állásának kiíratása
    private void Visual()
    {
        String abc = "ABCDEFG";
        for(int i = 6; i > 0; i--)
        {
            System.out.println(i + " ");
            for(int j = 0; j < 7; j++)
            {
                System.out.printf(board[j][i] + " ");
            }
        }
        for(int i = 0; i < 7; i++)
        {
            System.out.println(abc.charAt(i) + " ");
        }
    }
}

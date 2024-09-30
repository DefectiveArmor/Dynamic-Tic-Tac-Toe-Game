import java.util.Scanner;
import java.util.Random;
public class tictacktoe  {
  
  public static void main(String[] args){
    Scanner input = new Scanner (System.in);

    String [] boardSpace= new String[10];
    int boardSpacesTotal =9;
    int playerTurn=0;
    int menuChoice;
    int choice=0;
    boolean [] spaceTaken=new boolean[10];
    boolean gameWon;
    int sideSelector=0;
    String dominant="X";
    String nondominant="O";
    
    do{

    for(int count = 1; count<=boardSpacesTotal;count++){
      boardSpace[count]=" ";
      spaceTaken[count]=false;
    }

    do{
      System.out.println("\n---------------------------------\n");
      System.out.println("Please select a game mode: \n");
      System.out.print("1. 2v2 Mode\n2. Player against computer\n3. Computer against Computer\n4. Play against computer (Impossible)\n5. Exit \n\nChoice: ");
      menuChoice=input.nextInt();
      if(menuChoice<1||menuChoice>5){
        System.out.println("\u001B[31m\nError: Please only select from the options listed.\u001B[0m\n");
      }
    }while(menuChoice<1||menuChoice>5);
    
    if(menuChoice!=5){
      sideSelector=sideSelector(menuChoice,dominant,nondominant);
    if(sideSelector==1){
      dominant="X";nondominant="O";
    }else if(sideSelector==2){
      dominant="O";nondominant="X";
    }
    numberedBoard(boardSpacesTotal);




    for(int count = 1; count<=boardSpacesTotal;count++){
      playerTurn=playerTurnUpdater(count,sideSelector,dominant,nondominant);
      
      if (menuChoice==1){
        choice=mode1(playerTurn,count,spaceTaken,boardSpacesTotal,boardSpace,dominant,nondominant);
      }else if(menuChoice==2){
        choice=mode2(playerTurn,count,spaceTaken,boardSpacesTotal,boardSpace,dominant,nondominant);
      }else if (menuChoice==3){
        choice=mode3(playerTurn,count,spaceTaken,boardSpacesTotal,boardSpace,dominant,nondominant);
      }else if (menuChoice==4){
        choice=mode4(playerTurn, count, spaceTaken, boardSpacesTotal, boardSpace, dominant, nondominant);
      }
      spaceTaken[choice]=true;
      gameWon = winCheck(playerTurn, count, spaceTaken, boardSpacesTotal, boardSpace, menuChoice);

      if (gameWon==true&&count!=9){
        count=10;
      }
    }
    }else {
      System.out.println("\nThank you for using my program!");
    }

    
    }while(menuChoice!=5);
  }



  private static int mode1(int playerTurn,int count,boolean spaceTaken[],int boardSpacesTotal,String boardSpace[],String dominant, String nondominant){
    Scanner input = new Scanner (System.in);
    int choice;
    do{
      System.out.print("Player "+playerTurn+", please select a square: ");
      choice=input.nextInt();
      userErrorCheck(choice, spaceTaken, boardSpacesTotal, boardSpace);
    }while(choice<1||choice>9||spaceTaken[choice]==true);

    if(playerTurn==1){
      boardSpace[choice]=dominant;
    }else if(playerTurn==2){
      boardSpace[choice]=nondominant;
    }
    
    updatedBoard(boardSpacesTotal,boardSpace);

    return choice;
  }



  private static int mode2(int playerTurn,int count,boolean spaceTaken[],int boardSpacesTotal,String boardSpace[],String dominant, String nondominant){
    Scanner input = new Scanner (System.in);
    Random rand = new Random();
    int choice=0;
    
    do{
      if(playerTurn==1){
        System.out.print("Please select a square: ");
        choice=input.nextInt();
        userErrorCheck(choice, spaceTaken, boardSpacesTotal, boardSpace);
      }else if(playerTurn==2){
        choice=rand.nextInt((9)+1);
      }
    }while(choice<1||choice>9||spaceTaken[choice]==true);

    if(playerTurn==1){
      boardSpace[choice]=dominant;
    }else if(playerTurn==2){
      boardSpace[choice]=nondominant;
    }
    if(playerTurn==2){
      updatedBoard(boardSpacesTotal, boardSpace);
    }

    return choice;
  }



private static int mode3(int playerTurn,int count,boolean spaceTaken[],int boardSpacesTotal,String boardSpace[],String dominant, String nondominant){
  Random rand  = new Random();
  int choice=0;

  do{
    choice=rand.nextInt((9)+1);
  }while(choice<1||choice>9||spaceTaken[choice]==true);
  if(playerTurn==1){
    boardSpace[choice]=dominant;
  }else if(playerTurn==2){
    boardSpace[choice]=nondominant;
  }
  
  updatedBoard(boardSpacesTotal, boardSpace);



  return choice;
}



private static int mode4(int playerTurn,int count,boolean spaceTaken[],int boardSpacesTotal,String boardSpace[],String dominant, String nondominant){
  Scanner input = new Scanner (System.in);
  Random rand  = new Random();
  int choice=0;
  boolean lock=false;
  boolean completed=false;
  do{
    if (playerTurn==1){
      System.out.print("Please select a square: ");
      choice=input.nextInt();
      userErrorCheck(choice, spaceTaken, boardSpacesTotal, boardSpace);
    }else if(playerTurn==2){
      if(count==2){
        if(boardSpace[5].equals(dominant)){
          do{
            choice=rand.nextInt(9)+1;
          }while(choice!=1&&choice!=3&&choice!=7&&choice!=9);
        }else{
          choice=5;
        }
        lock=true;
      }else if(count==1){
        do{
          choice=rand.nextInt(9)+1;
        }while(choice!=1&&choice!=3&&choice!=5&&choice!=7&&choice!=9);
        lock=true;
      }
      int count1=1;
      int count2=2;

    for(int count3 = 3;count3<=9;count3+=3){
      if (boardSpace[count1]==dominant&&boardSpace[count2]==dominant&&spaceTaken[count3]==false&&lock!=true||boardSpace[count1]==nondominant&&boardSpace[count2]==nondominant&&spaceTaken[count3]==false&&lock!=true){
            choice=count3;completed=true;
            if(boardSpace[count1]==nondominant&&boardSpace[count2]==nondominant/*&&boardSpace[count3]=="O"||boardSpace[count1]=="X"&&boardSpace[count2]=="X"&&boardSpace[count3]=="X"*/){
              lock=true;
            }
            //cout<<choice<<endl;
          }else if(boardSpace[count2]==dominant&&boardSpace[count3]==dominant&&spaceTaken[count1]==false&&lock!=true||boardSpace[count2]==nondominant&&boardSpace[count3]==nondominant&&spaceTaken[count1]==false&&lock!=true){
            choice=count1;completed=true;
            if(boardSpace[count2]==nondominant&&boardSpace[count3]==nondominant/*&&boardSpace[count3]=="O"||boardSpace[count1]=="X"&&boardSpace[count2]=="X"&&boardSpace[count3]=="X"*/){
              lock=true;
            }
          }else if(boardSpace[count1]==dominant&&boardSpace[count3]==dominant&&spaceTaken[count2]==false&&lock!=true||boardSpace[count1]==nondominant&&boardSpace[count3]==nondominant&&spaceTaken[count2]==false&&lock!=true){
            choice=count2;completed=true;
            if(boardSpace[count1]==nondominant&&boardSpace[count3]==nondominant/*&&boardSpace[count3]=="O"||boardSpace[count1]=="X"&&boardSpace[count2]=="X"&&boardSpace[count3]=="X"*/){
              lock=true;
            }
    }

      count1+=3;
      count2+=3;
    }
    count1=1;
    count2=4;
    for(int count3=7;count3<=9;count3+=1){
      if (boardSpace[count1]==dominant&&boardSpace[count2]==dominant&&spaceTaken[count3]==false&&lock!=true||boardSpace[count1]==nondominant&&boardSpace[count2]==nondominant&&spaceTaken[count3]==false&&lock!=true){
            choice=count3;completed=true;
            if(boardSpace[count1]==nondominant&&boardSpace[count2]==nondominant/*&&boardSpace[count3]=="O"||boardSpace[count1]=="X"&&boardSpace[count2]=="X"&&boardSpace[count3]=="X"*/){
              lock=true;
            }
            //cout<<choice<<endl;
          }else if(boardSpace[count2]==dominant&&boardSpace[count3]==dominant&&spaceTaken[count1]==false&&lock!=true||boardSpace[count2]==nondominant&&boardSpace[count3]==nondominant&&spaceTaken[count1]==false&&lock!=true){
            choice=count1;completed=true;
            if(boardSpace[count2]==nondominant&&boardSpace[count3]==nondominant/*&&boardSpace[count3]=="O"||boardSpace[count1]=="X"&&boardSpace[count2]=="X"&&boardSpace[count3]=="X"*/){
              lock=true;
            }
          }else if(boardSpace[count1]==dominant&&boardSpace[count3]==dominant&&spaceTaken[count2]==false&&lock!=true||boardSpace[count1]==nondominant&&boardSpace[count3]==nondominant&&spaceTaken[count2]==false&&lock!=true){
            choice=count2;completed=true;
            if(boardSpace[count1]==nondominant&&boardSpace[count3]==nondominant/*&&boardSpace[count3]=="O"||boardSpace[count1]=="X"&&boardSpace[count2]=="X"&&boardSpace[count3]=="X"*/){
              lock=true;
            }
    }

    count1+=1;
    count2+=1;
    }
    count1=3;
    count2=5;
    for(int count3=7;count3<=9;count3+=2){
      if (boardSpace[count1]==dominant&&boardSpace[count2]==dominant&&spaceTaken[count3]==false&&lock!=true||boardSpace[count1]==nondominant&&boardSpace[count2]==nondominant&&spaceTaken[count3]==false&&lock!=true){
            choice=count3;completed=true;
            if(boardSpace[count1]==nondominant&&boardSpace[count2]==nondominant/*&&boardSpace[count3]=="O"||boardSpace[count1]=="X"&&boardSpace[count2]=="X"&&boardSpace[count3]=="X"*/){
              lock=true;
            }
          }else if(boardSpace[count2]==dominant&&boardSpace[count3]==dominant&&spaceTaken[count1]==false&&lock!=true||boardSpace[count2]==nondominant&&boardSpace[count3]==nondominant&&spaceTaken[count1]==false&&lock!=true){
            choice=count1;completed=true;
            if(boardSpace[count1]==nondominant&&boardSpace[count2]==nondominant/*&&boardSpace[count3]=="O"||boardSpace[count1]=="X"&&boardSpace[count2]=="X"&&boardSpace[count3]=="X"*/){
              lock=true;
            }
          }else if(boardSpace[count1]==dominant&&boardSpace[count3]==dominant&&spaceTaken[count2]==false&&lock!=true||boardSpace[count1]==nondominant&&boardSpace[count3]==nondominant&&spaceTaken[count2]==false&&lock!=true){
            choice=count2;completed=true;
            if(boardSpace[count1]==nondominant&&boardSpace[count3]==nondominant/*&&boardSpace[count3]=="O"||boardSpace[count1]=="X"&&boardSpace[count2]=="X"&&boardSpace[count3]=="X"*/){
              lock=true;
            }
    }

    count1-=2;
    }
    if(completed==false&&lock!=true){
      choice = rand.nextInt(9)+1;
      }
    }
  }while(choice<1||choice>9||spaceTaken[choice]==true);


  if(playerTurn==1){
    boardSpace[choice]=dominant;
  }else if(playerTurn==2){
    boardSpace[choice]=nondominant;
  }

  updatedBoard(boardSpacesTotal, boardSpace);
  return choice;
}



private static void userErrorCheck(int choice,boolean spaceTaken[], int boardSpacesTotal, String boardSpace[]){
  if(choice<1||choice>9){
    System.out.println("\n\u001B[31mError: Please only input numbers within the range of the board.\u001B[0m");
    updatedBoard(boardSpacesTotal,boardSpace);
  }else if(spaceTaken[choice]==true){
    System.out.println("\n\u001B[31mError: This square has already been taken. Please try again.\u001B[0m");
    updatedBoard(boardSpacesTotal,boardSpace);
  }
}



  private static int playerTurnUpdater(int count,int sideSelector,String dominant,String nondominant){
    int choice;
    if (count==1||count==3||count==5||count==7||count==9){
      choice=1;
    }else{
      choice=2;
    }

    if(sideSelector==2){
      if (count==1||count==3||count==5||count==7||count==9){
        choice=2;
      }else{
        choice=1;
      }
    }

    return choice;
  }



  private static void numberedBoard(int boardSpacesTotal){
    System.out.println("\n-------------");
    for(int count=1;count<=boardSpacesTotal;count++){
      System.out.print("| "+count+" ");
      if (count==3||count==6||count==9){
        System.out.println("|");
        System.out.println("-------------");
      }
    }
  }



  private static void updatedBoard(int boardSpacesTotal, String boardSpace[]){
    System.out.println("\n-------------");
    for(int count=1;count<=boardSpacesTotal;count++){
      System.out.print("| "+boardSpace[count]+" ");
      if (count==3||count==6||count==9){
        System.out.println("|");
        System.out.println("-------------");
      }
    }
    System.out.println("\n");
  }



private static int sideSelector(int menuChoice,String dominant,String nondominant){
  Scanner input = new Scanner (System.in);
  int choice=0;
  if (menuChoice==2||menuChoice==4){
    do{
      System.out.print("\nWhich player would you like to be?\n1. X\n2. O\nEnter Choice: ");
      choice=input.nextInt();
  
      if(choice<1||choice>2){
        System.out.println("\n\u001B[31mError: Please only select options 1 or 2.\u001B[0m\n");
      }
    }while(choice<1||choice>2);
  }

  return choice;
}



  private static boolean winCheck (int playerTurn,int count,boolean spaceTaken[],int boardSpacesTotal,String boardSpace[],int menuChoice){
    boolean gameWon=false;
    if(boardSpace[1]=="X"&&boardSpace[2]=="X"&&boardSpace[3]=="X"||boardSpace[4]=="X"&&boardSpace[5]=="X"&&boardSpace[6]=="X"||boardSpace[7]=="X"&&boardSpace[8]=="X"&&boardSpace[9]=="X"||boardSpace[1]=="X"&&boardSpace[4]=="X"&&boardSpace[7]=="X"||boardSpace[2]=="X"&&boardSpace[5]=="X"&&boardSpace[8]=="X"||boardSpace[3]=="X"&&boardSpace[6]=="X"&&boardSpace[9]=="X"||boardSpace[1]=="X"&&boardSpace[5]=="X"&&boardSpace[9]=="X"||boardSpace[3]=="X"&&boardSpace[5]=="X"&&boardSpace[7]=="X"||boardSpace[1]=="O"&&boardSpace[2]=="O"&&boardSpace[3]=="O"||boardSpace[4]=="O"&&boardSpace[5]=="O"&&boardSpace[6]=="O"||boardSpace[7]=="O"&&boardSpace[8]=="O"&&boardSpace[9]=="O"||boardSpace[1]=="O"&&boardSpace[4]=="O"&&boardSpace[7]=="O"||boardSpace[2]=="O"&&boardSpace[5]=="O"&&boardSpace[8]=="O"||boardSpace[3]=="O"&&boardSpace[6]=="O"&&boardSpace[9]=="O"||boardSpace[1]=="O"&&boardSpace[5]=="O"&&boardSpace[9]=="O"||boardSpace[3]=="O"&&boardSpace[5]=="O"&&boardSpace[7]=="O"){
      gameWon =true;
      if(playerTurn==1&&menuChoice==1||menuChoice==3){
        System.out.println("\nX has won the game. Congratulations!");
      }else if(playerTurn==2&&menuChoice==1||menuChoice==3){
        System.out.println("\nO has won the game. Congratulations!");
      }

      if(playerTurn==1&&menuChoice==2||playerTurn==1&&menuChoice==4){
        updatedBoard(boardSpacesTotal, boardSpace);
        System.out.println("\nYou have won the game. Congratulations!");
      }else if(playerTurn==2&&menuChoice==2||playerTurn==2&&menuChoice==4){
        updatedBoard(boardSpacesTotal, boardSpace);
        System.out.println("\nThe computer has won the game.");
      }

    }else if(count==9&&gameWon==false){
      System.out.println("\nThe game has ended in a draw.");
    }
    
    return gameWon;
  }
}
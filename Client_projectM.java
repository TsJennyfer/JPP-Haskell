import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
import java.net.ConnectException;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.io.File;
import java.io.IOException;


public class Client_projectM {
    
    public static int maxX;
    public static int maxY;
    public static String filebuff = "";
    
    public static void write(String id){
 
        String namefile = "communication"+id+".txt";

        try{
            PrintWriter writer = new PrintWriter(namefile, "UTF-8");
            writer.println("File writing started. ");
            writer.println(filebuff);
            writer.println("File writing ended. ");
            writer.close();
        } catch (IOException e) {
            // do something
        }
    }
    
   public static void printBoard(String buff)
    {
        for (int i = 0 ; i < maxX ; i++){
            for (int j = 0 ; j < maxY ; j++){
                System.out.print( buff.charAt( i * maxX + j ));
                System.out.print(" ");
            }
            System.out.println();
        }
    }
 
    public static void main(String[] args) throws Exception {
        Scanner keyboard = new Scanner(System.in);
        
        String myName = "";
        String dir = "";
        String buff = "";
        String nextGameInd = "";
        
        //buff = "Hello! Welcome to Zataczka game! We need only 8 people to start, so let's go! ";
        //System.out.println(buff);
        //filebuff += buff +" \n";
        
        try{
            Socket clientSocket = new Socket("localhost", 8781);
            
            //buff = "Connection started";
            //System.out.println(buff);
            //filebuff +=  buff + " \n";
            
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            buff = ">" + inFromServer.readLine();
            
            System.out.println(buff);
            filebuff += buff + "\n";
            
            maxX = Integer.parseInt(inFromServer.readLine());
            maxY = Integer.parseInt(inFromServer.readLine());
            
            while(myName.isEmpty()){
                buff = "LOGIN";
                System.out.println(buff);
                filebuff += buff + "\n";
                myName = keyboard.nextLine();
            }
            
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            
            outToServer.writeBytes(myName + "\n");
            filebuff += myName + "\n";
            
            String id = inFromServer.readLine();
            
            buff = ">OK.";
            System.out.println(buff);
            filebuff += buff + "\n";
            
            while(true){
                
                    String buffFromServer = "";
                
                    buffFromServer = inFromServer.readLine();

                    String[] parts = buffFromServer.split(" ");
                
                    if(parts[0].equals("PLAYERS"))
                    {
                        buff = ">START " + id;
                        System.out.println(buff);
                        filebuff += buff + "\n";
                        
                        System.out.println(">" + buffFromServer);
                        filebuff += buffFromServer + "\n";
                        
                        buff = ">BEGIN";
                        System.out.println(buff);
                        filebuff += buff + "\n";
                        dir = keyboard.nextLine();

                        outToServer.writeBytes(dir + "\n");
                        
                        filebuff += dir + "\n";
                    
                    }
                    else if(parts[1].equals("BOARD"))
                    {
                     //  buff = ">GAME"; //+parts[1];
                    //   System.out.println(buff);
                    //   filebuff += buff + "\n";
                       
                        buff = ">" + parts[0];
                        System.out.println(buff);
                        filebuff += buff + "\n";

                        buff = ">" + parts[1];
                        System.out.println(buff);
                        filebuff += buff + "\n";
                        
                        buff = ">" + parts[2];
                        System.out.println(buff);
                        filebuff += buff + "\n";

                        buff = ">ROUND " + parts[3];
                        System.out.println(buff);
                        filebuff += buff + "\n";

                        buff = "MOVE";
                        System.out.println(buff);
                        filebuff += buff + "\n";
                        
                        dir = keyboard.nextLine();
                        outToServer.writeBytes(dir + "\n");
                        
                        filebuff += dir + "\n";
                    }
                    else if(parts[1].equals("LOST"))
                    {
                        buff = ">" + parts[0];
                        System.out.println(buff);
                        filebuff += buff + "\n";
                        
                        buff = ">" + parts[1];
                        System.out.println(buff);
                        filebuff += buff + "\n";
                        
                        buff = ">" + parts[2];
                        System.out.println(buff);
                        filebuff += buff + "\n";
                        
                        buff = ">ROUND " + parts[3];
                        System.out.println(buff);
                        filebuff += buff + "\n";
                        
                        buff = "MOVE";
                        System.out.println(buff);
                        filebuff += buff + "\n";
                        
                        dir = keyboard.nextLine();
                        outToServer.writeBytes(dir + "\n");
                        
                        filebuff += dir + "\n";
                    }
                    else if(parts[1].equals("WIN"))
                    {
                        buff = ">" + parts[0];
                        System.out.println(buff);
                        filebuff += buff + "\n";
                        
                        buff = ">" + parts[1];
                        System.out.println(buff);
                        filebuff += buff + "\n";
                        
                        buff = ">" + parts[2];
                        System.out.println(buff);
                        filebuff += buff + "\n";
                        
                        buff = ">ROUND " + parts[3];
                        System.out.println(buff);
                        filebuff += buff + "\n";
                        
                        buff = "MOVE";
                        System.out.println(buff);
                        filebuff += buff + "\n";

                        dir = keyboard.nextLine();
                        outToServer.writeBytes(dir + "\n");
                        filebuff += dir + "\n";
                    }
                
                    else if(parts[0].equals("ENDGAME"))
                    {
                        buff = ">" + buffFromServer;
                        System.out.println(buff);
                        filebuff += buff + "\n";
                        break;
                    }
                write(id);
            }
            
        } catch (ConnectException e) {
            System.out.println("Connection refused");
        }
    }
}

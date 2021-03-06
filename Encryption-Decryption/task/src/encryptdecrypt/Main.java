package encryptdecrypt;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Stage 5
        String[] inputData = parse(args);
        int key = Integer.parseInt(inputData[2]);

        if("enc".equals(inputData[0])){
            if(readFromFile){
                String path = inputData[1];
                File file = new File(path);
                try(Scanner scan = new Scanner(file)){
                    
                    String enc = encrypt(key, scan.nextLine());

                    if(writeToFile){
                        System.out.println("Have to write to file");
                        writeData(enc, inputData[3]);
                    }else{
                        System.out.println(enc);
                    }

                }catch(FileNotFoundException e){
                    System.out.println("Error! File not found! Path: "+path);
                }
            }else{
                String enc = encrypt(key, inputData[1]);
                if(writeToFile){
                    writeData(enc, inputData[3]);
                }else{
                    System.out.println(enc);
                }
            }

        }else{
            if(readFromFile){
                String path = inputData[1];
                File file = new File(path);
                try(Scanner scan = new Scanner(file)){
                    String dec = decrypt(key, scan.nextLine());
                    if(writeToFile){
                        writeData(dec, inputData[3]);
                        System.out.println("Have to write to file");
                    }else{
                        System.out.println(dec);
                    }
                }catch(FileNotFoundException e){
                    System.out.println("Error! File not found! Path: "+path);
                }
            }else{

                String dec = decrypt(key, inputData[1]);
                if(writeToFile){
                    writeData(dec, inputData[3]);
                }else{
                    System.out.println(dec);
                }
            }

        }

        //Stage 4
        /*String[] data = parse(args);
        int key = Integer.parseInt(data[1]);
        String message = data[2];

        if(data[0].equals("enc")){
            encrypt(key, message);
        }else{
            decrypt(key, message);
        }
        */

        //Stage 3
        /*
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        if(input.equals("enc")){
            encrypt(scan);
        }else{
            decrypt(scan);
        }*/

        /*//Stage 2

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        int key = scan.nextInt();
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        char z = 'z';
        int max = z;
        String result ="";
        for(int i =0; i < input.length(); i++){
            if(input.charAt(i) == ' '){
                result+= " ";
            }else{
                int value = input.charAt(i);
                int val = value+key;
                if(val > max){
                    val -= alpha.length();
                }
                result += (char) val;
            }
        }
        System.out.println(result);
        */

        /*Stage 1
        String input  = "we found a treasure!";
        String result = "";
        char z = 'z';
        int max = z;
        int a ='a';
        int min = a;
        for(int i =0; i< input.length(); i++){
            if(input.charAt(i) == ' '){
                result +=" ";
            }else if(input.charAt(i) == '!'){
                result += "!";
            }else{
                int val = input.charAt(i);
                int value = min + max- val;
                result += (char) value;
            }
        }
        System.out.println(result);

        */
    }

    //Stage 3
    /*

    public static void encrypt(Scanner s){
        String message = s.nextLine();
        int key = s.nextInt();
        String out="";

        for(int i = 0; i < message.length(); i++){
            int val = message.charAt(i);
            int value =  val+ key;
            out+= (char) value;
        }
        System.out.println(out);
    }

    public static void decrypt(Scanner s){
        String message = s.nextLine();
        int key = s.nextInt();
        String out="";

        for(int i = 0; i < message.length(); i++){
            int val = message.charAt(i);
            int value = val - key;
            out+= (char) value;
        }
        System.out.println(out);
    }
    */
    //Stage 4
    /*
    public static String [] parse(String [] args){
        String mode ="enc";
        String key ="0";
        String message ="";
        for(int i =0; i< args.length-1; i++){
            if(args[i].equals("-mode")){
                mode = args[i+1];
            }else if(args[i].equals("-key")){
                key = args[i+1];
            }else if(args[i].equals("-data")){
                message = args[i+1];
            }
        }
        return new String[]{mode, key, message};
    }

    public static void encrypt(int key, String s){
        String out="";

        for(int i = 0; i < s.length(); i++){
            int val = s.charAt(i);
            int value =  val+ key;
            out+= (char) value;
        }
        System.out.println(out);
    }

    public static void decrypt(int key, String s){
        String out="";

        for(int i = 0; i < s.length(); i++){
            int val = s.charAt(i);
            int value = val - key;
            out+= (char) value;
        }
        System.out.println(out);
    }*/

    //Stage 5

    static boolean readFromFile = false;
    static boolean writeToFile = false;

    public static String[] parse (String [] args){
        String mode = "enc";
        String message ="";
        String key ="0";
        String input = "";
        String output = "Standard Output";

        for(int i = 0; i< args.length-1; i++){
            if(args[i].equals("-mode")){
                mode = args[i+1];
            }else if(args[i].equals("-data")){
                message = args[i+1];
            }else if(args[i].equals("-key")){
                key = args[i+1];
            }else if(args[i].equals("-in")){
                input = args[i+1];
            }else if(args[i].equals("-out")){
                output = args[i+1];
                writeToFile = true;
            }
        }
        String [] toReturn= new String[4];
        if(message.equals("") && input.equals("")){
            message = "";
            toReturn =  new String[] {mode, message, key, output};
        }else if(!message.equals("") && !input.equals("")){
            readFromFile = true;
            toReturn= new String[] {mode, input, key, output};
        }else if(!input.equals("")){
            readFromFile = true;
            toReturn = new String[] {mode, input, key, output};
        }
        return toReturn;
    }

    public static String encrypt(int key, String message){
        String out="";

        for(int i = 0; i < message.length(); i++){
            int val = message.charAt(i);
            int value =  val+ key;
            out+= (char) value;
        }
        return out;
    }

    public static String decrypt(int key, String message){
        String out="";

        for(int i = 0; i < message.length(); i++){
            int val = message.charAt(i);
            int value = val - key;
            out+= (char) value;
        }
        return out;
    }

    public static void writeData(String data, String filePath){
        try{
            FileWriter writer = new FileWriter(filePath);
            System.out.println("Data ready to be written: "+data);
            writer.write(data);
            writer.close();
        }catch(IOException e){
            System.out.println("Error during file writing process");
        }
    }
}

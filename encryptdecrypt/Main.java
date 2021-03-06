package encryptdecrypt;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Stage 5
        /*
        String[] inputData = parse(args);
        int key = Integer.parseInt(inputData[2]);

        if ("enc".equals(inputData[0])) {
            if (readFromFile) {
                String path = inputData[1];
                File file = new File(path);
                try (Scanner scan = new Scanner(file)) {

                    String enc = encrypt(key, scan.nextLine());

                    if (writeToFile) {
                        System.out.println("Have to write to file");
                        writeData(enc, inputData[3]);
                    } else {
                        System.out.println(enc);
                    }

                } catch (FileNotFoundException e) {
                    System.out.println("Error! File not found! Path: " + path);
                }
            } else {
                String enc = encrypt(key, inputData[1]);
                if (writeToFile) {
                    writeData(enc, inputData[3]);
                } else {
                    System.out.println(enc);
                }
            }

        } else {
            if (readFromFile) {
                String path = inputData[1];
                File file = new File(path);
                try (Scanner scan = new Scanner(file)) {
                    String dec = decrypt(key, scan.nextLine());
                    if (writeToFile) {
                        writeData(dec, inputData[3]);
                        System.out.println("Have to write to file");
                    } else {
                        System.out.println(dec);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Error! File not found! Path: " + path);
                }
            } else {

                String dec = decrypt(key, inputData[1]);
                if (writeToFile) {
                    writeData(dec, inputData[3]);
                } else {
                    System.out.println(dec);
                }
            }

        }*/
        //Stage 6

        String[] inputData = parse(args);
        int key = Integer.parseInt(inputData[2]);

        String alg = inputData[inputData.length-1];
        /*if(alg.equals("shift")){
            SelectAlgorithm selection = new SelectAlgorithm(ShiftEncrypt(key, scan.nextLine()));
        }else if(alg.equals("unicode")){

        }*/

        if ("enc".equals(inputData[0])) {
            if (readFromFile) {
                String path = inputData[1];
                File file = new File(path);

                try (Scanner scan = new Scanner(file)) {
                    String enc="";
                    if(alg.equals("shift")){
                        SelectAlgorithm selection = new SelectAlgorithm(new ShiftEncrypt());
                        enc=selection.execute(key, scan.nextLine());
                    }else if(alg.equals("unicode")){
                        SelectAlgorithm selection = new SelectAlgorithm(new UnicodeEncrypt());
                        enc = selection.execute(key, scan.nextLine());
                    }
                    //String enc = encrypt(key, scan.nextLine());

                    if (writeToFile) {
                        System.out.println("Have to write to file");
                        writeData(enc, inputData[3]);
                    } else {
                        System.out.println(enc);
                    }

                } catch (FileNotFoundException e) {
                    System.out.println("Error! File not found! Path: " + path);
                }
            } else {
                String enc ="";
                if(alg.equals("shift")){
                    SelectAlgorithm sel = new SelectAlgorithm(new ShiftEncrypt());
                    enc = sel.execute(key, inputData[1]);
                }else{
                    SelectAlgorithm sel = new SelectAlgorithm(new UnicodeEncrypt());
                    enc = sel.execute(key, inputData[1]);
                }
                //String enc = encrypt(key, inputData[1]);
                if (writeToFile) {
                    writeData(enc, inputData[3]);
                } else {
                    System.out.println(enc);
                }
            }

        } else {
            if (readFromFile) {
                String path = inputData[1];
                File file = new File(path);
                try (Scanner scan = new Scanner(file)) {
                    String dec = "";
                    if(alg.equals("shift")){
                        SelectAlgorithm sel = new SelectAlgorithm(new ShiftDecrypt());
                        dec = sel.execute(key, scan.nextLine());
                    }else{
                        SelectAlgorithm sel = new SelectAlgorithm(new UnicodeDecrypt());
                        dec = sel.execute(key, scan.nextLine());
                    }
                    if (writeToFile) {
                        writeData(dec, inputData[3]);
                        System.out.println("Have to write to file");
                    } else {
                        System.out.println(dec);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Error! File not found! Path: " + path);
                }
            } else {

                String dec = "";
                if(alg.equals("shift")){
                    SelectAlgorithm s = new SelectAlgorithm(new ShiftDecrypt());
                    dec = s.execute(key, inputData[3]);
                }else{
                    SelectAlgorithm s = new SelectAlgorithm(new UnicodeDecrypt());
                    dec = s.execute(key, inputData[3]);
                }
                if (writeToFile) {
                    writeData(dec, inputData[3]);
                } else {
                    System.out.println(dec);
                }
            }

        }
    }

    //Stage 5


    static boolean readFromFile = false;
    static boolean writeToFile = false;
/*
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

     */

    //Stage 6
    public static String[] parse (String [] args){
        String mode = "enc";
        String message ="";
        String key ="0";
        String input = "";
        String output = "Standard Output";
        String algorithm = "shift";

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
            }else if(args[i].equals("-alg")){
                algorithm = args[i+1];
            }
        }
        String [] toReturn= new String[5];
        if(message.equals("") && input.equals("")){
            message = "";
            toReturn =  new String[] {mode, message, key, output, algorithm};
        }else if(!message.equals("") && !input.equals("")){
            readFromFile = true;
            toReturn= new String[] {mode, input, key, output, algorithm};
        }else if(!input.equals("")){
            readFromFile = true;
            toReturn = new String[] {mode, input, key, output, algorithm};
        }
        return toReturn;
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

class SelectAlgorithm{
    private Encription encriptingMethod;

    public SelectAlgorithm(Encription method){
        this.encriptingMethod = method;
    }

    public String execute(int key, String message){
        return this.encriptingMethod.executeEncription(key, message);
    }
}

interface Encription{

     String executeEncription(int key, String message);
}

class ShiftEncrypt implements Encription{

    @Override
    public String executeEncription(int key, String message) {
        String abc = "abcdefghijklmnopqrstuvwxyz";
        String upper="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        /*StringBuilder s = new StringBuilder();
        for(char c: message.toCharArray()){
            if(abc.indexOf(c)!= -1){
                s.append((abc.indexOf(c)+key)%26);
            }else if(upper.indexOf(c)!= -1){
                s.append((upper.indexOf(c)+key)%26);
            }
        }
        return s.toString();*/
        StringBuilder result = new StringBuilder();
        for (char character : message.toCharArray()) {
            if (character != ' ') {
                int originalAlphabetPosition = character - 'a';
                int newAlphabetPosition = (originalAlphabetPosition + key) % 26;
                char newCharacter = (char) ('a' + newAlphabetPosition);
                result.append(newCharacter);
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }
}

class ShiftDecrypt implements Encription{

    @Override
    public String executeEncription(int key, String message){
        /*String abc = "abcdefghijklmnopqrstuvwxyz";
        String upper="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder s = new StringBuilder();
        for(char c: message.toCharArray()){
            if(abc.indexOf(c)!= -1){
                s.append((abc.indexOf(c)-key)%26);
            }else if(upper.indexOf(c)!= -1){
                s.append((upper.indexOf(c)-key)%26);
            }
        }
        return s.toString();*/
        key = 26 -(key%26);
        StringBuilder result = new StringBuilder();
        for (char character : message.toCharArray()) {
            if (character != ' ') {
                int originalAlphabetPosition = character - 'a';
                int newAlphabetPosition = (originalAlphabetPosition + key) % 26;
                char newCharacter = (char) ('a' + newAlphabetPosition);
                result.append(newCharacter);
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }
}

class UnicodeEncrypt implements Encription{

    public String executeEncription(int key, String message){
        String out="";

        for(int i = 0; i < message.length(); i++){
            int val = message.charAt(i);
            int value =  val+ key;
            out+= (char) value;
        }
        return out;
    }
}

class UnicodeDecrypt implements Encription{

    public String executeEncription(int key, String message){
        String out="";

        for(int i = 0; i < message.length(); i++){
            int val = message.charAt(i);
            int value = val - key;
            out+= (char) value;
        }
        return out;
    }
}

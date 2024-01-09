package org.example;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
//Це я написав додаткові коментарі :)

public class Main {
    static int i;
    static String name;

    public static void main(String[] args) {
        createOrOpenFile();
        readData();
        increase();
        increase();
        doSome();
        System.out.println("_______________________");
        readData(); //Я зробив двічі щоб показувати нові значення після виконання функції increase
        save();
    }

    public static void increase() {
        i++;
        System.out.println(i);
    }

    public static void createOrOpenFile() {
        try {
            File myObj = new File("filename.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void save() {
        try {
            FileWriter myWriter = new FileWriter("filename.txt");
            myWriter.write(String.valueOf(i+=2));
            myWriter.write("\n" + name);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void readData() {
        try {
            File myObj = new File("filename.txt");
            Scanner myReader = new Scanner(myObj);
            int index = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                switch (index) {
                    case 0:
                        System.out.println("this is num: " + i);
                        i = Integer.parseInt(data);
                        break;
                    case 1:
                        name = data;
                        System.out.println("this is name: " + name);
                        break;
                    case 2:
                        i = Integer.parseInt(data);
                        System.out.println("this is test: " + i); //Просто для секретного experience :D (спеціально зробив щоб не було save)
                        break;
                    default:
                        break;
                }
                index++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void doSome() {
        BookOfNames bookOfNames = new BookOfNames();

        try {
            bookOfNames.addName(name);
        } catch (CopyNameException e) {
            System.out.println("ups... okay? i will not add this name");
        } catch (BadNameException e) {
            System.out.println("Be polite, don't use bad words, please");
            name = null;
        }
        catch (AmogusException e) {
            System.out.println("You shouldn't to be suspicious");
            name = null;
        }finally {
            System.out.println("The end of code :0");
        }
    }
    public static class BookOfNames {
        int size;

        public void addName(String path) throws CopyNameException, BadNameException, AmogusException {
            if (path.isEmpty()) {
                System.out.println("is empty");
                throw new CopyNameException();
            }
            if (path.equals("sheet") || path.equals("Sheet")) {
                System.out.println("Bad word");
                throw new BadNameException();
            }
            if (path.equals("Amogus") || path.equals("amogus") || path.equals("Abobus") || path.equals("abobus")) {
                System.out.println("STRANGE");
                throw new AmogusException();
            }
            size++;
            System.out.println(path);
        }
    }

    public static class CopyNameException extends Exception {
    }

    public static class BadNameException extends Exception {
    }
    public static class AmogusException extends Exception { //Новий дивний тип помилок :_)
    }
}
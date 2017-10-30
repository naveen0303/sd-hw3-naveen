import java.util.*;
import java.lang.*;
import java.io.*;
import org.apache.commons.cli.*;

// COSC 6353 - Software Design               Programming Assignment 3
// Naveen Milind Chalawadi
// PSID:1553379
public class Main {
    public static void main(String[] args) {
//constructor for Main class
        Main main1 = new Main();
        if (args.length == 0) {System.out.print("Error!!! Missing arguments!!!: "); return;}
//constructor for org.apache.commons cli Options
        Options option1 = new Options();

//define options for inputs in commandline
        Option Optiontype = Option.builder("t").longOpt("type").hasArg().numberOfArgs(1).required(true).build();
        Option Optionkey = Option.builder("k").longOpt("key").hasArg().numberOfArgs(1).required(true).build();
        Option Optionlist = Option.builder("l").longOpt("list").hasArgs().valueSeparator(' ').required(true).build();
        option1.addOption(Optiontype);
        option1.addOption(Optionkey);
        option1.addOption(Optionlist);

        CommandLineParser cmdparser = new DefaultParser();
        CommandLine cmd;
        String type = null;
        String key = null;
        String[] list = null;

//Check if the values for options are null
        try {
            cmd = cmdparser.parse(option1, args);
            //get the arguments
            type = cmd.getOptionValue("type");
            key = cmd.getOptionValue("key");
            list = cmd.getOptionValues("list");

            if (type == null)
                throw new ParseException("Enter a valid type!!");
            if (key == null)
                throw new ParseException("Enter a valid key!!");
            if (list == null)
                throw new ParseException("The list cannot be empty!!");
        } catch (ParseException error) {
            System.out.println("Error!!! Missing arguments!!!: ");
            error.printStackTrace();
            System.exit(0);
        }
        int Result;

        //check the type and get the elements from command line
        if (type.equals("i")) {
            Integer[] aList = new Integer[list.length];
            for (int i = 0; i < list.length; i++) {
                aList[i] = Integer.parseInt(list[i]);
            }
            Integer aKey = Integer.parseInt(key);
            Result = main1.binSearch(aList, aKey);
        }
        else if (type.equals("s")) {
            String sKey = key;
            Result = main1.binSearch(list, sKey);
        }
        else
        {
            System.out.println("Error!! Invalid type!!");
            Result = -1;
            System.exit(1);
        }

        if (Result == -1) {
            System.out.println("The key was not found!");
        }
        else {
            System.out.println("They key was found at index:"+Result);
        }

    }

    //Function to perform binary search

    protected int binSearch(Comparable[] aList, Comparable key) {
        int low = 0, high = aList.length - 1, mid;
        int i;
        while (low <= high) {
            mid = (low + high) / 2;
            int i1 = aList[mid].compareTo(key);
            if (i1 == 0) {
                //if (aList[mid] == key) {
                i = mid;
                //Check for duplicates if any (concerned only about the duplicates that have the lesser index value)
                while (aList[i].compareTo(key) == 0 && i >= 0) {
                    i--;
                    if (i == -1) {
                        return i + 1;
                    }
                }
                return i + 1;
                //} else if (aList[mid] < key) low = mid + 1;
            } else if (i1 < 0) low = mid + 1;
            else {
                high = mid - 1;
            }
        }
        return -1;
    }

}


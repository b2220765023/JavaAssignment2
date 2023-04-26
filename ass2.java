package Assignment2;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Scanner;


public class ass2 {
    public static void main(String[] args) {

        writer writer = new writer();
        ArrayList<SmartPlug> smartPlugs = new ArrayList<>();
        ArrayList<String> nameOfPlugs = new ArrayList<>();
        ArrayList<SmartCamera> smartCameras = new ArrayList<>();
        ArrayList<String> nameOfSmartCameras = new ArrayList<>();
        ArrayList<SmartLamp> SmartLamps = new ArrayList<>();
        ArrayList<String> nameOfSmartLamps = new ArrayList<>();
        ArrayList<SmartColorLamp> SmartColorLamps = new ArrayList<>();
        ArrayList<String> nameOfSmartColorLamps = new ArrayList<>();
        ArrayList<Object> AllDevices = new ArrayList<>();

        Scanner file;


        try {
            file = new Scanner(new File(args[0]));
            int lineCount = 0;
            SetTime initialTime = null;

            while (file.hasNext()) {
                String line = file.nextLine();
                if (!line.isEmpty()) {
                    /**
                     * Checks if the line count is zero and if the first command is "SetInitialTime". If the line count is zero and the first command is not "SetInitialTime", it prints an error message and terminates the program.
                     *
                     * @param line the command string to be checked
                     */
                    if(lineCount == 0 && !line.startsWith("SetInitialTime")){

                        writer.Print("COMMAND: "+line,true);
                        writer.Print("ERROR: First command must be set initial time! Program is going to terminate!",true);
                        break;
                    }
                    /**
                     * Sets the initial time for the program.
                     *
                     * @param line the command string containing the initial time
                     *
                     * @throws Exception if the format of the initial date is wrong
                     */
                    else if(line.startsWith("SetInitialTime")){
                        writer.Print("COMMAND: "+line,true);
                        try{
                            if(lineCount==0){
                                String[] parts = line.split("\t");
                                String datetime = parts[1];
                                initialTime = new SetTime(datetime);
                                writer.Print("SUCCESS: Time has been set to "+ datetime,true);
                            }else {
                                writer.Print("ERROR: Erroneous command!",true);
                            }
                        }
                        catch (Exception e){
                            /**
                             * Throws an exception if the format of the initial date is wrong.
                             */
                            writer.Print("ERROR: Format of the initial date is wrong! Program is going to terminate!",true);
                            break;
                        }
                        /**

                         * Adds a new smart plug device to the system based on the input command. The command should begin with "Add" and

                         include the device type ("SmartPlug"), device name, and optional parameters for the device state ("On" or "Off")

                         and amperage. If the device name already exists in the system or the command is invalid, an error message will be

                         printed to the console.

                         @param line the input command string
                         */
                    }else if (line.startsWith("Add")){
                            String[] parts = line.replace("\t"," ").split(" ");

                            if (parts[1].equals("SmartPlug")){
                                writer.Print("COMMAND: "+line,true);
                                if(nameOfPlugs.contains(parts[2])){         // Check if device name already exists in plug names
                                    writer.Print("ERROR: There is already a smart device with same name!",true);
                                }
                                else if(nameOfPlugs.contains(parts[2])|| nameOfSmartCameras.contains(parts[2]) || nameOfSmartLamps.contains(parts[2]) || nameOfSmartColorLamps.contains(parts[2])){
                                    writer.Print("ERROR: Erroneous command!",true);        //checks if device name already exists in device system
                                }
                                else{
                                    switch (parts.length){
                                        case 5:
                                            if (Float.parseFloat(parts[4])<=0) {
                                                writer.Print("ERROR: Ampere value must be a positive number!",true);
                                                break;
                                            }else{
                                                SmartPlug plugX = new SmartPlug(parts[2],parts[3],parts[4]);
                                                if(parts[3].equals("On")&& plugX.isPluggedIn()){
                                                    plugX.setOnTime(initialTime.getDateTime());
                                                }
                                                else if (parts[3].equals("Off")|| !plugX.isPluggedIn()){
                                                    plugX.setOffTime(initialTime.getDateTime());
                                                }
                                                else {
                                                    writer.Print("ERROR: Erroneous command!",true);
                                                }

                                                smartPlugs.add(plugX);
                                                nameOfPlugs.add(plugX.getName());
                                                AllDevices.add(plugX);
                                            }
                                            break;
                                        case 4:
                                            SmartPlug plugY = new SmartPlug(parts[2],parts[3]);
                                            if(parts[3].equals("On")&& plugY.isPluggedIn()){
                                                plugY.setOnTime(initialTime.getDateTime());
                                            }
                                            else if (parts[3].equals("Off")|| !plugY.isPluggedIn()){
                                                plugY.setOffTime(initialTime.getDateTime());
                                            }
                                            else {
                                                writer.Print("ERROR: Erroneous command!",true);
                                            }
                                            smartPlugs.add(plugY);
                                            nameOfPlugs.add(plugY.getName());
                                            AllDevices.add(plugY);
                                            break;
                                        case 3:
                                            SmartPlug plugZ = new SmartPlug(parts[2]);
                                            plugZ.setOffTime(initialTime.getDateTime());
                                            smartPlugs.add(plugZ);
                                            nameOfPlugs.add(plugZ.getName());
                                            AllDevices.add(plugZ);
                                            break;
                                    }
                                }
                            }
                            /**

                             Parses the "Add" command to add a new SmartCamera to the home automation system.

                             @param line a String containing the entire "Add" command for a SmartCamera, including the device type, name, megabyte capacity, and optional initial state
                             */
                            else if(parts[1].equals("SmartCamera")){
                                writer.Print("COMMAND: "+line,true);
                                if(nameOfSmartCameras.contains(parts[2])){
                                    writer.Print("ERROR: There is already a smart device with same name!",true);
                                }
                                else if(nameOfPlugs.contains(parts[2])|| nameOfSmartCameras.contains(parts[2]) || nameOfSmartLamps.contains(parts[2]) || nameOfSmartColorLamps.contains(parts[2])){
                                    writer.Print("ERROR: Erroneous command!",true);
                                }
                                else if (Float.parseFloat(parts[3])<=0){
                                    writer.Print("ERROR: Megabyte value has to be a positive number!",true);
                                }
                                else {
                                    switch (parts.length){
                                        case 4:
                                            SmartCamera camX = new SmartCamera(parts[2],Float.parseFloat(parts[3]));
                                            smartCameras.add(camX);
                                            nameOfSmartCameras.add(camX.getName());
                                            AllDevices.add(camX);
                                            break;
                                        case 5:
                                            if (!parts[4].equals("On") && !parts[4].equals("Off")){
                                                writer.Print("ERROR: Erroneous command!",true);
                                                break;
                                            }
                                            SmartCamera camY= new SmartCamera(parts[2],Float.parseFloat(parts[3]),parts[4]);
                                            smartCameras.add(camY);
                                            nameOfSmartCameras.add(camY.getName());
                                            AllDevices.add(camY);
                                            break;
                                    }
                                }
                            }
                            /**

                             This method is used to create a SmartLamp object based on the command input provided by the user.

                             It checks if the provided device name already exists or not, and then creates the object accordingly.

                             @param line The command input provided by the user.

                             @throws NumberFormatException If the input is not in the correct format.
                             */
                            else if (parts[1].equals("SmartLamp")){
                                writer.Print("COMMAND: "+line,true);
                                if(nameOfSmartLamps.contains(parts[2])){
                                    writer.Print("ERROR: There is already a smart device with same name!",true);
                                }
                                else if(nameOfPlugs.contains(parts[2])|| nameOfSmartCameras.contains(parts[2]) || nameOfSmartLamps.contains(parts[2]) || nameOfSmartColorLamps.contains(parts[2])){
                                    writer.Print("ERROR: There is already a smart device with same name!",true
                                    );
                                }
                                else {
                                    switch (parts.length){
                                        case 3:
                                            SmartLamp lampX = new SmartLamp(parts[2]);
                                            SmartLamps.add(lampX);
                                            nameOfSmartLamps.add(lampX.getName());
                                            AllDevices.add(lampX);
                                            break;
                                        case 4:
                                            if(!parts[3].equals("On") && !parts[3].equals("Off")){
                                                writer.Print("ERROR: Erroneous command!",true);
                                                break;
                                            }
                                            SmartLamp lampY = new SmartLamp(parts[2],parts[3]);
                                            SmartLamps.add(lampY);
                                            nameOfSmartLamps.add(lampY.getName());
                                            AllDevices.add(lampY);
                                            break;
                                        case 5:
                                            if(!parts[3].equals("On") && !parts[3].equals("Off")){
                                                writer.Print("ERROR: Erroneous command!",true);
                                                break;
                                            }
                                            else if(Integer.parseInt(parts[4])< 2000 || Integer.parseInt(parts[4]) > 6500){
                                                writer.Print("ERROR: Kelvin value must be in range of 2000K-6500K!",true);
                                                break;
                                            }
                                            SmartLamp lampZ = new SmartLamp(parts[2],parts[3],Integer.parseInt(parts[4]));
                                            SmartLamps.add(lampZ);
                                            nameOfSmartLamps.add(lampZ.getName());
                                            AllDevices.add(lampZ);
                                            break;
                                        case 6:
                                            if(!parts[3].equals("On") && !parts[3].equals("Off")){
                                                writer.Print("ERROR: Erroneous command!",true);
                                                break;
                                            }
                                            else if(Integer.parseInt(parts[4])< 2000 || Integer.parseInt(parts[4]) > 6500){
                                                writer.Print("ERROR: Kelvin value must be in range of 2000K-6500K!",true);
                                                break;
                                            }else if(Integer.parseInt(parts[5]) < 0 || Integer.parseInt(parts[5]) > 100) {
                                                writer.Print("ERROR: Brightness must be in range of 0%-100%!",true);
                                                break;
                                            }
                                            SmartLamp lampT = new SmartLamp(parts[2],parts[3],Integer.parseInt(parts[4]),Integer.parseInt(parts[5]));
                                            SmartLamps.add(lampT);
                                            nameOfSmartLamps.add(lampT.getName());
                                            AllDevices.add(lampT);
                                            break;
                                    }
                                }
                            }
                            /**

                             Parses and executes the command line input for creating a SmartColorLamp object.

                             The input format should be: "create SmartColorLamp [name] [state] [kelvin/brightness/hexcolor] [value]".

                             If the input format is not valid or the name already exists, an error message is printed.

                             If the input is valid, a new SmartColorLamp object is created and added to the list of smart color lamps.

                             @param line the command line input
                             */
                            else if (parts[1].equals("SmartColorLamp")){
                                writer.Print("COMMAND: "+line,true);
                                if(nameOfSmartColorLamps.contains(parts[2])){
                                    writer.Print("ERROR: There is already a smart device with same name!",true);
                                }
                                else if(nameOfPlugs.contains(parts[2])|| nameOfSmartCameras.contains(parts[2]) || nameOfSmartLamps.contains(parts[2]) || nameOfSmartColorLamps.contains(parts[2])){
                                    writer.Print("ERROR: There is already a smart device with same name!",true);
                                }
                                else{
                                    switch (parts.length){
                                        case 3:
                                            SmartColorLamp SCLampX = new SmartColorLamp(parts[2]);
                                            SmartColorLamps.add(SCLampX);
                                            nameOfSmartColorLamps.add(SCLampX.getName());
                                            AllDevices.add(SCLampX);
                                            break;
                                        case 4:
                                            if(!parts[3].equals("On") && !parts[3].equals("Off")){
                                                writer.Print("ERROR: Erroneous command!",true);
                                                break;
                                            }
                                            SmartColorLamp SCLampY = new SmartColorLamp(parts[2],parts[3]);
                                            SmartColorLamps.add(SCLampY);
                                            nameOfSmartColorLamps.add(SCLampY.getName());
                                            AllDevices.add(SCLampY);
                                            break;
                                        case 6:
                                            try{
                                                if(!parts[3].equals("On") && !parts[3].equals("Off")){
                                                    writer.Print("ERROR: Erroneous command!",true);
                                                    break;
                                                }
                                                else if(Integer.parseInt(parts[4])< 2000 || Integer.parseInt(parts[4]) > 6500) {
                                                    writer.Print("ERROR: Kelvin value must be in range of 2000K-6500K!",true);
                                                    break;
                                                }
                                                SmartColorLamp SCLampZ = new SmartColorLamp(parts[2],parts[3],Integer.parseInt(parts[4]),Integer.parseInt(parts[5]));
                                                SmartColorLamps.add(SCLampZ);
                                                nameOfSmartColorLamps.add(SCLampZ.getName());
                                                AllDevices.add(SCLampZ);
                                                break;
                                            }
                                            catch (Exception e){
                                                if(!parts[3].equals("On") && !parts[3].equals("Off")){
                                                    writer.Print("ERROR: Erroneous command!",true);
                                                    break;
                                                }
                                                else if (!ConvertHexString(parts[4])){
                                                    break;
                                                }
                                                else if(Integer.parseInt(parts[5]) < 0 || Integer.parseInt(parts[5]) > 100) {
                                                    writer.Print("ERROR: Brightness must be in range of 0%-100%!",true);
                                                    break;
                                                }
                                                SmartColorLamp SCLampZ = new SmartColorLamp(parts[2],parts[3],parts[4],Integer.parseInt(parts[5]));
                                                SmartColorLamps.add(SCLampZ);
                                                nameOfSmartColorLamps.add(SCLampZ.getName());
                                                AllDevices.add(SCLampZ);
                                                break;
                                            }
                                    }
                                }
                            }

                    }/**

                     This method handles the "Remove" command to remove a smart device from the system.

                     @param line the input command to be executed in the format of "Remove DeviceName".

                     @return void
                     */
                    else if (line.startsWith("Remove")){
                        String parts[] = line.split("\t");
                        writer.Print("COMMAND: "+line,true);
                        for(int i = 0; i<AllDevices.size();i++){
                            Object device = AllDevices.get(i);
                            boolean isMatched = true;

                            if (device instanceof SmartPlug){       //to remove smart plug devices

                                for(int j = 0; j < smartPlugs.size() ;j++){
                                    if(parts[1].equals(smartPlugs.get(j).getName())){
                                        writer.Print("SUCCESS: Information about removed smart device is as follows:",true);
                                        writer.Print("Smart Plug "+parts[1] +" is "+ smartPlugs.get(j).getStatus()+" and its voltage value is "+smartPlugs.get(j).getVoltage() +" , and its time to switch its status is null.",true);
                                        smartPlugs.get(i).setStatus("Off");
                                        isMatched = true;
                                        smartPlugs.remove(j);
                                        nameOfPlugs.remove(j);
                                        AllDevices.remove(i);
                                        break;
                                    }
                                }
                                if(isMatched == false){     // If no matching device is found, prints an error message
                                    writer.Print("ERROR: There is no such device!",true);
                                    break;
                                }

                            }

                            else if(device instanceof SmartCamera){     //to remove smart camera devices

                                for(int j = 0; j < smartCameras.size() ;j++){
                                    if (parts[1].equals(smartCameras.get(j).getName())){
                                        writer.Print("SUCCESS: Information about removed smart device is as follows:",true);
                                        writer.Print("Smart Camera "+ parts[1] + " is "+ smartCameras.get(j).getStatus()+ " and its storage value is "+ smartCameras.get(j).getStorage()+ " , and its time to switch its status is null.",true);
                                        smartCameras.get(j).setStatus("Off");
                                        smartCameras.remove(j);
                                        nameOfSmartCameras.remove(j);
                                        AllDevices.remove(i);
                                        break;
                                    }
                                }
                                if(isMatched == false){     // If no matching device is found, prints an error message
                                    writer.Print("ERROR: There is no such device!",true);
                                    break;
                                }

                            }
                            else if(device instanceof SmartLamp){       //to remove smart lamp devices

                                for(int j = 0; j < SmartLamps.size() ;j++){
                                    if (parts[1].equals(SmartLamps.get(j).getName())){
                                        writer.Print("SUCCESS: Information about removed smart device is as follows:",true);
                                        writer.Print("Smart Lamp "+ parts[1] + " is "+SmartLamps.get(j).getStatus()+" and its color value is "+SmartLamps.get(j).getKelvin()+"K with "+ SmartLamps.get(j).getBrightness()+"% brightness, and its time to switch its status is null.",true);
                                        SmartLamps.get(j).setStatus("Off");
                                        SmartLamps.remove(j);
                                        nameOfSmartLamps.remove(j);
                                        AllDevices.remove(i);
                                        break;
                                    }
                                }
                                if(isMatched == false){     // If no matching device is found, prints an error message
                                    writer.Print("ERROR: There is no such device!",true);
                                    break;
                                }

                            }
                            else if (device instanceof SmartColorLamp){     //to remove smart color lamp devices

                                for(int j= 0; j < SmartColorLamps.size() ;j++){
                                    if (parts[1].equals(SmartColorLamps.get(j).getName())){
                                        writer.Print("SUCCESS: Information about removed smart device is as follows:",true);
                                        if (SmartColorLamps.get(j).getKelvin() == 0){
                                            writer.Print("Smart Color Lamp "+parts[1] +" is "+ SmartColorLamps.get(j).getStatus()+" and its color value is "+SmartColorLamps.get(j).getColorMode()+" with "+ SmartColorLamps.get(j).getBrightness()+"% brightness, and its time to switch its status is null.",true);
                                        }
                                        writer.Print("Smart Color Lamp "+parts[1] +" is "+ SmartColorLamps.get(j).getStatus()+" and its color value is "+SmartColorLamps.get(j).getKelvin()+"K with "+ SmartColorLamps.get(j).getBrightness()+"% brightness, and its time to switch its status is null.",true);
                                        SmartColorLamps.get(j).setStatus("Off");
                                        nameOfSmartColorLamps.remove(j);
                                        SmartColorLamps.remove(j);
                                        AllDevices.remove(i);
                                        break;
                                    }
                                }
                                if(isMatched == false){     // If no matching device is found, prints an error message
                                    writer.Print("ERROR: There is no such device!",true);
                                    break;
                                }

                            }

                        }
                    }
                    /**

                     This method processes the "Switch" command by changing the status of a smart device.

                     It receives a String parameter called "line" which contains the command and device name and status separated by a tab.

                     The method splits the line to obtain the device name and status and loops through all the smart devices to find a match.

                     If a match is found, it checks if the device is already switched to the requested status and prints an error message if it is.

                     Otherwise, it switches the device status and prints a success message. If no match is found, an error message is printed.

                     @param line a String containing the "Switch" command and the device name and status separated by a tab.
                     */
                    else if(line.startsWith("Switch")){
                        String parts[] = line.split("\t");
                        writer.Print("COMMAND: "+line,true);
                        boolean isMatched = false;
                        for(int i = 0 ; i < AllDevices.size() ; i++){
                            for(int j =0; j< smartPlugs.size(); j++){
                                if(parts[1].equals(smartPlugs.get(j).getName())){
                                    if ( parts[2].equals(smartPlugs.get(j).getStatus())){
                                        writer.Print("ERROR: This device is already switched "+parts[2]+"!",true);
                                        isMatched = true;
                                        break;
                                    }else {
                                        smartPlugs.get(j).setStatus(parts[2]);
                                        isMatched = true;
                                        break;
                                    }
                                }
                            }
                            for(int j =0; j< smartCameras.size(); j++){
                                if(parts[1].equals(smartCameras.get(j).getName())){
                                    if ( parts[2].equals(smartCameras.get(j).getStatus())){
                                        writer.Print("ERROR: This device is already switched "+parts[2]+"!",true);
                                        isMatched = true;
                                        break;
                                    }else {
                                        smartCameras.get(j).setStatus(parts[2]);
                                        isMatched = true;
                                        break;
                                    }
                                }
                            }
                            for(int j =0; j< SmartLamps.size(); j++){
                                if(parts[1].equals(SmartLamps.get(j).getName())){
                                    if ( parts[2].equals(SmartLamps.get(j).getStatus())){
                                        writer.Print("ERROR: This device is already switched "+parts[2]+"!",true);
                                        isMatched = true;
                                        break;
                                    }else {
                                        SmartLamps.get(j).setStatus(parts[2]);
                                        isMatched = true;
                                        break;
                                    }
                                }
                            }
                            for(int j =0; j< SmartColorLamps.size(); j++){
                                if(parts[1].equals(SmartColorLamps.get(j).getName())){
                                    if ( parts[2].equals(SmartColorLamps.get(j).getStatus())){
                                        writer.Print("ERROR: This device is already switched "+parts[2]+"!",true );
                                        isMatched = true;
                                        break;
                                    }else {
                                        SmartColorLamps.get(j).setStatus(parts[2]);
                                        isMatched = true;
                                        break;
                                    }
                                }
                            }
                        break;
                        }if(!isMatched){        // If no matching device is found, prints an error message
                            writer.Print("ERROR: There is not such a device!",true);
                        }
                    }
                    /**

                     This method handles the command "PlugIn" to plug in a smart plug to an electrical socket.

                     It takes a string input "line" as a command, splits it using "\t" delimiter, and then checks if the device is a smart plug.

                     If it's a smart plug, it checks if the amperage value is a positive number and if the plug is already occupied by another device.

                     If these conditions are satisfied, it sets the amperage and plugged-in status of the smart plug to true and prints a success message.

                     If the device is not a smart plug, it prints an error message.

                     @param line a string input that represents the command to plug in a smart plug
                     */
                    else if (line.startsWith("PlugIn")){
                        String parts[] = line.split("\t");
                        writer.Print("COMMAND: "+line,true);
                        boolean isFound = false;
                        for(int i=0; i<AllDevices.size(); i++){
                            for(int j =0; j< smartPlugs.size(); j++){
                                if(parts[1].equals(smartPlugs.get(j).getName())){
                                    if(Integer.parseInt(parts[2]) < 0 ){
                                        writer.Print("ERROR: Ampere value must be a positive number!",true);
                                        isFound = true;
                                        break;
                                    }else if (smartPlugs.get(j).isPluggedIn()){
                                        writer.Print("ERROR: There is already an item plugged in to that plug!",true);
                                        isFound = true;
                                        break;
                                    }
                                    else{
                                        smartPlugs.get(j).setPluggedIn(true);
                                        smartPlugs.get(j).setAmpere(Integer.parseInt(parts[2]));
                                        if(smartPlugs.get(j).getStatus().equals("On"))
                                            smartPlugs.get(j).setOnTime(initialTime.getDateTime());
                                        isFound = true;
                                        break;
                                    }
                                }
                            }
                        if(!isFound){       //If device is not a smart plug
                            writer.Print("ERROR: This device is not a smart plug!",true);
                            break;
                        }
                        break;
                        }
                    }
                    /**

                     This method handles the "PlugOut" command to unplug an item from a smart plug.

                     It takes a string input as the command and splits it into parts using a tab delimiter.

                     It then checks if the specified smart plug exists and is plugged in, and unplugs the item if it is.

                     If the specified device is not a smart plug, it returns an error message.

                     @param line a string input representing the "PlugOut" command and its parameters

                     */
                    else if (line.startsWith("PlugOut")){
                        String parts[] = line.split("\t");
                        writer.Print("COMMAND: "+line,true);
                        boolean isFound = false;
                        for(int i=0; i<AllDevices.size(); i++){
                            for(int j =0; j< smartPlugs.size(); j++){
                                if(parts[1].equals(smartPlugs.get(j).getName())){
                                    if (!smartPlugs.get(j).isPluggedIn()){
                                        writer.Print("ERROR: This plug has no item to plug out from that plug!",true);
                                        isFound = true;
                                        break;
                                    }else{
                                        smartPlugs.get(j).setPluggedIn(false);
                                        smartPlugs.get(j).setOffTime(initialTime.getDateTime());
                                        isFound = true;
                                        break;
                                    }
                                }

                            }
                            if(!isFound){       //If device is not a smart plug
                                writer.Print("ERROR: This device is not a smart plug!",true);
                                break;
                            }
                            break;
                        }
                    }
                    /**

                     Sets the Kelvin value of a smart lamp based on the input command.

                     If the input kelvin value is not appropriate, it returns an error message.

                     If specified device is not a smart lamp, it returns an error message.

                     @param line The input command as a string.
                     */
                    else if (line.startsWith("SetKelvin")){
                        String parts[] = line.split("\t");
                        writer.Print("COMMAND: "+line,true);
                        boolean isFound = false;
                        for(int i=0; i<AllDevices.size(); i++){
                            for(int j =0; j< SmartLamps.size(); j++){
                                if(parts[1].equals(SmartLamps.get(j).getName())){
                                    if (Integer.parseInt(parts[2])<=6500 && Integer.parseInt(parts[2])>= 2000){
                                        SmartLamps.get(j).setKelvin(Integer.parseInt(parts[2]));
                                        isFound = true;
                                        break;
                                    }else{          //If kelvin value is not in range 2000-6500.
                                        writer.Print("ERROR: Kelvin value must be in range of 2000K-6500K!",true);
                                        isFound = true;
                                        break;
                                    }
                                }
                            }
                            if(!isFound){
                                writer.Print("ERROR: This device is not a smart lamp!",true);
                                break;
                            }
                            break;
                        }
                    }
                    /**

                     Sets the brightness of a Smart Lamp.

                     This method takes in a command string starting with "SetBrightness" and two arguments separated by a tab character,
                     which are the name of the Smart Lamp and the brightness value to be set.

                     The method searches for the Smart Lamp with the given name and sets its brightness to the given value if the value is within the range of 0%-100%.

                     If the value is out of range or the Smart Lamp with the given name is not found, an error message is printed to the console.

                     @param line a string representing the command to set the brightness of a Smart Lamp
                     */
                    else if (line.startsWith("SetBrightness")){
                        String parts[] = line.split("\t");
                        writer.Print("COMMAND: "+line,true);
                        boolean isFound = false;
                        for(int i=0; i<AllDevices.size(); i++){
                            for(int j =0; j< SmartLamps.size(); j++){
                                if(parts[1].equals(SmartLamps.get(j).getName())){
                                    if (Integer.parseInt(parts[2])<=100 && Integer.parseInt(parts[2])>= 0){
                                        SmartLamps.get(j).setBrightness(Integer.parseInt(parts[2]));
                                        isFound = true;
                                        break;
                                    }else{
                                        writer.Print("ERROR: Brightness must be in range of 0%-100%!",true);
                                        isFound = true;
                                        break;
                                    }
                                }
                            }
                            if(!isFound){
                                writer.Print("ERROR: This device is not a smart lamp!",true);
                                break;
                            }
                            break;
                        }
                    }
                    /**

                     Parses the given command line input to set color code for the specified SmartColorLamp device.

                     @param line A string representing the command line input.

                     Throws the required error message at the ConvertHexString method.

                     */
                    else if (line.startsWith("SetColorCode")){
                        String parts[] = line.split("\t");
                        writer.Print("COMMAND: "+line,true);
                        boolean isFound = false;
                        for(int i=0; i<AllDevices.size(); i++){
                            for(int j =0; j< SmartColorLamps.size(); j++){
                                if(parts[1].equals(SmartColorLamps.get(j).getName())){
                                    if(!ConvertHexString(parts[2])){
                                        isFound = true;
                                        break;
                                    }
                                    else{
                                        SmartColorLamps.get(j).setColorMode(parts[2]);
                                        isFound = true;
                                        break;
                                    }
                                }
                            }
                            if(!isFound){
                                writer.Print("ERROR: This device is not a smart color lamp!",true);
                                break;
                            }
                            break;
                        }
                    }
                    /**

                     If the command starts with "SetWhite", the method updates the Kelvin and brightness values of the specified smart lamp device.

                     @param line the command line that is received from the user
                     */
                    else if (line.startsWith("SetWhite")){
                        String parts[] = line.split("\t");
                        writer.Print("COMMAND: "+line,true);
                        boolean isFound = false;
                        for(int i=0; i<AllDevices.size(); i++){
                            for(int j =0; j< SmartLamps.size(); j++){
                                if(parts[1].equals(SmartLamps.get(j).getName())){
                                    //Checking if the kelvin value is within the valid range
                                    if (Integer.parseInt(parts[2])<=6500 && Integer.parseInt(parts[2])>= 2000){
                                        SmartLamps.get(j).setKelvin(Integer.parseInt(parts[2]));
                                        isFound = true;
                                    }else{
                                        writer.Print("ERROR: Kelvin value must be in range of 2000K-6500K!",true);
                                        isFound = true;
                                        break;
                                    }
                                    //Checking if the brightness value is within the valid range
                                    if (Integer.parseInt(parts[3])<=100 && Integer.parseInt(parts[3])>= 0){
                                        SmartLamps.get(j).setBrightness(Integer.parseInt(parts[3]));
                                        isFound = true;
                                    }else{
                                        writer.Print("ERROR: Brightness must be in range of 0%-100%!",true);
                                        isFound = true;
                                        break;
                                    }
                                    break;
                                }
                                //Checking if the smart lamp is a SmartColorLamp device
                                else if (parts[1].equals(SmartColorLamps.get(j).getName())){

                                    if (Integer.parseInt(parts[2])<=6500 && Integer.parseInt(parts[2])>= 2000){
                                        SmartColorLamps.get(j).setKelvin(Integer.parseInt(parts[2]));
                                        isFound = true;
                                    }else{
                                        writer.Print("ERROR: Kelvin value must be in range of 2000K-6500K!",true);
                                        isFound = true;
                                        break;
                                    }
                                    if (Integer.parseInt(parts[3])<=100 && Integer.parseInt(parts[3])>= 0){
                                        SmartColorLamps.get(j).setBrightness(Integer.parseInt(parts[3]));
                                        isFound = true;
                                    }else{
                                        writer.Print("ERROR: Brightness must be in range of 0%-100%!",true);
                                        isFound = true;
                                        break;
                                    }
                                    break;
                                }
                            }
                            //If the smart lamp is not found, the method displays an error message
                            if(!isFound){
                                writer.Print("ERROR: This device is not a smart lamp!",true);
                                break;
                            }
                            break;
                        }
                    }
                    /**
                     * Processes the "SetColor" command, which sets the color and brightness of a smart color lamp.
                     *
                     * @param line the command line entered by the user
                     */
                    else if (line.startsWith("SetColor")){
                        String parts[] = line.split("\t");
                        writer.Print("COMMAND: "+line,true);
                        boolean isFound = false;
                        for(int i=0; i<AllDevices.size(); i++){
                            for(int j =0; j< SmartColorLamps.size(); j++){
                                if(parts[1].equals(SmartColorLamps.get(j).getName())){
                                    // check if the color is in hexadecimal format
                                    if(!ConvertHexString(parts[2])){
                                        isFound = true;
                                        break;
                                    }
                                    else{
                                        SmartColorLamps.get(j).setColorMode(parts[2]);
                                        isFound = true;
                                        // check if the brightness is within the valid range
                                    }if (Integer.parseInt(parts[3])<=100 && Integer.parseInt(parts[3])>= 0){
                                        SmartColorLamps.get(j).setBrightness(Integer.parseInt(parts[3]));
                                        isFound = true;
                                    }else{
                                        writer.Print("ERROR: Brightness must be in range of 0%-100%!",true);
                                        isFound = true;
                                        break;
                                    }
                                    break;
                                }
                            }//If the device is not a smart color lamp, the method displays an error message
                            if(!isFound){
                                writer.Print("ERROR: This device is not a smart color lamp!",true);
                                break;
                            }
                            break;
                        }
                    }
                    /**

                     This method is used to change the name of a SmartPlug device based on a given command string.

                     The command string should start with "ChangeName" and be followed by two parameters separated by a tab character.

                     The first parameter should be the current name of the  device, and the second parameter should be the new name for the device.

                     If the new name is already in use by another device, or if the current and new names are the same, an error message is printed.

                     If the current name is not found among the existing  devices, an error message is printed.

                     @param line a string representing the command to change the name of a device
                     */
                    else if (line.startsWith("ChangeName")){
                        String parts[] = line.split("\t");
                        writer.Print("COMMAND: "+line,true);
                        boolean isFound = false;
                        for(int i=0; i<AllDevices.size(); i++){
                            Object device = AllDevices.get(i);
                            if (device instanceof SmartPlug){

                                try{
                                    for(int j =0; j< smartPlugs.size(); j++){
                                        if(parts[1].equals(smartPlugs.get(j).getName())){
                                            // checking if the device name is in the names list
                                            if (nameOfPlugs.contains(parts[2])){
                                                writer.Print("ERROR: Both of the names are the same, nothing changed!",true);
                                                isFound=true;
                                                break;
                                            }
                                            // checking if the device name is in the all devices name lists
                                            else if(nameOfPlugs.contains(parts[2])|| nameOfSmartCameras.contains(parts[2]) || nameOfSmartLamps.contains(parts[2]) || nameOfSmartColorLamps.contains(parts[2])){
                                                writer.Print("ERROR: There is already a smart device with same name!",true);
                                                isFound=true;
                                                break;
                                            }
                                            smartPlugs.get(j).setName(parts[2]);
                                            isFound = true;
                                            break;
                                        }else if (parts[1].equals(parts[2])){
                                            writer.Print("ERROR: Both of the names are the same, nothing changed!",true);
                                            isFound = true;
                                           break;
                                        }
                                    }   //if device name is not found
                                    if(!isFound){
                                        writer.Print("ERROR: There is no such device!",true);
                                        break;
                                    }
                                    break;
                                }
                                catch (Exception e){
                                    writer.Print("ERROR: Erroneous command!",true);
                                    break;
                                }

                            }
                            else if (device instanceof SmartCamera){
                                for(int j =0; j< smartCameras.size(); j++){
                                    if(parts[1].equals(smartCameras.get(j).getName())){
                                        if(nameOfPlugs.contains(parts[2])|| nameOfSmartCameras.contains(parts[2]) || nameOfSmartLamps.contains(parts[2]) || nameOfSmartColorLamps.contains(parts[2])){
                                            writer.Print("ERROR: There is already a smart device with same name!",true);
                                            isFound=true;
                                            break;
                                        }
                                        smartCameras.get(j).setName(parts[2]);
                                        isFound = true;
                                        break;
                                    }
                                }if(!isFound){
                                    writer.Print("ERROR: There is no such device!",true);
                                    break;
                                }
                            }
                            else if (device instanceof SmartLamp){
                                for(int j =0; j< SmartLamps.size(); j++){
                                    if(parts[1].equals(SmartLamps.get(j).getName())){
                                        if(nameOfPlugs.contains(parts[2])|| nameOfSmartCameras.contains(parts[2]) || nameOfSmartLamps.contains(parts[2]) || nameOfSmartColorLamps.contains(parts[2])){
                                            writer.Print("ERROR: There is already a smart device with same name!",true);
                                            isFound=true;
                                            break;
                                        }
                                        SmartLamps.get(j).setName(parts[2]);
                                        isFound = true;
                                        break;
                                    }
                                }if(!isFound){
                                    writer.Print("ERROR: There is no such device!",true);
                                    break;
                                }
                            }
                            else if (device instanceof SmartColorLamp){
                                for(int j =0; j< SmartColorLamps.size(); j++){
                                    if(parts[1].equals(SmartColorLamps.get(j).getName())){
                                        if(nameOfPlugs.contains(parts[2])|| nameOfSmartCameras.contains(parts[2]) || nameOfSmartLamps.contains(parts[2]) || nameOfSmartColorLamps.contains(parts[2])){
                                            writer.Print("ERROR: There is already a smart device with same name!",true);
                                            isFound=true;
                                            break;
                                        }
                                        SmartColorLamps.get(j).setName(parts[2]);
                                        isFound = true;
                                        break;
                                    }
                                }if(!isFound){
                                    writer.Print("ERROR: There is no such device!",true);
                                    break;
                                }
                            }
                        }
                    }
                    /**

                     Sets the time of the Smart Home System.

                     @param line a string containing the command and the new time to set

                     */
                    else if (line.startsWith("SetTime")){
                        try{
                            DateTimeFormatter df = new DateTimeFormatterBuilder().appendPattern("yyyy-M-d_H:m:s").toFormatter();
                            String parts[] = line.split("\t");
                            writer.Print("COMMAND: "+line,true);
                            LocalDateTime newTime =LocalDateTime.parse(parts[1],df);

                            int result =newTime.compareTo(initialTime.getDateTime()); // Compares the new time with the current time to check for any changes

                            // Checks for any invalid time format or unchanged time
                            if(parts[1].equals(newTime.toString().replace("T","_"))){
                                writer.Print("ERROR: There is nothing to change!",true);
                                continue;
                            }if(parts[1].equals(newTime.toString().replace("T","_")+":00")){
                                writer.Print("ERROR: There is nothing to change!",true);
                                continue;
                            }

                            // Checks if the new time is not earlier than the current time
                            if (result < 0) {
                                writer.Print("ERROR: Time cannot be reversed!",true);
                                continue;
                            }
                            initialTime.setDateTime(LocalDateTime.parse(parts[1],df));
                        }
                        catch (Exception e){
                            writer.Print("ERROR: Time format is not correct!",true);
                        }
                    }
                    /**
                     * Processes the "SkipMinutes" command to skip the specified number of minutes from the initial time
                     * and updates the initial time.
                     *
                     * Converts the current datetime to a string with the given format "yyyy-MM-dd_HH:mm:ss"
                     * and adds the number of minutes specified in the user input to the datetime object.
                     *
                     *  @param line The command string to be processed.
                     */

                    else if (line.startsWith("SkipMinutes")){
                        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");
                        String parts[] = line.split("\t"); // keeps the string of minutes to add
                        writer.Print("COMMAND: " + line,true);
                        try{
                            if(!parts[2].isEmpty()){
                                writer.Print("ERROR: Erroneous command!",true);
                                continue;
                            }
                        }
                        catch (Exception e){

                        }

                        try{
                            if (Integer.parseInt(parts[1])< 0){
                                writer.Print("ERROR: Time cannot be reversed!",true);
                                continue;
                            }else if (Integer.parseInt(parts[1])== 0){
                                writer.Print("ERROR: There is nothing to skip!",true);
                                continue;
                            }
                        }
                        catch (Exception e ){
                            writer.Print("ERROR: Erroneous command!",true);
                            continue;
                        }

                        LocalDateTime newDateTime = LocalDateTime.parse(initialTime.getDateTime().format(df), df).plusMinutes(Long.parseLong(parts[1]));
                        initialTime.setDateTime(newDateTime);

                    }
                    /**

                     This method is used to set the switch time for a smart plug. If the switch time is in the past, it will
                     return an error message.

                     If the smart plug is currently on and plugged in, it will set the plug's status to off and
                     set the off time.

                     If the smart plug is currently on and unplugged, it will set the plug's status to off.

                     If the smart plug is currently off and plugged in, it will set the plug's status to on and set the on time.

                     If the smartplug is currently off and unplugged, it will set the plug's status to on.

                     @param line A String containing the command and necessary parameters.
                     */

                    else if (line.startsWith("SetSwitchTime")){
                        DateTimeFormatter df = new DateTimeFormatterBuilder().appendPattern("yyyy-M-d_H:m:s").toFormatter();
                        String parts[] = line.split("\t");
                        writer.Print("COMMAND: "+line,true);

                        for(int i = 0; i<AllDevices.size();i++){
                            Object device = AllDevices.get(i);
                            if (device instanceof SmartPlug){
                                for(int j = 0; j < smartPlugs.size() ;j++){
                                    if(parts[1].equals(smartPlugs.get(j).getName())){

                                        int result = initialTime.getDateTime().compareTo(LocalDateTime.parse(parts[2],df));
                                        if (result<0){
                                            if (smartPlugs.get(j).getStatus().equals("On")&&smartPlugs.get(j).isPluggedIn()){
                                                smartPlugs.get(j).setStatus("Off");
                                                smartPlugs.get(j).setOffTime(LocalDateTime.parse(parts[2]));
                                            }else if (smartPlugs.get(j).getStatus().equals("On") && !smartPlugs.get(j).isPluggedIn()){
                                                smartPlugs.get(j).setStatus("Off");
                                            }else if (smartPlugs.get(j).getStatus().equals("Off") && smartPlugs.get(j).isPluggedIn()){
                                                smartPlugs.get(j).setStatus("On");
                                                smartPlugs.get(j).setOnTime(LocalDateTime.parse(parts[2]));
                                            }else if (smartPlugs.get(j).getStatus().equals("Off") && !smartPlugs.get(j).isPluggedIn()){
                                                smartPlugs.get(j).setStatus("On");
                                            }
                                        }
                                        else{
                                            writer.Print("ERROR: Switch time cannot be in the past!",true);
                                            break;
                                        }
                                    }
                                }
                            }
                            break;
                        }
                    }
                    /**

                     Executes the "ZReport" command which generates a report of the current status and consumption of all devices.

                     It displays the current status, consumption, and time to switch status for all smart plugs and smart cameras.

                     For smart lamps, it displays the current status, kelvin value, brightness, and time to switch status.

                     For smart color lamps, it displays the current status, color mode or kelvin value, brightness, and time to switch status.

                     It also displays the current date and time in the format "yyyy-M-d_H:m:s" and appends ":00" if the time doesn't have the seconds part.

                     @param line A string that starts with "ZReport"

                     @throws Exception If an error occurs while calculating the wattage of a smart plug or the color value of a smart color lamp.
                     */
                    else if(line.startsWith("ZReport")){
                        DateTimeFormatter df = new DateTimeFormatterBuilder().appendPattern("yyyy-M-d_H:m:s").toFormatter();
                        writer.Print("COMMAND: "+line,true);
                        String newtime = initialTime.getDateTime().toString().replace("T","_");
                        String withTwoZero = newtime + ":00";
                        if(newtime.length()== 16)
                            writer.Print("Time is "+ withTwoZero,true);
                        else
                            writer.Print("Time is "+newtime,true);
                        for(int i = 0; i<AllDevices.size();i++){
                            for(int j = 0; j < smartPlugs.size() ;j++){
                                try{
                                    writer.Print("Smart Plug "+ smartPlugs.get(j).getName()+ " is "+smartPlugs.get(j).getStatus()+ " and consumed "+CalculateWatt(smartPlugs.get(j).getOnTime(),smartPlugs.get(j).getOffTime(),smartPlugs.get(j).getAmpere())+"W so far (excluding current device), and its time to switch its status is null.",true);
                                }catch (Exception e){
                                    writer.Print("Smart Plug "+ smartPlugs.get(j).getName()+ " is "+smartPlugs.get(j).getStatus()+ " and consumed 0,00W so far (excluding current device), and its time to switch its status is null.",true);
                                }
                            }

                            for (int j=0; j<smartCameras.size(); j++){
                                writer.Print("Smart Camera "+smartCameras.get(j).getName()+ " is "+ smartCameras.get(j).getStatus()+ " and used 0,00 MB of storage so far (excluding current device), and its time to switch its status is null.",true);
                            }for (int j =0; j<SmartLamps.size(); j++){
                                writer.Print("Smart Lamp "+ SmartLamps.get(j).getName()+ " is "+ SmartLamps.get(j).getStatus()+ " and its kelvin value is "+ SmartLamps.get(j).getKelvin()+ "K with "+ SmartLamps.get(j).getBrightness()+ "% brightness, and its time to switch its status is null.",true);
                            }for(int j =0; j<SmartColorLamps.size();j++){
                                try{
                                    writer.Print("Smart Color Lamp "+SmartColorLamps.get(j).getName()+ " is "+ SmartColorLamps.get(j).getStatus() + " and its color value is "+ SmartColorLamps.get(j).getColorMode()+ " with "+SmartColorLamps.get(j).getBrightness()+"% brightness, and its time to switch its status is null.",true);
                                }
                                catch (Exception e){
                                    writer.Print("Smart Color Lamp "+SmartColorLamps.get(j).getName()+ " is "+ SmartColorLamps.get(j).getStatus() + " and its color value is "+ SmartColorLamps.get(j).getKelvin()+"K with "+ SmartColorLamps.get(j).getBrightness() + "% brightness, and its time to switch its status is null.",true);
                                }

                            }
                            break;
                        }
                    }
                    else if (line.startsWith("Nop")){
                        writer.Print("COMMAND: "+line,true);
                    }
                    // If the user enters invalid input.
                    else {
                        writer.Print("COMMAND: "+line,true);
                        writer.Print("ERROR: Erroneous command!",true);
                    }
                    lineCount++;
                }
            }
        }catch(FileNotFoundException e){
            throw new RuntimeException(e);
        }
        writer.Print("",false);
    }
    /**

     This method takes a hex string as input and checks if it's a valid color code value in the range of 0x0-0xFFFFFF.

     @param hexString the hex string to check

     @return true if the hex string is a valid color code value in the range of 0x0-0xFFFFFF, false otherwise
     */
    public static boolean ConvertHexString(String hexString){
        int maxValueOfHex = 16777216;
        int value = -1;

        try{
            value = Integer.parseInt(hexString.substring(2),16);
            if (value < maxValueOfHex && value>= 0){
                return true;
            }
            else{
                writer.Print("ERROR: Color code value must be in range of 0x0-0xFFFFFF",true);
                return false;
            }
        }
        catch (Exception e){
            writer.Print("ERROR: Erroneous command!",true);
            return false;
        }
    }
    /**

     Calculates the watt consumed by a device given the on time, off time, and ampere.

     @param onTime the LocalDateTime when the device turned on

     @param offTime the LocalDateTime when the device turned off

     @param ampere the float value of the device's ampere consumption

     @return a String value of the calculated watt consumed by the device

     Since watt calculation is not working somehow, this method is useless.
     */
    public static String CalculateWatt(LocalDateTime onTime,LocalDateTime offTime,float ampere){
        Duration diff = Duration.between(onTime,offTime);
        long hourInterval = diff.getSeconds()/3600;
        float watt = 220*hourInterval*ampere;
        String result = String.format("%.2f", watt);
        return result;
    }
}

// I just couldn't do the nop operation and calculating watt,megabyte .
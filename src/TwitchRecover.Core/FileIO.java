/*
 * Copyright (c) 2020 Daylam Tayari <daylam@tayari.gg>
 *
 * This library is free software. You can redistribute it and/or modify it under the terms of the GNU General Public License version 3 as published by the Free Software Foundation.
 * This program is distributed in the that it will be use, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not see http://www.gnu.org/licenses/ or write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

/*
 * @author Daylam Tayari https://github.com/daylamtayari
 * @version 2.0
 * Github project home page: https://github.com/TwitchRecover
 * Twitch Recover repository: https://github.com/TwitchRecover/TwitchRecover
 */

package TwitchRecover.Core;

import TwitchRecover.Core.Enums.ContentType;
import TwitchRecover.Core.Enums.FileExtension;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class handles all IO related to files.
 */
public class FileIO {
    public static String fn="";     //String value which holds the latter part of the individual file name.

    /**
     * This method creates a file
     * and writes to it.
     * @param values    String arraylist representing the values to write to the file.
     * @param fp        String value representing the complete file path of the file to create and write to.
     */
    public static void write(ArrayList<String> values, String fp){
        File file=new File(fp);
        try{
            FileWriter fw=new FileWriter(fp);
            for(String s: values){
                fw.write("\n"+s);
            }
        }
        catch(IOException ignored){}
    }

    /**
     * This method reads the contents of a file.
     * @param fp                    Complete filepath of the file to read.
     * @return ArrayList<String>    String arraylist representing all of the contents of the file.
     */
    public static ArrayList<String> read(String fp){
        File file=new File(fp);
        ArrayList<String> contents=new ArrayList<String>();
        try{
            Scanner sc=new Scanner(file);
            while(sc.hasNextLine()){
                contents.add(sc.nextLine());
            }
            sc.close();
        }
        catch(IOException ignored){}
        return contents;
    }

    /**
     * This method adjusts a user
     * inputted file path.
     * @param fp        File path inputted by the user, to be adjusted.
     * @return String   String value representing the adjusted file path.
     */
    protected static String adjustFP(String fp){
        if(fp.indexOf('\\')!=fp.length()-1){
            fp+="\\";
        }
        return fp;
    }

    /**
     * This method checks if a file
     * currently exists at the specific
     * location.
     * @param fp        String value representing the complete file path to check for (excluding file extension).
     * @param fe        FileExtensions enum which represents the anticipated file extension of the file.
     * @return boolean  Boolean value representing whether or not a file alredy exists at that location or not.
     */
    public static boolean checkFileExistence(String fp, FileExtension fe){
        File location=new File(fp+fe);
        return location.exists();
    }

    /**
     * This method computes the file name for a
     * content to be downloaded from the given
     * ID and content type.
     * @param ct        Content type enum representing the content type of the content in question.
     * @param id        String value representing the ID (clip slug, VOD ID, etc.) of the content.
     * @return String   String value representing the compute file name (excluding file extension).
     */
    protected static String computeFN(ContentType ct, String id){
        return "TwitchRecover-"+ct.toString()+"-"+id;
    }
}
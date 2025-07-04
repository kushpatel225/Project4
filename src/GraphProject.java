// -------------------------------------------------------------------------

import java.io.IOException;

/**
 * Main for Graph project (CS3114/CS5040 Spring 2025 Project 4).
 * Usage: java GraphProject <init-hash-size> <command-file>
 *
 * @author Kush, Rushil
 * @version 1.0
 *
 */

// On my honor:
// - I have not used source code obtained from another current or
// former student, or any other unauthorized source, either
// modified or unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

public class GraphProject {
    /**
     * @param args
     *            Command line parameters
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // This is the main file for the program.
        // This will be our command processor
        if (args.length != 2) {
            System.out.println(
                "Usage: java GraphProject <initHashSize> <commandFile>");
            return;
        }
        int hashsize = Integer.parseInt(args[0]);
        String commandFile = args[1];
        Database db = new Database(hashsize);
        db.processCommands(commandFile);
    }
}

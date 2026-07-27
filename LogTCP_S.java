/** -----------------------------  */

/** Program LogTCP_S.java  */

/** -----------------------------  */

import java.net.*;
import java.util.*;
import java.io.*;

public class LogTCP_S {
    public static void main(String args[]) throws IOException {
        ServerSocket serverSocket = null;
        try {
            while(Integer.parseInt(args[0]) <= 2000)
            {
                System.out.println("Port number must be between 2000 and 65500");
                System.exit(1);
            }
            serverSocket = new ServerSocket(Integer.parseInt(args[0]));
            System.out.println("\nListening on port:" + args[0] + ", type Crtl-C to finish\n");
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + args[0]);
            System.exit(1);
        }

        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                clientSocket.getInputStream()));
        String inputLine, outputLine;
        
        outputLine = "[Server] ready ... starting log, to finish close the session";
        out.println(outputLine);

        while ((inputLine = in.readLine()) != null) {
             outputLine = inputLine;
             out.println("[Server] " + outputLine);
             System.out.println(outputLine);              
             }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}

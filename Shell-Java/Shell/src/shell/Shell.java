/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shell;

import java.io.*;
import java.util.*;

/**
 *----------------------
 * Klasse: 3AHIF
 * @author Daniel Andricic
 *----------------------
 *
 */
public class Shell {
    
    private static int exitCode = 0;
    
    public static void main(String[] args) throws InterruptedException{
        Scanner s = new Scanner(System.in);
        String[] inputs;

        do{
            System.out.print(">");
            String input = s.nextLine();
            inputs = input.trim().split("&");
            if(inputs[0].equals("exit")) {
                break;
            }
 
            try {
                for (int i = 0; i < inputs.length; i++) {
                    run(inputs[i]);
                }
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }while(exitCode == 0);
        System.exit(0);
    }

    private static void run(String cmd) throws InterruptedException, IOException{
        
        Process process = new ProcessBuilder().inheritIO().command("bash", "-c", cmd).start();
        InputStream strm = process.getInputStream();
        
        int e = strm.read();
        while(e != -1) {
            System.out.println((char) e);
            e = strm.read();
        }
        
        exitCode = process.waitFor();
        assert exitCode == 0;
    }
}

/*
 * Copyright ©2015. Created by P. Bauer <p.bauer@htl-leonding.ac.at>, Department
 * of Informatics and Media Technique and Design, HTBLA Leonding, Limesstr. 12 - 14,
 * 4060 Leonding, AUSTRIA. All Rights Reserved. Permission to use, copy, modify,
 * and distribute this software and its documentation for educational,
 * research, and not-for-profit purposes, without fee and without a signed
 * licensing agreement, is hereby granted, provided that the above copyright
 * notice, this paragraph and the following two paragraphs appear in all
 * copies, modifications, and distributions. Contact the Head of Informatics,
 * Media Technique and Design, HTBLA Leonding, Limesstr. 12 - 14, 4060 Leonding,
 * Austria, for commercial licensing opportunities.
 * 
 * IN NO EVENT SHALL HTBLA LEONDING BE  LIABLE TO ANY PARTY FOR DIRECT,
 * INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST
 * PROFITS, ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION,
 * EVEN IF HTBLA LEONDING HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * HTBLA LEONDING SPECIFICALLY DISCLAIMS ANY WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE. THE SOFTWARE AND ACCOMPANYING DOCUMENTATION, IF ANY,
 * PROVIDED HEREUNDER IS PROVIDED "AS IS". HTBLA LEONDING HAS NO OBLIGATION
 * TO PROVIDE MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR MODIFICATIONS.
 */
package at.htlleonding.fibonacci;

/**
 *
 * @author P. Bauer <p.bauer@htl-leonding.ac.at>
 */
public class Fibonacci extends Thread{
    
    private int n;
    private int result;
    
    public Fibonacci(int n) {
        this.n = n;
    }
    
    static int getNumberSingle(int n) {
        if (n < 2)
            return 1;
        else
            return getNumberSingle(n - 1) + getNumberSingle(n - 2);
    }

    static int getNumberParallel(int n) {
        try {
            Fibonacci fib = new Fibonacci(n);
            fib.start(); // Starts the Thread to calculate the result.
            fib.join();// Waits for the termination of the Thread.
            
            return fib.result;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return 1;
    }
    
    @Override
    public void run() {
        if(this.n < 2) {
            result = 1;
        }
        else {
            try {
                //Starting two Threads, which calculates the result of (n - 1) and (n - 2).
                Fibonacci fib1 = new Fibonacci(this.n - 1);
                Fibonacci fib2 = new Fibonacci(this.n - 2);
                
                fib1.start();
                fib2.start();
                
                fib1.join();
                fib2.join();
                result = fib1.result + fib2.result; // Adding the two results and returns to the main Fibonacci-Thread.
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}

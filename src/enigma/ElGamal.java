/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigma;

import java.util.*;
import java.math.BigInteger;

public class ElGamal {
        
    public BigInteger getNextPrime(String ans) {
        BigInteger one = new BigInteger("1");
        BigInteger test = new BigInteger(ans);
        while (!test.isProbablePrime(99))
            test = test.add(one);
        return test;
    }
    
// Precondition - p is prime and it's reasonably small, say, no more than
// 5,000,000. If it's larger, this method will be quite
// time-consuming.
// Postcondition - if a generator for p can be found, then it is returned
// if no generator is found after 1000 tries, null is
// returned.
    
    public BigInteger getGenerator(BigInteger p, Random r) {
        int numtries = 0;
        // Try finding a generator at random 100 times.
        while (numtries < 1000) {
            // Here's what we're trying as the generator this time.
            BigInteger rand = new BigInteger(p.bitCount()-1,r);
            BigInteger exp = BigInteger.ONE;
            BigInteger next = rand.mod(p);
            // We exponentiate our generator until we get 1 mod p.
            while (!next.equals(BigInteger.ONE)) {
                next = (next.multiply(rand)).mod(p);
                exp = exp.add(BigInteger.ONE);
            }
            // If the first time we hit 1 is the exponent p-1, then we have
            // a generator.
            if (exp.equals(p.subtract(BigInteger.ONE)))
                return rand;
        }
        // None of the 1000 values we tried was a generator.
        return null;
    }
}
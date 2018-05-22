/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDDTests;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

/**
 *
 * @author Micha
 */
public class PerformanceTest {
    
    @Test
    @DisplayName("Test the time it takes")
    public void testTime(){
        
    }
}


// Man må altid overveje hvor man skal ligge testene, functional tests tester det hele, men vi kommer ikke til at vide hvor det går galt.
// fordel at alle vores funktioner forspørger mod databasen, så ikke ændrer tabeller osv.
// ikke rens ud hver gang
// lav rigtige integrationtests
// Det her var bedst i vores situation... Fordi!
// tænk det som noget til os selv

// afklaring at man forstå de krav, så det forståes

// kan bruge time it.
// noget med performance vis test og tools.

// eventuelt se sunfire. Skal bare ind i pommen og derefter kaldes

// code coverage, testet hele koden? eller er der noget vi fravælger?
// viden om sin kode beslutter man hvad der skal testes -> whitebox
// ingen viden -> blackbox

// indexer på longitude og latitude
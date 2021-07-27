/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversor_assembly;

import java.io.PrintWriter;

/**
 *
 * @author juanh
 */
public class EscreverArqHex {

    public static void escrever_Hex(String inst, PrintWriter gravarArqB) {
        gravarArqB.printf("0x" + Integer.toHexString(Integer.parseInt(inst.substring(0, 4), 2)));
        gravarArqB.printf(Integer.toHexString(Integer.parseInt(inst.substring(4, 8), 2)));
        gravarArqB.printf(Integer.toHexString(Integer.parseInt(inst.substring(8, 12), 2)));
        gravarArqB.printf(Integer.toHexString(Integer.parseInt(inst.substring(12, 16), 2)));
        gravarArqB.printf(Integer.toHexString(Integer.parseInt(inst.substring(16, 20), 2)));
        gravarArqB.printf(Integer.toHexString(Integer.parseInt(inst.substring(20, 24), 2)));
        gravarArqB.printf(Integer.toHexString(Integer.parseInt(inst.substring(24, 28), 2)));
        gravarArqB.printf(Integer.toHexString(Integer.parseInt(inst.substring(28, 32), 2)) + "_h\n");
    }
}

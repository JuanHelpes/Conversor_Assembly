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
public class EscreverArq {

    public static void escreverTipoI(String[] primeiraPosicao, String[] linhaSeparada, Integer op, Integer rs, Integer rt, Integer imm, PrintWriter gravarArq) {
        gravarArq.printf(primeiraPosicao[0] + " " + primeiraPosicao[1] + "," + linhaSeparada[1] + ", " + linhaSeparada[2].replace(" ", "") + "\n");
        gravarArq.printf("Opcode\trs\trt\timmediate\n");
        gravarArq.printf(op + "\t" + rs + "\t" + rt + "\t" + imm + "\n");
    }

    public static void escreverTipoI_sw(String[] primeiraPosicao, String[] linhaSeparada, Integer op, Integer rs, Integer rt, Integer imm, PrintWriter gravarArq) {
        gravarArq.printf(primeiraPosicao[0] + " " + primeiraPosicao[1] + "," + linhaSeparada[1] + "\n");
        gravarArq.printf("Opcode\trs\trt\timmediate\n");
        gravarArq.printf(op + "\t" + rs + "\t" + rt + "\t" + imm + "\n");
    }

    public static void escreverTipoI_j(String[] primeiraPosicao, Integer op, Integer imm, PrintWriter gravarArq) {
        gravarArq.printf(primeiraPosicao[0] + " " + primeiraPosicao[1] + "\n");
        gravarArq.printf("Opcode\timmediate\n");
        gravarArq.printf(op + "\t" + imm + "\n");
    }

    public static void escreverTipoR_shamt(String[] primeiraPosicao, String[] linhaSeparada, Integer op, Integer rs, Integer rt, Integer rd, Integer shamt, PrintWriter gravarArq) {
        gravarArq.printf(primeiraPosicao[0] + " " + primeiraPosicao[1] + "," + linhaSeparada[1] + ", " + linhaSeparada[2] + "\n");
        gravarArq.printf("Opcode\trs\trt\trd\tshamt\tfunct" + "\n");
        gravarArq.printf(op + "\t" + rs + "\t" + rt + "\t" + rd + "\t" + shamt + "\t0\n");
    }

    public static void escreverTipoR(String[] primeiraPosicao, String[] linhaSeparada, Integer op, Integer rs, Integer rt, Integer rd, Integer shamt, Integer funct, PrintWriter gravarArq) {
        gravarArq.printf(primeiraPosicao[0] + " " + primeiraPosicao[1] + "," + linhaSeparada[1] + ", " + linhaSeparada[2] + "\n");
        gravarArq.printf("Opcode\trs\trt\trd\tshamt\tfunct" + "\n");
        gravarArq.printf(op + "\t" + rs + "\t" + rt + "\t" + rd + "\t" + shamt + "\t" + funct + "\t\n");
    }

}

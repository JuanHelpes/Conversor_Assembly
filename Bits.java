/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversor_assembly;

/**
 *
 * @author juanh
 */
public class Bits {

    public static String arrumaBits_TipoR(String op, String rs, String rt, String rd, String shamt, String funct) {
        String inst = "";

        if (op.length() < 6) {
            int a = (6 - op.length());
            for (int j = 0; j < a; j++) {
                op = "0" + op;
            }
        }
        if (rs.length() < 5) {
            int b = (5 - rs.length());
            for (int j = 0; j < b; j++) {
                rs = "0" + rs;
            }
        }
        if (rt.length() < 5) {
            int ab = (5 - rt.length());
            for (int j = 0; j < ab; j++) {
                rt = "0" + rt;
            }
        }
        if (rd.length() < 5) {
            int abc = (5 - rd.length());
            for (int j = 0; j < abc; j++) {
                rd = "0" + rd;
            }
        }
        if (shamt.length() < 5) {
            int g = (5 - shamt.length());
            for (int j = 0; j < g; j++) {
                shamt = "0" + shamt;
            }
        }
        if (funct.length() < 6) {
            int o = (6 - funct.length());
            for (int j = 0; j < o; j++) {
                funct = "0" + funct;
            }

        }
        //junta todos bits, tendo agora um binario de 32 bits
        inst = op + rs + rt + rd + shamt + funct;
        return inst;
    }

    public static String arrumaBits_TipoJ(String op, String imm) {
        String inst = "";

        if (op.length() < 6) {
            int a = (6 - op.length());
            for (int j = 0; j < a; j++) {
                op = "0" + op;
            }
        }
        if (imm.length() < 26) {
            int b = (26 - imm.length());
            for (int j = 0; j < b; j++) {
                imm = "0" + imm;
            }
        }
        //junta todos bits, tendo agora um binario de 32 bits
        inst = op + imm;

        return inst;
    }

    public static String arrumaBits_TipoI(String op, String rs, String rt, String imm) {
        String inst = "";

        if (op.length() < 6) {
            int a = (6 - op.length());
            for (int j = 0; j < a; j++) {
                op = "0" + op;
            }
        }
        if (rs.length() < 5) {
            int b = (5 - rs.length());
            for (int j = 0; j < b; j++) {
                rs = "0" + rs;
            }
        }
        if (rt.length() < 5) {
            int ab = (5 - rt.length());
            for (int j = 0; j < ab; j++) {
                rt = "0" + rt;
            }
        }
        if (imm.length() < 16) {
            int abc = (16 - imm.length());
            for (int j = 0; j < abc; j++) {
                imm = "0" + imm;
            }
        }
        if (imm.length() > 16) {
            imm = imm.substring(16, 32);
        }
        //junta todos bits, tendo agora um binario de 32 bits
        inst = op + rs + rt + imm;

        return inst;
    }
}

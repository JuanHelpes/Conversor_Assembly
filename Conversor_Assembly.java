/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversor_assembly;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.sql.JDBCType.NULL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author juanh
 */
public class Conversor_Assembly {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        HashMap<String, Integer> biblioteca_reg = new HashMap<String, Integer>(); //biblioteca para os registradores
        HashMap<String, Integer> biblioteca_opcode = new HashMap<String, Integer>(); //biblioteca para os opcodes
        HashMap<String, Integer> biblioteca_funct = new HashMap<String, Integer>(); //biblioteca para as functs
        HashMap<String, Integer> biblioteca_label = new HashMap<String, Integer>(); //biblioteca para as labels
        //Biblioteca para os registradores
        biblioteca_reg.put("$zero", 0);
        biblioteca_reg.put("$at", 1);
        biblioteca_reg.put("$v0", 2);
        biblioteca_reg.put("$v1", 3);
        biblioteca_reg.put("$a0", 4);
        biblioteca_reg.put("$a1", 5);
        biblioteca_reg.put("$a2", 6);
        biblioteca_reg.put("$a3", 7);
        biblioteca_reg.put("$t0", 8);
        biblioteca_reg.put("$t1", 9);
        biblioteca_reg.put("$t2", 10);
        biblioteca_reg.put("$t3", 11);
        biblioteca_reg.put("$t4", 12);
        biblioteca_reg.put("$t5", 13);
        biblioteca_reg.put("$t6", 14);
        biblioteca_reg.put("$t7", 15);
        biblioteca_reg.put("$s0", 16);
        biblioteca_reg.put("$s1", 17);
        biblioteca_reg.put("$s2", 18);
        biblioteca_reg.put("$s3", 19);
        biblioteca_reg.put("$s4", 20);
        biblioteca_reg.put("$s5", 21);
        biblioteca_reg.put("$s6", 22);
        biblioteca_reg.put("$s7", 23);
        biblioteca_reg.put("$t8", 24);
        biblioteca_reg.put("$t9", 25);
        biblioteca_reg.put("$k0", 26);
        biblioteca_reg.put("$k1", 27);
        biblioteca_reg.put("$gp", 28);
        biblioteca_reg.put("$sp", 29);
        biblioteca_reg.put("$fp", 30);
        biblioteca_reg.put("$ra", 31);
        //Biblioteca para os opcodes
        biblioteca_opcode.put("add", 0);
        biblioteca_opcode.put("addi", 0);
        biblioteca_opcode.put("and", 0);
        biblioteca_opcode.put("andi", 12);
        biblioteca_opcode.put("beq", 4);
        biblioteca_opcode.put("bne", 5);
        biblioteca_opcode.put("j", 2);
        biblioteca_opcode.put("lw", 35);
        biblioteca_opcode.put("or", 0);
        biblioteca_opcode.put("ori", 13);
        biblioteca_opcode.put("sll", 0);
        biblioteca_opcode.put("slt", 0);
        biblioteca_opcode.put("slti", 10);
        biblioteca_opcode.put("srl", 0);
        biblioteca_opcode.put("sub", 0);
        biblioteca_opcode.put("sw", 43);
        biblioteca_opcode.put("xor", 0);
        biblioteca_opcode.put("xori", 14);
        //Biblioteca para as functs
        biblioteca_funct.put("add", 32); //R
        biblioteca_funct.put("addi", -1); // I
        biblioteca_funct.put("and", 36); //R
        biblioteca_funct.put("andi", -1); // I
        biblioteca_funct.put("beq", -3); // I
        biblioteca_funct.put("bne", -3); // I
        biblioteca_funct.put("j", -2); // J
        biblioteca_funct.put("lw", -1); //R
        biblioteca_funct.put("or", 37); //R
        biblioteca_funct.put("ori", -1); // I
        biblioteca_funct.put("sll", 0); //R
        biblioteca_funct.put("slt", 42); //R
        biblioteca_funct.put("slti", -1); // I
        biblioteca_funct.put("srl", 2); //R
        biblioteca_funct.put("sub", 34); //R
        biblioteca_funct.put("sw", -3); // I
        biblioteca_funct.put("xor", 38); //R
        biblioteca_funct.put("xori", -1); // I

        //criação do reader e do buffer para percorrer o arquivo.
        FileReader file1 = new FileReader("Assembly.asm");
        BufferedReader br1 = new BufferedReader(file1);
        int cont1 = 0;
        String linha1 = "";
        //primeiro lemos o arquivo todo para adicionar as labels a biblioteca "biblioteca_label" 
        while (br1.ready()) {
            //lê a proxima linha
            linha1 = br1.readLine();
            //Verifica se a linha está vazia, se sim volta para o início do while
            if (linha1.isEmpty() || linha1 == " ") {
            } //Verifica se a linha é um comentario do programa, se sim volta para o início do while
            else if (linha1.charAt(0) == '#') {
            } else if (linha1.charAt(linha1.length() - 1) == ':') {
                String nome = linha1.substring(0, linha1.length() - 1);
                biblioteca_label.put(nome, cont1);
                
            }
            else{
                cont1 = cont1+4;
            }
        }
        br1.close();
        file1.close();
       //criação do reader e do buffer para percorrer o arquivo.
        FileReader file = new FileReader("Assembly.asm");
        BufferedReader br = new BufferedReader(file);
        //criação do writer e do buffer para percorrer o arquivo.
        FileWriter arq = new FileWriter("AssemblyToDec.txt");
        PrintWriter gravarArq = new PrintWriter(arq);
        int cont = 0;
        while (br.ready()) {
            //lê a proxima linha
            String linha = br.readLine();
            //Verifica se a linha é um comentario do programa, se volta para o inicio do while
            if (linha.indexOf("#") == 0) {
            } //Verifica se a linha está vazia, se sim volta para o inicio do while
            else if (linha.isEmpty()) {
            } //se a linha for um comando válido do mips
            else if(linha.charAt(linha.length()-1)== ':'){
                }
            else {
                char aux[] = new char[linha.length()]; //criação do vetor de caracteres com o tamanho da linha lida
                char novaLinha[] = new char[linha.length()]; //criação do vetor de caracteres com o tamanho da linha lida
                //for usado para caso haja um comentario no final do comando, "novaLinha = comando sem comentário"
                for (int i = 0; i < linha.length(); i++) {
                    aux[i] = linha.charAt(i);
                    if (aux[i] != '#') {
                        novaLinha[i] = aux[i];
                    } else {
                        break;
                    }
                }
                String linhaSeparada[] = new String[5]; //criação de um vetor de string
                String a = String.copyValueOf(novaLinha);//passando a linha lida para a string a, "a = novaLinha"

                linhaSeparada = a.split(","); //separa a linha de comando lida pelas vírgulas e armazena em cada posição do vetor de string
                String[] primeiraPosicao = (linhaSeparada[0].split(" ")); //divide a string da primeira posição do vetor linhaSeparada em dois e armazena em outro vetor de string "primeiraPosicao"
                //verifica e retira os espaços em branco das strings
                for (int j = 1; j < linhaSeparada.length; j++) {
                    linhaSeparada[j].replace(" ", "");
                }

                for (int i = 0; i < primeiraPosicao.length; i++) {
                    primeiraPosicao[i].replace(" ", "");
                }
                //variáveis para guardar nossos valores
                Integer funct = 0;
                Integer op = 0;
                Integer rt = 0;
                Integer rs = 0;
                Integer rd = 0;
                Integer shamt = 0;
                String immAux = new String();
                Integer imm = 0;
                Integer immA = 0;
                //percorremos biblioteca_funct comparando a chave da biblioteca com a primeira instrução que aparece na linha
                for (String key : biblioteca_funct.keySet()) {
                    //quando achar coloca o valor da biblioteca na variável funct
                    if (key.equals(primeiraPosicao[0])) {
                        funct = biblioteca_funct.get(key);
                        //se funct igual a -1 significa que o comando é do tipo I.
                        if (funct == -1) {
                            //procura na biblioteca_opcode o opcode da intrução e salva em "op"
                            for (String k : biblioteca_opcode.keySet()) {
                                if (k.equals(primeiraPosicao[0])) {
                                    op = biblioteca_opcode.get(k);
                                }
                            }
                            //procura na biblioteca_reg os registradores usados e atribui os valores em sua respectivas variáveis
                            for (String k1 : biblioteca_reg.keySet()) {
                                if (k1.equals(primeiraPosicao[1])) {
                                    rt = biblioteca_reg.get(k1);
                                }
                                if (k1.equals(linhaSeparada[1].replace(" ", ""))) {
                                    rs = biblioteca_reg.get(k1);
                                }
                            }

                            linhaSeparada[2] = linhaSeparada[2].replace(" ", ""); //retira os espaços em branco, caso tenha, da string linhaSeparada[2]
                            immAux = linhaSeparada[2]; //salva a string sem espaços em immAux
                            //verifica se no comando está sendo passado algum valor em hexadecimal, se sim, o salva e converte em decimal 
                            if (linhaSeparada[2].substring(0, 2).equals("0x")) {
                                for (int i = 2; i <= (linhaSeparada.length) - 1; i++) {
                                    immAux = immAux.replace("0x", "");
                                    imm = Integer.parseInt(immAux.substring(0, 4), 16); // convertendo hex para decimal
                                }
                            } //se não for valor em hexadecimal apenas o converte de string para decimal
                            else {
                                imm = Integer.parseInt(linhaSeparada[2]);
                            }
                            //escrevendo no arquivo "AssemblyToDec.txt"
                            gravarArq.printf(primeiraPosicao[0] + " " + primeiraPosicao[1] + "," + linhaSeparada[1] + ", " + linhaSeparada[2] + "\n");
                            gravarArq.printf("Opcode\trs\trt\timmediate\n");
                            gravarArq.printf(op + "\t" + rs + "\t" + rt + "\t" + imm + "\n");
                        } //se funct for igual a -3 significa que é um caso especial do tipo I (beq, bne, sw)
                        else if (funct == -3) {
                            //procura na biblioteca_opcode o opcode da intrução e salva em "op"
                            for (String k : biblioteca_opcode.keySet()) {
                                if (k.equals(primeiraPosicao[0])) {
                                    op = biblioteca_opcode.get(k);
                                }
                            }
                            //procura na biblioteca_reg os registradores usados e atribui os valores em sua respectivas variáveis
                            for (String k1 : biblioteca_reg.keySet()) {
                                //se op é igual a 4 ou igual a 5, quer dizer que é beq ou bne
                                if (op == 4 || op == 5) {
                                    //salva os valores da biblioteca_reg nas repectivas variáveis
                                    rs = biblioteca_reg.get(primeiraPosicao[1]);
                                    rt = biblioteca_reg.get(linhaSeparada[1].replace(" ", ""));
                                    //procura na biblioteca_label as labels usadas e salva o valor em imm
                                    for (String k2 : biblioteca_label.keySet()) {
                                        if (k2.equals(linhaSeparada[2].replace(" ", ""))) {
                                            imm = biblioteca_label.get(k2);
                                        }
                                    }
                                    if(imm > cont){
                                      imm = ((imm/4)- cont) +1;
                                    }
                                    else{
                                      imm = ((imm/4)- cont)-1;
                                    }
                                    //escrevendo no arquivo "AssemblyToDec.txt"
                                    gravarArq.printf(primeiraPosicao[0] + " " + primeiraPosicao[1] + "," + linhaSeparada[1] + ", " + linhaSeparada[2].replace(" ", "") + "\n");
                                    gravarArq.printf("Opcode\trs\trt\timmediate\n");
                                    gravarArq.printf(op + "\t" + rs + "\t" + rt + "\t" + imm + "\n");
                                    op = 0; //após uma execução op é setado para 0 para sair do if

                                } //se op é igual a 43, quer dizer que a instrução é o sw
                                else if (op == 43) {
                                    String[] temp = linhaSeparada[1].split("\\("); //separa a string por "(" e salva em um vetor de string temporário
                                    temp[1] = temp[1].substring(0, temp[1].length() - 1); //retira a última posição do vetor de string na segunda posição que é equivalente a ")"  
                                    immAux = temp[0].replace(" ", ""); //retira os espaços em branco, caso tenha, da primeira posição de temp "temp[0] = "n($)""
                                    imm = Integer.parseInt(immAux); //converte em decimal
                                    //procura na biblioteca_reg os registradores usados e atribui os valores em suaa respectivas variáveis
                                    for (String k3 : biblioteca_reg.keySet()) {
                                        if (k3.equals(primeiraPosicao[1])) {
                                            rs = biblioteca_reg.get(k3);
                                        }
                                        if (k3.equals(temp[1])) {
                                            rt = biblioteca_reg.get(k3);
                                        }
                                    }
                                    //escrevendo no arquivo "AssemblyToDec.txt"
                                    gravarArq.printf(primeiraPosicao[0] + " " + primeiraPosicao[1] + "," + linhaSeparada[1] + "\n");
                                    gravarArq.printf("Opcode\trs\trt\timmediate\n");
                                    gravarArq.printf(op + "\t" + rs + "\t" + rt + "\t" + imm + "\n");
                                    op = 0; //após uma execução op é setado para 0 para sair do if
                                }
                            }
                        } //se funct igual a -2, quer dizer que a instrução é do tipo J (j)
                        else if (funct == -2) {
                            op = 2; //coloca op para dois
                            //procura na biblioteca_label as labels usadas e salva o valor em imm
                            for (String k4 : biblioteca_label.keySet()) {
                                if (k4.equals(primeiraPosicao[1])) {
                                    imm = biblioteca_label.get(k4);
                                }
                            }
                            //escrevendo no arquivo "AssemblyToDec.txt"
                            gravarArq.printf(primeiraPosicao[0] + " " + primeiraPosicao[1] + "\n");
                            gravarArq.printf("Opcode\timmediate\n");
                            gravarArq.printf(op + "\t" + imm + "\n");
                        } //instruções do tipo R
                        else {
                            //se funct for igual a 0 ou 2 necessita de tratamento especial (sll ou srl)
                            if (funct == 0 || funct == 2) {
                                //procura na biblioteca_opcode o opcode da intrução e salva em "op"
                                for (String k : biblioteca_opcode.keySet()) {
                                    if (k.equals(primeiraPosicao[0])) {
                                        op = biblioteca_opcode.get(k);
                                    }
                                }
                                //procura na biblioteca_reg os registradores usados e atribui os valores em sua respectivas variáveis
                                for (String k1 : biblioteca_reg.keySet()) {
                                    if (k1.equals(primeiraPosicao[1])) {
                                        rd = biblioteca_reg.get(k1);
                                    }
                                    if (k1.equals(linhaSeparada[1].replace(" ", ""))) {
                                        rt = biblioteca_reg.get(k1);
                                    }
                                }
                                //tira os espaços em branco, caso tenha, do vetor de string na posição dois, que corresponde ao shamt
                                shamt = Integer.parseInt(linhaSeparada[2].replace(" ", ""));
                                //escrevendo no arquivo "AssemblyToDec.txt"
                                gravarArq.printf(primeiraPosicao[0] + " " + primeiraPosicao[1] + "," + linhaSeparada[1] + ", " + linhaSeparada[2] + "\n");
                                gravarArq.printf("Opcode\trs\trt\trd\tshamt\tfunct" + "\n");
                                gravarArq.printf(op + "\t" + rs + "\t" + rt + "\t" + rd + "\t" + shamt + "\t0\n");
                            } //resto das instruções do tipo R que não precisam de tratamento especial
                            else {
                                //procura na biblioteca_opcode o opcode da intrução e salva em "op"
                                for (String k2 : biblioteca_opcode.keySet()) {
                                    if (k2.equals(primeiraPosicao[0])) {
                                        op = biblioteca_opcode.get(k2);
                                    }
                                }
                                //procura na biblioteca_reg os registradores usados e atribui os valores em sua respectivas variáveis
                                for (String k3 : biblioteca_reg.keySet()) {
                                    if (k3.equals(primeiraPosicao[1])) {
                                        rd = biblioteca_reg.get(k3);
                                    }
                                    if (k3.equals(linhaSeparada[1].replace(" ", ""))) {
                                        rs = biblioteca_reg.get(k3);
                                    }
                                    if (k3.equals(linhaSeparada[2].replace(" ", ""))) {
                                        rt = biblioteca_reg.get(k3);
                                    }
                                }
                                //escrevendo no arquivo "AssemblyToDec.txt"
                                gravarArq.printf(primeiraPosicao[0] + " " + primeiraPosicao[1] + "," + linhaSeparada[1] + ", " + linhaSeparada[2] + "\n");
                                gravarArq.printf("Opcode\trs\trt\trd\tshamt\tfunct" + "\n");
                                gravarArq.printf(op + "\t" + rs + "\t" + rt + "\t" + rd + "\t" + shamt + "\t" + funct + "\t\n");
                            }
                        }
                    }
                }
                cont++;
            }
        }
        //fechando o arquivo para salvar as alterações
        arq.close();
        gravarArq.close();
        //criação do reader e do buffer para percorrer o  arquivo.
        FileReader fileB = new FileReader("AssemblyToDec.txt");
        BufferedReader brB = new BufferedReader(fileB);
        //criação do writer e do buffer para percorrer o segundo arquivo.
        FileWriter arqB = new FileWriter("AssemblyToHex.txt");
        PrintWriter gravarArqB = new PrintWriter(arqB);

        int contar = 0;// variável contadora
        while (brB.ready()) {
            String linha3 = brB.readLine();
            contar++;
            //criação das váriaveis usadas
            String op = "";
            int num = 0;
            String rs;
            String rt;
            String rd;
            String shamt;
            String funct;
            String inst;
            //a cada 3 linhas do arquivo "AssemblyToDec.txt" sabemos que tera uma sequência de numeros decimais que devem ser convertidos para hexadecimal
            if (contar % 3 == 0) {
                //separa a linha por "\t", cada posição do vetor le tera um número decimal
                String[] le = linha3.split("\\t");
                //se a primeira posição que é o opcode é igual a zero quer dizer que a instrução é do tipo R
                if (le[0].equals("0")) {
                    //converte cada posição do vetor le para inteiro e o transforma em binario e salva na respectíva variável
                    num = Integer.parseInt(le[0]);
                    op = Integer.toBinaryString(num);
                    num = Integer.parseInt(le[1]);
                    rs = Integer.toBinaryString(num);
                    num = Integer.parseInt(le[2]);
                    rt = Integer.toBinaryString(num);
                    num = Integer.parseInt(le[3]);
                    rd = Integer.toBinaryString(num);
                    num = Integer.parseInt(le[4]);
                    shamt = Integer.toBinaryString(num);
                    num = Integer.parseInt(le[5]);
                    funct = Integer.toBinaryString(num);
                    //formata o binario para que cada posição tenha o número de bits correto
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
                    //escreve no arquivo "AssemblyToHex.txt" convertendo de 4 em 4 bits para um decimal e depois para hexadecimal
                    gravarArqB.printf("0x" + Integer.toHexString(Integer.parseInt(inst.substring(0, 4), 2)));
                    gravarArqB.printf(Integer.toHexString(Integer.parseInt(inst.substring(4, 8), 2)));
                    gravarArqB.printf(Integer.toHexString(Integer.parseInt(inst.substring(8, 12), 2)));
                    gravarArqB.printf(Integer.toHexString(Integer.parseInt(inst.substring(12, 16), 2)));
                    gravarArqB.printf(Integer.toHexString(Integer.parseInt(inst.substring(16, 20), 2)));
                    gravarArqB.printf(Integer.toHexString(Integer.parseInt(inst.substring(20, 24), 2)));
                    gravarArqB.printf(Integer.toHexString(Integer.parseInt(inst.substring(24, 28), 2)));
                    gravarArqB.printf(Integer.toHexString(Integer.parseInt(inst.substring(28, 32), 2)) + "_h\n");

                } //se a primeira posição que é o opcode é igual a dois quer dizer que a instrução é do tipo J (j)
                else if (le[0].equals("2")) {
                    //converte cada posição do vetor le para inteiro e o transforma em binario e salva na respectíva variável
                    num = Integer.parseInt(le[0]);
                    op = Integer.toBinaryString(num);
                    num = Integer.parseInt(le[1]);
                    rs = Integer.toBinaryString(num);
                    //formata o binario para que cada posição tenha o número de bits correto
                    if (op.length() < 6) {
                        int a = (6 - op.length());
                        for (int j = 0; j < a; j++) {
                            op = "0" + op;
                        }
                    }
                    if (rs.length() < 26) {
                        int b = (26 - rs.length());
                        for (int j = 0; j < b; j++) {
                            rs = "0" + rs;
                        }
                    }
                    //junta todos bits, tendo agora um binario de 32 bits
                    inst = op + rs;
                    //escreve no arquivo "AssemblyToHex.txt" convertendo de 4 em 4 bits para um decimal e depois para hexadecimal
                    gravarArqB.printf("0x" + Integer.toHexString(Integer.parseInt(inst.substring(0, 4), 2)));
                    gravarArqB.printf(Integer.toHexString(Integer.parseInt(inst.substring(4, 8), 2)));
                    gravarArqB.printf(Integer.toHexString(Integer.parseInt(inst.substring(8, 12), 2)));
                    gravarArqB.printf(Integer.toHexString(Integer.parseInt(inst.substring(12, 16), 2)));
                    gravarArqB.printf(Integer.toHexString(Integer.parseInt(inst.substring(16, 20), 2)));
                    gravarArqB.printf(Integer.toHexString(Integer.parseInt(inst.substring(20, 24), 2)));
                    gravarArqB.printf(Integer.toHexString(Integer.parseInt(inst.substring(24, 28), 2)));
                    gravarArqB.printf(Integer.toHexString(Integer.parseInt(inst.substring(28, 32), 2)) + "_h\n");

                } //casos que sobraram, tipo I
                else {
                    //converte cada posição do vetor le para inteiro e o transforma em binario e salva na respectíva variável
                    num = Integer.parseInt(le[0]);
                    op = Integer.toBinaryString(num);
                    num = Integer.parseInt(le[1]);
                    rs = Integer.toBinaryString(num);
                    num = Integer.parseInt(le[2]);
                    rt = Integer.toBinaryString(num);
                    num = Integer.parseInt(le[3]);
                    rd = Integer.toBinaryString(num); // imm

                    //formata o binario para que cada posição tenha o número de bits correto
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
                    if (rd.length() < 16) {
                        int abc = (16 - rd.length());
                        for (int j = 0; j < abc; j++) {
                            rd = "0" + rd;
                        }
                    }
                    if(rd.length() >16){
                        rd = rd.substring(16, 32);
                    }
                    //junta todos bits, tendo agora um binario de 32 bits
                    inst = op + rs + rt + rd;
                    //escreve no arquivo "AssemblyToHex.txt" convertendo de 4 em 4 bits para um decimal e depois para hexadecimal
                    gravarArqB.write("0x" + Integer.toHexString(Integer.parseInt(inst.substring(0, 4), 2)));
                    gravarArqB.write(Integer.toHexString(Integer.parseInt(inst.substring(4, 8), 2)));
                    gravarArqB.write(Integer.toHexString(Integer.parseInt(inst.substring(8, 12), 2)));
                    gravarArqB.write(Integer.toHexString(Integer.parseInt(inst.substring(12, 16), 2)));
                    gravarArqB.write(Integer.toHexString(Integer.parseInt(inst.substring(16, 20), 2)));
                    gravarArqB.write(Integer.toHexString(Integer.parseInt(inst.substring(20, 24), 2)));
                    gravarArqB.write(Integer.toHexString(Integer.parseInt(inst.substring(24, 28), 2)));
                    gravarArqB.write(Integer.toHexString(Integer.parseInt(inst.substring(28, 32), 2)) + "_h\n");
                }
            }

        }
        //fechando o arquivo para salvar as alterações
        brB.close();
        gravarArqB.close();
    }

}
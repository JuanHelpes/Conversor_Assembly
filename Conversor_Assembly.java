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

            } else {
                cont1 = cont1 + 4;
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
            else if (linha.charAt(linha.length() - 1) == ':') {
            } else {
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
                //atribui o valor que corresponde a instrução da biblioteca funct na variavel funct
                funct = biblioteca_funct.get(primeiraPosicao[0]);
                //se funct igual a -1 significa que o comando é do tipo I.
                if (funct == -1) {
                    //atribui o valor que corresponde a instrução da biblioteca opcode na variavel op
                    op = biblioteca_opcode.get(primeiraPosicao[0]);
                    //atribui o valor que corresponde aos registradores da biblioteca reg nas variaveis rt e rs
                    rt = biblioteca_reg.get(primeiraPosicao[1]);
                    rs = biblioteca_reg.get(linhaSeparada[1].replace(" ", ""));
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
                    //Chamada da função que escreve no arquivo "AssemblyToDec.txt"
                    EscreverArq.escreverTipoI(primeiraPosicao, linhaSeparada, op, rs, rt, imm, gravarArq);
                }//se funct for igual a -3 significa que é um caso especial do tipo I (beq, bne)
                else if (funct == -3) {
                    //atribui o valor que corresponde a instrução da biblioteca opcode na variavel op
                    op = biblioteca_opcode.get(primeiraPosicao[0]);
                    //se op é igual a 4 ou igual a 5, quer dizer que é beq ou bne
                    if (op == 4 || op == 5) {
                        //atribui o valor que corresponde aos registradores da biblioteca reg nas variaveis rs e rt
                        rs = biblioteca_reg.get(primeiraPosicao[1]);
                        rt = biblioteca_reg.get(linhaSeparada[1].replace(" ", ""));
                        //atribui o valor que corresponde a label da biblioteca label na variavel imm
                        imm = biblioteca_label.get(linhaSeparada[2].replace(" ", ""));
                        //conta quantas linhas o beq e bne subiu ou desceu
                        imm = ((imm / 4) - (cont + 1));
                        //Chamada da função que escreve no arquivo "AssemblyToDec.txt"
                        EscreverArq.escreverTipoI(primeiraPosicao, linhaSeparada, op, rs, rt, imm, gravarArq);
                        op = 0; //após uma execução op é setado para 0 para sair do if
                    } //se op é igual a 43, quer dizer que a instrução é o sw
                    else if (op == 43) {
                        String[] temp = linhaSeparada[1].split("\\("); //separa a string por "(" e salva em um vetor de string temporário
                        temp[1] = temp[1].substring(0, temp[1].length() - 1); //retira a última posição do vetor de string na segunda posição que é equivalente a ")"  
                        immAux = temp[0].replace(" ", ""); //retira os espaços em branco, caso tenha, da primeira posição de temp "temp[0] = "n($)""
                        imm = Integer.parseInt(immAux); //converte em decimal
                        //atribui o valor que corresponde aos registradores da biblioteca reg nas variaveis rs e rt
                        rs = biblioteca_reg.get(primeiraPosicao[1]);
                        rt = biblioteca_reg.get(temp[1]);

                        //Chamada da função que escreve no arquivo "AssemblyToDec.txt"
                        EscreverArq.escreverTipoI_sw(primeiraPosicao, linhaSeparada, op, rs, rt, imm, gravarArq);
                        op = 0; //após uma execução op é setado para 0 para sair do if
                    }
                } //se funct igual a -2, quer dizer que a instrução é do tipo J (j)
                else if (funct == -2) {
                    op = 2; //coloca op para dois
                    //atribui o valor que corresponde a label da biblioteca label na variavel imm
                    imm = biblioteca_label.get(primeiraPosicao[1]);
                    //Chamada da função que escreve no arquivo "AssemblyToDec.txt"
                    EscreverArq.escreverTipoI_j(primeiraPosicao, op, imm, gravarArq);
                } //instruções do tipo R
                else {
                    //se funct for igual a 0 ou 2 necessita de tratamento especial (sll ou srl)
                    if (funct == 0 || funct == 2) {
                        //atribui o valor que corresponde a instrução da biblioteca opcode na variavel op
                        op = biblioteca_opcode.get(primeiraPosicao[0]);
                        //atribui o valor que corresponde aos registradores da biblioteca reg nas variaveis rd e rt
                        rd = biblioteca_reg.get(primeiraPosicao[1]);
                        rt = biblioteca_reg.get(linhaSeparada[1].replace(" ", ""));
                        //tira os espaços em branco, caso tenha, do vetor de string na posição dois, que corresponde ao shamt
                        shamt = Integer.parseInt(linhaSeparada[2].replace(" ", ""));
                        //Chamada da função que escreve no arquivo "AssemblyToDec.txt"
                        EscreverArq.escreverTipoR_shamt(primeiraPosicao, linhaSeparada, op, rs, rt, rd, shamt, gravarArq);
                    } //resto das instruções do tipo R que não precisam de tratamento especial
                    else {
                        //atribui o valor que corresponde a instrução da biblioteca opcode na variavel op
                        op = biblioteca_opcode.get(primeiraPosicao[0]);
                        //atribui o valor que corresponde aos registradores da biblioteca reg nas variaveis rd, rs e rt
                        rd = biblioteca_reg.get(primeiraPosicao[1]);
                        rs = biblioteca_reg.get(linhaSeparada[1].replace(" ", ""));
                        rt = biblioteca_reg.get(linhaSeparada[2].replace(" ", ""));
                        //Chamada da função que escreve no arquivo "AssemblyToDec.txt"
                        EscreverArq.escreverTipoR(primeiraPosicao, linhaSeparada, op, rs, rt, rd, shamt, funct, gravarArq);
                    }
                }
                cont++; //contador de linha de instruções (não contando espaços em branco, comentarios e labels)
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
            String imm;
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
                    inst = Bits.arrumaBits_TipoR(op, rs, rt, rd, shamt, funct);
                    //escreve no arquivo "AssemblyToHex.txt" convertendo de 4 em 4 bits para um decimal e depois para hexadecimal
                    EscreverArqHex.escrever_Hex(inst, gravarArqB);
                } //se a primeira posição que é o opcode é igual a dois quer dizer que a instrução é do tipo J (j)
                else if (le[0].equals("2")) {
                    //converte cada posição do vetor le para inteiro e o transforma em binario e salva na respectíva variável
                    num = Integer.parseInt(le[0]);
                    op = Integer.toBinaryString(num);
                    num = Integer.parseInt(le[1]);
                    imm = Integer.toBinaryString(num);
                    //Chamada da função que formata o binario para que cada posição tenha o número de bits correto e retorna uma string com os 32 bits
                    inst = Bits.arrumaBits_TipoJ(op, imm);
                    //Chamada da função que escreve no arquivo "AssemblyToHex.txt" convertendo de 4 em 4 bits para um decimal e depois para hexadecimal
                    EscreverArqHex.escrever_Hex(inst, gravarArqB);
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
                    imm = Integer.toBinaryString(num); // imm
                    //Chamada da função que formata o binario para que cada posição tenha o número de bits correto e retorna uma string com os 32 bits
                    inst = Bits.arrumaBits_TipoI(op, rs, rt, imm);
                    //Chamada da função que escreve no arquivo "AssemblyToHex.txt" convertendo de 4 em 4 bits para um decimal e depois para hexadecimal
                    EscreverArqHex.escrever_Hex(inst, gravarArqB);
                }
            }
        }
        //fechando o arquivo para salvar as alterações
        brB.close();
        gravarArqB.close();
    }
}
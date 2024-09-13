# processadores-texto-java
Atividade - Processadores de Texto - Universidade Presbiteriana Mackenzie 

A MackData recebeu uma solicitação para aprimorar o sistema de processamento de texto. Agora, o usuário final deve fornecer um arquivo de texto para ser processado. O sistema deve aplicar múltiplos processadores em sequência e gerar um novo arquivo com o texto processado.

## Parte 1 - Leitura e Processamento de Arquivos
O sistema deverá realizar o seguinte fluxo:

1. Entrada: O usuário deve informar o nome do arquivo de entrada que contém o texto a ser processado.

2. Processamento: O texto será processado por uma série de processadores, removendo espaços excedentes e caracteres especiais.

3. Saída: O sistema deve gerar um arquivo de saída com o texto processado. O nome do arquivo de saída deve ser derivado do nome do arquivo de entrada, com o sufixo _processado.txt.

### Requisitos:

• O sistema deve ler o texto de um arquivo fornecido pelo usuário.

• O nome do arquivo de entrada deve ser solicitado via terminal.

• O sistema deve criar um arquivo de saída com o texto já processado.

Para orientar sua implementação foi fornecido o programa principal, o qual não pode ser alterado neste momento:

````java
package br.mack.data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Entrada do nome do arquivo de texto
        System.out.println("Digite o nome do arquivo de entrada (com extensão .txt):");
        String arquivoEntrada = scanner.nextLine();

        // Leitura do arquivo de entrada
        String texto = lerArquivo(arquivoEntrada);
        if (texto == null) {
            System.err.println("Erro ao ler o arquivo de entrada.");
            return;
        }

        // Criação dos processadores
        List<ProcessadorTexto> processadores = new ArrayList<>();
        processadores.add(new RemoverEspacosProcessador());
        processadores.add(new RemoverCaracteresEspeciaisProcessador());

        // Encadeamento dos processadores
        String textoProcessado = texto;
        for (ProcessadorTexto processador : processadores) {
            textoProcessado = processador.processar(textoProcessado);
        }

        // Gerar arquivo de saída com o texto processado
        String nomeArquivoSaida = gerarNomeArquivoSaida(arquivoEntrada);
        escreverArquivo(nomeArquivoSaida, textoProcessado);

        System.out.println("Processamento concluído. Arquivo gerado: " + nomeArquivoSaida);

        scanner.close();
    }

    // Função para ler o conteúdo de um arquivo de texto
    private static String lerArquivo(String nomeArquivo) {
        StringBuilder conteudo = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                conteudo.append(linha).append(System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            return null;
        }
        return conteudo.toString();
    }

    // Função para escrever o conteúdo processado em um arquivo de saída
    private static void escreverArquivo(String nomeArquivo, String conteudo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo))) {
            bw.write(conteudo);
        } catch (IOException e) {
            System.err.println("Erro ao escrever o arquivo: " + e.getMessage());
        }
    }

    // Função para gerar o nome do arquivo de saída com o sufixo "_processado"
    private static String gerarNomeArquivoSaida(String nomeArquivoEntrada) {
        int pontoIndex = nomeArquivoEntrada.lastIndexOf('.');
        if (pontoIndex > 0) {
            return nomeArquivoEntrada.substring(0, pontoIndex) + "_processado.txt";
        } else {
            return nomeArquivoEntrada + "_processado.txt";
        }
    }
}
````
## Parte 2 - Desafio: Implementação de um Processador de Palavras Ofensivas
O cliente pediu um novo recurso para o sistema. Agora, além de remover espaços e caracteres especiais, o sistema deve ser capaz de remover palavras ofensivas do texto. A lista de palavras ofensivas deve ser carregada a partir de um arquivo de configuração chamado palavras_ofensivas.txt.

### Requisitos do Desafio:
• Você deve criar uma nova classe que implemente a interface ProcessadorTexto e que remova palavras ofensivas.

• A lista de palavras ofensivas deve ser carregada do arquivo palavras_ofensivas.txt. Cada linha do arquivo representa uma palavra ofensiva.

• Ao encontrar uma palavra ofensiva no texto, ela deve ser substituída por [REMOVIDO].

• O sistema deve ser capaz de processar o texto utilizando todos os processadores em sequência, incluindo o de palavras ofensivas, e gerar o arquivo de saída com o texto processado. Agora sim você pode modificar o método main, mas o mínimo necessário para adicionar o novo processador de texto.

### Fluxo Esperado:

1. O usuário fornece o nome de um arquivo de texto de entrada.

2. O sistema processa o texto aplicando, em sequência:

    • Remoção de espaços excedentes.

    • Remoção de caracteres especiais.

    • Remoção de palavras ofensivas (implementada por você neste momento).

3. O sistema gera um arquivo de saída com o texto final processado.

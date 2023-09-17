/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tde_ra1;

/**
 *
 * @author Casa
 */
import java.util.Random;
import java.util.Scanner;

public class Pilha extends ListaEncadeada {

    private int capacidade;

    public Pilha(int tamanho) {
        super();
        capacidade = tamanho;
    }

    public boolean excluir() {
        return excluir(0);
    }

    public Integer primeiro() {
        return lista.getDados();
    }

    public void inserir(Integer dado) {
        if (lista == null) {
            lista = new Node(dado);
        } else {
            Node novo = new Node(dado);
            novo.setProximo(lista);
            lista = novo;
        }
    }

    public int pop() {
        if (vazio()) {
            System.out.println("Esta pilha esta vazia");
            return -1;
        }
        int num = this.primeiro();
        this.excluir();
        return num;
    }

    public boolean vazio() {
        return lista == null;
    }

    public boolean cheia() {
        int tamanho = 0;
        for (Node n = lista; n != null; n = n.getProximo()) {
            tamanho++;
        }
        if (tamanho == capacidade) {
            return true;
        }
        return false;
    }

    public int tamanho() {
        int tamanho = 0;
        for (Node n = lista; n != null; n = n.getProximo()) {
            tamanho++;
        }
        return tamanho;
    }

    public void push(int dado) {
        if (!cheia()) {
            this.inserir(dado);
        } else {
            System.out.println("Pilha Cheia");
        }
    }

    public boolean ordenada(int modo) {
        // modo 1 é crescente
        if (modo == 1) {
            Node atual = lista;
            while (atual != null) {
                Node prox = atual.getProximo();
                while (prox != null) {
                    if (atual.getDados() > prox.getDados()) {
                        return false;
                    }
                    prox = prox.getProximo();
                }
                atual = atual.getProximo();
            }
            return true;
        // modo decrescente
        } else {
            Node atual = lista;
            while (atual != null) {
                Node prox = atual.getProximo();
                while (prox != null) {
                    if (atual.getDados() < prox.getDados()) {
                        return false;
                    }
                    prox = prox.getProximo();
                }
                atual = atual.getProximo();
            }
            return true;
        }
    }

    public boolean ordenada(int modo, int base) {
        // modo 1 é crescente
        if (modo == 1) {
            Node atual = lista;
            if (atual != null) {
                if (base > atual.getDados()) {
                    return false;
                } else {
                    atual = atual.getProximo();
                }
            }
            while (atual != null) {
                Node prox = atual.getProximo();
                while (prox != null) {
                    if (atual.getDados() > prox.getDados()) {
                        return false;
                    }
                    prox = prox.getProximo();
                }
                atual = atual.getProximo();
            }
            return true;
        // modo decrescente
        } else {
            Node atual = lista;
            if (atual != null) {
                if (base < atual.getDados()) {
                    return false;
                } else {
                    atual = atual.getProximo();
                }
            }
            while (atual != null) {
                Node prox = atual.getProximo();
                while (prox != null) {
                    if (atual.getDados() < prox.getDados()) {
                        return false;
                    }
                    prox = prox.getProximo();
                }
                atual = atual.getProximo();
            }
            return true;
        }

    }

    public static int solucaoAutomatica(Pilha pilhas[], int jogadas, int modo) {
        int pb = -1, pdst = -1, pd = -1;
        Pilha pilha_da_base, pilha_de_destino, pilha_de_descarte;

        // definir base
        int base = -1;
        for (int i = 0; i < 3; i++) {
            for (Node n = pilhas[i].lista; n != null; n = n.getProximo()) {
                if (base == -1) {
                    base = n.getDados();
                    pb = i;
                } else {
                    // modo crescente
                    if (modo == 1) {
                        if (n.getDados() > base) {
                            base = n.getDados();
                            pb = i;
                        }
                    // modo decrescente
                    } else {
                        if (n.getDados() < base) {
                            base = n.getDados();
                            pb = i;
                        }
                    }
                }
            }
        }

        // definir pilhas
        {
            int tamanho_da_pilha = -1;
            for (int i = 0; i < 3; i++) {
                if (i == pb) continue;
                if (pilhas[i].vazio()) {
                    pdst = i;
                    for (int j = 0; j < 3; j++) {
                        if (j == pdst || j == pb) continue;
                        pd = j;
                    }
                    break;
                } else {
                    if (tamanho_da_pilha == -1) {
                        tamanho_da_pilha = pilhas[i].tamanho();
                    } else {
                        if (pilhas[i].ordenada(modo, base)) {
                            pdst = i;
                            for (int j = 0; j < 3; j++) {
                                if (j == pdst || j == pb) continue;
                                pd = j;
                            }
                            break;
                        }
                        else if (pilhas[i].tamanho() < tamanho_da_pilha) {
                            pdst = i;
                            for (int j = 0; j < 3; j++) {
                                if (j == pdst || j == pb) continue;
                                pd = j;
                            }
                            break;
                        } else {
                            pd = i;
                            for (int j = 0; j < 3; j++) {
                                if (j == pd || j == pb) continue;
                                pdst = j;
                            }
                            break;
                        }
                    }
                }
            }
            pilha_da_base = pilhas[pb];
            pilha_de_destino = pilhas[pdst];
            pilha_de_descarte = pilhas[pd];
        }

        // esvaziar pilha de destino, se necessário
        if (!pilha_de_destino.ordenada(modo, base)) {
            while (!pilha_de_destino.vazio()) {
                int dado = pilha_de_destino.pop();
                pilha_de_descarte.push(dado);
                jogadas++;
                imprimir_pilhas(pilhas);
            }
        }

        do {
            // desempilhar da pilha da base até chegar na base
            while (pilha_da_base.primeiro() != base) {
                int dado = pilha_da_base.pop();
                pilha_de_descarte.push(dado);
                jogadas++;
                imprimir_pilhas(pilhas);
            }

            // mandar base para pilha de destino, se necessário
            if (pilha_da_base.tamanho() == 1 && pilha_de_destino.vazio()) {
                int tmp = pdst;
                pdst = pb;
                pb = tmp;
                pilha_da_base = pilhas[pb];
                pilha_de_destino = pilhas[pdst];
            } else {
                int dado = pilha_da_base.pop();
                pilha_de_destino.push(dado);
                jogadas++;
                imprimir_pilhas(pilhas);
            }

            System.out.println("Pilha de destino: " + (pdst + 1));
            System.out.println("Pilha base: " + (pb + 1));
            System.out.println("Pilha de descarte: " + (pd + 1));

            // definir próxima base
            {
                int base_tmp = -1;
                for (int i = 0; i < 3; i++) {
                    if (i == pdst) continue;
                    for (Node n = pilhas[i].lista; n != null; n = n.getProximo()) {
                        if (base_tmp == -1) {
                            base_tmp = n.getDados();
                            pb = i;
                        } else {
                            // modo crescente
                            if (modo == 1) {
                                if (n.getDados() > base) {
                                    break;
                                }
                                if (n.getDados() > base_tmp) {
                                    base_tmp = n.getDados();
                                    pb = i;
                                }
                            // modo decrescente
                            } else {
                                if (n.getDados() < base) {
                                    break;
                                }
                                if (n.getDados() < base_tmp) {
                                    base_tmp = n.getDados();
                                    pb = i;
                                }
                            }
                        }
                    }
                }
                base = base_tmp;
                if (pb == pd) {
                    for (int i = 0; i < 3; i++) {
                        if (i == pb || i == pdst) continue;
                        pd = i;
                    }
                }
                pilha_da_base = pilhas[pb];
                pilha_de_descarte = pilhas[pd];
            }

            System.out.println("Nova Pilha base: " + (pb + 1));
            System.out.println("Nova Pilha descarte: " + (pd + 1));
        } while (!pilha_de_destino.cheia());
        return jogadas;
    }

    public static void imprimir_pilhas(Pilha pilhas[]) {
        for (int i = 0; i < 3; i++) {
            System.out.println("Pilha " + (i+1));
            pilhas[i].imprimir();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int tamanho = 0;

        while (tamanho < 2) {
            System.out.print("Informe o tamanho das pilhas: ");
            tamanho = scanner.nextInt();
            if (tamanho < 2) {
                System.out.println("Tamanho invalido");
            }
        }

        Pilha pilhas[] = new Pilha[3];

        for (int i = 0; i < 3; i++) {
            pilhas[i] = new Pilha(tamanho);
        }

        for (int i = 0; i < tamanho; i++) {
            pilhas[0].inserir(random.nextInt(100) + 1);
        }

        int modoJogo = 0;

        while (modoJogo != 1 && modoJogo != 2) {
            System.out.println("Voce deseja ordenar de forma?\n(1)crescente\n(2)decrescente");
            modoJogo = scanner.nextInt();
            if (modoJogo != 1 && modoJogo != 2) {
                System.out.println("Escolha invalida");
            }
        }

        if (pilhas[0].ordenada(modoJogo)) {
            int dado1 = pilhas[0].pop();
            int dado2 = pilhas[0].pop();
            pilhas[0].push(dado1);
            pilhas[0].push(dado2);
        }

        int opcao;
        int origem;
        int destino;
        int dado;
        int jogadas = 0;

OUTER:
        while (true) {
            imprimir_pilhas(pilhas);

            System.out.println("======================================");
            System.out.println("Escolha uma opcao:\n0 - Sair do jogo.\n1 - Movimentar.\n2 - Solucao automatica.");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 0:
                    System.out.println("Jogo encerrado.");
                    scanner.close();
                    return;
                case 1:
                    System.out.println("De qual filha voce deseja mover?");
                    origem = scanner.nextInt();
                    System.out.println("Para qual pilha voce deseja mover?");
                    destino = scanner.nextInt();

                    if (origem > 3 || origem < 1 || destino > 3 || destino < 1) {
                        System.out.println("Entrada invalida");
                        break;
                    }
                    dado = pilhas[origem - 1].pop();
                    if (dado == -1) {
                        break;
                    }
                    pilhas[destino - 1].push(dado);
                    jogadas++;

                    break;
                case 2:
                    jogadas = solucaoAutomatica(pilhas, jogadas, modoJogo);
                    break;
            }

            for (int i = 0; i < 3; i++) {
                if (pilhas[i].cheia() && pilhas[i].ordenada(modoJogo)) {
                    imprimir_pilhas(pilhas);
                    System.out.println("Parabens voce ganhou em " + jogadas + " jogdas");
                    break OUTER;
                }
            }
        }
        scanner.close();
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tde_ra1;

/**
 *
 * @author Casa
 */
public class ListaEncadeada {
  protected Node lista;
  public ListaEncadeada() {
    lista = null;
  }

  public void inserir(Integer dado) {
    if (lista == null) {
      lista = new Node(dado);
    } else {
      /* encontrar referencia do ultimo nodo da lista */
      Node ultimo = lista;
      while (ultimo.getProximo() != null) {
        ultimo = ultimo.getProximo();
      }
      ultimo.setProximo(new Node(dado));
    }
  }

  /* retorna true se a delecao foi possivel */
  public boolean excluir(int idx) {
    if (idx < 0 || lista == null) return false;
    /* deletar o primeiro nodo */
    if (idx == 0) {
      System.out.println("O elemento " + lista.getDados() + " foi removido");
      lista = lista.getProximo();
      return true;
    }

    Node anterior = lista, atual = lista;
    for (int i = 0; atual != null && i < idx; i++) {
      anterior = atual;
      atual = atual.getProximo();
    }
    /* deletar o nodo de indice idx */
    if (atual != null) {
      System.out.println("O elemento " + atual.getDados() + " foi removido");
      anterior.setProximo(atual.getProximo());
      atual = null;
      return true;
    }
    /* indice idx maior que tamanho da lista */
    return false;
  }

  public void imprimir() {
    for (Node n = lista; n != null; n = n.getProximo()) {
      System.out.println(n);
    }
  }
}


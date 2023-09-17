/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tde_ra1;

/**
 *
 * @author Casa
 */
public class Node {
    private Integer dados;
  private Node proximo;
  public Node(Integer dados) {
    this.dados = dados;
    proximo = null;
  }

  public void setDados(Integer dado) {
    dados = dado;
  }

  public Integer getDados() {
    return dados;
  }

  public void setProximo(Node n) {
    this.proximo = n;
  }

  public Node getProximo() {
    return this.proximo;
  }

  public String toString() {
    return dados.toString();
  }
}

package aprendendo.multithread.usandocolecoessincronizadas;

import java.util.Random;

public class MeuRunnable implements Runnable{
  /*

  //usando listas thread safe
  @Override
  public void run() {
    //isso vai gerár um problema grave, pois não exibirá erros,
    //pois um List não é sincronizado...
    //soluções: colocar a lista dentro de um bloco sincronizado 
    //ou usar coleções syncronizadas.
    SincronizarColecoes.lista.add("Inscreva-se no canal!");
    System.out.println(Thread.currentThread().getName()+" inseriu na lista!");
  }
  */
  @Override
  public void run() {
    //map
    //SincronizarColecoes.mapa.put(new Random().nextInt(3),"Curta esse vídeo!");

    //queue - fila
    SincronizarColecoes.fila.add("Se tiver condições, torne-se um apoiador do canal!");
    System.out.println(Thread.currentThread().getName()+" inseriu na lista!");
  }
  
}

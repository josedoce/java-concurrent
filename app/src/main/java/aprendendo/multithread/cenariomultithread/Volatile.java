package aprendendo.multithread.cenariomultithread;

import java.lang.Thread.State;

public class Volatile {
  /* 
  
  //sem volatile, vai da erro...
  private static int numero = 0;
  private static boolean preparado = false;
  */
  /*
   * Com o volatile, garantimos que a leitura das variaveis serão feitas na memoria ram,
   * evitando que o programa use os valores em cache, pois isso pode não está sincronizado
   * com o valor novo que uma thread modificou.
   * resumindo: a thread terá sempre acesso ao valor atualizado, usando o valor de memoria ram
   * ao inves do valor em memoria cache.
   * Isso é pesado, porem necessário se for modificar valores em uma thread usando paralelismo.
   * 
  */
  private static volatile int numero = 0;
  private static volatile boolean preparado = false;
  public static void logica(){
    Volatile v = new Volatile();
    //v.exemplo1();
    v.exemplo2();
  }

  private static class MeuRunnable implements Runnable{

    @Override
    public void run() {
      while(!Volatile.preparado){
        //yield avisa para o processador que não há por um momento o 
        //que ser executado, dando lugar a outra thread, mas avisa 
        //para voltar e executar quando tiver.
        Thread.yield();//meio que pausa
      }
      System.out.println(numero);
    }
    
  }

  private void exemplo1(){
    Thread th = new Thread(new MeuRunnable());
    th.start();
    numero = 42;
    preparado = true;
  }

  private static class MeuRunnable2 implements Runnable{

    @Override
    public void run() {
      while(!Volatile.preparado){
        Thread.yield();
      }
      if(numero != 42){
        
        throw new IllegalStateException("Inscreva-se no canal!");
      }
    }
    
  }

  private void exemplo2(){
    while(true){
      Thread th0 = new Thread(new MeuRunnable2());
      th0.start();
      Thread th1 = new Thread(new MeuRunnable2());
      th1.start();
      Thread th2 = new Thread(new MeuRunnable2());
      th2.start();
 
      numero = 42;
      preparado = true;
     
      while(th0.getState() != State.TERMINATED 
      || th1.getState() != State.TERMINATED 
      || th2.getState() != State.TERMINATED){
        //espera
      }
 
      numero = 0;
      preparado = false;
    }
    
  }
}

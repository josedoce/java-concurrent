package aprendendo.multithread.usandosyncronized;

public class SynchronizedDaFormaErrada {
  //esse recurso esta sendo concorrido por varias thread
  public static int i = -1;

  public static void logica(){
    MeuRunnable meuRunnable = new MeuRunnable();

    Thread t0 = new Thread(meuRunnable);
    Thread t1 = new Thread(meuRunnable);
    Thread t2 = new Thread(meuRunnable);
    Thread t3 = new Thread(meuRunnable);
    Thread t4 = new Thread(meuRunnable);

    //paralelismo 
    t0.start();
    t1.start();
    t2.start();
    t3.start();
    t4.start();
  }

   /**
   * DESVANTAGENS DE USAR O SYNCHRONIZED:
   * sincroniza o bloco de codigo inteiro acabando com o paralelelismo e o 
   * conceito de multithreading(sincronizou a execução, simplesmente)...
   * Faria mais sentido fazer o run usando um loop...
   */
  public static void imprime() {
    //Sincronizando o acesso ao recurso...
    synchronized(SynchronizedDaFormaErrada.class){
      i++;
      System.out.println(Thread.currentThread().getName()+":"+i);
    }
  }
}

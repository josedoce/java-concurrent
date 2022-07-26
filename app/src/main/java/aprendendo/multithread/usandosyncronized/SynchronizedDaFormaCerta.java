package aprendendo.multithread.usandosyncronized;

public class SynchronizedDaFormaCerta {
  //esse recurso esta sendo concorrido por varias thread
  public static int i = 0;
  public static void logica(){
     //synchronized do jeito correto
     MeuRunnable2 meuRunnable2 = new MeuRunnable2();
     Thread t0 = new Thread(meuRunnable2);
     Thread t1 = new Thread(meuRunnable2);
     Thread t2 = new Thread(meuRunnable2);
     Thread t3 = new Thread(meuRunnable2);
     Thread t4 = new Thread(meuRunnable2);
 
    
     t0.start();
     t1.start();
     t2.start();
     t3.start();
     t4.start();
  }
}

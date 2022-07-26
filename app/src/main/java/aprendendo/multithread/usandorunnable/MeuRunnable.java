package aprendendo.multithread.usandorunnable;

public class MeuRunnable implements Runnable {
  public static int execucao = 0;

  @Override
  public void run() { 
    System.out.println("-----------------------------------------------------");   
    String threadname = Thread.currentThread().getName();
    System.out.println("Olá mundo!");
    System.out.println("Nome thread: "+threadname);
    execucao++;
    System.out.println("execução-"+execucao);
    System.out.println("-----------------------------------------------------");
  }
  
}

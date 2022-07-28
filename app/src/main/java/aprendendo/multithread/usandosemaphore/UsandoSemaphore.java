package aprendendo.multithread.usandosemaphore;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class UsandoSemaphore {
  public static AtomicInteger qtd = new AtomicInteger(0);

  //o construtor do Semaphore diz quantas threads podem passar(ser executadas no mesmo instante) o semaforo por vez, quanto maior o numero, mais rapido.
  private static final Semaphore SEMAFORO = new Semaphore(100);
  public static void logica(){
   //usandoAcquireEReleaseDoSemaforo();
   usandoTryAcquire();
  }

  private static void usandoTryAcquire(){
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(501);

    Runnable r1 = ()->{
      String name = Thread.currentThread().getName();
      int usuario = new Random().nextInt(10000);
      boolean conseguiu = false;
      qtd.incrementAndGet();//incremente se não conseguir a vaga
      while(!conseguiu){
        conseguiu = tryAcquire();
      }
      qtd.decrementAndGet();//decremente se conseguir a vaga.
      System.out.println("Usuário "+usuario+"se increveu no canal usando a thread "+name+"\n");
      sleep();//para testar o semaforo.
      //aqui avisa pro semaforo liberar a vaga para a proxima thread pois essa já terminou
      SEMAFORO.release();//se não chamar o release, vai ficar executando a tarefa infinitamente.
    };

    Runnable r2 = ()-> {
      System.out.println("tarefas restantes: "+qtd.get());
      if(qtd.get()==0){
        executor.shutdown();
      }
    };
    
    for(int i = 0; i < 500; i++){
      executor.execute(r1);
    }

    executor.scheduleWithFixedDelay(r2, 0, 100, TimeUnit.MILLISECONDS);
    //executor.shutdown();
  }

  private static void usandoAcquireEReleaseDoSemaforo() {
    ExecutorService executor = Executors.newCachedThreadPool();

    Runnable r1 = ()->{
      String name = Thread.currentThread().getName();
      int usuario = new Random().nextInt(10000);
      acquire(); //aqui avisa pro semaforo que está exigindo uma vaga
      System.out.println("Usuário "+usuario+"se increveu no canal usando a thread "+name+"\n");
      sleep();//para testar o semaforo.
      //aqui avisa pro semaforo liberar a vaga para a proxima thread pois essa já terminou
      SEMAFORO.release();//se não chamar o release, vai ficar executando a tarefa infinitamente.
    };

    for(int i = 0; i < 500; i++){
      executor.execute(r1);
    }

    executor.shutdown();
  }


  public static boolean tryAcquire(){
    try {
      //no tryAcquire() precisamos definir quanto tempo ele fica esperando a tarefa terminar
      //para da a vez para outra tarefa.
      //retorna true se conseguir uma vaga, se não conseguir uma vaga, ele retorna false.
      return SEMAFORO.tryAcquire(1, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
      return false;
    }
  }

  public static void acquire(){
    try {
      SEMAFORO.acquire();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    }
  }

  public static void sleep(){
    //espera de 1 a 6 segundos.
    try {
      int tempoEspera = new Random().nextInt(6);
      tempoEspera++;
      Thread.sleep(tempoEspera*1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

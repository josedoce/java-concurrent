package aprendendo.multithread.cordenandothreads;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ContadorParaCordenarThreads {
  private static volatile int i = 0;
  /**
   * Desvantagens: não é reutilizavel, temos que reeinstanciar com um contador novo 
   * para reiniciar a contagem.
   */
  private static CountDownLatch latch = new CountDownLatch(3);

  //countDownLatch é parecido com CyclicBarrier, só que mais flexivel.
  public static void logica(){
    //usandoCountDownLatch();
    usandoCountDownLatchComMuitosAwaits();
  }

  public static void usandoCountDownLatchComMuitosAwaits(){
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(4);


    Runnable r1 = () -> {
      int j = new Random().nextInt(1000);
      int x = i * j;
      System.out.println(i + " X " + j + " = " + x);
      latch.countDown();
    };

    Runnable r2 = () -> {
      await(latch);
      i = new Random().nextInt(100);
    };

    Runnable r3 = () -> {
      await(latch);
      latch = new CountDownLatch(3);
    };

    Runnable r4 = () -> {
      await(latch);
      System.out.println("Terminou! Vamos começar de novo!");
    };

    executor.scheduleAtFixedRate(r1, 0, 1, TimeUnit.SECONDS);
    executor.scheduleWithFixedDelay(r2, 0, 1, TimeUnit.SECONDS);
    executor.scheduleWithFixedDelay(r3, 0, 1, TimeUnit.SECONDS);
    executor.scheduleWithFixedDelay(r4, 0, 1, TimeUnit.SECONDS);


    while(true){
      //sleep();//em vez disso, usaremos o cauntDownLatch, que espera que uma tarefa rode x vezes, dependendo do valor do construtor.
      await(latch);
    }
  }

  public static void usandoCountDownLatch(){
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);


    Runnable r1 = () -> {
      int j = new Random().nextInt(1000);
      int x = i * j;
      System.out.println(i + " X " + j + " = " + x);
      /**
       * decrementa o contador definido no construtor,
       * quando chegar a zero, o await será liberado.
       */
      latch.countDown();
    };
    executor.scheduleAtFixedRate(r1, 0, 1, TimeUnit.SECONDS);
    while(true){
      //sleep();//em vez disso, usaremos o cauntDownLatch, que espera que uma tarefa rode x vezes, dependendo do valor do construtor.
      await(latch);
      i = new Random().nextInt(100);
      latch = new CountDownLatch(3);
    }
  }

  public static void await(CountDownLatch latch){
    try {
      latch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void sleep(){
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

package aprendendo.multithread.usandocyclicbarrier;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class UsandoCyclicBarrier {
  //usaremos para pegar os dados, pois estamos trabalhando com operações paralelas...
  private static BlockingQueue<Double> resultados = new LinkedBlockingQueue<>();

  //para testar o lado ciclico do cyclicBarrier
  private static ExecutorService bExecutor;
  private static Runnable cr1;
  private static Runnable cr2;
  private static Runnable cr3;
  private static double cResultadFinal = 0;

  //(432*3) + (3^4) + (45*127/12) = ?
  public static void logica(){
    //cyclicBarrierBasico();
    //cyclicBarrierComAcaoFinal();
    mostrandoOladoCiclicoDoCyclicBarrie();
  } 
  private static void mostrandoOladoCiclicoDoCyclicBarrie() {
   
    Runnable sumarizacao = () -> {
      System.out.println("Somando tudo.");
      
      cResultadFinal += resultados.poll();
      cResultadFinal += resultados.poll();
      cResultadFinal += resultados.poll();
      System.out.println("Processamento finalizado: Resultado final: "+cResultadFinal);
      System.out.println("---------------------------------------------------------");
      //ao finalizar a operação, será reiniciada novamente...
      //restart(); //dando vida a um looping dentro da sumarização ou no proprio participante
    };
   
    CyclicBarrier cyclicBarrier = new CyclicBarrier(3, sumarizacao);

    bExecutor = Executors.newFixedThreadPool(3);

    cr1 = ()-> {
      //looping no participante
      while(true){
        resultados.add(432d * 3d);
        await(cyclicBarrier); 
        sleep();
      }
    };

    cr2 = ()-> {
      while(true){
        resultados.add(Math.pow(3d, 14d));
        await(cyclicBarrier);
        sleep();
      }
    };

    cr3 = ()-> {
      while(true){
        resultados.add(45d * 127d/12d);
        await(cyclicBarrier);
        sleep();
      }
    };

    restart();
    //bExecutor.shutdown();
  }


  private static void restart(){
    sleep();
    bExecutor.submit(cr1);
    bExecutor.submit(cr2);
    bExecutor.submit(cr3);
  }
  private static void sleep(){
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  //pegaremos os dados processados e trataremos da nossa forma.
  public static void cyclicBarrierComAcaoFinal(){
     //transformará em resultado pelo proprio cyclicBarrier
     Runnable finalizacao = () -> {
      System.out.println("Somando tudo.");
      double resultadFinal = 0;
      resultadFinal += resultados.poll();
      resultadFinal += resultados.poll();
      resultadFinal += resultados.poll();
      System.out.println("Processamento finalizado: Resultado final: "+resultadFinal);
    };
    //o cyclicBarrier chamará a soma, quando todos os 3 participantes finalizarem a tarefa.
    CyclicBarrier cyclicBarrier = new CyclicBarrier(3, finalizacao);

    ExecutorService executor = Executors.newFixedThreadPool(3);

    Runnable r1 = ()-> {
      System.out.println(Thread.currentThread().getName());
      resultados.add(432d * 3d);
      await(cyclicBarrier);
      //quando o cyclicBarrier finalizar a operação, liberará a execução...
      System.out.println(Thread.currentThread().getName());
    };

    Runnable r2 = ()-> {
      System.out.println(Thread.currentThread().getName());
      resultados.add(Math.pow(3d, 14d));
      await(cyclicBarrier);
      System.out.println(Thread.currentThread().getName());
    };

    Runnable r3 = ()-> {
      System.out.println(Thread.currentThread().getName());
      resultados.add(45d * 127d/12d);
      await(cyclicBarrier);
      System.out.println(Thread.currentThread().getName());
    };

    executor.submit(r1);
    executor.submit(r2);
    executor.submit(r3);

    executor.shutdown();
  }

  public static void cyclicBarrierBasico(){
    //funcionamento basico do cyclicBarrier
    //o seu construtor indica o numero de participantes dessa barreira.
    CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

    ExecutorService executor = Executors.newFixedThreadPool(3);


    Runnable r1 = ()-> {
      System.out.println(432d * 3d);

      /**
       * A execução vai travar até que 3 awaits sejam chamados...
       */
      await(cyclicBarrier);
      //após os 3 participantes terminarem a operação, será liberado
      System.out.println("Terminei o processamento.");
    };

    Runnable r2 = ()-> {
      System.out.println(Math.pow(3d, 14d));
      await(cyclicBarrier);
      System.out.println("Terminei o processamento.");
    };

    Runnable r3 = ()-> {
      System.out.println(45d * 127d/12d);
      await(cyclicBarrier);
      System.out.println("Terminei o processamento.");
    };

    executor.submit(r1);
    executor.submit(r2);
    executor.submit(r3);

    executor.shutdown();
  }

  public static void await(CyclicBarrier cyclicBarrier){
    try {
      cyclicBarrier.await();
    } catch (InterruptedException | BrokenBarrierException e) {
      e.printStackTrace();
    }
  }
}

package aprendendo.multithread.usandoexecutores;

import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

//São tarefas agendadas...
public class AgendamentoExecutorsScheduled {
  public static void logica() throws InterruptedException, ExecutionException{
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
    //usandoScheduleComCallable(executor);
    //usandoScheduleComRunnable(executor);
    //usandoScheduleComExecucaoIntermitente(executor);//remova o shutdown para testar.
    usandoScheduleComExecucaoTardia(executor);
    //executor.shutdown();
  }

  

  private static void usandoScheduleComExecucaoTardia(ScheduledExecutorService executor) {
    /*
     * executa uma tarefa com um delay entre as execuções com periodos definidos.
     * parametro 1 - o callable ou runnable
     * parametro 2 - o delay inicial
     * parametro 3 - o delay antes de executar
     * parametro 4 - a unidade de tempo
     */
    executor.scheduleWithFixedDelay(new Tarefa2(), 0, 1, TimeUnit.SECONDS);
  }



  private static void usandoScheduleComExecucaoIntermitente(ScheduledExecutorService executor) {
    /*
     * executa uma tarefa infinitamente com periodos definidos.
     * parametro 1 - o callable ou runnable
     * parametro 2 - o delay antes de executar
     * parametro 3 - um tempo para dize de quanto em quanto tempo será executada a tarefa...
     * parametro 4 - a unidade de tempo
     */
    executor.scheduleAtFixedRate(new Tarefa2(), 0, 1, TimeUnit.SECONDS);
  }

  private static void usandoScheduleComRunnable(ScheduledExecutorService executor) throws InterruptedException, ExecutionException {
    System.out.println(System.currentTimeMillis());
    //agenda uma tarefa para daqui dois segundos.
    executor.schedule(new Tarefa2(), 3, TimeUnit.SECONDS);
  
    System.out.println(System.currentTimeMillis());
  }

  public static void usandoScheduleComCallable(ScheduledExecutorService executor) throws InterruptedException, ExecutionException {
    System.out.println(System.currentTimeMillis());
    //agenda uma tarefa para daqui dois segundos.

    ScheduledFuture<String> future = executor.schedule(new Tarefa(), 5, TimeUnit.SECONDS);
    System.out.println(future.get());
    System.out.println(System.currentTimeMillis());
  }

  public static class Tarefa implements Callable<String> {

    @Override
    public String call() throws Exception {
      String thname = Thread.currentThread().getName();
      int nextInt = new Random().nextInt(1000);
      return thname + ": Calculo do resultado foi "+nextInt;
    }

  }

  public static class Tarefa2 implements Runnable {
    @Override
    public void run() {
      try {
        /**
         * Se a tarefa demorar mais que o valor do periodo, não mudará nada,
         * ele executará a tarefa e independente de quanto demore sem interferir,
         * mas ele executára rapidamente apos o termino da tarefa que demorou mais
         * que o periodo definido segundo para ser executada...
         * se a tarefa executou em menos tempo que o periodo definido, a tarefa só
         * iniciará quando dé o tempo do periodo definido 
         */
        //Thread.sleep(2000);//use para testar o scheduleAtFixedRate

        /**
         * O valor do delay descrito em scheduleWithFixedDelay() se somará ao tempo que a tarefa demorou para executar.
         * Exemplo: se a tarefa demora 1s e o delay é 2, a proxima tarefa executa em 3s 
        */
        Thread.sleep(1000);//use para testar o scheduleWithFixedDelay();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(LocalTime.now());
      String thname = Thread.currentThread().getName();
      int nextInt = new Random().nextInt(1000);
      System.out.println(thname + ": Calculo do resultado foi "+nextInt);
    }
  }
}

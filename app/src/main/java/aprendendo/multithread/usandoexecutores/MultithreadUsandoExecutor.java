package aprendendo.multithread.usandoexecutores;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//aqui teremos mais opções que usando o Thread
public class MultithreadUsandoExecutor {
  public static void logica() throws InterruptedException, ExecutionException{
    ExecutorService executor = null;
    try {
      /*
        uma das formas para rodar multithreads, usamos o newFixedThreadPool(numero de threads)
        com o numero de threads que o executor pode usar para executar as tarefas.
        executor = Executors.newFixedThreadPool(4);
      */
      /*
       * Essa forma é mais inteligente, ele avisa pro executor usar as threads que não usa mais ou criar se for preciso uma nova thread.
       * Se uma thread não for usada dentro de 1 minuto, a thread será destruida.
       * CUIDADO! Essa funcionalidade pode criar muitas threads, use se tiver tarefas pequenas.
       
      executor = Executors.newCachedThreadPool();
      Future<String> f1 = executor.submit(new Tarefa());
      //System.out.println(f1.get());//para vê o newCachedThreadPool() aproveitando a thread.
      Future<String> f2 = executor.submit(new Tarefa());
      Future<String> f3 = executor.submit(new Tarefa());
      System.out.println(f1.get());
      System.out.println(f2.get());
      System.out.println(f3.get());
      //------------------------------------------------------------
      */

      //pode usar Executors.newFixedThreadPool(4); se quiser, pois não muda nada.
      executor = Executors.newCachedThreadPool();
      List<Tarefa> tarefas = new ArrayList<>();
      for(int i = 0; i < 10; i++){
        tarefas.add(new Tarefa());
      }
      /*
       *
      //executa muitas tarefas num unico metodo e retorna uma lista de Future...
      List<Future<String>> futures = executor.invokeAll(tarefas);
      for(Future<String> f : futures){
        System.out.println(f.get());
      }
       * 
       */

      //o invokeAny() executa uma lista de tarefas e só retorna o call da que executou mais rapido.
      System.out.println(executor.invokeAny(tarefas));
      
    }catch(Exception e){
      throw e;
    }finally{
      if(executor != null)
        //finaliza as tarefas
        //executor.shutdown();
        //finaliza o executor
        executor.shutdownNow();
    }
  }

  public static class Tarefa implements Callable<String> {

    @Override
    public String call() throws Exception {
      String thname = Thread.currentThread().getName();
      int nextInt = new Random().nextInt(1000);
      return thname + ": Calculo do resultado foi "+nextInt;
    }

  }
}

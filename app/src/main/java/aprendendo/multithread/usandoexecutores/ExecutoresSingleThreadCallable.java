package aprendendo.multithread.usandoexecutores;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutoresSingleThreadCallable {
  private static AtomicInteger i = new AtomicInteger(0);
  public static void logica() throws Exception {
    ExecutoresSingleThreadCallable e = new ExecutoresSingleThreadCallable();
    //e.usandoRunnableComExecutores();
    e.usandoCallbleComExecutores();
  }
  
  //com o callable, dá pra retornar algo de sua preferencia após a tarefa terminar...
  //possibilitando que nós possamos pegar o valor usando o submit()...
  public static class Tarefa2 implements Callable<String> {

    @Override
    public String call() throws Exception {
      //para testar o future.get() com timeout de 1s
      Thread.sleep(937);
      return Thread.currentThread().getName() + " Executou um calculo "+new Random().nextInt(1000);
    }

  }

  private void usandoCallbleComExecutores() throws Exception {
    ExecutorService executor = null;
    try{
      executor = Executors.newSingleThreadExecutor();
    
  
      Future<String> future = executor.submit(new Tarefa2());

      System.out.println(future.isDone());
      //String informacao = future.get(); //use esse, se quiser esperar a tarefa terminar sozinha
      //tem o future.get() com timeout, ele espera ser executado antes de um tempo,
      //e se a tarefa demorar mais que o tempo estabelecido, será lançado um erro
      //TimeoutException
      //faz muito sentido usar isso, para a segurança do codigo.
      String informacao = future.get(1, TimeUnit.SECONDS);//use esse se tiver querendo uma tarefa rapida.
      
      System.out.println(informacao);
      
      //não faz sentido, pois o future.get(); já espera que a tarefa tenha sido executada
      //para ser chamado de fato.
      //executor.shutdown();
      //executor.awaitTermination(5, TimeUnit.SECONDS);
      System.out.println(future.isDone());
    }catch(Exception e){
      throw e;
    }finally {
      if(executor!=null){
        //essa forma de parar as tarefas é mais tardia
        //executor.shutdown();

        //essa forma de parar as tarefas é mais instantanea
        executor.shutdownNow();
      }
    } 
  }

  private void usandoRunnableComExecutores() throws InterruptedException {
    ExecutorService executor = null;
    try{
      executor = Executors.newSingleThreadExecutor();
      //aqui podemos executar as mesma tarefa varias vezes.
      executor.execute(new Tarefa());
      executor.execute(new Tarefa());
      executor.execute(new Tarefa());
      executor.execute(new Tarefa());
      
      /**
       * Para saber se uma tarefa terminou, usamos o submit(), que alem de
       * executar a tarefa, retorna informações sobre a tarefa.
       * É como o execute, só que com mais opções...
      */
      Future<?> future = executor.submit(new Tarefa());
      System.out.println(future.isDone());//retorna para saber se já executou.
      executor.shutdown();//para o teste do future
      /**
       * O codigo abaixo do awaitTermination(), só será executado após o termino
       * do tempo definido, ou se o metodo shutdown() ou shutdownNow() forem
       * chamados antes, nesse caso, o awaitTermination() não terá efeitos... 
      */
      executor.awaitTermination(5, TimeUnit.SECONDS);
      System.out.println(future.isDone());
    }catch(Exception e){
      throw e;
    }finally {
      if(executor!=null){
        //essa forma de parar as tarefas é mais tardia
        //executor.shutdown();

        //essa forma de parar as tarefas é mais instantanea
        executor.shutdownNow();
      }
    }
  }

  public static class Tarefa implements Runnable {

    @Override
    public void run() {
      System.out.println("thname: "+Thread.currentThread().getName());
      System.out.println("Tarefa-"+i.getAndIncrement()); 
    }

  }

}

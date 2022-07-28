package aprendendo.multithread.trocadedadosentreduasthreads;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

public class TrocandoDadosEntreThreads {
  //usando SynchronousQueue - fila syncrona
  private static final SynchronousQueue<String> FILA = new SynchronousQueue<>();

  //usando o exchanger - permutador
  //faz com que a threads troquem entre si os dados: exemplo:
  //thread A oferece algo a thread b, a thread b recebe e envia obrigado a Thread A,
  //permutação...
  private static final Exchanger<String> EXCHANGER = new Exchanger<>();

  public static void logica(){
    //usandoSynchronounsQueue();
    usandoExanger();
  }

  //basicamente uma fila cria uma linha de montagem, onde uma cria e só liberá execução se alguem ler o que foi criado.
  private static void usandoSynchronounsQueue() {
    ExecutorService executor = Executors.newCachedThreadPool();


    Runnable r1 = () -> {
      put("Inscreva-se");
      //não será possivel ler, pois o put trava a execução
      //até que outra thread queira ler os dados escritos na 
      //fila.
      System.out.println("Escreveu na fila!");
    };

    Runnable r2 = () -> {
      //o poll tem um limite de espera pelo dado, se não vier, ele retornará null...
      //return FILA.poll(timeout, unit);
      String dado = take();//essa thread conseguirá ler os dados da fila...
      System.out.println("Pegou da fila: "+dado);
    };
    executor.execute(r1);
    executor.execute(r2);
    executor.shutdown();
  }

  private static void usandoExanger() {
    ExecutorService executor = Executors.newCachedThreadPool();

    Runnable r1 = () -> {
      String thname = Thread.currentThread().getName();
      String msg = "Toma isso!";
      System.out.println(thname+"-"+msg);
      String retorno = exchange(msg);
      System.out.println(retorno+"-"+thname);
    };

    Runnable r2 = () -> {
      String thname = Thread.currentThread().getName();
      String msg = "Obrigado!";
      System.out.println(thname+"-"+msg);
      String retorno = exchange(msg);
      System.out.println(retorno+"-"+thname);
    };

    executor.execute(r1);
    executor.execute(r2);
    executor.shutdown();
  } 

  private static String exchange(String msg){
    try {
      return EXCHANGER.exchange(msg);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
      return "Exceção!";
    }
  }

  private static String take() {
    try {
      return FILA.take();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
      return "Exceção!";
    }
  }

  private static void put(String value) {
    try {
      FILA.put(value);
      /*
        Coloca algum dado na fila em um determinado tempo.
        se ninguem chamar o take ou o poll no decorrer do tempo que definimos em offer,
        o valor de offer irá para fila e retorna true se conseguir colocar ou false se
        não conseguir colocar
        FILE.offer(value, timeout, unit); 
      */
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    }
  }
}

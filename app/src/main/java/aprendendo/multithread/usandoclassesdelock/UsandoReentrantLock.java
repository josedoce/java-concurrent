package aprendendo.multithread.usandoclassesdelock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class UsandoReentrantLock {
  //alternativas ao uso do synchronized...
  //reentrant-permite você chamar mais de uma vez o look
  private static Lock lock = new ReentrantLock();
  private static ReadWriteLock lock2 = new ReentrantReadWriteLock();

  //recurso acessivel a todas a threads
  private static int i = -1;

  public static void logica(){
    //usando de forma basica o lock
    //usandoOLockComReentrantLock();

    usandoReadWriteLock();
  }

  private static void usandoReadWriteLock(){
    ExecutorService executor = Executors.newCachedThreadPool();
    Runnable r1 = ()-> {
      /**
       * Parece com o semaphore.
       * diferente do synchonized, ele é mais flexivel.
       * Melhor que usar o synchronized.
       * Enquanto uma thread estiver com o lock de escrita aberto, nenhuma outra thread
       * poderá escrever nada ou modificar algo muito menos ler algo, mas poderá ler se tiver
       * lock de leitura aberto apos o fechamento do writelock.
       */
      Lock writeLock = lock2.writeLock();
      writeLock.lock();//abrindo o bloco
      String name = Thread.currentThread().getName();
      System.out.println("Escrevendo: "+i);
      i++;
      System.out.println("Escrito: "+i);
      writeLock.unlock();//fechando o bloco
    };

    Runnable r2 = ()-> {
      /**
       * Parece com o semaphore.
       * diferente do synchonized, ele é mais flexivel.
       * Melhor que usar o synchronized.
       * Todo mundo pode obter o lock de leitura e ler todos de uma vez ou mais, porem ninguem
       * poderá usar o rock de escrita..
       */
      Lock readLock = lock2.readLock();
      readLock.lock();//abrindo o bloco
      String name = Thread.currentThread().getName();
      System.out.println("Lendo: " +i);
      System.out.println("Lido: "+i);
      readLock.unlock();//fechando o bloco
    };


    for(int i = 0; i < 6; i++){
      executor.execute(r1);
      executor.execute(r2);
    }

    executor.shutdown();
  }

  private static void usandoOLockComReentrantLock() {
    ExecutorService executor = Executors.newCachedThreadPool();
    Runnable r1 = ()-> {
      /**
       * Parece com o semaphore.
       * diferente do synchonized, ele é mais flexivel.
       * Melhor que usar o synchronized.
       * Basicamente a primeira thread que usar o lock, bloqueará as proxima threads
       * até que essa abra o lock para a proxima thread abrir o lock e assim por diante.
       */
      lock.lock();//abrindo o bloco
      String name = Thread.currentThread().getName();
      i++;
      System.out.println(name + " : "+i);
      lock.unlock();//fechando o bloco
    };

    for(int i = 0; i < 6; i++){
      executor.execute(r1);
    }

    executor.shutdown();
  }
}

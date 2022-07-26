package aprendendo.multithread.usandocolecoessincronizadas;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import com.google.common.util.concurrent.AtomicDouble;

public class ClassesAtomicas {
  //isso evita que os valores repitam e que as threads não quebrem os valores, pense como se fosse sincronizado.
  static AtomicInteger i = new AtomicInteger(-1);
  static AtomicDouble n = new AtomicDouble(0);
  static AtomicBoolean isOk = new AtomicBoolean(false);
  static AtomicReference<Animal> referencia = new AtomicReference<>(new Animal("gato"));

  public static void logica(){
    MeuRunnable2 meuRunnable = new MeuRunnable2();

    Thread t0 = new Thread(meuRunnable);
    Thread t1 = new Thread(meuRunnable);
    Thread t2 = new Thread(meuRunnable);

    t0.start();
    t1.start();
    t2.start();
    try {
      
      Thread.sleep(3000);
      System.out.println(referencia.get().getNome());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
   
  }

  public static class Animal {
    private String nome;
    public Animal(String nome){
      this.nome = nome;
    }

    public void setNome(String nome){
      this.nome = nome;
    }

    public String getNome(){
      return this.nome;
    }
  }
}

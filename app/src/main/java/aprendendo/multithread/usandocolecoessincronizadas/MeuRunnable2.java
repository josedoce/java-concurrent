package aprendendo.multithread.usandocolecoessincronizadas;

import aprendendo.multithread.usandocolecoessincronizadas.ClassesAtomicas.Animal;

public class MeuRunnable2 implements Runnable {
  @Override
  public void run() {
    
    System.out.println(Thread.currentThread().getName()
    +":"+ClassesAtomicas.i.incrementAndGet()
    +":"+ClassesAtomicas.n.addAndGet(+1)
    +": s="+ClassesAtomicas.isOk.compareAndExchange(false, true)
    +": a="+ClassesAtomicas.referencia.getAndSet(new Animal("galinha")).getNome()
    );
    
  }

}

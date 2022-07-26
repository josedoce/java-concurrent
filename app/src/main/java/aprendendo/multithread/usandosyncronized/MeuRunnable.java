package aprendendo.multithread.usandosyncronized;


public class MeuRunnable implements Runnable {
  /* 
  Object lock1 = new Object();
  Object lock2 = new Object();
  //com o synchronized - só uma thread pode executar o metodo run por vez experando a thread 
  //terminar para iniciar outra...
  //é quase como dizer que enfileirou, fazendo que as thread executem em fila.
  //resumindo: não há paralelismo com o synchronized, executa uma de cada vez e ponto final...
  //@Override
  //public synchronized void run() {
  //outra forma é usando synchronized no bloco de codigo, assim:
  @Override
  public void run(){
    
    synchronized(lock1){ //o this - significa em que objeto será sincronizado
  
      SynchronizedDaFormaErrada.i++;
      System.out.println(Thread.currentThread().getName()+":"+SynchronizedDaFormaErrada.i);
    }
    //se tiverem dois ou mais blocos sincronizados no objeto, haverá concorrencia entre eles...
    //para evitar isso, crie objetos burros para evitar a concorrencia...
    //assim, haverá uma separação
    synchronized(lock2){ //o this - significa em que objeto será sincronizado
  
      SynchronizedDaFormaErrada.i++;
      System.out.println(Thread.currentThread().getName()+":"+SynchronizedDaFormaErrada.i);
    }
  }
  */
  /*
  //usando synchronized em metodos static em outra classe
  @Override
  public void run() {
    //podemos syncronizar com base na classe onde está o metodo...
    SynchronizedDaFormaErrada.imprime();
    
  }*/
  @Override
  public synchronized void run() {
    SynchronizedDaFormaErrada.i++;
    System.out.println(Thread.currentThread().getName()+":"+SynchronizedDaFormaErrada.i);
    
  }
}

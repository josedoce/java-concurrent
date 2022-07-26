package aprendendo.multithread.usandosyncronized;


public class MeuRunnable2 implements Runnable{
  
  //faz mais sentido usar o synchronized desta forma...
  @Override
  public void run() {
    int j;
    synchronized(this){
      SynchronizedDaFormaCerta.i++;
      j = SynchronizedDaFormaCerta.i * 2;
    }
    

    double jElevadoA10 = Math.pow(j, 100);
    double sqrt = Math.sqrt(jElevadoA10);
    System.out.println(sqrt);
  }

  
}

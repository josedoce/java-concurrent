package aprendendo.multithread.usandorunnable;

public class CriandoThreads {
  public static void logica(){
    //para saber em qual thread estamos rodando
    Thread t = Thread.currentThread();
    //thread principal do java é a main
    System.out.println(t.getName());

    
    MeuRunnable meuRunnable = new MeuRunnable();

    //Nova thread
    Thread t1 = new Thread(meuRunnable);
    t1.setName("minha-thread-1");
    //ainda não criamos uma thread de fato usando o run...
    //t1.run(); //apenas executando na mesma thread
    
    //usando o start, ai sim estamos iniciando uma thread
    //a jvm escolherá o melhor momento de começar a thread.
    //t1.start(); //executando em uma nova thread

    //thread usando lambda
    Thread t2 = new Thread(()->{
      meuRunnable.run();
    });
    t2.setName("minha-thread-2");
    //t2.start();
    //uma thread só pode ser executada uma unica vez
    //t2.start(); //lançará erro...

    //mas posso executar uma nova thread com o mesmo runnable
    Thread t3 = new Thread(meuRunnable);
    t3.setName("minha-thread-3");
    //t3.start();
    

    t1.start();
    t2.start();
    t3.start();
  }
}

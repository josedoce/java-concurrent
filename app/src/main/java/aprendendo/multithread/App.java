package aprendendo.multithread;

import aprendendo.multithread.cenariomultithread.Volatile;
import aprendendo.multithread.cordenandothreads.ContadorParaCordenarThreads;
import aprendendo.multithread.trocadedadosentreduasthreads.TrocandoDadosEntreThreads;
import aprendendo.multithread.usandoclassesdelock.UsandoReentrantLock;
import aprendendo.multithread.usandocolecoessincronizadas.ClassesAtomicas;
import aprendendo.multithread.usandocolecoessincronizadas.SincronizarColecoes;
import aprendendo.multithread.usandocyclicbarrier.UsandoCyclicBarrier;
import aprendendo.multithread.usandoexecutores.AgendamentoExecutorsScheduled;
import aprendendo.multithread.usandoexecutores.ExecutoresSingleThreadCallable;
import aprendendo.multithread.usandoexecutores.MultithreadUsandoExecutor;
import aprendendo.multithread.usandorunnable.CriandoThreads;
import aprendendo.multithread.usandorunnable.MeuRunnable;
import aprendendo.multithread.usandosemaphore.UsandoSemaphore;
import aprendendo.multithread.usandosyncronized.SynchronizedDaFormaCerta;
import aprendendo.multithread.usandosyncronized.SynchronizedDaFormaErrada;

public class App {
  
  public String iniciado() {
    return "Iniciado";
  }

	public static void main(String[] args) throws Exception {
		/*
      System.out.println("Bem vindo josé");
      //criando e inciaindo threads
       CriandoThreads.logica();


      //syncronized - não é tão utilizada, mas é importante compreender
      SynchronizedDaFormaErrada.logica();
      SynchronizedDaFormaCerta.logica();

      //Sincronizar Coleções para evitar uso do synchronized
      SincronizarColecoes.logica();
      
      //Classes com operações atómicas
      ClassesAtomicas.logica();

      //usando volatile para ler e modificar dados na memoria ram
      Volatile.logica();
    
      //forma alternativa e melhor que o Thread.
      ExecutoresSingleThreadCallable.logica();
      //froma de execultar multiplas threads
      MultithreadUsandoExecutor.logica();

      //forma de agendar tarefas
      AgendamentoExecutorsScheduled.logica();

      //forma de fazer uma thread esperar uma outra thread terminar uma tarefa...
      UsandoCyclicBarrier.logica();

      //identico ao CyclicBarrier, só que ele alterna as tarefas de uma forma que você decide quantas vezes uma thread pode executar.
      ContadorParaCordenarThreads.logica();

      //usando a classe Semaphore para limitar execuções de threads.
      UsandoSemaphore.logica();
   
      //bloqueando recursos para threads executarem de corma sincronizada...
      UsandoReentrantLock.logica();
    */
    //Usando syncronousQueue e Exchanger para permitir troca de dados entre threas.
    TrocandoDadosEntreThreads.logica();

    //parei em https://www.youtube.com/watch?v=Y7niN7cMdgY&list=PLuYctAHjg89YNXAXhgUt6ogMyPphlTVQG&index=17
	} 

 
}

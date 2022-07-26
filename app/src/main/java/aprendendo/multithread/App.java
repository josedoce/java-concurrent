package aprendendo.multithread;

import aprendendo.multithread.cenariomultithread.Volatile;
import aprendendo.multithread.usandocolecoessincronizadas.ClassesAtomicas;
import aprendendo.multithread.usandocolecoessincronizadas.SincronizarColecoes;
import aprendendo.multithread.usandoexecutores.ExecutoresSingleThreadCallable;
import aprendendo.multithread.usandorunnable.CriandoThreads;
import aprendendo.multithread.usandorunnable.MeuRunnable;
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
    
    */

    ExecutoresSingleThreadCallable.logica();

    //parei no https://www.youtube.com/watch?v=ljSCwqJmgr4&list=PLuYctAHjg89YNXAXhgUt6ogMyPphlTVQG&index=10
	}

 
}

package aprendendo.multithread;

import aprendendo.multithread.usandocolecoessincronizadas.SincronizarColecoes;
import aprendendo.multithread.usandorunnable.CriandoThreads;
import aprendendo.multithread.usandorunnable.MeuRunnable;
import aprendendo.multithread.usandosyncronized.SynchronizedDaFormaCerta;
import aprendendo.multithread.usandosyncronized.SynchronizedDaFormaErrada;

public class App {
  
  public String iniciado() {
    return "Iniciado";
  }

	public static void main(String[] args){
		/*
      System.out.println("Bem vindo josé");
      //criando e inciaindo threads
       CriandoThreads.logica();


      //syncronized - não é tão utilizada, mas é importante compreender
      SynchronizedDaFormaErrada.logica();
      SynchronizedDaFormaCerta.logica();

      //Sincronizar Coleções para evitar uso do synchronized
      
    */
    SincronizarColecoes.logica();
    //parei no 7
    //https://www.youtube.com/watch?v=hSCJFSm4QQM&list=PLuYctAHjg89YNXAXhgUt6ogMyPphlTVQG&index=7
	}

 
}

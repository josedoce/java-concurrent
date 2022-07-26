package aprendendo.multithread.usandocolecoessincronizadas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingDeque;

public class SincronizarColecoes {
  //usando o Collections.synchronized.XXX(list);
  //static List<String> lista = new ArrayList<>();

  //thread safe
  //essa classe é pesada, pois ela faz uma copia do array quando
  //adiciona ou remove um elemento do array...
  static List<String> lista = new CopyOnWriteArrayList<>();

  //é pesada tambem
  static Map<Integer, String> mapa = new ConcurrentHashMap<>();

  //fila
  static BlockingDeque<String> fila = new LinkedBlockingDeque<>();
  //fila com dois fins
  //LinkedBlockingDeque
  public static void logica() {
    
   //usandocolecoessincronizadasList();

    colecoesparaconcorrencia();
  }

  static void colecoesparaconcorrencia(){
    /**
     * coleções Thread safe - São classes que podem ser acessadas * por threads sem causar problemas ou efeitos colaterais.
    */
   
     MeuRunnable meuRunnable = new MeuRunnable();
     Thread t1 = new Thread(meuRunnable);
     Thread t2 = new Thread(meuRunnable);
     Thread t3 = new Thread(meuRunnable);
 
     t1.start();
     t2.start();
     t3.start();
     try {
       Thread.sleep(500);
        //lista
       //System.out.println(lista);

       //map
       //System.out.println(mapa);

       //fila
       System.out.println(fila);
     } catch (InterruptedException e) {
       e.printStackTrace();
     }
  }

  static void usandocolecoessincronizadasList(){
     //evita o uso do synchronized, pois internamente já será colocada.
     lista = Collections.synchronizedList(lista);
     //lista = Collections.synchronizedCollection(generico);
     //lista = Collections.synchronizedMap(paramaps);
     //lista = Collections.synchronizedSet(parasets);
   
     MeuRunnable meuRunnable = new MeuRunnable();
     Thread t1 = new Thread(meuRunnable);
     Thread t2 = new Thread(meuRunnable);
     Thread t3 = new Thread(meuRunnable);
 
     t1.start();
     t2.start();
     t3.start();
     try {
       Thread.sleep(500);
       System.out.println(lista);
     } catch (InterruptedException e) {
       e.printStackTrace();
     }
  }
}

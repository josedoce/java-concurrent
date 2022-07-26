## Multithread


### Monothread
	É execução linha a linha numa ordem de cima para baixo(java) ou de baixo para cima ou execução sequencial...
	ex: 
	linha 1;
	linha 2;
	linha 3;
	linha 4...
### Multithread
	São duas ou mais execuções em paralelo.
	ex:
	linha 1 da execução 1
	linha 2 da execução 1
	linha 1 da execução 2
	linha 3 da execução 1
	linha 2 da execução 2
	
### Paralelismo x Concorrência

	Paralelismo: execução de vários trechos de código no mesmo instante.
	
	Concorrência: várias execuções de código concorrendo pelo mesmo recurso.
	
### Aplicações web (Atenção):

	Spring, Quarkus, JBosss, Wildfly, etc possuem suas maneiras proprias de lidar com coisas paralelas, execuções e tals...
	Na duvida consulte a documentação para saber se o framwork possui soluções para multithread
 	
### Separando

	Algoritmos sequenciais com múltiplas execuções
	
	Algoritmos que esperam paralelismo ou concorrência


### Quando usar 
	
	Processos batch(em lote) - Quando tem uma grande carga de dados, um filme por exemplo, processar grandes dados...
	
	
	Aplicações que executam no cliente - Processamentos no celular ou desktop, mas saiba se o framwork permite isso e como...
 
### Foco da aula: 
	Processos batch e Aplicações que executam no cliente
	
### Thread e Runnable
	Thread é uma classe e Runnable é uma interface.
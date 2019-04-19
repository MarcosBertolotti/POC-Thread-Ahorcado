# POC_Thread_Ahorcado

## Diferencia entre Runnable y Thread

_**Runnable** es sólo una interfaz que necesita para instanciar un hilo para contenerlo. Mientras que el hilo contiene ya la capacidad de generar un hilo. Si se extiende el hilo, se estara limitado porque Java no admite la Herencia múltiple. En cambio, Runnable se utiliza mediante implementación lo que no interfiere en la herencia. Además, cuando extiende la clase Thread, cada subproceso crea un objeto único y se asocia con él. Cuando implementa Runnable, comparte el mismo objeto con varios subprocesos._

_**Thread** es un hilo de ejecución en un programa. La máquina virtual de Java permite que una aplicación tenga varios subprocesos de ejecución ejecutándose simultáneamente._

_Thread.start() inicia un hilo que llama al método run() , mientras que Runnable.run() solo llama al método run() en el hilo actual._

## Ciclo de vida de un thread

* _**Nuevo** (New): El thread ha sido creado pero no inicializado, es decir, no se ha ejecutado todavía el método start()._

* _**Ejecutable** (Runnable): El thread puede estar ejecutándose, siempre y cuando se le haya asignado un determinado tiempo de CPU._

* _**Bloqueado** (Blocked o Not Runnable): El thread podría estar ejecutándose, pero hay alguna actividad interna suya que lo impide._

* _**Muerto** (Dead): La forma habitual de que un thread muera es finalizando el método run(). También puede llamarse al método stop() de la clase Thread._

## Explique los Metodos[start,sleep,yield,join]

* _**start()**: Para poner en marcha un nuevo Thread se debe llamar al método start(), heredado de  la súper-clase Thread, es el que crea al Thread y en algún punto hace que ese Thread ejecute lo que esta en run(). Este método devuelve el control inmediatamente._

* _**sleep()**: Simplemente le dice al Thread que duerma durante los milisegundos específicos. Se debería utilizar sleep() cuando se pretenda retrasar la ejecución del Thread_

* _**yield()**: Tiene la función de hacer que un hilo que se está ejecutando, de regreso al estado en “listo para ejecutar” para permitir que otros hilos de la misma prioridad puedan ejecutarse. El funcionamiento no está garantizado._

* _**join()**: El metodo join() que llamamos al final hace que el programa principal espere hasta que este Thread este “muerto”(finalize su ejecucion). Si un Thread necesita esperar a que otro termine puede usar el método join()._

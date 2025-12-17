package co.linxsi.controlador.cliente.cotizacion.task;

import co.linxsi.modelo.cliente.cotizacion2.DAO_Referencia2;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Clase que implementa una tarea programada para revertir el desecho de extrusión
 * en la base de datos. La tarea se programa para ejecutarse en un horario específico
 * cada día 12:00 am.
 */
public class ScheduledTask {
  final private  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * Programa la tarea de revertir el desecho de extrusión para ejecutarse
     * en un horario específico cada día.
     */
    public void scheduleTask() {
        final Timer timer = new Timer();
        
        // Obtén la hora actual
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);   // Hora
        calendar.set(Calendar.MINUTE, 0);        // Minuto
        calendar.set(Calendar.SECOND, 0);         // Segundo
        
        // Calcula la diferencia hasta la próxima hora programada
        long delay = calendar.getTimeInMillis() - System.currentTimeMillis();
        
        // Si ya pasó la hora programada de hoy, programa para el siguiente día
        if (delay < 0) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            delay = calendar.getTimeInMillis() - System.currentTimeMillis();
        }

        // Programa la tarea
        timer.schedule(new CustomTimerTask(), new Date(System.currentTimeMillis() + delay), 24 * 60 * 60 * 1000); // 24 horas
    }

    /**
     * Clase interna que representa la tarea específica a ejecutar
     * cuando se active el temporizador.
     */
    private class CustomTimerTask extends TimerTask {

        @Override
        public void run() {
            final boolean resultadoRestauracion = new DAO_Referencia2().revertirDesechoExtrusion();

            // Obtiene la hora actual del sistema
            final String horaActual = sdf.format(new Date());

            // Crea el mensaje de la consola que incluye la hora actual
            final String mensaje = resultadoRestauracion ? " Restauración de desecho exitosa" : " Restauración de desecho falló";
            System.out.println(" [" + horaActual + "] " + this.getClass().getName() + mensaje);

        }
    }
}


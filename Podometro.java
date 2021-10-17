/**
 * La clase modela un  sencillo pod�metro que registra informaci�n
 * acerca de los pasos, distancia, ..... que una persona
 * ha dado en una semana. 
 * 
 * @author    - pon aqu� tu nombre - 
 */
public class Podometro {
    
    private final char HOMBRE = 'H';
    private final char MUJER = 'M';
    private final double ZANCADA_HOMBRE = 0.45;
    private final double ZANCADA_MUJER = 0.41;
    private final int SABADO = 6;
    private final int DOMINGO = 7;
    private String marca;
    private int altura;
    private char sexo;
    private double longitudZancada;
    private int totalPasosLaborables;
    private int totalPasosSabado;
    private int totalPasosDomingo;
    private double totalDistanciaSemana;
    private double totalDistanciaFinSemana;
    private int tiempo;
    private int caminatasNoche;
    
    /**
     * Inicializa el pod�metro con la marca indicada por el par�metro.
     * El resto de atributos se ponen a 0 y el sexo, por defecto, es mujer
     */
    public Podometro(String marca) {
        this.marca = marca;
        char sexo = 'M';
        int altura = 0;
        double longitudZancada = 0;
        int totalPasosLaborables = 0;
        int totalPasosSabado = 0;
        int totalPasosDomingo = 0;
        double totalDistanciaSemana = 0;
        double totalDistanciaFinSemana = 0;
        double tiempo = 0;
        int caminatasNoche = 0;
    }

    /**
     * accesor para la marca
     *  
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Simula la configuraci�n del pod�metro.
     * Recibe como par�metros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna adem�s el valor adecuado al atributo longitudZancada
     * 
     * (leer enunciado)
     *  
     */
    public void configurar(double queAltura, char queSexo) {
        altura = (int)queAltura;
        if(queSexo == 'M' || queSexo == 'H')
        {
            sexo = queSexo;
        }
        
        else 
        {
            System.out.println("Introduzca como sexo 'H' o 'M'");    
        }
        
        if(queSexo == 'M')
        {
            longitudZancada = Math.floor(altura * ZANCADA_MUJER);
        }
        
        else if(queSexo == 'H')
        {
            longitudZancada = Math.ceil(altura * ZANCADA_HOMBRE);
        }
    }

     /**
     *  Recibe cuatro par�metros que supondremos correctos:
     *    pasos - el n� de pasos caminados
     *    dia - n� de d�a de la semana en que se ha hecho la caminata 
     *              (1 - Lunes, 2 - Martes - .... - 6 - S�bado, 7 - Domingo)
     *    horaInicio � hora de inicio de la caminata
     *    horaFin � hora de fin de la caminata
     *    
     *    A partir de estos par�metros el m�todo debe realizar ciertos c�lculos
     *    y  actualizar� el pod�metro adecuadamente  
     *   
     *   (leer enunciado del ejercicio)
     */
    public void registrarCaminata(int pasos, int dia, int horaInicio, int horaFin) {
        int horasInicio = horaInicio / 100;
        int minsInicio = horaInicio % 100;
        int minutosInicio = horasInicio * 60;
        int tiempoInicio = minutosInicio + minsInicio;
        int horasFin = horaFin / 100;
        int minsFin = horaFin % 100;
        int minutosFin = horasFin * 60;
        int tiempoFin = minutosFin + minsFin;
        tiempo += (tiempoFin - tiempoInicio); 
        switch(dia){
            case 1: 
            case 2: 
            case 3: 
            case 4: 
            case 5: totalPasosLaborables += pasos;
                    totalDistanciaSemana += (pasos * longitudZancada / 100);
                    break;
            case 6: totalPasosSabado += pasos;
                    totalDistanciaFinSemana += (pasos * longitudZancada / 100);
                    break;
            case 7: totalPasosDomingo += pasos;
                    totalDistanciaFinSemana += (pasos * longitudZancada / 100);
                    break;
        }
        
        if(horaInicio >= 2100)
        {
            caminatasNoche ++;
        }
        
    }
    
     /**
     * Muestra en pantalla la configuraci�n del pod�metro
     * (altura, sexo y longitud de la zancada)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() {
        System.out.println("Configuraci�n del pod�metro");
        System.out.println("***************************");
        System.out.println("Altura: " + ((double)altura / 100) + " mtos.");
        if(sexo == 'H') 
        {
            System.out.println("Sexo: HOMBRE");
            System.out.println("Longitud zancada: " + ((double)altura / 100 * 0.45) + " Mtos.");
        }
        else
        {
            System.out.println("Sexo: MUJER");
            System.out.println("Longitud zancada: " + ((double)altura / 100 * 0.41) + " Mtos.");
        }
    }

    /**
     * Muestra en pantalla informaci�n acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstad�sticas() {
        double totalDistancia = (totalDistanciaSemana + totalDistanciaFinSemana) / (double)1000;
        int tiempoHoras = (tiempo / 60);
        int tiempoMins = (tiempo % 60);
        String diaMax = ""; 
        if (totalPasosLaborables > totalPasosSabado && totalPasosLaborables > totalPasosDomingo)
        {
             diaMax = "LABORABLES"; 
        }
        else if (totalPasosSabado > totalPasosLaborables && totalPasosSabado > totalPasosDomingo)
        {
             diaMax = "SABADO"; 
        }
        else if(totalPasosDomingo > totalPasosLaborables && totalPasosDomingo > totalPasosSabado)
        {
            diaMax = "DOMINGO";
        }
        
        else if(totalPasosLaborables == totalPasosDomingo && totalPasosLaborables > totalPasosSabado)
        {
            diaMax = "LABORABLES Y DOMINGO";
        }
        
        else if(totalPasosSabado == totalPasosDomingo && totalPasosSabado > totalPasosLaborables)
        {
            diaMax = "SABADO Y DOMINGO";
        }
        
        else if(totalPasosSabado == totalPasosDomingo && totalPasosSabado > totalPasosLaborables)
        {
            diaMax = "LABORABLES Y SABADO";
        }
        
        System.out.println("Estad�sticas");
        System.out.println("*********************************");
        System.out.println("Distancia recorrida toda la semana: " + totalDistancia + "Km");
        System.out.println("\n");
        System.out.println("N� pasos d�as laborables: " + totalPasosLaborables);
        System.out.println("N� pasos S�bado: " + totalPasosSabado);
        System.out.println("N� pasos Domingo: " + totalPasosDomingo);
        System.out.println("\n");
        System.out.println("N� de caminatas a partir de las 21h.: " + caminatasNoche);
        System.out.println("\n");
        System.out.println("Tiempo total caminado en la semana: " + tiempoHoras + "h. y " + tiempoMins + "m.");
        System.out.println("Dia/s con m�s pasos caminados: " + diaMax);
    }

   

    /**
     *  Calcula y devuelve un String que representa el nombre del d�a
     *  en el que se ha caminado m�s pasos - "S�BADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroPasos() {
        String diaMax = ""; 
        if (totalPasosLaborables > totalPasosSabado && totalPasosLaborables > totalPasosDomingo)
        {
             diaMax = "LABORABLES"; 
        }
        else if (totalPasosSabado > totalPasosLaborables && totalPasosSabado > totalPasosDomingo)
        {
             diaMax = "SABADO"; 
        }
        else if(totalPasosDomingo > totalPasosLaborables && totalPasosDomingo > totalPasosSabado)
        {
            diaMax = "DOMINGO";
        }
        
        else if(totalPasosSabado == totalPasosDomingo && totalPasosSabado > totalPasosLaborables)
        {
            diaMax = "LABORABLES Y SABADO";
        }
        
        else if(totalPasosLaborables == totalPasosDomingo && totalPasosLaborables > totalPasosSabado)
        {
            diaMax = "LABORABLES Y DOMINGO";
        }
        
        else if(totalPasosSabado == totalPasosDomingo && totalPasosSabado > totalPasosLaborables)
        {
            diaMax = "SABADO Y DOMINGO";
        }
        return diaMax;
    }
    
    
    /**
     * Restablecer los valores iniciales del pod�metro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no var�a
     *  
     */    
    public void reset() {
        char sexo = 'M';
        int altura = 0;
        double longitudZancada = 0;
        int totalPasosLaborables = 0;
        int totalPasosSabado = 0;
        int totalPasosDomingo = 0;
        double totalDistanciaSemana = 0;
        double totalDistanciaFinSemana = 0;
        double tiempo = 0;
        int caminatasNoche = 0;
    }
}
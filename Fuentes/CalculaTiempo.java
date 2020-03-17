/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.cau;
import com.sfp.ejb.CauHorausuFacadeLocal;
import com.sfp.model.CauFechas;
import com.sfp.model.CauHorausu;
import com.sfp.model.CauSuspservicio;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import javax.ejb.EJB;

/**
 *
 * @author jleal
 */
public class CalculaTiempo implements Serializable {
    final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 
    // En los arreglos siguientes se almacenan los datos de la tabla CAU_Fechas
    // que contiene 5 tipos de fechas y horarios que se pueden establecer para 
    // un día de la semana el tipo de horario se establese en la tabla CAU_TipoDia
        
    // colección de dias de la semana con horario laboral anualizado
    // Tipo 1 las fechas establecen los dias de la semana que se aplican en 
    // todo el año con el horario establecido para horario laboral es el de 
    // nivel 0 de precedencia 
    Collection<CauFechas> DiasSemHoraLabAn_1 = new ArrayList<>();    
    // colección de dias de la semana con horario no laboral anualizado restan
    // Tipo 2 las fechas establecen los dias de la semana que se aplican en 
    // todo el año con horario establecido para horario no laboral tiene nivel 1 
    // de precedencia sobreescribe el nivel 0 
    Collection<CauFechas> DiasSemHoraNoLabAn_2 = new ArrayList<>();
    // colección de dias de la semana no laborables anualizado (dias de descanso) resta    
    // Tipo 3 las fechas establecen los dias de la semana que se aplican en 
    // todo el año con horario establecido para horario no laboral tiene nivel 2
    // de precedencia sobreescribe el niven 1
    Collection<CauFechas> DiasSemNoLabAn_3 = new ArrayList<>();
    // colección de fechas con horario laboral suma
    Collection<CauFechas> DiasHoraLabEsp_4 = new ArrayList<>();
    // colección de fechas con Horario no laboral resta
    Collection<CauFechas> DiasHoraNoLabEsp_5 = new ArrayList<>();
    // colección de fechas no laborales  vacaciones resta
    Collection<CauFechas> DiasNoLabVac_6 = new ArrayList<>();
    // colección de fechas no laborales  suspecion de estandar de servicio
    Collection<CauFechas> DiasNoLabSuspServ_7 = new ArrayList<>();
    
    // Se aplican para el calendario final en secuencia de tipo 1 a tipo 7
    // contiene la secuencia de fechas con horario laborable y no laborable
    Collection<DiasLabCat> DiasSecuencia  = new ArrayList<>();
    
    // Fechas tipadas en CAUTipoDia que se asigaron al usuario y que utilizaran
    // para establecer los horarios laborales efectivos
    private Collection<CauHorausu> HorasUsu;
    
    // conjunto de fechas que no se deben de tomar en cuenta para la establecer 
    // los horarios efectivos de trabajo
    private Collection<CauSuspservicio> SuspServicio;
    
    private Collection<ImagenDiaHora> DiaHora  = new  ArrayList<ImagenDiaHora>();;
    // Secuencia DiaHora
    
    int SecuenciaDH;

    
    // id del usuario asignado al tecnico que atiende el servicio
    private int idusuario;       
    // fecha inicial de asignación del servicio
    private Calendar FechaInicial;   
    // fecha final de conclución del servicio
    private Calendar FechaFinal;
    
    // constructor que se utiliza para inicializar el id de usuario y las fechas
    // inicial y final del servicio
    public CalculaTiempo(int idusuario, Date FechaI, Date FechaF,
                             Collection<CauHorausu> HorasUsu,
                             Collection<CauSuspservicio> SuspServicio) {              
       FechaInicial =  Calendar.getInstance();
       FechaFinal = Calendar.getInstance();
       FechaInicial.setTime(FechaI);
       FechaFinal.setTime(FechaF);
       this.idusuario = idusuario;
       this.HorasUsu = HorasUsu;
       this.SuspServicio = SuspServicio;
       
       
    } 
    
    public Collection<CauHorausu> getHorasUsu() {
        return HorasUsu;
    }

    public void setHorasUsu(Collection<CauHorausu> HorasUsu) {
        this.HorasUsu = HorasUsu;
    }
   
    public void ObtenHorariousuario (int usuario) {            
      // Lee las reglas de horario que se aplican por día y se aplican al perido 
      // que dura el servicio desde la Fechainicial a la FechaFinal
      DiaHora.clear();
      SecuenciaDH = 1;
      AlmaTiemposPeriodo(); 
      // genera una secuencia de días cada día tiene asignada un conjunto de 
      // segmentos de horario que suman o restan tiempo al dia 
      GeneraSecServicio();
      Cad_DiasSecuencia("Secuencia_dias_Servicio");
      // Primera regla se establece el horario laboral por día Anualizado
      Poblar_DiasSemHoraLabAn_1();
      Cad_DiasSecuencia("Secuencia_HoraSem_Lab_Anualizado_1");
      // Segunda regla se establece horario no laboral por dia Anualizado (Comida)
      Poblar_DiasSemHoraNoLabAn_2();
      Cad_DiasSecuencia("Secuencia_HoraSem_NoLab_Anualizado_2");
      // Tercera regla se establecen los días no laborables todo el día se cancela       
      // Sobreescribe la regla 2      
      Poblar_DiasSemNoLabAn_3();
      Cad_DiasSecuencia("Secuencia_DiasSem_NoLab_Anualizado_3");
      // Cuarta regla se establecen las horas laborables adicionales
      // por día que estan fuera del horario laboral y dias no laborables
      // sobreescribe la regla 3
      Poblar_DiasHoraLabEsp_4();
      Cad_DiasSecuencia("Secuencia_Horas_Lab_Especiales_4");
      // Quinta regla se establecen los segmentos del dia que se considera no 
      // laborables reescribe la regla 4
      Poblar_DiasHoraNoLabEsp_5();
      Cad_DiasSecuencia("Secuencia_Horas_NoLab_Especiales_5");
      // Sexta regla se establece los dias de vacaciones reescribe la regla 5
      Poblar_DiasNoLabVac_6();
      Cad_DiasSecuencia("Secuencia_Horas_NoLab_Vacaciones_6");
      // Septima regla se establece los dias de suspencion de servicio del 
      // pra el servicio en cuestion reescribe regla 6
      if ((SuspServicio != null) && (!SuspServicio.isEmpty())) {
        Poblar_SuspServicio_7();
        Cad_DiasSecuencia("Secuencia_Susp_Servicio_7");
      }   
    }

   private void Cad_DiasSecuencia(String tipoHorario) {       
        String LogDia="";
        String LogHora="";
        
        for (DiasLabCat secuencia : DiasSecuencia) {
            ImagenDiaHora paso1 = new ImagenDiaHora();
            paso1.setCadenaDia(" Dia = " + secuencia.getFechaS());
            paso1.setSecuencia(SecuenciaDH);
            
            for(HorasOper paso2: secuencia.getHoras()){
                LogHora = LogHora + "( " + paso2.getHoraIniS() + " - " + paso2.getHoraFinS()+" ) Oper= " + paso2.getOper() ;
                
            }
            paso1.setHoras(LogHora);  
            paso1.setTipoHorario(tipoHorario);
            LogHora = "";
            DiaHora.add(paso1);
            SecuenciaDH++;
        }        
    }
    
    // recorre la tabla CauHorausu que contiene una colección que tiene periodos
    // cada periodo tiene estipulado un tipo de dia (IdTipodia) el cual determina
    // si este periodo contiene segmentos de día que aportan tiempo al servicio o
    // disminullen tiempo al servicio dentro de cada día
    // esta función es de caracter global y despues se aplica cada una de ellas 
    // al periodo de tiempo en que dura la atención del servicio
     private void AlmaTiemposPeriodo() {
        for (CauHorausu paso : HorasUsu) {
            for (CauFechas fecha1 : paso.getIdPeriodo().getCauFechasCollection()) {
                if (null != fecha1.getIdPeriodo().getIdTipodia().getIdTipodia()) {
                    switch (fecha1.getIdPeriodo().getIdTipodia().getIdTipodia()) {
                        case 1:
                            DiasSemHoraLabAn_1.add(fecha1);
                            break;
                        case 2: {
                            DiasSemHoraNoLabAn_2.add(fecha1);
                            break;
                        }
                        case 3: {
                            DiasSemNoLabAn_3.add(fecha1);
                            break;
                        }
                        case 4: {
                            DiasHoraLabEsp_4.add(fecha1);
                            break;
                        }
                        case 5: {
                            DiasHoraNoLabEsp_5.add(fecha1);
                            break;
                        }
                        case 6: {
                            DiasNoLabVac_6.add(fecha1);
                            break;
                        }
                        default:
                            break;
                    }
                }
            }
        }
        if ((SuspServicio != null) && (!SuspServicio.isEmpty())) {
            GeneraSecSuspServ();
        }
    }
    // Genera para el rango de fechas en que dura la atención de un servicio 
    // un arreglo de dias en que cada dia incluye un arreglo de rangos de 
    // horarios que indican si ese rango es laborable o no laborable dentro de 
    // ese día inicialmente se ingresa un rango de las 00 horas (00:00:00) a las
    // 24 horas (23:59:59) del dia como no laborable
    private void GeneraSecServicio(){       
        Calendar FechaRec = Calendar.getInstance();
        Calendar FechaRecI = Calendar.getInstance();
        Calendar FechaRecF = Calendar.getInstance();
        
        FechaRecI.set(FechaInicial.get(Calendar.YEAR),FechaInicial.get(Calendar.MONTH) , 
                      FechaInicial.get(Calendar.DAY_OF_MONTH), 00, 00, 00);
        
        FechaRecF.set(FechaFinal.get(Calendar.YEAR),FechaFinal.get(Calendar.MONTH) , 
                      FechaFinal.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        
        FechaRec.set(FechaInicial.get(Calendar.YEAR),FechaInicial.get(Calendar.MONTH) , 
                      FechaInicial.get(Calendar.DAY_OF_MONTH), 00, 00, 00);
                
        // FechaRec.setTime(FechaRecI.getTime());
        int sec = 1;
        while (FechaRecF.after(FechaRec)){
            DiasLabCat dias = new DiasLabCat(FechaRec,sec);
            // Establece la hora de inicio del día 00:00:00 cero horas cero minutos cero segundos
            Calendar FechaI = Calendar.getInstance();
            FechaI.set(FechaRec.get(Calendar.YEAR),FechaRec.get(Calendar.MONTH) , FechaRec.get(Calendar.DAY_OF_MONTH), 00, 00, 00);
            // Establece la hora de fin del día 23:59:59 23 horas 59 minutos 59 segundos
            Calendar FechaF = Calendar.getInstance();
            FechaF.set(FechaRec.get(Calendar.YEAR),FechaRec.get(Calendar.MONTH) , FechaRec.get(Calendar.DAY_OF_MONTH), 23,59,59);
            // En este punto se etiqueta el rango del día como no laborable
            dias.AgregarHora(FechaI, FechaF, -1);
            DiasSecuencia.add(dias);
            // Recorre la fecha del día al siguiente día 
            FechaRec.add(Calendar.DAY_OF_YEAR, 1); 
            sec++;
        }
        
    }
    
    
        
    private void Poblar_DiasSemHoraLabAn_1(){
        for(DiasLabCat fecha:DiasSecuencia){
            // se determina en que fecha laboral es el dia y se signa 
            int dia = fecha.getDiasemana();
            for(CauFechas fechalab : DiasSemHoraLabAn_1){
                if (dia == toCalendar(fechalab.getFechainic()).get(Calendar.DAY_OF_WEEK)){
                   Calendar FechaInicp = Calendar.getInstance();
                   FechaInicp = toCalendar(fechalab.getFechainic());
                   FechaInicp.set(fecha.getFechax().get(Calendar.YEAR), 
                                  fecha.getFechax().get(Calendar.MONTH), 
                                  fecha.getFechax().get(Calendar.DAY_OF_MONTH));
                   Calendar FechaFinp = Calendar.getInstance();
                   FechaFinp = toCalendar(fechalab.getFechafin());
                   FechaFinp.set(fecha.getFechax().get(Calendar.YEAR), 
                                  fecha.getFechax().get(Calendar.MONTH), 
                                  fecha.getFechax().get(Calendar.DAY_OF_MONTH));
                   // fecha.AgregarHora(toCalendar(fechalab.getFechainic()), toCalendar(fechalab.getFechafin()), 1);                    
                   fecha.AgregarHora(FechaInicp, FechaFinp, 1);                    
                }     
            }
        }
    }
    
    private void Poblar_DiasSemHoraNoLabAn_2() {
        for(DiasLabCat fecha:DiasSecuencia){
            // se determina en que fecha laboral es el dia y se signa 
            int dia = fecha.getDiasemana();
            for(CauFechas fechalab : DiasSemHoraNoLabAn_2){
                if (dia == toCalendar(fechalab.getFechainic()).get(Calendar.DAY_OF_WEEK)){
                   Calendar FechaInicp = Calendar.getInstance();
                   FechaInicp = toCalendar(fechalab.getFechainic());
                   FechaInicp.set(fecha.getFechax().get(Calendar.YEAR), 
                                  fecha.getFechax().get(Calendar.MONTH), 
                                  fecha.getFechax().get(Calendar.DAY_OF_MONTH));
                   Calendar FechaFinp = Calendar.getInstance();
                   FechaFinp = toCalendar(fechalab.getFechafin());
                   FechaFinp.set(fecha.getFechax().get(Calendar.YEAR), 
                                  fecha.getFechax().get(Calendar.MONTH), 
                                  fecha.getFechax().get(Calendar.DAY_OF_MONTH)); 
                   //fecha.AgregarHora(toCalendar(fechalab.getFechainic()), toCalendar(fechalab.getFechafin()), -1);  
                   fecha.AgregarHora(FechaInicp, FechaFinp, -1);   
                }     
            }
        }
    }
    
    private void Poblar_DiasSemNoLabAn_3() {
        for(DiasLabCat fecha:DiasSecuencia){
            // se determina en que fecha laboral es el dia y se signa 
            int dia = fecha.getDiasemana();
            for(CauFechas fechalab : DiasSemNoLabAn_3){
                if (dia == toCalendar(fechalab.getFechainic()).get(Calendar.DAY_OF_WEEK)) {
                    Calendar FechaInicp = Calendar.getInstance();
                    FechaInicp = toCalendar(fechalab.getFechainic());
                    FechaInicp.set(fecha.getFechax().get(Calendar.YEAR),
                            fecha.getFechax().get(Calendar.MONTH),
                            fecha.getFechax().get(Calendar.DAY_OF_MONTH));
                    Calendar FechaFinp = Calendar.getInstance();
                    FechaFinp = toCalendar(fechalab.getFechafin());
                    FechaFinp.set(fecha.getFechax().get(Calendar.YEAR),
                            fecha.getFechax().get(Calendar.MONTH),
                            fecha.getFechax().get(Calendar.DAY_OF_MONTH));
                    // fecha.AgregarHora(toCalendar(fechalab.getFechainic()), toCalendar(fechalab.getFechafin()), -1);                    
                    fecha.AgregarHora(FechaInicp, FechaFinp, -1);
                }
            }
        }
    }

    private void Poblar_DiasHoraLabEsp_4() {
        for(DiasLabCat fecha:DiasSecuencia){
            // se determina en que fecha laboral es el dia y se signa  
            String fecha1 = fecha.getFechaS();
            Calendar fechapaso = Calendar.getInstance();
            fechapaso.setTime(fecha.getFechay());
            int dia = fechapaso.get(Calendar.DAY_OF_MONTH);
            int mes = fechapaso.get(Calendar.MONTH)+1;
            int anio = fechapaso.get(Calendar.YEAR);
            Date fevchayy = fecha.getFechay();
            
            String fecha2 = fecha.getFechaS2();
            
            int dia1 = fecha.getDia();
            int mes1 = fecha.getMes();
            int anio1 =  fecha.getAnio();
            
            for (CauFechas fechalab : DiasHoraLabEsp_4) {
                if ((dia == toCalendar(fechalab.getFechainic()).get(Calendar.DAY_OF_MONTH))
                        && (mes == toCalendar(fechalab.getFechainic()).get(Calendar.MONTH) + 1)
                        && (anio == toCalendar(fechalab.getFechainic()).get(Calendar.YEAR))) {
                    Calendar FechaInicp = Calendar.getInstance();
                    FechaInicp = toCalendar(fechalab.getFechainic());
                    FechaInicp.set(fecha.getFechax().get(Calendar.YEAR),
                            fecha.getFechax().get(Calendar.MONTH),
                            fecha.getFechax().get(Calendar.DAY_OF_MONTH));
                    Calendar FechaFinp = Calendar.getInstance();
                    FechaFinp = toCalendar(fechalab.getFechafin());
                    FechaFinp.set(fecha.getFechax().get(Calendar.YEAR),
                            fecha.getFechax().get(Calendar.MONTH),
                            fecha.getFechax().get(Calendar.DAY_OF_MONTH));
                    // fecha.AgregarHora(toCalendar(fechalab.getFechainic()), toCalendar(fechalab.getFechafin()), 1);                    
                    fecha.AgregarHora(FechaInicp, FechaFinp, 1);
                }
            }
        }
    }

    private void Poblar_DiasHoraNoLabEsp_5() {
        for(DiasLabCat fecha:DiasSecuencia){
            // se determina en que fecha laboral es el dia y se signa  
            Calendar fechapaso = Calendar.getInstance();
            fechapaso.setTime(fecha.getFechay());
            int dia = fechapaso.get(Calendar.DAY_OF_MONTH);
            int mes = fechapaso.get(Calendar.MONTH)+1;
            int anio = fechapaso.get(Calendar.YEAR);
            for (CauFechas fechalab : DiasHoraNoLabEsp_5) {
                if ((dia == toCalendar(fechalab.getFechainic()).get(Calendar.DAY_OF_MONTH))
                        && (mes == toCalendar(fechalab.getFechainic()).get(Calendar.MONTH) + 1)
                        && (anio == toCalendar(fechalab.getFechainic()).get(Calendar.YEAR))) {
                    Calendar FechaInicp = Calendar.getInstance();
                    FechaInicp = toCalendar(fechalab.getFechainic());
                    FechaInicp.set(fecha.getFechax().get(Calendar.YEAR),
                            fecha.getFechax().get(Calendar.MONTH),
                            fecha.getFechax().get(Calendar.DAY_OF_MONTH));
                    Calendar FechaFinp = Calendar.getInstance();
                    FechaFinp = toCalendar(fechalab.getFechafin());
                    FechaFinp.set(fecha.getFechax().get(Calendar.YEAR),
                            fecha.getFechax().get(Calendar.MONTH),
                            fecha.getFechax().get(Calendar.DAY_OF_MONTH));
                    //fecha.AgregarHora(toCalendar(fechalab.getFechainic()), toCalendar(fechalab.getFechafin()), -1);                    
                    fecha.AgregarHora(FechaInicp, FechaFinp, -1);
                }
            }
        }
    }

    private void Poblar_DiasNoLabVac_6() {
        for(DiasLabCat fecha:DiasSecuencia){
            // se determina en que fecha laboral es el dia y se signa             
            Calendar fechapaso = Calendar.getInstance();
            fechapaso.setTime(fecha.getFechay());
            int dia = fechapaso.get(Calendar.DAY_OF_MONTH);
            int mes = fechapaso.get(Calendar.MONTH)+1;
            int anio = fechapaso.get(Calendar.YEAR);
            for (CauFechas fechalab : DiasNoLabVac_6) {
                if ((dia == toCalendar(fechalab.getFechainic()).get(Calendar.DAY_OF_MONTH))
                        && (mes == toCalendar(fechalab.getFechainic()).get(Calendar.MONTH) + 1)
                        && (anio == toCalendar(fechalab.getFechainic()).get(Calendar.YEAR))) {
                    Calendar FechaInicp = Calendar.getInstance();
                    FechaInicp = toCalendar(fechalab.getFechainic());
                    FechaInicp.set(fecha.getFechax().get(Calendar.YEAR),
                            fecha.getFechax().get(Calendar.MONTH),
                            fecha.getFechax().get(Calendar.DAY_OF_MONTH));
                    Calendar FechaFinp = Calendar.getInstance();
                    FechaFinp = toCalendar(fechalab.getFechafin());
                    FechaFinp.set(fecha.getFechax().get(Calendar.YEAR),
                            fecha.getFechax().get(Calendar.MONTH),
                            fecha.getFechax().get(Calendar.DAY_OF_MONTH));
                    //fecha.AgregarHora(toCalendar(fechalab.getFechainic()), toCalendar(fechalab.getFechafin()), -1);                    
                    fecha.AgregarHora(FechaInicp, FechaFinp, -1);
                }
            }
        }
    }
    
    private void Poblar_SuspServicio_7() {
        for (DiasLabCat fecha : DiasSecuencia) {
            // se determina en que fecha laboral es el dia y se signa             
            Calendar fechapaso = Calendar.getInstance();
            fechapaso.setTime(fecha.getFechay());
            int dia = fechapaso.get(Calendar.DAY_OF_MONTH);
            int mes = fechapaso.get(Calendar.MONTH) + 1;
            int anio = fechapaso.get(Calendar.YEAR);

            //Se debe de recorrer desde la FechaHoraSusp hasta la fechaHoraReanud
            // del arreglo de la suspencion de servicio y estas fechas se agregaran 
            // al arreglo de horarios de cada fecha de DíasSecuencia
            for (CauFechas fechalab : DiasNoLabSuspServ_7) {
                if ((dia == toCalendar(fechalab.getFechainic()).get(Calendar.DAY_OF_MONTH))
                        && (mes == toCalendar(fechalab.getFechainic()).get(Calendar.MONTH) + 1)
                        && (anio == toCalendar(fechalab.getFechainic()).get(Calendar.YEAR))) {
                    Calendar FechaInicp = Calendar.getInstance();
                    FechaInicp = toCalendar(fechalab.getFechainic());
                    FechaInicp.set(fecha.getFechax().get(Calendar.YEAR),
                            fecha.getFechax().get(Calendar.MONTH),
                            fecha.getFechax().get(Calendar.DAY_OF_MONTH));
                    Calendar FechaFinp = Calendar.getInstance();
                    FechaFinp = toCalendar(fechalab.getFechafin());
                    FechaFinp.set(fecha.getFechax().get(Calendar.YEAR),
                            fecha.getFechax().get(Calendar.MONTH),
                            fecha.getFechax().get(Calendar.DAY_OF_MONTH));
                    //fecha.AgregarHora(toCalendar(fechalab.getFechainic()), toCalendar(fechalab.getFechafin()), -1);                    
                    fecha.AgregarHora(FechaInicp, FechaFinp, -1);
                }
            }
        }
    }
        
    private void GeneraSecSuspServ(){
        Calendar FechaRec = Calendar.getInstance();
        Calendar FechaFinal = Calendar.getInstance();
        int sec = 1;
        int diastot = 0;        
        for (CauSuspservicio suspserv : SuspServicio) {
            // Para cada rango de fechas se procede a realizar la secuenciación
            // primero se establece la FechaRec como la fecha inicial es decir
            // la fecha que se incrementara hasta llegar a la fecha final
            FechaRec.setTime(suspserv.getFechahorasusp()); // inicial   
            FechaFinal.setTime(suspserv.getFechahorareanud()); //final  
            CauFechas fecha1;
            if (CompF(FechaRec,"=",FechaFinal)){
                fecha1 = new CauFechas();
                fecha1.setFechainic(suspserv.getFechahorasusp());
                fecha1.setFechafin(suspserv.getFechahorareanud());
                DiasNoLabSuspServ_7.add(fecha1);
            } else if (CompF(FechaFinal,">",FechaRec)) {
                // se procede hacer el recorrido
                // Se pregunta si la fecha final es menor que la fecha recorrida 
                // FechaRec si es asi se procede con el ciclo de otra forma la 
                // FechaRec ya es mayor que la fecha final, al final del ciclo se 
                // incrementa FechaRec
                sec = 0;
                Calendar FI = Calendar.getInstance();
                Calendar FF = Calendar.getInstance();
                
                FI.set(FechaRec.get(Calendar.YEAR),FechaRec.get(Calendar.MONTH),
                       FechaRec.get(Calendar.DAY_OF_MONTH), 00, 00, 00);
                
                FF.set(FechaFinal.get(Calendar.YEAR),FechaFinal.get(Calendar.MONTH),
                       FechaFinal.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
                
                diastot = (int) ((FechaFinal.getTime().getTime() - 
                                  FechaRec.getTime().getTime())/MILLSECS_PER_DAY);
                
                diastot = (int) ((FF.getTime().getTime() - 
                                  FI.getTime().getTime())/MILLSECS_PER_DAY);
               
                
                
                //while (FechaFinal.after(FechaRec)) {
                while (CompF(FechaFinal,">=",FechaRec)) {    
                    // Verificamos  si la fecha inicial es mayor que la fecha final
                    // para para establecer el segmento de hora para el dia inicial
                    // como para el dia final
                    if (sec == 0) // se crea instancia de dias susp servicio por día               
                    {
                        Calendar FeFin = Calendar.getInstance();
                        FeFin.setTime(FechaRec.getTime());
                        FeFin.set(Calendar.HOUR_OF_DAY, 23);
                        FeFin.set(Calendar.MINUTE, 59);
                        FeFin.set(Calendar.SECOND, 59);
                        
                        fecha1 = new CauFechas();
                        fecha1.setFechainic(suspserv.getFechahorasusp());
                        fecha1.setFechafin(FeFin.getTime());
                        DiasNoLabSuspServ_7.add(fecha1);
                    } else if (sec == diastot) {
                        Calendar FeIni = Calendar.getInstance();
                        FeIni.setTime(FechaRec.getTime());
                        
                        FeIni.set(Calendar.HOUR_OF_DAY, 00);
                        FeIni.set(Calendar.MINUTE, 00);
                        FeIni.set(Calendar.SECOND, 00);
                       
                        fecha1 = new CauFechas();
                        fecha1.setFechainic(FeIni.getTime());
                        fecha1.setFechafin(FechaFinal.getTime());
                        DiasNoLabSuspServ_7.add(fecha1);
                        
                    } else {
                        Calendar FechaI = Calendar.getInstance();
                        FechaI.set(FechaRec.get(Calendar.YEAR), FechaRec.get(Calendar.MONTH), FechaRec.get(Calendar.DAY_OF_MONTH), 00, 00, 00);
                        // Establece la hora de fin del día 23:59:59 23 horas 59 minutos 59 segundos
                        Calendar FechaF = Calendar.getInstance();
                        FechaF.set(FechaRec.get(Calendar.YEAR), FechaRec.get(Calendar.MONTH), FechaRec.get(Calendar.DAY_OF_MONTH), 23, 59, 59);                        
                        
                        fecha1 = new CauFechas();                        
                        fecha1.setFechainic(FechaI.getTime());
                        fecha1.setFechafin(FechaF.getTime());
                        DiasNoLabSuspServ_7.add(fecha1);
                    }                    
                    sec++;
                    FechaRec.add(Calendar.DAY_OF_YEAR, 1);
                }
            }
        }
    }  
     public static Calendar toCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
    
    public static Date toDate(Calendar cal) {
        Date date = cal.getTime();
        return date;
    }
    
    public static Date ParseFecha(String fecha)
    {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        } 
        catch (ParseException ex) 
        {
            System.out.println(ex);
        }
        return fechaDate;
    }

    public long  CalculaTiempoEfectivo() {
        // recorre los dias del rango del servicio
        long minutostot= 0;
        long minutospar= 0;
        int cont = DiasSecuencia.size();
        int paso=1;
        Calendar FechaI = Calendar.getInstance();
        FechaI.setTime(FechaInicial.getTime());
        
        Calendar FechaF = Calendar.getInstance();
        FechaF.setTime(FechaFinal.getTime());
        
        ImagenDiaHora paso1 = new ImagenDiaHora();
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        paso1.setCadenaDia("Intervalo de dias FechaIni Fecha Final");
        paso1.setSecuencia(SecuenciaDH);
        paso1.setHoras("Fecha Inicial " + formateador.format(FechaInicial.getTime()) +
                       " Fecha Final " + formateador.format(FechaFinal.getTime()));
        paso1.setTipoHorario("Tiempo del servicio");
        
        SecuenciaDH++;
        for(DiasLabCat fechai:DiasSecuencia){
            if (cont > 1) {
                if (paso == 1) {
                    FechaF.setTime(FechaInicial.getTime());
                    FechaF.set(Calendar.HOUR_OF_DAY,23);
                    FechaF.set(Calendar.MINUTE,59);
                    FechaF.set(Calendar.SECOND,59);
                } else if (paso == cont){
                    FechaI.setTime(FechaFinal.getTime());
                    FechaF.setTime(FechaFinal.getTime());
                    FechaI.set(Calendar.HOUR_OF_DAY,00);
                    FechaI.set(Calendar.MINUTE,00);
                    FechaI.set(Calendar.SECOND,00);
                } else {
                    FechaF.setTime(fechai.getFechax().getTime());
                    FechaF.set(Calendar.HOUR_OF_DAY,23);
                    FechaF.set(Calendar.MINUTE,59);
                    FechaF.set(Calendar.SECOND,59);
                    
                    FechaI.setTime(fechai.getFechax().getTime());
                    FechaI.set(Calendar.HOUR_OF_DAY,00);
                    FechaI.set(Calendar.MINUTE,00);
                    FechaI.set(Calendar.SECOND,00);                    
                }
            }
            minutospar =  ObtenMinutosRango(FechaI, FechaF, fechai.getHoras());
            minutostot = minutostot + minutospar;
            
            paso1 = new ImagenDiaHora();
            paso1.setCadenaDia(" Dia = " + Integer.toString(FechaI.get(Calendar.DAY_OF_MONTH)) +  "/" +
                                           Integer.toString(FechaI.get(Calendar.MONTH)+1) +"/" +
                                           Integer.toString(FechaI.get(Calendar.YEAR)));            
            paso1.setSecuencia(SecuenciaDH);
            paso1.setHoras(String.format("%-20s","Min Par = " + Long.toString(minutospar)).replace(' ','_') +  " Min Tot Acum = " + Long.toString(minutostot));
            paso1.setTipoHorario("Tiempo Calculado Parcial Total_Acumulado_8");
            
            
            DiaHora.add(paso1);
            SecuenciaDH++;
            paso++;
        }
       return minutostot;
    }
    // obtiene la suma del tiempo laboral entre FechaInicialp y FechaFinalp para
    // un arreglo de rangos de horas Horasp
    private long  ObtenMinutosRango(Calendar Fip,Calendar Ffp, 
                                                 Collection<HorasOper> Horasp){
        long minutos = 0;
        long diferencia = 0;
        for (HorasOper recorre : Horasp) {
            if (recorre.getOper() == 1) {
                // rango del servicio dentro del rango del dia
                if (ComH(Fip, ">=", recorre.getHoraIni())  &&
                    ComH(Ffp, "<=", recorre.getHoraFin()) ) {
                    diferencia = Ffp.getTime().getTime() - Fip.getTime().getTime();
                    minutos += (diferencia / (60 * 1000));
                } else if ((ComH(Fip, ">=", recorre.getHoraIni())
                        && ComH(Fip, "<=", recorre.getHoraFin()))
                        && ComH(Ffp, ">=", recorre.getHoraFin())) {
                    // fecha inicial en el rango   y fecha final mayor al del rango
                    diferencia = recorre.getHoraFin().getTime().getTime() - Fip.getTime().getTime();
                    minutos += (diferencia / (1000 * 60));
                } else if (ComH(Fip, "<=", recorre.getHoraIni())
                        && ComH(Ffp, ">=", recorre.getHoraFin())) {
                    // fecha inicial menor a fecha inicial del rango y fecha final mayor que 
                    // la fecha final del rango 
                    diferencia = recorre.getHoraFin().getTime().getTime() - recorre.getHoraIni().getTime().getTime();
                    minutos += (diferencia / (1000 * 60));
                } else if ((ComH(Ffp, ">=", recorre.getHoraIni())
                        && ComH(Ffp, "<=", recorre.getHoraFin()))
                        && ComH(Fip, "<=", recorre.getHoraIni())) {
                    // fecha inicial menor a la fecha inicial del rango y fecha final menor que
                    // la fecha final del rango
                    diferencia = Ffp.getTime().getTime() - recorre.getHoraIni().getTime().getTime();
                    minutos += (diferencia / (1000 * 60));
                }
            }
        }
        return minutos;
   }
    
    private boolean CompF(Calendar Fechai, String Comp, Calendar Fechaf){
        int diai, diaf, mesi, mesf, anioi, aniof;
        
        boolean resultado = false;
        diai = Fechai.get(Calendar.DAY_OF_MONTH);
        diaf = Fechaf.get(Calendar.DAY_OF_MONTH);
        mesi = Fechai.get(Calendar.MONTH)+1;
        mesf = Fechaf.get(Calendar.MONTH)+1;
        anioi = Fechai.get(Calendar.YEAR);
        aniof = Fechaf.get(Calendar.YEAR);
        
        
        switch (Comp) {
            case "=":
                if ((anioi == aniof) && (mesi == mesf) && (diai == diaf)) {
                    resultado = true;
                }   break;
            case ">=":
                if ((anioi == aniof) && (mesi == mesf) && (diai == diaf)) {
                    resultado = true;
                } else if ((anioi == aniof) && (mesi ==  mesf) && (diai > diaf)){
                    resultado = true;
                } else if  ((anioi == aniof) && (mesi > mesf)){
                    resultado = true;
                } else if  ((anioi > aniof) ){
                    resultado = true;
                } 
                break;
            case ">":
                if ((anioi == aniof) && (mesi ==  mesf) && (diai > diaf)){
                    resultado = true;
                } else if  ((anioi == aniof) && (mesi > mesf)){
                    resultado = true;
                } else if  (anioi > aniof) {
                    resultado = true;
                }
                break;
            case "<=":
                if ((anioi == aniof) && (mesi == mesf) && (diai == diaf)) {
                    resultado = true;
                } else if ((anioi == aniof) && (mesi ==  mesf) && (diai < diaf)){
                    resultado = true;
                } else if  ((anioi == aniof) && (mesi < mesf)){
                    resultado = true;
                } else if  ((anioi < aniof) ){
                    resultado = true;
                } 
                break;
            case "<":
                if ((anioi == aniof) && (mesi ==  mesf) && (diai < diaf)){
                    resultado = true;
                } else if  ((anioi == aniof) && (mesi < mesf)){
                    resultado = true;
                } else if  ((anioi < aniof) ){
                    resultado = true;
                }
                break;
            default:
                break;
        }        
        return resultado;
    }

    private boolean ComH(Calendar tiempoi, String Comp, Calendar tiempof) {
        int horai;
        int horaf;
        int mini;
        int minf;
        boolean resultado = false;
        horai = tiempoi.get(Calendar.HOUR_OF_DAY);
        horaf = tiempof.get(Calendar.HOUR_OF_DAY);
        mini = tiempoi.get(Calendar.MINUTE);
        minf = tiempof.get(Calendar.MINUTE);

        switch (Comp) {
            case "=":
                if ((horai == horaf) && (mini == minf)) {
                    resultado = true;
                }
                break;
            case ">=":
                if ((horai == horaf) && (mini == minf)) {
                    resultado = true;
                } else if ((horai == horaf) && (mini > minf)) {
                    resultado = true;
                } else {
                    resultado = (horai > horaf);
                }
                break;
            case ">":
                if ((horai == horaf) && (mini > minf)) {
                    resultado = true;
                } else {
                    resultado = (horai > horaf);
                }
                break;
            case "<=":
                if ((horai == horaf) && (mini == minf)) {
                    resultado = true;
                } else if ((horai == horaf) && (mini < minf)) {
                    resultado = true;
                } else {
                    resultado = (horai < horaf);
                }
                break;
            case "<":
                if ((horai == horaf) && (mini < minf)) {
                    resultado = true;
                } else {
                    resultado = (horai < horaf);
                }
                break;
            default:
                break;
        }
        return resultado;
    }

    public Collection<ImagenDiaHora> getDiaHora() {
        return DiaHora;
    }

    public void setDiaHora(Collection<ImagenDiaHora> DiaHora) {
        this.DiaHora = DiaHora;
    }
    
    
}

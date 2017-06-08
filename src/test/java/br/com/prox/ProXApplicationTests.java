package br.com.prox;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ProXApplicationTests {

	@Test
	public void contextLoads() {
	LocalTime hora1 = LocalTime.of(20, 0);
	LocalTime hora2 = LocalTime.of(8,0);
	Duration total = Duration.ZERO;
	LocalDateTime total2 = LocalDateTime.of(0, 1,1,0,0, 0);
	
	total = total.plusHours(hora1.getHour());
	total = total.plusHours(hora2.getHour());
	
	total2 = total2.plusHours(hora1.getHour());
	total2 = total2.plusHours(hora2.getHour());
	
	System.out.println(total);
	System.out.println(total2);
	
	LocalTime setTime = LocalTime.of(23, 25, 45, 20);
	System.out.println(setTime);
	
	Double hora = 29.9d;
	
	Duration duration = Duration.ofHours(hora.longValue());
	System.out.println(duration);
	
	Duration duration2 = Duration.ofHours(20);
	System.out.println(duration.between(hora1, hora2).abs());
	
	Duration oneHours2 = Duration.of(730, ChronoUnit.HOURS);
    System.out.println(oneHours2.toHours()); 
    
   

	 
	}

}

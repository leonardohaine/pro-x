package br.com.prox;

import java.time.LocalTime;

import org.junit.Test;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ProXApplicationTests {

	@Test
	public void contextLoads() {
	LocalTime hora1 = LocalTime.of(2,0);
	LocalTime hora2 = LocalTime.of(3,0);
	LocalTime total = LocalTime.of(0,0);
	
	total = total.plusHours(hora1.getHour());
	total = total.plusHours(hora2.getHour());
	
	System.out.println(total);
	
	
	}

}

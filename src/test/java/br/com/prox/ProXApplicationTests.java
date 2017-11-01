package br.com.prox;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import org.apache.axis2.util.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.prox.model.CertificadoDigital;
import br.com.prox.model.ControleNFeDistribuicao;
import br.com.prox.repository.CertificadoDigitalDAO;
import br.com.prox.repository.ControleNFeDistribuicaoDAO;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProXApplicationTests {

	@Autowired
    private TestEntityManager entityManager;
	
    @Autowired
    private CertificadoDigitalDAO repository;
    
    @Autowired
    private ControleNFeDistribuicaoDAO controleDao;
    
    @Test
    public void testFindByName() throws Exception {
    	
    	ControleNFeDistribuicao controle = new ControleNFeDistribuicao();
    	ControleNFeDistribuicao controle2 = new ControleNFeDistribuicao();
    	
    	controle.setId(1L);
    	controle.setEmpresaId(85L);
    	controle.setNsu(123L);
    	
    	controle2.setId(2L);
    	controle2.setEmpresaId(85L);
    	controle2.setNsu(456L);
    	
    	//controleDao.save(Arrays.asList(controle, controle2));
    	
    	System.out.println("MAX NSU: " + controleDao.getMaxNsuByEmpresaIdAndTipoAmbiente(85L, "2"));
    	
    	/*Path path = Paths.get("D:\\Projetos MasterSAF DF-e\\epson\\certificados\\Certificado_DFE_EPL_2017_epson1718.pfx");
    	byte[] data = Files.readAllBytes(path);
    	
    	CertificadoDigital c = new CertificadoDigital();
    	c.setId(2L);
    	c.setAlias("teste alias");
    	c.setRaiz("01554976");
    	c.setPassword(Base64.encode("epson1718".getBytes("UTF-8")));
    	c.setKeyStoreBlob(data);
    	entityManager.merge(c);
    	
        CertificadoDigital client = repository.findByRaiz("01554976");
        //assertEquals("01554976", client.getRaiz());
        System.out.println("Certificado: " + client);
        System.out.println("KeyStoreBlob: " + client.getKeyStoreBlob());*/
    }
	
	//@Test
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
	
	Double horasProjeto = 80d;
	Double hora = 64.0;
	
	double porcentagem = hora / horasProjeto * 100;
	
	System.out.println("Porcentagem: " + porcentagem);
	
	Duration duration = Duration.ofHours(hora.longValue());
	System.out.println(duration);
	
	Duration duration2 = Duration.ofHours(20);
	System.out.println(duration.between(hora1, hora2).abs());
	
	Duration oneHours2 = Duration.of(730, ChronoUnit.HOURS);
    System.out.println(oneHours2.toHours()); 
    
   

	 
	}

}

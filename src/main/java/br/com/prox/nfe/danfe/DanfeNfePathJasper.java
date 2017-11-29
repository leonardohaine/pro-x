package br.com.prox.nfe.danfe;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import br.com.prox.nfe.util.WebServiceUtil;

public class DanfeNfePathJasper
{
  private static final String DANFE_OBS_PAISAGEM_JASPER = "DANFE_OBS_PAISAGEM.jasper";
  private static final String DANFE_OBS_MR_JASPER = "DANFE_OBS_MR.jasper";
  private static final String DANFE_DUPLICATAS_PAISAGEM_JASPER = "DANFE_DUPLICATAS_PAISAGEM.jasper";
  private static final String DANFE_DUPLICATAS_JASPER = "DANFE_DUPLICATAS.jasper";
  private static final String DANFE_ITEM_PAISAGEM2_JASPER = "DANFE_ITEM_PAISAGEM2.jasper";
  private static final String DANFE_ITEM_PAISAGEM_JASPER = "DANFE_ITEM_PAISAGEM.jasper";
  private static final String DANFE_ITEM_MR_JASPER = "DANFE_ITEM_MR.jasper";
  private static final String DANFE_MR_PAISAGEM2_JASPER = "DANFE_MR_PAISAGEM2.jasper";
  private static final String DANFE_MR2_JASPER = "DANFE_MR2.jasper";
  private static final String DANFE_MR_PAISAGEM_JASPER = "DANFE_MR_PAISAGEM.jasper";
  private static final String DANFE_MR_JASPER = "DANFE_MR.jasper";
  private static final String DANFE_CARTA_CORRECAO_JASPER = "DANFE_CARTA_CORRECAO.jasper";
  public static final int RETRATO = 1;
  public static final int PAISAGEM = 2;
  public static final int RETRATO2 = 3;
  public static final int PAISAGEM2 = 4;
  private static final Map<String, Map<Integer, String>> RESOURCES_TIPO_ORIENTACAO = new HashMap();
  private String basePathDanfe;
  
  static
  {
    Map<Integer, String> capa = new HashMap();
    capa.put(Integer.valueOf(1), "DANFE_MR.jasper");
    capa.put(Integer.valueOf(2), "DANFE_MR_PAISAGEM.jasper");
    capa.put(Integer.valueOf(3), "DANFE_MR2.jasper");
    capa.put(Integer.valueOf(4), "DANFE_MR_PAISAGEM2.jasper");
    RESOURCES_TIPO_ORIENTACAO.put("capa", capa);
    
    Map<Integer, String> item = new HashMap();
    item.put(Integer.valueOf(1), "DANFE_ITEM_MR.jasper");
    item.put(Integer.valueOf(2), "DANFE_ITEM_PAISAGEM.jasper");
    item.put(Integer.valueOf(3), "DANFE_ITEM_MR.jasper");
    item.put(Integer.valueOf(4), "DANFE_ITEM_PAISAGEM2.jasper");
    RESOURCES_TIPO_ORIENTACAO.put("item", item);
    
    Map<Integer, String> duplicatas = new HashMap();
    duplicatas.put(Integer.valueOf(1), "DANFE_DUPLICATAS.jasper");
    duplicatas.put(Integer.valueOf(2), "DANFE_DUPLICATAS_PAISAGEM.jasper");
    duplicatas.put(Integer.valueOf(3), "DANFE_DUPLICATAS.jasper");
    duplicatas.put(Integer.valueOf(4), "DANFE_DUPLICATAS_PAISAGEM.jasper");
    RESOURCES_TIPO_ORIENTACAO.put("duplicatas", duplicatas);
    
    Map<Integer, String> obs = new HashMap();
    obs.put(Integer.valueOf(1), "DANFE_OBS_MR.jasper");
    obs.put(Integer.valueOf(2), "DANFE_OBS_PAISAGEM.jasper");
    obs.put(Integer.valueOf(3), "DANFE_OBS_MR.jasper");
    obs.put(Integer.valueOf(4), "DANFE_OBS_PAISAGEM.jasper");
    RESOURCES_TIPO_ORIENTACAO.put("obs", obs);
  }
  
  public DanfeNfePathJasper()
  {
    this.basePathDanfe = "src/main/resources/modelo/danfe/";
  }
  
  public DanfeNfePathJasper(String basePathDanfe)
  {
    this.basePathDanfe = basePathDanfe;
  }
  
  public InputStream getCapaDanfe(Integer orientacao)
  {
    return abreStrean("capa", orientacao);
  }
  
  public InputStream getItemDanfe(Integer orientacao)
  {
    return abreStrean("item", orientacao);
  }
  
  public InputStream getDuplicatasDanfe(Integer orientacao)
  {
    return abreStrean("duplicatas", orientacao);
  }
  
  public InputStream getOBSDanfe(Integer orientacao)
  {
    return abreStrean("obs", orientacao);
  }
  
  public InputStream getCartaCorrecao()
  {
    return getClass().getResourceAsStream(this.basePathDanfe + "DANFE_CARTA_CORRECAO.jasper");
  }
  
  public InputStream abreStrean(String tipo, Integer orientacao)
  {
    String resourceName = (String)((Map)RESOURCES_TIPO_ORIENTACAO.get(tipo)).get(orientacao);
    DanfeNfePathJasper basePathDanfePadrao = new DanfeNfePathJasper();
    if (this.basePathDanfe.equals("/modelo/danfe/")) {
      return getClass().getResourceAsStream(this.basePathDanfe + resourceName);
    }
    InputStream input = null;
    String caminho = this.basePathDanfe.toString().trim() + resourceName;
    try
    {
      return new FileInputStream(caminho);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("N�o foi possivel encontrar o arquivo " + caminho + " no diretorio " + this.basePathDanfe + ". Sera utilizado o arquivo padr�o. ");
    }
    return getClass().getResourceAsStream(basePathDanfePadrao.basePathDanfe + resourceName);
  }
  
  public String getBasePathDanfe()
  {
    return this.basePathDanfe;
  }
  
  public void setBasePathDanfe(String basePathDanfe)
  {
    this.basePathDanfe = basePathDanfe;
  }
}


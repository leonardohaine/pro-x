package br.com.prox.nfe.danfe;


public class ParametrosEmpresaDanfe
{
  private String emailEmpresa;
  private String emailEmitente;
  private byte[] logoEmpresa;
  private String pathDanfeDir;
  private Integer nrCasasDecimais;
  private String diretorioModeloDanfe;
  
  public ParametrosEmpresaDanfe(String emailEmpresa, String emailEmitente, Integer nrCasasDecimais)
  {
    this.emailEmpresa = emailEmpresa;
    this.emailEmitente = emailEmitente;
    this.nrCasasDecimais = nrCasasDecimais;
  }
  
  public String getEmailEmpresa()
  {
    return this.emailEmpresa;
  }
  
  public void setEmailEmpresa(String emailEmpresa)
  {
    this.emailEmpresa = emailEmpresa;
  }
  
  public String getEmailEmitente()
  {
    return this.emailEmitente;
  }
  
  public void setEmailEmitente(String emailEmitente)
  {
    this.emailEmitente = emailEmitente;
  }
  
  public byte[] getLogoEmpresa()
  {
    return this.logoEmpresa;
  }
  
  public void setLogoEmpresa(byte[] logoEmpresa)
  {
    this.logoEmpresa = logoEmpresa;
  }
  
  public String getPathDanfeDir()
  {
    return this.pathDanfeDir;
  }
  
  public void setPathDanfeDir(String pathDanfeDir)
  {
    this.pathDanfeDir = pathDanfeDir;
  }
  
  public Integer getNrCasasDecimais()
  {
    return this.nrCasasDecimais;
  }
  
  public String getDiretorioModeloDanfe()
  {
    return this.diretorioModeloDanfe;
  }
  
  public void setDiretorioModeloDanfe(String diretorioModeloDanfe)
  {
    this.diretorioModeloDanfe = diretorioModeloDanfe;
  }
}


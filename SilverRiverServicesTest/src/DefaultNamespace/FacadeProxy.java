package DefaultNamespace;

public class FacadeProxy implements DefaultNamespace.Facade {
  private String _endpoint = null;
  private DefaultNamespace.Facade facade = null;
  
  public FacadeProxy() {
    _initFacadeProxy();
  }
  
  public FacadeProxy(String endpoint) {
    _endpoint = endpoint;
    _initFacadeProxy();
  }
  
  private void _initFacadeProxy() {
    try {
      facade = (new DefaultNamespace.FacadeServiceLocator()).getFacade();
      if (facade != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)facade)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)facade)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (facade != null)
      ((javax.xml.rpc.Stub)facade)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public DefaultNamespace.Facade getFacade() {
    if (facade == null)
      _initFacadeProxy();
    return facade;
  }
  
  public void main(java.lang.String[] args) throws java.rmi.RemoteException{
    if (facade == null)
      _initFacadeProxy();
    facade.main(args);
  }
  
  
}
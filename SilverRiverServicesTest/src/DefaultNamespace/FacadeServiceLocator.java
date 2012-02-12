/**
 * FacadeServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package DefaultNamespace;

public class FacadeServiceLocator extends org.apache.axis.client.Service implements DefaultNamespace.FacadeService {

    public FacadeServiceLocator() {
    }


    public FacadeServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public FacadeServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for Facade
    private java.lang.String Facade_address = "http://localhost:8080/WebServiceProject/services/Facade";

    public java.lang.String getFacadeAddress() {
        return Facade_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String FacadeWSDDServiceName = "Facade";

    public java.lang.String getFacadeWSDDServiceName() {
        return FacadeWSDDServiceName;
    }

    public void setFacadeWSDDServiceName(java.lang.String name) {
        FacadeWSDDServiceName = name;
    }

    public DefaultNamespace.Facade getFacade() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Facade_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getFacade(endpoint);
    }

    public DefaultNamespace.Facade getFacade(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            DefaultNamespace.FacadeSoapBindingStub _stub = new DefaultNamespace.FacadeSoapBindingStub(portAddress, this);
            _stub.setPortName(getFacadeWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setFacadeEndpointAddress(java.lang.String address) {
        Facade_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (DefaultNamespace.Facade.class.isAssignableFrom(serviceEndpointInterface)) {
                DefaultNamespace.FacadeSoapBindingStub _stub = new DefaultNamespace.FacadeSoapBindingStub(new java.net.URL(Facade_address), this);
                _stub.setPortName(getFacadeWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("Facade".equals(inputPortName)) {
            return getFacade();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://DefaultNamespace", "FacadeService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://DefaultNamespace", "Facade"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("Facade".equals(portName)) {
            setFacadeEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}

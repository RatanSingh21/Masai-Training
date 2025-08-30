package in.ratansgh.ExampleOfSetterInjection;

public class PresentationLayer {

    private ServiceBean serviceBean;

    public void setServiceBean(ServiceBean serviceBean){
        this.serviceBean = serviceBean;
    }

    public void present(){
        serviceBean.calculateInterest();
        System.out.println("Presenting interest value....");
    }
}


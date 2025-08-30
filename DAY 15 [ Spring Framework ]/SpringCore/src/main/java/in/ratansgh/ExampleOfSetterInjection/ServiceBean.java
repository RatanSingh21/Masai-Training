package in.ratansgh.ExampleOfSetterInjection;

public class ServiceBean {

    // dependency
    public DAOBean dao;
    // setter injection
    public void setDao(DAOBean dao) {
        this.dao = dao;
    }

    public void calculateInterest(){
        dao.findAccount();
        System.out.println(
                "Interest calculated successfully...."
        );
    }

}

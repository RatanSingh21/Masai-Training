package in.ratansgh;

public class ServiceLayer {


    DAOLayer dao;

    public void setDao(DAOLayer dao) {
        this.dao = dao;
    }

    public void service(){
        dao.fetch();
        System.out.println("Service Layer fetched");
    }


}

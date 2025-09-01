package in.ratansgh;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.swing.event.ListDataEvent;
import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan(basePackages = "in.ratansgh")
public class Config {

    @Bean
    public List<String> getCities (){

        List<String> cities = new ArrayList<String>();
        cities.add("Mumbai");
        cities.add("Delhi");
        cities.add("Bang");
        cities.add("vizag");

        return cities;
    };

}

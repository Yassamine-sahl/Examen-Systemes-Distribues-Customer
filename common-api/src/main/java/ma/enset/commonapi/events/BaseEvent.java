package ma.enset.commonapi.events;

import lombok.Getter;

public class BaseEvent <T>{

    @Getter private  T id ;

    public BaseEvent(T id ){
        this.id =id ;
    }
    public T getId() {
        return id;
    }

}

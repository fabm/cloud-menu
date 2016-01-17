package pt.ipg.ei.cloud.menu.shared.util.enums;

import java.util.Arrays;
import java.util.Iterator;

class IdentifiableEnumIterator<T extends Enum<?> & Identifiable> implements Iterator<T>{
    private int idSet;
    private Iterator<T> all;
    private T current;

    public IdentifiableEnumIterator(int idSet, T[] allEnums) {
        this.idSet = idSet;
        this.all=Arrays.asList(allEnums).iterator();
    }


    @Override
    public boolean hasNext() {
        while (all.hasNext()){
            current = all.next();
            if((current.getId()&idSet) != 0){
                return true;
            }
        }
        return false;
    }

    @Override
    public T next() {
        return current;
    }

    @Override
    public void remove() {

    }
}

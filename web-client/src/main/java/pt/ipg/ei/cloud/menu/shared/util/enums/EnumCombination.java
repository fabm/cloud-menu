package pt.ipg.ei.cloud.menu.shared.util.enums;

import pt.ipg.ei.cloud.menu.shared.model.profile.Profile;

import java.util.Iterator;

public abstract class EnumCombination <T extends Enum<?> & Identifiable> implements Iterable<T>{
    protected abstract T[] getAllEnumerations();
    protected abstract int getIdSet();

    @Override
    public Iterator<T> iterator() {
        return new IdentifiableEnumIterator<>(getIdSet(),getAllEnumerations());
    }

    public boolean hasEnum(T thisEnum){
        return (getIdSet()&thisEnum.getId())!=0;
    }
    public boolean hasOnlyThisEnum(T thisEnum){
        return getIdSet() == thisEnum.getId();
    }
}

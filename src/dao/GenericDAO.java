package dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO <T, K extends Serializable>{
    T create(T newObj);
    T read(K pKey);
    boolean update(T updateObj);
    boolean delete(K key);
    List<T> getAll();
}

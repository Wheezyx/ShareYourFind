package dao;

import model.Find;

import java.util.List;

public interface FindDAO extends GenericDAO<Find, Long> {

    List<Find> getAll();
    List<Find> getSpecifiedAmount(int startIndex, int numberOfArticlePerpage);
    Find readByName(String name);
}

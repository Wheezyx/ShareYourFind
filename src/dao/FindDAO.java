package dao;

import model.Find;

import java.util.List;

public interface FindDAO extends GenericDAO<Find, Long> {
    List<Find> getAll();
}

package services.interfaces;

import models.Charge;

import java.util.List;

public interface ChargeService {

    Charge getById(int id);

    void delete(int id);

    void saveOrUpdate(Charge charge);

    List<Charge> getAll();

    List<Charge> getByOrder(String order);
}

package portfollio.myPortfollio.Controllers;

import java.util.List;

public interface REST<T> {
    List<T> getAllItems();

    T getById(int id);

    T addItem(T item); // post

    T updateItem(T item); // put

    String deleteItem(int id); // Del
}

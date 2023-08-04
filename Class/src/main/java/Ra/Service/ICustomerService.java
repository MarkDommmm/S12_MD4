package Ra.Service;

import Ra.Model.Customers;

import java.util.List;

public interface ICustomerService<T , E> {
   List<T> findall();
   void  save(T t);
   T findById (E e);
   void  deleteById(E e);

}

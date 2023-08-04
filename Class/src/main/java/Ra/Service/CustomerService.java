package Ra.Service;

import Ra.Model.Customers;

import java.util.ArrayList;
import java.util.List;

public class CustomerService implements ICustomerService<Customers, Integer> {
    private List<Customers> customersList;

    public CustomerService() {
        customersList = new ArrayList<>();
    }

    @Override
    public List<Customers> findall() {
        return customersList;
    }

    @Override
    public void save(Customers customers) {
        if (findById(customers.getId()) == null) {
            customersList.add(customers);
        } else {
            customersList.set(customersList.indexOf(findById(customers.getId())), customers);
        }
    }

    @Override
    public Customers findById(Integer integer) {
        for (Customers c : customersList) {
            if (c.getId() == integer) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void deleteById(Integer integer) {
        customersList.remove(findById(integer));
    }

    public int newId() {
        int maxid = 0;
        for (Customers c : customersList) {
            if (c.getId() > maxid) {
                maxid = c.getId();
            }
        }
        return maxid + 1;
    }
}

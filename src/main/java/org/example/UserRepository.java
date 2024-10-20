package org.example;

import java.util.List;

public interface UserRepository {

    User create (User user);

    void update (User user);

    User getUserById (Long id);

    List<User> getListUser ();

    void delete (Long id);

}

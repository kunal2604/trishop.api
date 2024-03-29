package trishop.api.service;

import trishop.api.dao.RoleDao;
import trishop.api.dao.UserDao;
import trishop.api.entity.Role;
import trishop.api.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);

        User adminUser = new User();
        adminUser.setUserName("admin123");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);

        Set<Role> userRoles = new HashSet<>();

        User user = new User();
        user.setUserName("trisha.samanta");
        user.setUserPassword(getEncodedPassword("123123somi"));
        user.setUserFirstName("Trisha");
        user.setUserLastName("Samanta");
        userRoles.add(userRole);
        user.setRole(userRoles);
        userDao.save(user);

        User user2 = new User();
        user2.setUserName("kunal.kumar");
        user2.setUserPassword(getEncodedPassword("123123somi"));
        user2.setUserFirstName("Kunal");
        user2.setUserLastName("Kumar");
        user2.setRole(userRoles);
        userDao.save(user2);
    }

    public User registerNewUser(User user) {
        Role role = roleDao.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        return userDao.save(user);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}

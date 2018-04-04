package service;

import model.Right;
import model.Role;
import model.User;

import javax.print.DocFlavor;
import java.util.List;

public interface RoleRightsService {
    public boolean hasRight(User user,String right);
    public Role getRoleByTitle(String role);
}

package service;

import model.Right;
import model.User;

import javax.print.DocFlavor;

public interface RoleRightsService {
    public boolean hasRight(User user,String right);
}

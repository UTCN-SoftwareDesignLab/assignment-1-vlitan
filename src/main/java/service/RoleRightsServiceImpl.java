package service;

import model.Right;
import model.Role;
import model.User;
import repository.security.RightsRolesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RoleRightsServiceImpl implements RoleRightsService{

    private RightsRolesRepository rightsRolesRepository;

    public RoleRightsServiceImpl(RightsRolesRepository rightsRolesRepository){
        this.rightsRolesRepository = rightsRolesRepository;
    }

    @Override
    public boolean hasRight(User user, String right) {
        List<String> allRights = new ArrayList<>();
        for(Role role : user.getRoles()){
            allRights.addAll(rightsRolesRepository.findRightsByRole(role).stream().map(Right::getRight).collect(Collectors.toList()));
        }
        return allRights.contains(right);
    }

    @Override
    public Role getRoleByTitle(String role) {
        return rightsRolesRepository.findRoleByTitle(role);
    }
}

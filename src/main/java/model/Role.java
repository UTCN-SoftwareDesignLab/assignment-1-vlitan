package model;

import java.util.List;

/**
 * Created by Alex on 11/03/2017.
 * Copied by Virgil on 30/03/2018
 */
public class Role {

    private Long id;
    private String role;
    private List<Right> rights;

    public Role(Long id, String role, List<Right> rights) {
        this.id = id;
        this.role = role;
        this.rights = rights;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Right> getRights() {
        return rights;
    }

    public void setRights(List<Right> rights) {
        this.rights = rights;
    }

    @Override
    public String toString(){
        return role;
    }

    @Override
    public boolean equals(Object object)
    {
        boolean same = false;

        if (object != null && object instanceof Role)
        {
            same = this.id == ((Role) object).getId();
        }

        return same;
    }
}

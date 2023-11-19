package capstone.tutor.member.domain;

public enum Role {
    ROLE_USER("ROLE_USER"),
    ROLE_TEACHER("ROLE_TEACHER"),
    ADMIN("ROLE_ADMIN");

    String role;

    Role(String role){
        this.role = role;
    }
    public String value(){
        return role;
    }
}
